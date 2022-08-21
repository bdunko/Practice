package uncategorized;

import testing.Test;

//give an array of positive and negative numbers, find and return the sum of the continguous sequence with the largest sum 
public class ContiguousSequence {

	public static int contiguousSequence(int[] array) {
		int sum = 0;
		int maxSum = 0;
		
		for(int n : array) {
			sum += n;
			if(sum > maxSum)
				maxSum = sum;
			if(sum < 0)
				sum = 0;
		}
		
		return maxSum;
	}
	
	
	public static void main(String[] args) {
		Test.header("ContiguousSequence");
		
		int[][] inputs = {
				{2, 3, -8, -1, 2, 4, -2, 3},
				{1, 1, 1, 1}, 
				{-1, -2, -3, -4},
				{100, -1, 2},
				{100, -102, 99, 1}
		};
		
		int[] answers = {
				7, 4, 0, 101, 100
		};
		
		for(int i = 0; i < inputs.length; i++) {
			int[] input = inputs[i];
			int expected = answers[i];
			Test.equals(contiguousSequence(input), expected);
		}
		
		Test.results();
	}
}
