package bitmanip;

import testing.Test;

//Swap two numbers in place without using a temporary variable as storage
public class SwapInPlace {
	
	public static void main(String[] args) {
		Test.header("SwapInPlace");
		
		int A = 12345;
		int B = 88888;
		
		int a = A;
		int b = B;
		
		//swap
		a = a + b; //a = a + b
		b = a - b; //a + b - b = a; so now b = a and a = a + b
		a = a - b; //a + b - a = b, so now a = b
		
		//verify swapped
		Test.equals(a, B);
		Test.equals(b, A);
		
		//swap back
		//example: A = 1100 and B = 1010
		a = a ^ b; //A = 0110
		b = a ^ b; //B = 1100
		a = a ^ b; //A = 1010
		
		//verify swapped
		Test.equals(a, A);
		Test.equals(b, B);
		
		Test.results();
	}
}
