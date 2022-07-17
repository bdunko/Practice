package bitmanip;

import testing.Test;

public class BitBasics {

	//constants for a mask of alternating 1s and 0s
	public static int EVEN_MASK = 0b10101010101010101010101010101010; //-1431655766
	public static int ODD_MASK = 0b01010101010101010101010101010101; //1431655765
	
	public static String itob(int i) {
		return Integer.toBinaryString(i);
	}
	
	public static void pitob(int i) {
		System.out.println(Integer.toBinaryString(i));
	}
	
	//returns the bit at the given position of i
	public static boolean get(int i, int position) {
		int mask = 1<<position; 
		return (i & mask) != 0;
		//create a mask with a 1 at the target position
		//AND this with the number. If there is NOT a 1 at that position, the result must be 0. Otherwise it is non-zero.
		
		//example: i = 10001010
		//             76543210
		//if position = 3, mask = 00001000, i & mask = 00001000, this is != 0, so return 1
		//if position = 0, mask = 00000001, i & mask = 00000000, this is == 0, so return 0
	}
	
	//sets a bit in i at position to 1
	public static int set(int i, int position) {
		int mask = 1 << position;
		return i | mask;
		//create a mask with 1 at the target position
		//OR this with the number, this forces that particular bit to be a 1
		
		//example: i = 10001010
		//             76543210
		//if position = 3, mask = 00001000, i | mask = 10001010, there is a 1 at position 3
		//if position = 0, mask = 00000001, i | mask = 10001011, there is now a 1 at position 0
	}
	
	//set a bit in i at position to 0
	public static int clear(int i, int position) {
		int mask = ~(1 << position);
		return i & mask;
		//create a mask with a 0 at the target position and 1s at the rest
		//AND this with the number, this forces that particular bit to be a 0
		
		//example: i = 10001010
		//             76543210
		//if position = 3, mask = 11110111, i & mask = 10000010, there is now a 0 at position 3
		//if position = 0, mask = 11111110, i & mask = 10001010, there is a 0 at position 0
	}
	
	//negates a number by flipping all bits and adding 1
	public static int negate(int i) {
		return ~i + 1;
	}
	
