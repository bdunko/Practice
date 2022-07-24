package recursiondynamic;

import testing.Test;

//Fibonacci Sequence in both brute force and dynamic programming approaches
public class Fibonacci {

	public static long fibDynamic(int n) {
		if(n <= 0)
			return 0;
		
		long[] results = new long[n+1];
		for(int i = 0; i < results.length; i++)
			results[i] = -1;
		results[0] = 0;
		results[1] = 1;
		
		return fibDynamicHelper(n, results);
	}
	
	private static long fibDynamicHelper(int n, long[] results) {
		if(results[n] == -1)
			results[n] = fibDynamicHelper(n-1, results) + fibDynamicHelper(n-2, results);
		
		return results[n];
	}
	
	public static long fib(int n) {
		if(n <= 0)
			return 0;
		if(n == 1)
			return 1;
		return fib(n-1) + fib(n-2);
	}
	
	
	public static void main(String[] args) {
		Test.header("Fibonacci");
		
		Test.timerStart();
		Test.equals(fib(0), 0L);
		Test.equals(fib(1), 1L);
		Test.equals(fib(2), 1L);
		Test.equals(fib(3), 2L);
		Test.equals(fib(4), 3L);
		Test.equals(fib(5), 5L);
		Test.equals(fib(6), 8L);
		Test.equals(fib(7), 13L);
		Test.equals(fib(8), 21L);
		Test.equals(fib(9), 34L);
		Test.equals(fib(10), 55L);
		//Test.equals(fib(50), 12586269025L); //timeout
		Test.timerReportAndReset();
		
		Test.header("(dynamic version)");
		Test.timerStart();
		Test.equals(fibDynamic(0), 0L);
		Test.equals(fibDynamic(1), 1L);
		Test.equals(fibDynamic(2), 1L);
		Test.equals(fibDynamic(3), 2L);
		Test.equals(fibDynamic(4), 3L);
		Test.equals(fibDynamic(5), 5L);
		Test.equals(fibDynamic(6), 8L);
		Test.equals(fibDynamic(7), 13L);
		Test.equals(fibDynamic(8), 21L);
		Test.equals(fibDynamic(9), 34L);
		Test.equals(fibDynamic(10), 55L);
		Test.equals(fibDynamic(50), 12586269025L);
		Test.timerReportAndReset();
		
		Test.results();
	}
}
