package uncategorized;

import testing.Test;

//Given an array of integers, find the indices n and m such that, if you sorted the subarray from n to m, the entire array would be sorted
//Find the smallest such sequence
public class SubSort {

	private static class Pair {
		public int n, m;
		
		public Pair(int n, int m) {
			this.n = n;
			this.m = m;
		}
		
		@Override
		public boolean equals(Object other) {
			if(other == null)
				return false;
			if(!(other instanceof Pair))
				return false;
			
			Pair otherPair = (Pair) other;
			
			return n == otherPair.n && m == otherPair.m;
		}
		
		@Override
		public String toString() {
			return "(" + n + ", " + m + ")";
		}
	}
	
	private static Pair subSort(int[] array) {
		if(array.length <= 1)
			return null;
		
		//go from left to right, find index of first unsorted element
		int previous = array[0];
		int left = 0;
		while(left+1 < array.length && array[left+1] > previous) {
			left++;
			previous = array[left];
		}
		
		//if entire array is sorted already
		if(left == array.length-1)
			return null;
		
		//go from right to left, find index of last unsorted element
		previous = array[array.length-1];
		int right = array.length-1;
		while(right-1 >= 0 && array[right-1] < previous) {
			right--;
			previous = array[right];
		}
		
		//now, find the smallest element in the unsorted middle
		//move the left index back until all elements in left are smaller than smallest middle element
		int smallestInMiddle = array[left];
		for(int i = left+1; i <= right; i++) {
			if(array[i] < smallestInMiddle)
				smallestInMiddle = array[i];
		}
		while(left-1 >= 0 && array[left-1] > smallestInMiddle)
			left--;
		
		//last, find the largest element in the unsorted middle
		//move the right index forward until all element sin right are larger than largest middle element
		int largestInMiddle = array[right];
		for(int i = right-1; i >= left; i--) {
			if(array[i] > largestInMiddle)
				largestInMiddle = array[i];
		}
		while(right+1 < array.length && array[right+1] < largestInMiddle)
			right++;
		
		
		return new Pair(left, right);
	}
	
	public static void main(String[] args) {
		Test.header("SubSort");
		
		int[][] inputs = {
				{1, 2, 3, 4, 9, 13, 14, 11, 8, 15, 16, 17},
				{1, 2, 10, 8, 0, 11, 12},
				{1, 2, 4, 16, 8, 3, 11, 12},
				{3, 1, 5, 2},
				{1, 2, 3, 4, 5, 6, 7, 8},
				{5, 2, 1, 6, 7, 8, 9},
				{1, 2, 3, 4, 13, 11, 9},
		};
		
		Pair[] answers = {
				new Pair(4, 8),
				new Pair(0, 4),
				new Pair(2, 7),
				new Pair(0, 3),
				null,
				new Pair(0, 2),
				new Pair(4, 6)
		};
		
		for(int i = 0; i < inputs.length; i++) {
			int[] inputArray = inputs[i];
			Pair expected = answers[i];
			
			Test.equals(subSort(inputArray), expected);
		}
		
		Test.results();
	}
}
