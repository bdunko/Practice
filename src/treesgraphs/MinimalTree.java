package treesgraphs;

import datastructures.BBinaryNode;
import testing.Test;

//Given a sorted array of integer elements in ascending order
//Create a BST with minimum height
public class MinimalTree {

	private enum Child {
		LEFT, RIGHT
	}
	
	//recursive helper for minimalTree
	//Acts similarly to a binary search, creating nodes as it goes
	private static void minTreeHelper(int[] array, int low, int high, BBinaryNode<Integer> current, Child child) {
		if(low > high)
			return;
		
		int middle = (low + high) / 2;
		
		if(child == Child.LEFT) 
			current.left = new BBinaryNode<Integer>(array[middle]);
		else
			current.right = new BBinaryNode<Integer>(array[middle]);
		
		BBinaryNode<Integer> next = (child == Child.LEFT ? current.left : current.right);
		
		minTreeHelper(array, low, middle-1, next, Child.LEFT);
		minTreeHelper(array, middle+1, high, next, Child.RIGHT);
	}
	
	//Creates a minimal BST given an array of ascending elements
	//Time: O(N) where N is number of nodes in tree
	public static BBinaryNode<Integer> minimalTree(int[] array) {
		if(array.length == 0)
			return null;
		
		int middle = array.length/2;
		
		BBinaryNode<Integer> root = new BBinaryNode<Integer>(array[middle]);
		
		if(array.length == 1)
			return root;
		
		minTreeHelper(array, 0, middle-1, root, Child.LEFT);
		minTreeHelper(array, middle+1, array.length-1, root, Child.RIGHT);
		
		return root;
	}
	
	public static void main(String[] args ) {
		Test.header("MinimalTree");
		int[] even = {1, 2, 3, 4, 5, 6, 7, 8};
		int[] even2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] odd = {1, 2, 3, 4, 5, 6, 7};
		int[] odd2 = {1, 2, 3};
		
		BBinaryNode<Integer> tree1 = minimalTree(even);
		Test.equals(BinaryTreeHeight.binaryTreeHeight(tree1), 4);
		Test.assertion(VerifyBST.verifyBST(tree1));
		Test.equals(tree1.data, 5);
		Test.equals(tree1.left.data, 2);
		Test.equals(tree1.left.left.data, 1);
		Test.equals(tree1.left.right.data, 3);
		Test.equals(tree1.left.right.right.data, 4);
		Test.equals(tree1.right.data, 7);
		Test.equals(tree1.right.left.data, 6);
		Test.equals(tree1.right.right.data, 8);
		
		BBinaryNode<Integer> tree2 = minimalTree(even2);
		Test.equals(BinaryTreeHeight.binaryTreeHeight(tree2), 4);
		Test.assertion(VerifyBST.verifyBST(tree2));
		
		BBinaryNode<Integer> tree3 = minimalTree(odd);
		Test.equals(BinaryTreeHeight.binaryTreeHeight(tree3), 3);
		Test.assertion(VerifyBST.verifyBST(tree3));
		Test.equals(tree3.data, 4);
		Test.equals(tree3.left.data, 2);
		Test.equals(tree3.right.data, 6);
		Test.equals(tree3.left.left.data, 1);
		Test.equals(tree3.left.right.data, 3);
		Test.equals(tree3.right.left.data, 5);
		Test.equals(tree3.right.right.data, 7);
		
		BBinaryNode<Integer> tree4 = minimalTree(odd2);
		Test.equals(BinaryTreeHeight.binaryTreeHeight(tree4), 2);
		Test.assertion(VerifyBST.verifyBST(tree4));
		Test.equals(tree4.data, 2);
		Test.equals(tree4.left.data, 1);
		Test.equals(tree4.right.data, 3);
		
		Test.results();
	}
}
