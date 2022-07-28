package recursiondynamic;

import testing.Test;

//Write a recursive function to multiply two positive integers without using the * operator
public class RecursiveMultiply {

	public static long recursiveMultiply(int a, int b) {		
		int larger = a > b ? a : b;
		int smaller = a > b ? b : a;
		
		return multHelper(larger, smaller);
	}
	
	//10 7 - call on 10 3 - return 30 + 30 + 10 = 70
	// 10 3 - call on 10 1 - return 10+10+10=30
	//  10 1 - return 10
	private static int multHelper(int larger, int smaller) {
		if(smaller == 0)
			return 0;
		if(smaller == 1)
			return larger;
		
		int s = smaller >> 1; //divide by 2
		int halfProd = multHelper(larger, s);

		if(smaller % 2 == 0)
			return halfProd + halfProd;
		else
			return halfProd + halfProd + larger;
	}
	
	
	
	public static void main(String[] args) {
		Test.header("Recursive Multiply");
		
		Test.equals(recursiveMultiply(0, 3), 0L);
		Test.equals(recursiveMultiply(1, 1), 1L);
		Test.equals(recursiveMultiply(3, 4), 12L);
		Test.equals(recursiveMultiply(123, 3), 369L);
		Test.equals(recursiveMultiply(25, 50), 25*50L);
		Test.equals(recursiveMultiply(28347, 999), 28347*999L);
		Test.equals(recursiveMultiply(10, 1), 10L);
		Test.equals(recursiveMultiply(45, 45), 45*45L);
		Test.equals(recursiveMultiply(123456789, 10), 1234567890L);
		Test.equals(recursiveMultiply(0, 0), 0L);
		
		Test.results();
	}
}
