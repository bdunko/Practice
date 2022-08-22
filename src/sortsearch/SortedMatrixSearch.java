package sortsearch;

import testing.Test;
import utility.Util.Pair;

//Given a NxM matrix where rows and columns are sorted ascendingly,
//implemenet a method to search the matrix for an element
public class SortedMatrixSearch {
	
//	public static Pair matrixSearch(int[][] matrix, int target) {		
//		if(matrix.length == 0 || matrix[0].length == 0)
//			return null;
//		
//		return helper(matrix, target, 0, 0);
//	}
//	
//	private static Pair helper(int[][] matrix, int target, int x, int y) {
//		int current = matrix[x][y]; //get current value
//		
//		//success case - found target
//		if(current == target)
//			return new Pair(x, y);
//		
//		//otherwise, get next x and next y value, if they exist
//		int nextX = (x+1 != matrix.length ? matrix[x+1][y] : Integer.MAX_VALUE);
//		int nextY = (y+1 != matrix[0].length ? matrix[x][y+1] : Integer.MAX_VALUE);
//		
//		//if both are larger than target, then target isn't in matrix, return null
//		if(nextX > target && nextY > target)
//			return null;
//		
//		//if X is greater but Y isn't, move in Y direction
//		if(nextX > target && nextY <= target)
//			return helper(matrix, target, x, y+1);
//		
//		//if Y is greater but X isn't, move in X direction
//		if(nextY > target && nextX <= target)
//			return helper(matrix, target, x+1, y);
//		
//		//if both are smaller or equal, move in both directions
//		Pair xResult = helper(matrix, target, x+1, y);
//		if(xResult != null)
//			return xResult;
//		return helper(matrix, target, x, y+1);
//	}
	
	private static Pair matrixSearch(int[][] matrix, int target) {		
		if(matrix.length == 0 || matrix[0].length == 0)
			return null;
		
		int x = 0;
		int y = matrix[0].length - 1;
		
		while(y >= 0 && x < matrix.length) {
			int current = matrix[x][y];
			
			if(current == target)
				return new Pair(x, y);
			
			//if current value is larger than target, then element cannot be in this column
			//so we move to the column to the left
			if(current > target)
				y--; 
			//otherwise, current value is smaller than target, so it cannot be earlier in this row
			//so we can safely move down
			else
				x++;
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Test.header("SortedMatrixSearch");
		
		int[][][] tests = {
				{
					{1, 2, 3, 4, 5},
					{2, 3, 4, 5, 6},
					{3, 4, 5, 6, 7}
				},
				{
					{1, 2, 3, 4, 5}
				},
				{
					{1},
					{2},
					{3},
					{4},
					{5}
				},
				{
					{1, 5, 9, 20},
					{100, 103, 106, 205},
					{101, 104, 120, 400},
					{102, 105, 121, 401},
					{106, 112, 150, 408}
				}
		};
		
		for(int[][] testcase : tests) {
			for(int x = 0; x < testcase.length; x++) {
				for(int y = 0; y < testcase[0].length; y++) {
					Pair answer = matrixSearch(testcase, testcase[x][y]);
					if(answer == null)
						Test.fail(String.format("Answer was null, but should have been %d %d!", x, y));
					else
						Test.equals(testcase[answer.n][answer.m], testcase[x][y]);
				}
			}
			Test.isNull(matrixSearch(testcase, 8888888));
			Test.isNull(matrixSearch(testcase, -8888888));
		}
		
		Test.results();
	}
} 
