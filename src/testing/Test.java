package testing;

import java.util.Formatter;

public class Test {
	private static int counter = 1;
	private static int successes = 0;
	
	public static void reset() {
		counter = 1;
		successes = 0;
	}
	
	public static void isNull(Object a) {
		boolean success = a == null;
		System.out.printf("Test %4d: %s - Object was %s.\n", counter, success ? "SUCCESS" : "FAILURE", success ? "null" : "not null");
		increment(success);
	}
	
	public static void assertion(boolean b) {
		System.out.printf("Test %4d: %s - Assertion was %b.\n", counter, b ? "SUCCESS" : "FAILURE", b);
		increment(b);
	}

	public static void equals(int a, int b) {
		equals(Integer.valueOf(a), Integer.valueOf(b));
	}
	
	public static void equals(Object a, Object b) {
		boolean success;
		if(a == null || b == null)
			success = false;
		else 
			success = a.equals(b);
		
		System.out.printf("Test %4d: %s - %s %s %s\n", counter, success ? "SUCCESS" : "FAILURE", (a == null ? "(NULL)" : a.toString()), success ? "=" : "!=", (b == null ? "(NULL)" : b.toString()));
		increment(success);
	}
	
	//matrix equality
	public static void equals(int[][] m1, int[][]m2) {
		boolean success = true;
		
		for(int row = 0; row < m1.length; row++) {
			
			for(int col = 0; col < m1[row].length; col++) {
				if(m1.length != m2.length || 
						m1[row].length != m2[row].length ||
						m1[row][col] != m2[row][col]) {
					success = false;
					break;
				}
			}
		}
		
		System.out.printf("Test %4d: %s - matrix1 %s matrix2\n", counter, success ? "SUCCESS" : "FAILURE", success ? "=" : "!=");
		increment(success);
	}
	
	private static void increment(boolean success) {
		Test.counter++;
		if(success)
			successes++;
	}
	
	public static void results() {
		int nTests = counter - 1;
		System.out.printf("----------\n%d/%d tests succeeded.\n", successes, nTests);
		if(successes == nTests)
			System.out.printf("All tests pass!\n\n");
		else
			System.out.printf("%d tests failed...\n\n", nTests - successes);
	}
	
	public static void log(int[][] matrix) {
		StringBuilder sb = new StringBuilder();
		
		for(int x = 0; x < matrix.length; x++) {
			for(int y = 0; y < matrix[x].length; y++) {
				Formatter f = new Formatter();
				sb.append(f.format("[%03d]", matrix[x][y]).out().toString());
				f.close();
			}
			sb.append("\n");
		}
		
		log(sb.toString());
	}
	
	public static void log(String s) {
		System.out.println(s);
	}
	
	public static void log(Object o) {
		log(o.toString());
	}
	
	public static void header() {
		System.out.printf("-----Tests so far: %d/%d----\n", successes, counter-1);
	}
	
	public static void header(String header) {
		System.out.printf("-----%s : Tests so far: %d/%d----\n", header, successes, counter-1);
	}
}
