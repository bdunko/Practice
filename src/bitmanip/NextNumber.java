package bitmanip;

import testing.Test;

//Given a number, return the next largest and next smallest number with the same number of 0s and 1s in its binary representation
public class NextNumber {
	
	private static boolean get(int N, int index) {
		int mask = 1 << index;
		return (N & mask) != 0;
	}
	
	public static class NextPair {
		int nextSmallest, nextLargest;
		
		public NextPair(int nextSmallest, int nextLargest) {
			this.nextLargest = nextLargest;
			this.nextSmallest = nextSmallest;
		}
	}
	
	private static int numOfOnes(int N) {
		int ones = 0;
		for(int i = 0; i < 32; i++) {
			if(get(N, i))
				ones++;
		}
		return ones;
	}
	
	public static NextPair nextNumberBrute(int num) {
		if(num == 0)
			return null;
		
		//count # of 1
		int nOnes = numOfOnes(num);
		
		int smallest = num - 1;
		while(smallest != -1 && numOfOnes(smallest) != nOnes) {
			smallest--;
		}
		
		int largest = num + 1;
		while(largest != Integer.MAX_VALUE && numOfOnes(largest) != nOnes) {
			largest++;
		}
		
		return new NextPair(smallest, largest);
	}
	
	//idea: find the rightmost 0 with a 1 to the right.
	//Swap this 1 and 0. Now we have a larger number with same number of 1s and 0s.
	//Now we want to make this number as small as possible with the remaining 1s to the right.
	//Move each 1 as far right as possible (so if there were 3 1s to the right, the leftmost 3 digits would be 111)
	private static int nextLargest(int num) {
		int nextLargest = -1;
		
		//find rightmost 0 with 1s still to the right
		int rightmostZeroIndex;
		boolean pastOne = false;
		int ones = 0;
		
		for(rightmostZeroIndex = 0; rightmostZeroIndex < 32; rightmostZeroIndex++) {
			boolean isOne = get(num, rightmostZeroIndex);

			if(isOne) {
				pastOne = true;
				ones++;
			}
			else if(!isOne && pastOne) {
				break;
			}
		}
		
		if(rightmostZeroIndex != 32) {
			nextLargest = num;
			//flip to 1
			nextLargest |= (1 << rightmostZeroIndex);
			
			//zero bits prior to flip by making a mask that looks like 11111111..000000000
			nextLargest &= ~((1 << rightmostZeroIndex) - 1);
			
			//now put the ones back with a mask that looks like 00000...1111
			nextLargest |= (1 << (ones-1))-1;
		}
		
		return nextLargest;
	}
	
	public static int nextSmallest(int num) {
		int nextSmallest = -1;
		
		//find rightmost one with zeroes to the right
		int rightmostOneIndex = 0;
		int ones = 0;
		boolean pastZero = false;
		for(rightmostOneIndex = 0; rightmostOneIndex < 32; rightmostOneIndex++) {
			boolean isOne = get(num, rightmostOneIndex);
			if(isOne) {
				ones++;
				if(pastZero)
					break;
			}
			else {
				pastZero = true;
			}
		}
		
		if(rightmostOneIndex != 32) {
			nextSmallest = num;
			
			//set the rightmostOneIndex to 0
			nextSmallest &= ~(1 << rightmostOneIndex);
			
			//set everything before to zero
			nextSmallest &= ~((1 << rightmostOneIndex) - 1);
			
			//now put the ones back starting at rightmostOneIndex
			//want to make a mask like... for example, with 3 ones... 00R11100
			int mask = 1 << ones; //00001000
			mask -= 1; //00000111
			mask <<= (rightmostOneIndex-ones); //00011100
			nextSmallest |= mask;
		}
		
		return nextSmallest;
	}
	
	public static NextPair nextNumber(int num) {
		return new NextPair(nextSmallest(num), nextLargest(num));
	}
	
	public static void main(String[] args) {
		Test.header("NextNumber");
		
		Test.equals(nextNumberBrute(5).nextSmallest, 3);
		Test.equals(nextNumberBrute(5).nextLargest, 6);
		Test.equals(nextNumberBrute(6).nextSmallest, 5);
		Test.equals(nextNumberBrute(6).nextLargest, 9);
		Test.equals(nextNumberBrute(2).nextSmallest, 1);
		Test.equals(nextNumberBrute(2).nextLargest, 4);
		Test.equals(nextNumberBrute(8).nextSmallest, 4);
		Test.equals(nextNumberBrute(8).nextLargest, 16);
		Test.equals(nextNumberBrute(0b010001).nextSmallest, 0b001100);
		Test.equals(nextNumberBrute(0b010001).nextLargest, 0b010010);
		
		Test.equals(nextNumber(5).nextSmallest, 3);
		Test.equals(nextNumber(5).nextLargest, 6);
		Test.equals(nextNumber(6).nextSmallest, 5);
		Test.equals(nextNumber(6).nextLargest, 9);
		Test.equals(nextNumber(2).nextSmallest, 1);
		Test.equals(nextNumber(2).nextLargest, 4);
		Test.equals(nextNumber(8).nextSmallest, 4);
		Test.equals(nextNumber(8).nextLargest, 16);
		Test.equals(nextNumber(0b010001).nextSmallest, 0b001100);
		Test.equals(nextNumber(0b010001).nextLargest, 0b010010);
		
		Test.results();
	}
}
