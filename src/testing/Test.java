package testing;

import java.util.Formatter;

import arraysstrings.BinarySearch;
import arraysstrings.IsUnique;
import arraysstrings.MatrixRotation;
import arraysstrings.MatrixZero;
import arraysstrings.OneAway;
import arraysstrings.PalindromePermutation;
import arraysstrings.StringCompression;
import arraysstrings.StringPermutation;
import arraysstrings.StringRotation;
import arraysstrings.URLify;
import datastructures.BArrayList;
import datastructures.BDeque;
import datastructures.BHashMap;
import datastructures.BHashSet;
import datastructures.BLinkedList;
import datastructures.BLinkedNode;
import datastructures.BMaxHeap;
import datastructures.BMinHeap;
import datastructures.BStringBuilder;
import linkedlists.DeleteMiddle;
import linkedlists.KthToLast;
import linkedlists.ListIntersection;
import linkedlists.ListPalindrome;
import linkedlists.ListPartition;
import linkedlists.LoopDetection;
import linkedlists.RemoveDuplicates;
import linkedlists.SumLists;
import stacksqueues.AnimalShelter;
import stacksqueues.QueueViaStacks;
import stacksqueues.SetOfStacks;
import stacksqueues.SortStack;
import stacksqueues.StackMin;
import stacksqueues.ThreeInOne;
import treesgraphs.BinaryTraversals;
import treesgraphs.BinaryTreeHeight;
import treesgraphs.DepthLists;
import treesgraphs.GraphTraversals;
import treesgraphs.InorderSuccessor;
import treesgraphs.MinimalTree;
import treesgraphs.PathBetweenNodes;
import treesgraphs.TreeBalanced;
import treesgraphs.VerifyBST;

//Simple testing framework
public class Test {
	private static int testCounter = 1;
	private static int successCounter = 0;
	
	//Tests if given object is null.
	public static void isNull(Object a) {
		boolean success = a == null;
		System.out.printf("Test %6d: %s - Object was %s.\n", testCounter, success ? "SUCCESS" : "FAILURE", success ? "null" : "not null");
		increment(success);
	}
	
	//Tests if given statement is true.
	public static void assertion(boolean b) {
		System.out.printf("Test %6d: %s - Assertion was %b.\n", testCounter, b ? "SUCCESS" : "FAILURE", b);
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
		
		System.out.printf("Test %6d: %s - %s %s %s\n", testCounter, success ? "SUCCESS" : "FAILURE", (a == null ? "(NULL)" : a.toString()), success ? "=" : "!=", (b == null ? "(NULL)" : b.toString()));
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
		
		System.out.printf("Test %06d: %s - matrix1 %s matrix2\n", testCounter, success ? "SUCCESS" : "FAILURE", success ? "=" : "!=");
		increment(success);
	}
	
	//Internal helper method which increments test number and success number
	private static void increment(boolean success) {
		Test.testCounter++;
		if(success)
			successCounter++;
	}
	
	//Prints a nice header
	public static void header(String header) {
		System.out.printf("-----%s : Tests so far: %d/%d----\n", header, successCounter, testCounter-1);
	}
	
	//Prints the results of all tests to this point
	public static void results() {
		int nTests = testCounter - 1;
		System.out.printf("----------\n%d/%d tests succeeded.\n", successCounter, nTests);
		if(successCounter == nTests)
			System.out.printf("All tests pass!\n");
		else
			System.out.printf("%d tests failed...\n", nTests - successCounter);
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
	
	//List of all classes to be tested
	//Each class must have a main method which contains its tests
	@SuppressWarnings("rawtypes")
	private final static Class[] classes = {
			IsUnique.class, MatrixRotation.class, MatrixZero.class, OneAway.class, PalindromePermutation.class,
			StringPermutation.class, StringCompression.class, StringRotation.class, URLify.class, BinarySearch.class,
			
			DeleteMiddle.class, KthToLast.class, ListIntersection.class, LoopDetection.class, ListPalindrome.class,
			LoopDetection.class, ListPartition.class, RemoveDuplicates.class, SumLists.class,
			
			SetOfStacks.class, SortStack.class, StackMin.class, ThreeInOne.class, QueueViaStacks.class, 
			AnimalShelter.class,
			
			BinaryTraversals.class, GraphTraversals.class, PathBetweenNodes.class, VerifyBST.class, BinaryTreeHeight.class,
			MinimalTree.class, DepthLists.class, TreeBalanced.class, InorderSuccessor.class,
			
			BArrayList.class, BHashMap.class, BHashSet.class, BLinkedList.class, BLinkedNode.class, 
			BStringBuilder.class, BDeque.class, BMinHeap.class, BMaxHeap.class
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
			log("Failed to run tests... " + e.toString());
		}
		log("All done!");
	}
}