	public static void main(String[] args) {
		Test.header("BitManip");
		int binary =  0b0001011;
		int binary2 = 0b1010010;
		Test.equals(11, binary);
		Test.equals(82, binary2);
		
		Test.header("& AND");
		Test.equals(0b0 & 0b0, 0b0);
		Test.equals(0b1 & 0b0, 0b0);
		Test.equals(0b0 & 0b1, 0b0);
		Test.equals(0b1 & 0b1, 0b1);
		Test.equals(0b001101 & 0b010101, 0b000101);
		Test.equals(0b0 & 0b1111111, 0b0);
		Test.equals(0b1 & 0b11111, 0b00001);
		Test.equals(0b00111 & 0b11, 0b011);
		
		Test.header("| OR");
		Test.equals(0b0 | 0b0, 0b0);
		Test.equals(0b1 | 0b0, 0b1);
		Test.equals(0b0 | 0b1, 0b1);
		Test.equals(0b1 | 0b1, 0b1);
		Test.equals(0b001101 | 0b010101, 0b011101);
		Test.equals(0b0 | 0b1111111, 0b1111111);
		Test.equals(0b1 | 0b11011, 0b11011);
		Test.equals(0b00111 | 0b11, 0b111);
		
		Test.header("~ COMPLETEMENT/NOT"); //flips all bits
		//in 2s complement
		//leading 0 means positive
		//leading 1 means negative
		Test.equals(~0b10000000000000000000000000000000, 2147483647);
		Test.equals(~0b00000000000000000000000000000000, -1);
		Test.equals(~0b0, -1);
		Test.equals(~0b1, -2); //= 0b11111111111111111111111111111110
		
		Test.header("^ XOR");
		Test.equals(0b0 ^ 0b0, 0b0);
		Test.equals(0b1 ^ 0b0, 0b1);
		Test.equals(0b0 ^ 0b1, 0b1);
		Test.equals(0b1 ^ 0b1, 0b0);
		Test.equals(0b11110000 ^ 0b11001100, 0b00111100);
		Test.equals(0b01010101 ^ 0b10101010, 0b11111111);
		Test.equals(0b11110011 ^ 0b11111100, 0b00001111);
		
		Test.header("<< LEFT SHIFT");
		//left shift = multiply by 2
		Test.equals(0b1<<1, 0b10);
		Test.equals(0b1<<5, 0b100000);
		Test.equals(0b0<<10000, 0b0);		
		Test.equals(0b00000000000000001<<32, 0b1); //weird quirk - shifting by 32 does nothing - would expect the 1 to be shifted off of the left, end result of 0b0
		Test.equals(0b1<<34, 0b100); //shifts by 34 % 32 = 2
		Test.equals(0b11111111111111110000000000000000<<15, 0b10000000000000000000000000000000); //shifts by 15, bits on front are lost (no wraparound)
		
		Test.header("ARITHMETIC RIGHT SHIFT >>");
		//arithmetic right shift = divide by 2 - pad with most significant bit instead of 0 - preserves signedness
		System.out.println(Integer.toBinaryString(0b1011>>1));
		Test.equals(0b1011>>1, 0b0101); //most significant bit is 0, because 0b1011 is actually 0b00000000000000000000000000001011
		Test.equals(0b00000000000000000000000000001011>>1, 0b00000000000000000000000000000101); //most significant bit is 0, because 0b1011 is actually 0b00000000000000000000000000001011
		Test.equals(0b1>>1, 0b0); //no space for 1 to shift
		Test.equals(0b0>>0, 0b0);
		Test.equals(0b10000000000000000000000000000001>>1, 0b11000000000000000000000000000000); 
		Test.equals(0b00000000000000000000000000011011>>3, 0b00000000000000000000000000000011); 
		Test.equals(0b10000000000000000000000000011011>>3, 0b11110000000000000000000000000011); 
		
		
		Test.header("LOGICAL RIGHT SHIFT >>>");
		//logical right shift - pad with 0, does not preserve signedness
		Test.equals(0b1>>>1, 0b0);
		Test.equals(0b0>>>0, 0b0);
		Test.equals(0b100>>>1, 0b010);
		Test.equals(0b001>>>1, 0b0);
		
		//losing bits
		Test.header("lost bits");
		int bin = 0b11111111111111111111111111111111;
		bin = bin << 4;
		Test.equals(bin, 0b11111111111111111111111111110000);
		bin = bin >>> 2;
		Test.equals(bin, 0b00111111111111111111111111111100);
		bin = bin << 5;
		Test.equals(bin, 0b11111111111111111111111110000000);
		bin = bin >> 3;
		Test.equals(bin, 0b11111111111111111111111111110000);
		bin = bin >>> 1;
		Test.equals(bin, 0b01111111111111111111111111111000);
		
		Test.header("negate");
		Test.equals(BitBasics.negate(10), -10);
		Test.equals(BitBasics.negate(0), 0);
		Test.equals(BitBasics.negate(-1), 1);
		Test.equals(BitBasics.negate(1), -1);
		Test.equals(BitBasics.negate(12345), -12345);
		Test.equals(BitBasics.negate(-999), 999);
		
		Test.header("get");
		Test.assertion(BitBasics.get(0b11001111, 0));
		Test.assertion(BitBasics.get(0b11001111, 1));
		Test.assertion(BitBasics.get(0b11001111, 2));
		Test.assertion(BitBasics.get(0b11001111, 3));
		Test.assertion(!BitBasics.get(0b11001111, 4));
		Test.assertion(!BitBasics.get(0b11001111, 5));
		Test.assertion(BitBasics.get(0b11001111, 6));
		Test.assertion(BitBasics.get(0b11001111, 7));
		
		Test.header("set");
		Test.equals(BitBasics.set(0b0000, 0), 0b0001);
		Test.equals(BitBasics.set(0b0000, 1), 0b0010);
		Test.equals(BitBasics.set(0b0000, 2), 0b0100);
		Test.equals(BitBasics.set(0b0000, 3), 0b1000);
		Test.equals(BitBasics.set(0b1100, 0), 0b1101);
		Test.equals(BitBasics.set(0b1111, 3), 0b1111);
		Test.equals(BitBasics.set(0b0001, 3), 0b1001);
		
		Test.header("clear");
		Test.equals(BitBasics.clear(0b1111, 0), 0b1110);
		Test.equals(BitBasics.clear(0b1111, 1), 0b1101);
		Test.equals(BitBasics.clear(0b1111, 2), 0b1011);
		Test.equals(BitBasics.clear(0b1111, 3), 0b0111);
		Test.equals(BitBasics.clear(0b1100, 0), 0b1100);
		Test.equals(BitBasics.clear(0b1111, 3), 0b0111);
		Test.equals(BitBasics.clear(0b0001, 3), 0b0001);
		
		Test.results();
	}
}
