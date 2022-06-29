package arraysstrings;

import testing.Test;

//Rotate a NxN Matrix by 90 degrees to the right.
public class MatrixRotation {
	
	//Matrix rotation using another matrix (not in place)
	//Time: O(N^2)	Space: O(N^2)	Where the matrix is NxN
	public static int[][] matrixRotate(int[][] m) {
		int n = m.length; //matrix guaranteed to be NxN
		
		int[][] rotated = new int[n][n];
		
		for(int x = 0; x < n; x++) {
			for(int y = 0; y < n; y++) {
				rotated[y][n-x-1] = m[x][y];
			}
		}
		
		return rotated;
	}
	
	//Matrix rotation in place, by performing a transpose and then vertical mirroring.
	//Time: O(N^2)	Space: O(1)		Where the matrix is NxN
	public static int[][] matrixRotateInplace(int[][] m) {
		//transpose
		m = matrixTranspose(m);
		
		//mirror vertically
		m = matrixMirrorV(m);

		return m;
	}
	
	//Transposes a NxN matrix in place
	//Time: O(N^2)	Space: O(1)		Where the matrix is NxN
	public static int[][] matrixTranspose(int[][] m){
		int n = m.length;
		
		for(int x = 0; x < n; x++) {
			for(int y = x; y < n; y++) {
				int temp = m[y][x];
				m[y][x] = m[x][y];
				m[x][y] = temp;
			}
		}
		
		return m;
	}
	
	//Vertically mirrors a NxN matrix in place
	//Time: O(N^2)	Space: O(1)		Where the matrix is NxN
	public static int[][] matrixMirrorV(int[][] m) {
		int n = m.length;
		
		for(int x = 0; x < n; x++) {
			for(int y = 0; y < n/2; y++) {
				int temp = m[x][y];
				m[x][y] = m[x][n-y-1];
				m[x][n-y-1] = temp;
			}
		}
		
		return m;
	}
	
	public static void main(String[] args) {
		int[][] odd = new int[5][5];
		for(int x = 0; x < odd.length; x++) {
			for(int y = 0; y < odd[x].length; y++) {
				odd[x][y] = ((x+1)*10) + (y+1);
			}
		}
		
		int[][] even = new int[6][6];
		for(int x = 0; x < even.length; x++) {
			for(int y = 0; y < even[x].length; y++) {
				even[x][y] = ((x+1)*10) + (y+1);
			}
		}
		
		int[][] rotationOdd = {
				{51, 41, 31, 21, 11},
				{52, 42, 32, 22, 12},
				{53, 43, 33, 23, 13},
				{54, 44, 34, 24, 14},
				{55, 45, 35, 25, 15}
		};
		
		int[][] rotationEven = {
				{61, 51, 41, 31, 21, 11},
				{62, 52, 42, 32, 22, 12},
				{63, 53, 43, 33, 23, 13},
				{64, 54, 44, 34, 24, 14},
				{65, 55, 45, 35, 25, 15},
				{66, 56, 46, 36, 26, 16}
		};
		
		Test.header("matrixRotation");
		Test.equals(matrixRotate(odd), rotationOdd);
		Test.equals(matrixRotateInplace(odd), rotationOdd);
		Test.equals(matrixRotate(even), rotationEven);
		Test.equals(matrixRotateInplace(even), rotationEven);
		Test.results();
	}
}
