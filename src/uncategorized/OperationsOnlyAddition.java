package uncategorized;

import testing.Test;

//implement subtraction, multiplication, and division on integers using only addition
public class OperationsOnlyAddition {
	
	//flip sign of x
	private static int negate(int x) {
		int neg = 0;
		int toAdd = x > 0 ? -1 : 1;
		//if x is positive, repeatedly add -1 to x
		//if x is negative, repeatedly add 1 to x 
		while(x != 0) {
			neg += toAdd;
			x += toAdd;
		}
		return neg;
	}
	
	private static int subtract(int a, int b) {
		return a + negate(b);
	}
	
	private static int multiply(int a, int b) {
		int mult = 0;
		boolean bNegative = b < 0;
		
		if(bNegative) 
			b = negate(b);
		
		for(int i = 0; i < b; i++) {
			mult += a;
		}
		
		return bNegative ? negate(mult) : mult;
	}
	
	private static int divide(int a, int b) {
		if(b < 0 || b == 0)
			return Integer.MIN_VALUE; //error case
		
		boolean aNegative = a < 0;
		if(aNegative)
			a = negate(a);
		
		int answer = 0;
		int sum = 0;
		while((sum+b) <= a) {
			sum += b;
			answer++;
		}
		
		return aNegative ? negate(answer) : answer;
	}
	
	public static void main(String[] args) {
		Test.header("OperationsOnlyAddition");
		
		Test.header("subtract");
		Test.equals(subtract(10, 3), 7);
		Test.equals(subtract(1004, 34), 970);
		Test.equals(subtract(10, 10), 0);
		Test.equals(subtract(53, 43), 10);
		Test.equals(subtract(35, 5), 30);
		Test.equals(subtract(0, 10), -10);
		Test.equals(subtract(354, 360), -6);
		Test.equals(subtract(-33, 2), -35);
		Test.equals(subtract(50, -10), 60);
		Test.equals(subtract(-345, 0), -345);
		Test.equals(subtract(-247, -10), -237);
		
		Test.header("multiply");
		Test.equals(multiply(10, 3), 30);
		Test.equals(multiply(1004, 34), 34136);
		Test.equals(multiply(10, 10), 100);
		Test.equals(multiply(53, 43), 2279);
		Test.equals(multiply(35, 5), 175);
		Test.equals(multiply(0, 10), 0);
		Test.equals(multiply(354, 360), 127440);
		Test.equals(multiply(-33, 2), -66);
		Test.equals(multiply(50, -10), -500);
		Test.equals(multiply(-345, 0), 0);
		Test.equals(multiply(-247, -10), 2470);
		
		Test.header("divide");
		Test.equals(divide(10, 3), 3);
		Test.equals(divide(1004, 34), 29);
		Test.equals(divide(10, 10), 1);
		Test.equals(divide(53, 43), 1);
		Test.equals(divide(35, 5), 7);
		Test.equals(divide(0, 10), 0);
		Test.equals(divide(354, 360), 0);
		Test.equals(divide(-33, 2), -16);
		Test.equals(divide(50, -10), Integer.MIN_VALUE);
		Test.equals(divide(-345, 0), Integer.MIN_VALUE);
		Test.equals(divide(-247, -10), Integer.MIN_VALUE);
		Test.equals(divide(-247, 10), -24);
		
		Test.results();
	}
}
