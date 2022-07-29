package recursiondynamic;

import java.util.HashMap;

import testing.Test;

//Count the ways to parenthesize a boolean expression such that it returns a given result
public class BooleanEvaluation {

	public static int waysToEvalulate(String expression, boolean result) {
		return countEval(expression, result, new HashMap<String, Integer>());
	}
	
	
	public static int countEval(String expression, boolean result, HashMap<String, Integer> memo) {
		if(memo.containsKey(expression + result))
			return memo.get(expression + result);
		if(expression.length() == 0)
			return 0;
		if(expression.length() == 1)
			return result == strToBool(expression) ? 1 : 0;
		
		//iterate over each spot to place parens
		int ways = 0;
		for(int i = 1; i < expression.length(); i += 2) {
			char operator = expression.charAt(i);
			String left = expression.substring(0, i);
			String right = expression.substring(i+1, expression.length());
			
			int leftTrue = countEval(left, true, memo);
			int leftFalse = countEval(left, false, memo);
			int rightTrue = countEval(right, true, memo);
			int rightFalse = countEval(right, false, memo);
			
			int totalWays = (leftTrue + leftFalse) * (rightTrue + rightFalse);
			
			int totalTrue = 0;
			if(operator == '^') { //XOR
				totalTrue = leftTrue * rightFalse + leftFalse * rightTrue; 
			} else if (operator == '&') { //AND
				totalTrue = leftTrue * rightTrue;
			} else if (operator == '|') { //OR
				totalTrue = leftTrue * rightTrue + leftFalse * rightTrue + leftTrue * rightFalse;
			}
			
			int totalFalse = totalWays - totalTrue;
			
			ways += result ? totalTrue : totalFalse;
		}
		
		memo.put(expression + result, ways);
		
		return ways;
	}
	
	private static boolean strToBool(String expr) {
		return expr.equals("1");
	}
	
	public static void main(String[] args) {
		Test.header("Boolean Expression");
		
		Test.equals(waysToEvalulate("1^0|0|1", false), 2);
		Test.equals(waysToEvalulate("0&0&0&1^1|0", true), 10);
		
		Test.results();
	}
}
