package chp1arraysstrings;

public class MatrixRotation {
	
	@Deprecated
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
	
	public static int[][] matrixRotateInplace(int[][] m) {
		//transpose
		m = matrixTranspose(m);
		
		//mirror vertically
		m = matrixMirrorV(m);

		return m;
	}
	
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
	
	private static void printMatrix(int[][] m) {
		for(int x = 0; x < m.length; x++) {
			for(int y = 0; y < m[x].length; y++) {
				System.out.printf("[%02d]", m[x][y]);
			}
			System.out.println();
		}
		System.out.println();
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
		
		printMatrix(odd);
		printMatrix(matrixRotate(odd));
		printMatrix(matrixRotateInplace(odd));
	}
}
