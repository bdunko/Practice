package sortsearch;

import testing.Test;

//Given an array with numbers from 0 to N, where N is at most 32000 but unknown
//Print all duplicate elements
//Using only 4 KB of memory
public class FindDuplicates {

	//maximum possible integer in list
	private static int N = 32000;
	
	public static void findDuplicates(int[] array) {
		//4KB = ~4000 bytes = ~32000 bits
		//1 boolean = 1 bit
		//we can track whether we've seen a number before with a bit array
		boolean[] isDuplicate = new boolean[N+1];
		
		for(int n : array) {
			if(isDuplicate[n])
				System.out.print(n + " ");
			isDuplicate[n] = true;
		}
	}
	
	public static void main(String[] args) {
		Test.header("FindDuplicates");
		
		int[][] tests = {
				{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
				{1, 1, 2, 2, 3, 4, 5, 4, 6, 7, 3, 8, 10, 9, 10},
				{0, 32000, 3, 3, 0, 32000, 1, 31999},
		};
		
		String[] results = {
			"",
			"1 2 4 3 10 ",
			"3 0 32000 "
		};
		
		for(int t = 0; t < tests.length; t++) {
			Test.redirectStdoutToString();
			findDuplicates(tests[t]);
			Test.restoreStdoutAndTestStdoutEquals(results[t]);
		}
		
		Test.results();
	}
}
