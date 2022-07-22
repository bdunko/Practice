package bitmanip;

import testing.Test;

public class Insertion {

	private static boolean get(int num, int index) {
		int mask = 1 << index;
		return (num & mask) != 0;
	}
	
	private static int set(int num, int index) {
		int mask = 1 << index;
		return (num | mask);
	}
	
	private static int clear(int num, int index) {
		int mask = ~(1 << index);
		return (num & mask);
	}
	
	//inserts bits from M into N, starting at index start and ending at index end (inclusive on both sides)
	public static int insertion(int N, int M, int start, int end) {
		
		//iterate over bits in M from start to end
		for(int i = start; i <= end; i++) {
			if(get(M, i))
				N = set(N, i); //if index contains a 1 in M, set that index in N to 1
			else
				N = clear(N, i); //if index contains a 0 in M, clear that index in N to 0
		}
		
		return N;
	}
	
	public static int insertion2(int N, int M, int start, int end) {
		//clear bits from start to end in N
		
		//3, 6
		//goal: 
		//10000111
		//76543210
		
		//11111111 << 7 = 10000000
		int mask = (~0) << (end+1); 
		
		//1 << 3 = 00001000 - 1 = 00000111
		int startMask = (1 << start) - 1;
		
		//10000111
		mask |= startMask;
		
		//zero out the bits in N
		N &= mask;
		
		//zero out the other bits in M
		M &= ~(mask);

		//merge the two
		N += M;
		
		//return the result
		return N;
	}
	
	private static void testCase(int N, int M, int start, int end, int expected) {
		Test.equals(insertion(N, M, start, end), expected); 
		Test.equals(insertion2(N, M, start, end), expected); 
	}
	
	public static void main(String[] args) {
		Test.header("Insertion");
		
		Test.assertion(!get(0b1010, 0));
		Test.assertion(get(0b1010, 1));
		Test.assertion(!get(0b1010, 2));
		Test.assertion(get(0b1010, 3));
		
		Test.equals(set(0b000, 0), 0b001);
		Test.equals(set(0b000, 1), 0b010);
		Test.equals(set(0b000, 2), 0b100);
		
		Test.equals(clear(0b111, 0), 0b110);
		Test.equals(clear(0b111, 1), 0b101);
		Test.equals(clear(0b111, 2), 0b011);
		
		
		testCase(0b11000000, 0b00011011, 1, 4, 0b11011010);
		testCase(0b00000000, 0b11111111, 0, 7, 0b11111111);
		testCase(0b11001100, 0b10101010, 3, 6, 0b10101100);
		testCase(0b0000, 0b1000, 3, 3, 0b1000);
		testCase(0b11000000, 0b00011011, 0, 0, 0b11000001);
		
		Test.results();
	}
}

