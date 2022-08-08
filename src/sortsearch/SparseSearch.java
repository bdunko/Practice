package sortsearch;

import testing.Test;

public class SparseSearch {
	
	private static int sparseSearch(String[] array, String target) {
		return sparseBinSearch(array, 0, array.length-1, target);
	}
	
	private static int sparseBinSearch(String[] array, int min, int max, String target) {
		if(min > max)
			return -1;
		
		int middle = (min + max) / 2;
		String current = array[middle];
		
		//did we find target?
		if(current.equals(target))
			return middle;
		
		//if "", we learned nothing and must search both sides
		if(current.equals("")) {
			int result = sparseBinSearch(array, min, middle-1, target);
			if(result != -1)
				return result;
			return sparseBinSearch(array, middle+1, max, target);
		}
		
		//otherwise, search either left or right depending
		if(current.compareTo(target) > 0) { //current > target, search left
			return sparseBinSearch(array, min, middle-1, target);
		} else { //current < target, search right
			return sparseBinSearch(array, middle+1, max, target);
		}
	}
	
	public static void main(String[] args) {
		Test.header("SparseSearch");
		
		String[][] tests = {
				{"A", "", "", "B", "", "", "", "C", "D", "E", "", "", "F"},	
				{"A", "B", "C", "", "D", "E", "F"},	
				{"A", "B", "C", "", "", "E", "F"},	
				{"", "B", "C", "", "D", "E", "F", ""},	
				{"", "B", "B", "B", "C", "", "D", "E", "F", "F", ""},	
				{"A", "", "", "", "", "", "", "", "", "", "B"},	
				{"", "", "", "", "A", "", "B", "", "C", "", ""},	
				{"", "", "", "", "A", "", "B", "", "C", "", ""},	
				{""},	
		};
		
		for(String[] testcase : tests) {
			for(int i = 0; i < testcase.length; i++) {
				if(!testcase[i].equals("")) {
					String target = testcase[i];
					int result = sparseSearch(testcase, target);
					if(result == -1)
						Test.fail("Returned -1, but array contains target.");
					else
						Test.equals(target, testcase[result]);
				}
			}
			//a few false searchs
			Test.equals(sparseSearch(testcase, "Z"), -1);
			Test.equals(sparseSearch(testcase, "1"), -1);
			Test.equals(sparseSearch(testcase, "AA"), -1);
			Test.equals(sparseSearch(testcase, "ABC"), -1);
		}
		
		Test.results();
	}
}
