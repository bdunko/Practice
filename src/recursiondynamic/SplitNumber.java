package recursiondynamic;

import testing.Test;

public class SplitNumber {
	
	private static long splitNumber(int n) {
		if(n < 0)
			return 0;
		
		long[] memo = new long[n+1];
		memo[0] = 1;
		
		return splitHelper(n, memo);
	}
	
	private static long splitHelper(int remaining, long[] memo) {
		if(memo[remaining] != 0) //dynamic and base case
			return memo[remaining];
		
		long count = 0;
		
		for(int i = remaining; i > 0; i--) {
			count += splitHelper(remaining-i, memo);
		}
		
		memo[remaining] = count;
		
		return memo[remaining];
	}
	
	public static void main(String[] args) {
		Test.header("SplitNumber");
		
		Test.equals(splitNumber(-1), 0L);
		Test.equals(splitNumber(1), 1L);
		Test.equals(splitNumber(2), 2L);
		Test.equals(splitNumber(3), 4L);
		Test.equals(splitNumber(4), 8L);
		Test.equals(splitNumber(5), 16L);
		Test.equals(splitNumber(10), 512L);
		Test.equals(splitNumber(20), 524288L);
		Test.equals(splitNumber(50), 562949953421312L);
		Test.equals(splitNumber(60), 576460752303423488L);
		Test.equals(splitNumber(61), 1152921504606846976L);
		
		Test.results();
	}
}
