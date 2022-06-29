package arraysstrings;

import testing.Test;

//Generic binary search
public class BinarySearch {
	
	private static <T extends Comparable<T>> boolean binSearchHelper(T[] array, T target, int low, int high) {
		if(low > high)
			return false;
		
		int middleIndex = (low + high)/2;
		T middle = array[middleIndex];
		
		if(middle.compareTo(target) == 0)
			return true;
		
		
		if(middle.compareTo(target) > 0) //current element is larger, so search bottom half
			return binSearchHelper(array, target, low, middleIndex-1);
		else //current element is smaller, so search top half
			return binSearchHelper(array, target, middleIndex+1, high);
	}

	public static <T extends Comparable<T>>  boolean binSearch(T[] array, T target) {
		return binSearchHelper(array, target, 0, array.length-1);
	}
	
	
	public static void main(String[] args) {
		Integer[] array1 = {1, 2, 3, 4, 5};
		Integer[] array2 = {1, 3, 5, 7, 10};
		
		Test.header("binSearch");
		Test.header("odd length");
		Test.assertion(binSearch(array1, 1));
		Test.assertion(binSearch(array1, 2));
		Test.assertion(binSearch(array1, 3));
		Test.assertion(binSearch(array1, 4));
		Test.assertion(binSearch(array1, 5));
		Test.assertion(!binSearch(array1, 0));
		Test.assertion(!binSearch(array1, 6));
		Test.assertion(binSearch(array2, 1));
		Test.assertion(binSearch(array2, 3));
		Test.assertion(binSearch(array2, 5));
		Test.assertion(binSearch(array2, 7));
		Test.assertion(binSearch(array2, 10));
		Test.assertion(!binSearch(array2, 0));
		Test.assertion(!binSearch(array2, 2));
		Test.assertion(!binSearch(array2, 4));
		Test.assertion(!binSearch(array2, 6));
		Test.assertion(!binSearch(array2, 8));
		Test.assertion(!binSearch(array2, 9));
		Test.assertion(!binSearch(array2, 100));
		
		Test.header("even length");
		Integer[] array3 = {1, 2, 3, 4, 5, 6, 7, 8};
		Integer[] array4 = {1, 1, 3, 4, 7, 8, 10, 15};
		Test.assertion(binSearch(array3, 1));
		Test.assertion(binSearch(array3, 2));
		Test.assertion(binSearch(array3, 3));
		Test.assertion(binSearch(array3, 4));
		Test.assertion(binSearch(array3, 5));
		Test.assertion(binSearch(array3, 6));
		Test.assertion(binSearch(array3, 7));
		Test.assertion(binSearch(array3, 8));
		Test.assertion(!binSearch(array3, 0));
		Test.assertion(!binSearch(array3, 9));
		Test.assertion(binSearch(array4, 1));
		Test.assertion(binSearch(array4, 4));
		Test.assertion(binSearch(array4, 7));
		Test.assertion(binSearch(array4, 8));
		Test.assertion(binSearch(array4, 10));
		Test.assertion(binSearch(array4, 15));
		Test.assertion(!binSearch(array4, 0));
		Test.assertion(!binSearch(array4, 2));
		Test.assertion(!binSearch(array4, 5));
		Test.assertion(!binSearch(array4, 6));
		Test.assertion(!binSearch(array4, 9));
		Test.assertion(!binSearch(array4, 14));
		Test.assertion(!binSearch(array4, 16));
		
		Test.results();
	}
	
}
