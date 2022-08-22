package uncategorized;

import java.util.ArrayList;
import java.util.List;

import testing.Test;

//Given an equation as a string, consisting only of positive integers and +, -, *, and /,
//calculate the result
public class Calculator {
	
	private static class Term {
		public enum Type {
			NUMBER, ADD, SUBTRACT, MULTIPLY, DIVIDE
		}
		
		public Type type;
		public float value;
		private String original;
		
		public Term(String term) {
			original = term;
			value = Integer.MIN_VALUE;
			if(term.equals("+"))
				type = Type.ADD;
			else if (term.equals("-"))
				type = Type.SUBTRACT;
			else if (term.equals("*"))
				type = Type.MULTIPLY;
			else if (term.equals("/"))
				type = Type.DIVIDE;
			else {
				type = Type.NUMBER;
				value = Float.parseFloat(term);
			}
		}
		
		private void applyOperator(Term left, Term right) {
			if(type == Type.NUMBER)
				return;
			
			switch(type) {
				case ADD:
					value = left.value + right.value;
					break;
				case SUBTRACT:
					value = left.value - right.value;
					break;
				case MULTIPLY:
					value = left.value * right.value;
					break;
				case DIVIDE:
					value = left.value / right.value;
					break;
				default:
					break;
			}
			
			type = Type.NUMBER;
			original = Float.toString(value);
		}
		
		@Override
		public String toString() {
			return original;
		}
	}
	
	private static boolean isOperator(String s) {
		return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
	}
	
	private static Term nextTerm(StringBuilder equation) {
		if(equation.length() == 0)
			return null;
		
		String token = equation.substring(0, 1);
		if(isOperator(token)) {
			equation.replace(0, 1, ""); //trim off token
			return new Term(token);
		}
		
		StringBuilder num = new StringBuilder();
		
		//otherwise, consume tokens until hitting an operator, building up num
		while(equation.length() != 0) {
			token = equation.substring(0, 1);
			if(isOperator(token))
				break;
			num.append(token);
			equation.replace(0, 1, ""); //trim off token
		}
		
		return new Term(num.toString());
	}
	
	private static void printTerms(List<Term> terms) {
		StringBuilder sb = new StringBuilder();
		for(Term t : terms)
			sb.append(t);
		System.out.println(sb.toString());
	}
	
	private static void pass(List<Term> terms, Term.Type operator1, Term.Type operator2) {
		boolean done = false;
		int index = 0;
		while(!done) {
			if(index+1 >= terms.size())
				done = true;
			else {
				Term left = terms.get(index);
				Term operator = terms.get(index+1);
				Term right = terms.get(index+2);
				
				if(operator.type == operator1 || operator.type == operator2) {
					terms.remove(left);
					terms.remove(right);
					operator.applyOperator(left, right);
				} else {
					index += 2;
				}
			}
		} 
	}

	public static float compute(String eq) {
		//read equation
		List<Term> terms = new ArrayList<Term>();
		
		boolean parsed = false;
		StringBuilder equation = new StringBuilder().append(eq);
		while(!parsed) {
			Term next = nextTerm(equation);
			if(next == null)
				parsed = true;
			else
				terms.add(next);
		}
		
		//multiply & divide
		pass(terms, Term.Type.MULTIPLY, Term.Type.DIVIDE);
		
		//add & subtract
		pass(terms, Term.Type.ADD, Term.Type.SUBTRACT);
		
		if(terms.size() != 1)
			return -1;
		
		return terms.get(0).value;
	}
	
	private static void verify(float a, float b) {
		float diff = Math.abs(a-b);
		Test.assertion(diff < 0.1f);
	}
	
	public static void main(String[] args) {
		Test.header("Calculator");
		
		verify(compute("2*3+5/6*3+15"), 23.5f);
		verify(compute("1+2+3+4+5-1-2-3-4-5"), 0.0f);
		verify(compute("1*2*3*4*5/5/4/3/2/1"), 1.0f);
		verify(compute("1+2*3+4"), 11.0f);
		verify(compute("1+2/3+4"), 5.666666f);
		

		Test.results();
	}
}
