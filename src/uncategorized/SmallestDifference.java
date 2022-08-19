package uncategorized;

import java.util.Arrays;

import testing.Test;

//Compute the smallest difference between any two numbers in a pair of arrays
public class SmallestDifference {

	private static int smallestDifference(int[] array1, int[] array2) {
		//presort both - O(nlogn)
		Arrays.sort(array1);
		Arrays.sort(array2);
		
		//now compare pairs, increment whichever is smaller
		//O(n)
		int index1 = 0;
		int index2 = 0;
		int smallestDiff = Integer.MAX_VALUE;
		
		while(index1 < array1.length && index2 < array2.length) {
			int diff = Math.abs(array1[index1] - array2[index2]);
			if(diff < smallestDiff)
				smallestDiff = diff;
			
			//move pointers - smallest moves up
			if(array1[index1] < array2[index2])
				index1++;
			else
				index2++;
		}
		
		//total O(nlogn)
		return smallestDiff;
	}
	
	public static void main(String[] args) {
		Test.header("SmallestDifference");
		
		int[][][] tests = {
				{
					{5, 10, 15},
					{3, 2, 1}
				}, 
				{
					{1, 3, 15, 11, 2},
					{23, 127, 235, 19, 8}
				}, 
				{
					{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
					{100, 200, 300}
				}, 
				{
					{500},
					{501}
				},
				{
					{5, 3, 0, 19},
					{17, 3, 12}
				}
		};
		
		int[] answers = {
				2, 3, 90, 1, 0
		};
		
		for(int i = 0; i < tests.length; i++) {
			int[] a1 = tests[i][0];
			int[] a2 = tests[i][1];
			int answer = answers[i];
			
			Test.equals(smallestDifference(a1, a2), answer);
		}
		
		Test.results();
	}
	
}
