package uncategorized;

import testing.Test;

//implement a max function without comparisons
public class MaxNoIf {

	
	
	private static int max(int a, int b) {
		//cast to long to prevent overflow
		long x = (long)a - b;
		
		//if negative, b is larger
		//if positive, a is larger
		//shift to the right 63 places then & with 1 to get sign
		//1 if b is larger, 0 if a is larger
		int sign = (int)((x >> 63) & 1);
		
		//if b is larger, sign = 1, return b * sign
		//if a is larger, sign = 0, return a * (1 - sign)
		return (b * sign) + (a * (1-sign));
	}
	
	public static void main(String[] args) {
		Test.header("MaxNoIf");
		
		Test.equals(10, max(10, 1));
		Test.equals(1000, max(500, 1000));
		Test.equals(453, max(-1, 453));
		Test.equals(1, max(1, 0));
		Test.equals(1, max(0, 1));
		Test.equals(Integer.MAX_VALUE, max(Integer.MAX_VALUE, -Integer.MAX_VALUE));
		Test.equals(Integer.MAX_VALUE, max(-Integer.MAX_VALUE, Integer.MAX_VALUE));
		
		Test.results();
	}
}
