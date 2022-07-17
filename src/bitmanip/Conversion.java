package bitmanip;

import testing.Test;

//determine the minimum number of bits that must be flipped to make A = B
public class Conversion {
	
	public static int conversion(int A, int B) {
		int diff = 0;
		
		int mask = 1;
		for(int i = 0; i < 32; i++) {
			if((A & mask) != (B & mask))
				diff++;
			mask <<= 1;
		}
		
		return diff;
	}
	
	
	public static void main(String[] args) {
		Test.header("conversion");
		
		Test.equals(conversion(0b0, 0b0), 0);
		Test.equals(conversion(0b1, 0b0), 1);
		Test.equals(conversion(0b11110000, 0b0), 4);
		Test.equals(conversion(0b11110000, 0b10000000), 3);
		Test.equals(conversion(0b11110000, 0b00001111), 8);
		Test.equals(conversion(0b11110000, 0b00111000), 3);
		Test.equals(conversion(0b10100, 0b01011), 5);
		
		Test.results();
	}
}
