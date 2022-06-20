package chp1arraysstrings;

import testing.Test;

public class MatrixZero {

	@SuppressWarnings("unused")
	@Deprecated
	private static int[][] zeroMatrix(int[][] m) {
		int M = m.length;
		int N = m[0].length;
		
		boolean[][] mask = new boolean[M][N];
		for(int row = 0; row < M; row++) {
			for(int col = 0; col < N; col++) {
				mask[row][col] = true;
			}
		}
		
		for(int row = 0; row < M; row++) {
			for(int col = 0; col < N; col++) {
				if(m[row][col] == 0) {
					for(int zrow = 0; zrow < M; zrow++) {
						mask[zrow][col] = false;
					}
					for(int zcol = 0; zcol < N; zcol++) {
						mask[row][zcol] = false;
					}
				}
			}
		}
		
		for(int row = 0; row < M; row++) {
			for (int col = 0; col < N; col++) {
				m[row][col] *= mask[row][col] ? 1 : 0;
			}
		}
		
		return m;
	}
	
	public static int[][] zeroMatrix2(int[][] m) {
		int M = m.length;
		int N = m[0].length;
		
		boolean zeroInHeaderRow = false;
		boolean zeroInHeaderCol = false;
		
		for(int col = 0; col < N; col++)
		{
			if(m[0][col] == 0) {
				zeroInHeaderRow = true;
				break;
			}
		}
		
		for(int row = 0; row < M; row++) {
			if(m[row][0] == 0) {
				zeroInHeaderCol = true;
				break;
			}
		}
		
		for(int row = 1; row < M; row++) {
			for(int col = 1; col < N; col++) {
				if(m[row][col] == 0) {
					m[0][col] = 0;
					m[row][0] = 0;
				}
			}
		}
		
		for(int row = 1; row < M; row++) {
			if(m[row][0] == 0) {
				//zero out the row
				for(int col = 0; col < N; col++) {
					m[row][col] = 0;
				}
			}
		}
		
		for(int col = 1; col < N; col++) {
			if(m[0][col] == 0) {
				//zero out the column
				for(int row = 0; row < M; row++) {
					m[row][col] = 0;
				}
			}
		}
		
		if(zeroInHeaderRow) {
			for(int col = 0; col < N; col++) {
				m[0][col] = 0;
			}
		}
		if(zeroInHeaderCol) {
			for(int row = 0; row < M; row++) {
				m[row][0] = 0;
			}
		}
		
		return m;
	}
	
	public static void main(String[] args) {
		int[][] m = {
				{1, 1, 1, 1, 1},
				{1, 1, 1, 0, 1},
				{1, 1, 1, 1, 1},
				{1, 1, 1, 1, 0},
				{1, 1, 1, 1, 1},
				{1, 0, 1, 0, 1}
		};
		int[][] z = {
				{1, 0, 1, 0, 0},
				{0, 0, 0, 0, 0},
				{1, 0, 1, 0, 0},
				{0, 0, 0, 0, 0},
				{1, 0, 1, 0, 0},
				{0, 0, 0, 0, 0}
		};
		
		Test.equals(zeroMatrix2(m), z);
		
		int[][] m2 = {
				{1, 1, 0, 1, 1},
				{1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1}
		};
		int[][] z2 = {
				{0, 0, 0, 0, 0},
				{1, 1, 0, 1, 1},
				{1, 1, 0, 1, 1}
		};
		Test.equals(zeroMatrix2(m2), z2);
		
		int[][] m3 = {
				{1, 1, 1, 0, 0},
				{1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1},
				{0, 0, 1, 1, 1}
		};
		int[][] z3 = {
				{0, 0, 0, 0, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0}
		};
		Test.equals(zeroMatrix2(m3), z3);
		
		int[][] m4 = {
				{0, 1, 1, 1},
				{1, 1, 1, 1},
				{1, 1, 1, 1}
		};
		int[][] z4 = {
				{0, 0, 0, 0},
				{0, 1, 1, 1},
				{0, 1, 1, 1}
		};
		
		Test.equals(zeroMatrix2(m4), z4);
		
		Test.results();
	}
}
