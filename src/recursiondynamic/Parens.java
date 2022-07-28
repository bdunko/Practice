package recursiondynamic;

import java.util.LinkedList;
import java.util.List;

import testing.Test;

//Implement an algorithm to return all valid (opened and closed) combinations of N pairs of parenthesis
public class Parens {

	public static List<String> parens(int numParenthesis) {
		List<String> solutions = new LinkedList<String>();
		parensHelper(numParenthesis, 0, "", solutions);
		return solutions;
	}
	
	public static void parensHelper(int leftAvailable, int rightAvailable, String prefix, List<String> solutions) {
		if(leftAvailable == 0 && rightAvailable == 0) {
			solutions.add(prefix);
			return;
		}
		
		if(leftAvailable != 0) {
			parensHelper(leftAvailable-1, rightAvailable+1, prefix+"(", solutions);
		}
		
		if(rightAvailable != 0) {
			parensHelper(leftAvailable, rightAvailable-1, prefix+")", solutions);
		}
	}
	
	private static void verify(List<String> combos, int expected) {
		System.out.println(combos);
		Test.assertion(combos.size() == expected);
		
		for(int i = 0; i < combos.size(); i++) {
			for(int j = i+1; j < combos.size(); j++) {
				String p1 = combos.get(i);
				String p2 = combos.get(j);
				if(p1.equals(p2)) {
					Test.assertion(false);
					return;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Test.header("Parens");
		
		verify(parens(1), 1); //()
		verify(parens(2), 2); //() (())
		verify(parens(3), 5); //()()() ()(()) (())() ((())) (()())
		
		Test.results();
	}
}
