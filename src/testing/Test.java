package testing;

import java.util.Formatter;

import chp1arraysstrings.IsUnique;
import chp1arraysstrings.MatrixRotation;
import chp1arraysstrings.MatrixZero;
import chp1arraysstrings.OneAway;
import chp1arraysstrings.PalindromePermutation;
import chp1arraysstrings.StringCompression;
import chp1arraysstrings.StringPermutation;
import chp1arraysstrings.StringRotation;
import chp1arraysstrings.URLify;
import chp2linkedlists.DeleteMiddle;
import chp2linkedlists.KthToLast;
import chp2linkedlists.ListIntersection;
import chp2linkedlists.ListPalindrome;
import chp2linkedlists.ListPartition;
import chp2linkedlists.LoopDetection;
import chp2linkedlists.RemoveDuplicates;
import chp2linkedlists.SumLists;
import datastructures.BArrayList;
import datastructures.BDeque;
import datastructures.BHashMap;
import datastructures.BHashSet;
import datastructures.BLinkedList;
import datastructures.BNode;
import datastructures.BStringBuilder;

//Simple testing framework
public class Test {
	private static int counter = 1;
	private static int successes = 0;
	
	//Tests if given object is null.
	public static void isNull(Object a) {
		boolean success = a == null;
		System.out.printf("Test %4d: %s - Object was %s.\n", counter, success ? "SUCCESS" : "FAILURE", success ? "null" : "not null");
		increment(success);
	}
	
	//Tests if given statement is true.
	public static void assertion(boolean b) {
		System.out.printf("Test %4d: %s - Assertion was %b.\n", counter, b ? "SUCCESS" : "FAILURE", b);
		increment(b);
	}
	
	//Tests equality between two objects.
	//Also tests equality between primitives thanks to autoboxing
	public static void equals(Object a, Object b) {
		boolean success;
		if(a == null || b == null)
			success = false;
		else 
			success = a.equals(b);
		
		System.out.printf("Test %4d: %s - %s %s %s\n", counter, success ? "SUCCESS" : "FAILURE", (a == null ? "(NULL)" : a.toString()), success ? "=" : "!=", (b == null ? "(NULL)" : b.toString()));
		increment(success);
	}
	
	//Tests equality between two matrices
	public static void equals(int[][] m1, int[][]m2) {
		boolean success = true;
		
		for(int row = 0; row < m1.length; row++) {
			
			for(int col = 0; col < m1[row].length; col++) {
				if(m1.length != m2.length || 
						m1[row].length != m2[row].length ||
						m1[row][col] != m2[row][col]) {
					System.out.printf("%d != %d at %d, %d\n", m1[row][col], m2[row][col], row, col);
					success = false;
				}
			}
		}
		
		System.out.printf("Test %4d: %s - matrix1 %s matrix2\n", counter, success ? "SUCCESS" : "FAILURE", success ? "=" : "!=");
		increment(success);
	}
	
	//Internal helper method which increments test number and success number
	private static void increment(boolean success) {
		Test.counter++;
		if(success)
			successes++;
	}
	
	//Prints a nice header
	public static void header() {
		System.out.printf("-----Tests so far: %d/%d----\n", successes, counter-1);
	}
	
	//Prints a nice header given a test name
	public static void header(String header) {
		System.out.printf("-----%s : Tests so far: %d/%d----\n", header, successes, counter-1);
	}
	
	//Prints the results of all tests to this point
	public static void results() {
		int nTests = counter - 1;
		System.out.printf("----------\n%d/%d tests succeeded.\n", successes, nTests);
		if(successes == nTests)
			System.out.printf("All tests pass!\n");
		else
			System.out.printf("%d tests failed...\n", nTests - successes);
	}
	
	//Outputs a matrix to stdout
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
	
	//Outputs a string to stdout
	public static void log(String s) {
		System.out.println(s);
	}
	
	//Outputs an object to stdout
	public static void log(Object o) {
		log(o.toString());
	}
	
	//Resets the test counters
	public static void reset() {
		counter = 1;
		successes = 0;
	}
	
	//List of all classes to be tested
	//Each class must have a main method which contains its tests
	@SuppressWarnings("rawtypes")
	private final static Class[] classes = {
			IsUnique.class, MatrixRotation.class, MatrixZero.class, OneAway.class, PalindromePermutation.class,
			StringPermutation.class, StringCompression.class, StringRotation.class, URLify.class,
			
			DeleteMiddle.class, KthToLast.class, ListIntersection.class, LoopDetection.class, ListPalindrome.class,
			LoopDetection.class, ListPartition.class, RemoveDuplicates.class, SumLists.class,
			
			BArrayList.class, BHashMap.class, BHashSet.class, BLinkedList.class, BNode.class, 
			BStringBuilder.class, BDeque.class
			};
	
	//Runs all tests
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] a) {
		log("Running all tests!");
		try {
			final Object[] args = new Object[1];
			args[0] = new String[] {};
			
			for(Class clazz : classes) {
				clazz.getMethod("main", String[].class).invoke(null, args);
			}
			
		} catch (Exception e) {
			log("Failed to run all tests... " + e.toString());
		}
		log("All done!");
	}
}
