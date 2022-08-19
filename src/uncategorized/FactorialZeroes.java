package uncategorized;

import testing.Test;

//Compute the number of trailing zeroes in n!
public class FactorialZeroes {

	//a leading zero is created only when 2 multiplies by 5
	//there is guranteed to be more multiples of two than five
	//count the number of multiples of five and how many fives go into them, that's the answer
	private static int factorialZeroes(int n) {
		int zeroes = 0;
		for(int i = 0; i <= n; i++)
			zeroes += factorsOf5(i);
		return zeroes;
	}
	
	private static int factorsOf5(int n) {
		int fives = 0;
		while(n % 5 == 0 && n != 0) {
			fives++;
			n /= 5;
		}
		return fives;
	}
	
	public static void main(String[] args) {
		Test.header("FactorialZeroes");
		
		Test.equals(factorialZeroes(5), 1);
		Test.equals(factorialZeroes(1), 0);		
		Test.equals(factorialZeroes(20), 4);
		Test.equals(factorialZeroes(100), 24);
		
		Test.results();
	}
	
}
