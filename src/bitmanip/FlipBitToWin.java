package bitmanip;

import testing.Test;

//Given a number, if you flip one bit, what is the length of the longest sequence of 1s?
public class FlipBitToWin {
	
	private static boolean get(int N, int index) {
		int mask = 1 << index;
		return (N & mask) != 0;
	}
	
	private static int BITS_PER_INT = 32;
	
	public static int flipBitToWin(int N) {
		int longest = 1;
		int curr_seq = 0;
		int last_seq = 0;
		
		for(int i = 0; i < BITS_PER_INT-1; i++) {
			if(get(N, i)) {
				curr_seq++;
			} else {
				longest = Math.max(longest, curr_seq + last_seq + 1);
				last_seq = curr_seq;
				curr_seq = 0;
			}
		}
		
		longest = Math.max(longest, curr_seq + last_seq);
		
		return longest;
	}
	
	public static void main(String[] args) {
		Test.header("FlipBitToWin");
		
		Test.equals(flipBitToWin(0b00000), 1);
		Test.equals(flipBitToWin(0b110011), 3);
		Test.equals(flipBitToWin(0b01001), 2);
		Test.equals(flipBitToWin(0b101101), 4);
		Test.equals(flipBitToWin(0b11101), 5);
		Test.equals(flipBitToWin(0b1010101000100), 3);
		Test.equals(flipBitToWin(0b101), 3);
		Test.equals(flipBitToWin(0b1), 2);
		
		Test.results();
	}
}
