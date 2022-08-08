package sortsearch;

import testing.Test;

//Given a sorted array of integers that has been rotated an unknown amount
//Return the index of an element in the array
public class SearchInRotated {

	private static int searchRotated(int[] rotated, int target) {
		return rotatedBinSearch(rotated, 0, rotated.length-1, target);
	}
	
	private static int rotatedBinSearch(int[] array, int min, int max, int target) {
		if(min > max)
			return -1;
		
		int middle = (min+max)/2;
		
		//check if we found answer
		if(array[middle] == target)
			return middle;
		
		//determine if left or right side is 'normal'
		//at any index, one side will be sorted and the other will contain the inflection point
		boolean leftNormal = array[min] < array[middle];
		boolean rightNormal = array[max] > array[middle];
		
		if(leftNormal) {
			if(target < array[middle] && target >= array[min]) { //if left is normally ordered, target is smaller than current, and target is larger than smallest value to left, search left
				return rotatedBinSearch(array, min, middle-1, target);
			} else {  //otherwise search right
				return rotatedBinSearch(array, middle+1, max, target);
			}
		} else if (rightNormal) {
			if(target > array[middle] && target <= array[max]) { //if right is normally ordered, target is larger than current value, and target is smaller than largest value on right, serach right
				return rotatedBinSearch(array, middle+1, max, target);
			} else { //otherwise search left
				return rotatedBinSearch(array, min, middle-1, target);
			}
		} else { //if we can't tell which is sorted, check both
			int result = rotatedBinSearch(array, min, middle-1, target);
			if(result != -1)
				return result;
			return rotatedBinSearch(array, middle+1, max, target);
		}
	}
	
	public static void main(String[] args) {
		Test.header("SearchInRotated");
		
		int[][] tests = {
				{0, 1, 2, 3, 4},
				{6, 7, 8, 0, 1, 2, 3, 4, 5},
				{2, 3, 4, 5, 6, 7, 8, 0, 1},
				{7, 7, 8, 8, 0, 0, 0, 1, 1, 2, 3, 4, 5, 6, 7},
				{7, 7, 7, 7, 7, 7, 1, 7, 7, 7, 7, 7, 7, 7, 7},
		};
		
		for(int[] test : tests) {
			Test.header(test.toString());
			for(int i = 0; i < test.length; i++) {
				int searchingFor = test[i];
				int index = searchRotated(test, test[i]);
				Test.equals(searchingFor, test[index]);
			}
			Test.equals(searchRotated(test, -10000), -1);
			Test.equals(searchRotated(test, 10000), -1);
		}
		
		Test.results();
	}
}
