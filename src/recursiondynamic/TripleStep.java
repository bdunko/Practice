package recursiondynamic;

import testing.Test;

//If you have a staircase with N steps, and can go up 1, 2, or 3 steps at a time, how many ways are there to climb the staircase?
public class TripleStep {
	
	public static long tripleStepDynamic(int stepsLeft) {
		long[] answers = new long[stepsLeft+1];
		answers[0] = 1;
		
		return tripleStepDynamicHelper(stepsLeft, answers);
	}

	private static long tripleStepDynamicHelper(int stepsLeft, long[] results) {
		if(stepsLeft < 0)
			return 0;
		
		if(results[stepsLeft] == 0)
			results[stepsLeft] = tripleStepDynamicHelper(stepsLeft-3, results) + tripleStepDynamicHelper(stepsLeft-2, results) + tripleStepDynamicHelper(stepsLeft-1, results);
		
		return results[stepsLeft];
	}
	
	public static long tripleStep(int stepsLeft) {
		if(stepsLeft == 0)
			return 1;
		if(stepsLeft < 0)
			return 0;
		
		return tripleStep(stepsLeft-3) + tripleStep(stepsLeft-2) + tripleStep(stepsLeft-1);
	}
	
	public static void main(String[] args) {
		Test.header("Triple Step");
		
		Test.timerStart();
		Test.equals(tripleStep(1), 1L);
		Test.equals(tripleStep(2), 2L);
		Test.equals(tripleStep(3), 4L); //111 12 21 3
		Test.equals(tripleStep(4), 7L); //1111 112 121 211 22 13 31
		Test.equals(tripleStep(5), 13L); 
		Test.equals(tripleStep(15), 5768L); 
		Test.equals(tripleStep(25), 2555757L); 
		Test.equals(tripleStep(30), 53798080L); 
		Test.timerReportAndReset();
		
		Test.header("(dynamic version)");
		Test.timerStart();
		Test.equals(tripleStepDynamic(1), 1L);
		Test.equals(tripleStepDynamic(2), 2L);
		Test.equals(tripleStepDynamic(3), 4L); //111 12 21 3
		Test.equals(tripleStepDynamic(4), 7L); //1111 112 121 211 22 13 31
		Test.equals(tripleStepDynamic(5), 13L); 
		Test.equals(tripleStepDynamic(15), 5768L); 
		Test.equals(tripleStepDynamic(25), 2555757L); 
		Test.equals(tripleStepDynamic(32), 181997601L); 
		Test.equals(tripleStepDynamic(35), 1132436852L); 
		Test.equals(tripleStepDynamic(40), 23837527729L); 
		Test.equals(tripleStepDynamic(50), 10562230626642L); 
		Test.timerReportAndReset();

		Test.results();
	}
}
