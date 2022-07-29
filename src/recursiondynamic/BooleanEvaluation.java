package recursiondynamic;

import testing.Test;

public class BooleanEvaluation {

	//1^0|0|1
	
	
	public static int booleanExpression(String expression, boolean result) {
		return boolHelper(expression, result);
	}
	
	private static int boolHelper(String expression, boolean result) {
		
	}
	
	public static void main(String[] args) {
		Test.header("Boolean Expression");
		
		Test.equals(booleanExpression("1^0|0|1", false), 2);
		Test.equals(booleanExpression("0&0&0&1^1|0", true), 10);
		
		Test.results();
	}
}
