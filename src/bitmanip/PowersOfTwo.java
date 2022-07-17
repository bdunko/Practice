package bitmanip;

import testing.Test;

//determine the purpose of the code (n & (n-1)) == 0
public class PowersOfTwo {

	public static boolean isPowerOfTwo(int n) {
		//this code determines if the number of a power of two
		//when does n & n-1 equal 0?
		//only when n has exactly 1 one.
		//consider: n = 000010000
		//n-1 =         000001111
		//n & n-1 =     000000000 == 0
		//versus
		//n =       01000100
		//n-1 =     01000011
		//n & n-1 = 01000000 != 0
		//What does it mean for a number if only 1 bit is a 1? 
		//that means it is a power of 2.
		return (n & (n-1)) == 0;
	}
	
	public static void main(String[] args) {
		Test.header("PowersOfTwo");
		
		Test.assertion(isPowerOfTwo(1));
		Test.assertion(isPowerOfTwo(2));
		Test.assertion(isPowerOfTwo(4));
		Test.assertion(isPowerOfTwo(8));
		Test.assertion(isPowerOfTwo(16));
		Test.assertion(isPowerOfTwo(32));
		Test.assertion(isPowerOfTwo(0));
		Test.assertion(!isPowerOfTwo(3));
		Test.assertion(!isPowerOfTwo(5));
		Test.assertion(!isPowerOfTwo(6));
		Test.assertion(!isPowerOfTwo(7));
		Test.assertion(!isPowerOfTwo(9));
		Test.assertion(!isPowerOfTwo(10));
		Test.assertion(!isPowerOfTwo(11));
		Test.assertion(!isPowerOfTwo(12));
		Test.assertion(!isPowerOfTwo(13));
		
		Test.results();
	}
	
}
