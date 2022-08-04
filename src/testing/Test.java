package testing;

import java.util.Arrays;
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
import arraysstrings.SubarraySum;
import arraysstrings.URLify;
import bitmanip.BinaryToString;
import bitmanip.BitBasics;
import bitmanip.Conversion;
import bitmanip.DrawLine;
import bitmanip.FlipBitToWin;
import bitmanip.Insertion;
import bitmanip.NextNumber;
import bitmanip.PairwiseSwap;
import bitmanip.PowersOfTwo;
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
import recursiondynamic.BooleanEvaluation;
import recursiondynamic.Coins;
import recursiondynamic.Fibonacci;
import recursiondynamic.MagicIndex;
import recursiondynamic.NQueens;
import recursiondynamic.PaintFill;
import recursiondynamic.Parens;
import recursiondynamic.Pathfinding;
import recursiondynamic.Permutations;
import recursiondynamic.PowerSet;
import recursiondynamic.RecursiveMultiply;
import recursiondynamic.RobotInAGrid;
import recursiondynamic.StackOfBoxes;
import recursiondynamic.TowersOfHanoi;
import recursiondynamic.TripleStep;
import stacksqueues.AnimalShelter;
import stacksqueues.QueueViaStacks;
import stacksqueues.SetOfStacks;
import stacksqueues.SortStack;
import stacksqueues.StackMin;
import stacksqueues.ThreeInOne;
import treesgraphs.BSTSequences;
import treesgraphs.BinaryTraversals;
import treesgraphs.BinaryTreeHeight;
import treesgraphs.BuildOrder;
import treesgraphs.DepthLists;
import treesgraphs.FirstCommonAncestor;
import treesgraphs.GraphTraversals;
import treesgraphs.InorderSuccessor;
import treesgraphs.IsSubtree;
import treesgraphs.MinimalTree;
import treesgraphs.PathBetweenNodes;
import treesgraphs.PathsWithSum;
import treesgraphs.RandomBST;
import treesgraphs.TreeBalanced;
import treesgraphs.VerifyBST;
import uncategorized.IsPrime;

//Simple unit testing framework
public class Test {
	private static int testCounter = 1;
	private static int successCounter = 0;
	
	private static class Timer {
		private static long startTime;
		private boolean started;
		
		private Timer() {
			startTime = 0;
			started = false;
		}
		
		public void start() {
			startTime = System.nanoTime();
			started = true;
		}
		
		public void reportAndReset() {
			if(!started) {
				System.out.println("Timer never started!");
			} else 
			{
				long time = System.nanoTime() - startTime;
				System.out.printf("Timer ran for: %d ms\n", time/1000000L);
				startTime = 0;
			}
		}
	}
	
	private static Timer TIMER = new Test.Timer();
	
	//Tests if given object is null.
	public static void isNull(Object a) {
		boolean success = a == null;
		System.out.printf("Test %6d: %s - Object was %s.\n", testCounter, success ? "SUCCESS" : "FAILURE", success ? "null" : "not null");
		increment(success);
	}
	
	//Tests if given object is not null.
	public static void notNull(Object a) {
		boolean success = a != null;
		System.out.printf("Test %6d: %s - Object was %s.\n", testCounter, success ? "SUCCESS" : "FAILURE", success ? "not null" : "null");
		increment(success);
	}
	
	//Tests if given statement is true.
	public static void assertion(boolean b) {
		System.out.printf("Test %6d: %s - Assertion was %b.\n", testCounter, b ? "SUCCESS" : "FAILURE", b);
		increment(b);
	}
	
	public static void fail(String msg) {
		System.out.printf("Test %6d: FAILURE - %s\n", testCounter, msg);
		increment(false);
	}
	
	public static void success(String msg) {
		System.out.printf("Test %6d: SUCCESS - %s\n", testCounter, msg);
		increment(true);
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
	
	public static void log(int[] array) {
		log(Arrays.toString(array));
	}
	
	public static <T> void log(T[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0; i < array.length; i++) {
			sb.append(array[i].toString()).append(",");
		}
		if(sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		System.out.println(sb.toString());
	}
	
	public static void timerStart() {
		TIMER.start();
	}
	
	public static void timerReportAndReset() {
		TIMER.reportAndReset();
	}
	
	//List of all classes to be tested
	//Each class must have a main method which contains its tests
	@SuppressWarnings("rawtypes")
	private final static Class[] classes = {
			//strings/arrays
			IsUnique.class, MatrixRotation.class, MatrixZero.class, OneAway.class, PalindromePermutation.class,
			StringPermutation.class, StringCompression.class, StringRotation.class, URLify.class, BinarySearch.class,
			SubarraySum.class,
			
			//linked lists
			DeleteMiddle.class, KthToLast.class, ListIntersection.class, LoopDetection.class, ListPalindrome.class,
			LoopDetection.class, ListPartition.class, RemoveDuplicates.class, SumLists.class,
			
			//stacks and queues
			SetOfStacks.class, SortStack.class, StackMin.class, ThreeInOne.class, QueueViaStacks.class, 
			AnimalShelter.class,
			
			//graphs and trees
			BinaryTraversals.class, GraphTraversals.class, PathBetweenNodes.class, VerifyBST.class, BinaryTreeHeight.class,
			MinimalTree.class, DepthLists.class, TreeBalanced.class, InorderSuccessor.class, BuildOrder.class, 
			FirstCommonAncestor.class, BSTSequences.class, IsSubtree.class, RandomBST.class, PathsWithSum.class,
			
			//bit manipulation
			BitBasics.class, BinaryToString.class, FlipBitToWin.class, Insertion.class, NextNumber.class,
			Conversion.class, PairwiseSwap.class, PowersOfTwo.class, DrawLine.class,
			
			//recursion and dynamic programming
			BooleanEvaluation.class, Coins.class, Fibonacci.class, MagicIndex.class, NQueens.class,
			PaintFill.class, Parens.class, Pathfinding.class, Permutations.class, PowerSet.class,
			RecursiveMultiply.class, RobotInAGrid.class, StackOfBoxes.class, TowersOfHanoi.class, TripleStep.class,
			
			//data structures
			BArrayList.class, BHashMap.class, BHashSet.class, BLinkedList.class, BLinkedNode.class, 
			BStringBuilder.class, BDeque.class, BMinHeap.class, BMaxHeap.class,
			
			//uncategorized
			IsPrime.class
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
