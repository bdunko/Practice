package sortsearch;

import testing.Test;

public class PeaksAndValleys {

	public static void peaksAndValleys(int[] array) {
		//iterate over all sets of 3 elements
		for(int i = 0; i < array.length-2; i++) {
			int a1 = array[i];
			int a2 = array[i+1];
			int a3 = array[i+2];
			
			//check if PVP or VPV
			boolean pvp = (a1 >= a2) && (a3 >= a2);
			boolean vpv = (a1 <= a2) && (a3 <= a2);
			
			if(pvp || vpv)
				continue;
			
			//first two have either a vp or pv relationship
			//if vp, vpv is false only if third element > p, which means it is also > v, so we can swap them to make vpv
			//if pv, pvp is false only is third element < p, which means it is also < p, so we can swap them to make pvp
			array[i+1] = a3;
			array[i+2] = a2;
		}
	}
	
	private static void verify(int[] array) {
		if(array.length <= 2) {
			Test.success("Array is sorted into peaks and valleys");
			return;
		}
		
		//for every sequence of 3, verify that is either PVP or VPV
		for(int i = 0; i < array.length - 2; i++) {
			int a1 = array[i];
			int a2 = array[i+1];
			int a3 = array[i+2];
			
			boolean pvp = (a1 >= a2) && (a3 >= a2);
			boolean vpv = (a1 <= a2) && (a3 <= a2);
			
			if(!(pvp || vpv)) {
				Test.fail("Array is not sorted into peaks and valleys");
				return;
			}
		}
		
		Test.success("Array is sorted into peaks and valleys");
	}
	
	public static void main(String[] args) {
		Test.header("PeaksAndValleys");
		
		int[][] tests = {
				{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
				{20, 42, 3493, 1901, 348, 112, 20, 20, 20, 34934},
				{5, 5, 5, 5, 5, 5, 5, 5, 3, 3, 3, 3},
				{10, 30, 20}
		};
		
		for(int[] testcase : tests) {
			peaksAndValleys(testcase);
			verify(testcase);
		}
		
		Test.results();
	}
}
