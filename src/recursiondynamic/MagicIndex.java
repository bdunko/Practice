package recursiondynamic;

import testing.Test;

//A magic index is defined as an index where the value of the array at that index is equals to the index itself
//for example, if the 2 index of an array is equal to 2.
//Write a function that finds a magic index (if one exists) in a sorted array. Elements are not distinct.
public class MagicIndex {

	public static int magicIndex(int[] arr) {
		return indexSearch(arr, 0, arr.length-1);
	}
	
	private static int indexSearch(int[] arr, int low, int high) {
		int index = (low + high)/2;
		
		if(index > high || index < low)
			return -1;
		
		if(arr[index] == index)
			return index;
		
		int left = indexSearch(arr, low, Math.min(arr[index], index-1));
		if(left != -1)
			return left;

		int right = indexSearch(arr, Math.max(arr[index], index+1), high);
		return right;
	}
	
	
	private static void testCase(int[] a, boolean hasMagic) {
		int index = magicIndex(a);
		Test.assertion((index != -1) == hasMagic);
		
		if(index != -1)
			Test.equals(index, a[index]);
	}
	
	public static void main(String[] args) {
		Test.header("Magic Index");
		
		Test.header("distinct");
		testCase(new int[]{}, false);
		testCase(new int[]{-1, 0, 1, 2, 3, 4, 5}, false);
		testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, false);
		
		testCase(new int[]{0, 1, 2, 3}, true);
		testCase(new int[]{-3, -2, -1, 0, 3, 5}, true);
		testCase(new int[]{-1, 0, 2, 3, 4}, true);
		
		//repeated values
		Test.header("non-distinct");
		testCase(new int[]{5, 5, 5, 5, 5}, false);
		testCase(new int[]{-3, -2, -1, -1, 0, 3, 7, 7, 7, 7, 7}, true);
		testCase(new int[]{1, 1, 2, 3, 4, 5, 6, 7, 8}, true);
		testCase(new int[]{0, 0, 1, 2, 3, 3}, true);
		testCase(new int[]{1, 2, 3, 3, 4, 5}, true);
		testCase(new int[]{5, 5, 5, 5, 5, 5}, true);
		testCase(new int[]{-10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13}, true);
		
		Test.results();
	}
}
