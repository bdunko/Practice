package arraysstrings;

import testing.Test;

//Determine the number of subsequences of an array that sum to a target value 
public class SubarraySum {

	public static int subarraySumTo(int[] array, int target) {
		int front = 0;
		int back = 0;
		int sum = 0;
		int subarrays = 0;
		
		while(front != array.length || back != array.length) {
			if ((back < front && sum > target) || front == array.length) {
				sum -= array[back];
				back++;
			} else {
				sum += array[front];
				front++;
			}
			
			if(sum == target) {
				subarrays++;
			}
		}
		
		return subarrays;
		
	}
	
	public static void main(String[] args) {
		Test.header("Subsequence Sum");
		
		int[] a = {1, 2, 3, 0, 2, 1};
		Test.equals(subarraySumTo(a, 3), 5); //1 2, 3, 3 0, 0 2 1, 2 1
		Test.equals(subarraySumTo(a, 4), 0); 
		Test.equals(subarraySumTo(a, 2), 2); //2, 2 
		
		int[] b = {1, 4, 0, 0, 3, 10, 5};
		Test.equals(subarraySumTo(b, 7), 1);
		
		Test.results();
	}
}
