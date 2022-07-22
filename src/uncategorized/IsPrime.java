package uncategorized;

import testing.Test;

public class IsPrime {

	public static boolean isPrimeBrute(int n) {
		if(n < 2)
			return false;
		
		int sqrt = (int)Math.sqrt(n);
		for(int i = 2; i <= sqrt; i++) {
			if(n % i == 0)
				return false;
		}
		return true;
	}
	
	public static boolean isPrimeSieveOfEratosthenes(int n) {
		if(n < 2)
			return false;
		
		//create sieve - set 0 and 1 to false, rest to true
		boolean[] sieve = new boolean[n+1];
		sieve[0] = false;
		sieve[1] = false;
		for(int i = 2; i < sieve.length; i++) 
			sieve[i] = true;
		
		//look for the next prime, then set all multiples of it to false
		for(int i = 2; i < sieve.length; i++) {
			if(sieve[i] == true) {
				for(int j = i * i; j < sieve.length; j += i) {
					sieve[j] = false;
				}
			}
		}
		
		return sieve[n];
	}
	
	
	private static void testCase(int n, boolean expected) {
		Test.assertion(isPrimeBrute(n) == expected);
		Test.assertion(isPrimeSieveOfEratosthenes(n) == expected);
	}
	
	public static void main(String[] args) {
		Test.header("IsPrime");
		
		testCase(0, false);
		testCase(1, false);
		testCase(2, true);
		testCase(3, true);
		testCase(4, false);
		testCase(5, true);
		testCase(6, false);
		testCase(7, true);
		testCase(293, true);
		testCase(997, true);
		testCase(998, false);
		testCase(-1, false);
		testCase(-2, false);
		
		Test.results();
	}
}
