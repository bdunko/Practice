package bitmanip;

import testing.Test;

public class PairwiseSwap {

	private static int EVEN_MASK = 0b10101010101010101010101010101010; //-1431655766
	private static int ODD_MASK = 0b01010101010101010101010101010101; //1431655765
	
	public static int pairwiseSwap(int N) {
		int evens = N << 1;
		evens &= EVEN_MASK;
		
		System.out.println(ODD_MASK);
		
		int odds = N >> 1;
		odds &= ODD_MASK;
		
		return evens + odds;
		
		//shorter
		//return ((N << 1) & EVEN_MASK) + ((N >> 1) & ODD_MASK);
	}
	
	public static void main(String[] args) {
		Test.header("Pairwise Swap");
		
		Test.equals(pairwiseSwap(0b01), 0b10);
		Test.equals(pairwiseSwap(0b0), 0b0);
		Test.equals(pairwiseSwap(0b10), 0b01);
		Test.equals(pairwiseSwap(0b11), 0b11);
		Test.equals(pairwiseSwap(0b11110000), 0b11110000);
		Test.equals(pairwiseSwap(0b10001101), 0b01001110);
		Test.equals(pairwiseSwap(0b10101010000000000000000000000000), 0b01010101000000000000000000000000);
		Test.equals(pairwiseSwap(0b10101010000000000000000000000101), 0b01010101000000000000000000001010);
		
		Test.results();
		
	}
	
}
