package treesgraphs;

import datastructures.BBinaryNode;
import testing.Test;

//Verify that a given tree fulfills the BST property; where all nodes are larger than their left child and smaller than their right
public class VerifyBST {

	//returns true if all nodes in the subtree starting at and including the given node are smaller than the given value
	private static <T extends Comparable<T>> boolean subtreeLessThan(BBinaryNode<T> tree, T value) {
		if(tree == null)
			return true;
		
		if(tree.data.compareTo(value) >= 0)
			return false;
		
		if(!subtreeLessThan(tree.left, value) || !subtreeLessThan(tree.right, value))
			return false;
		
		return true;
	}
	
	//returns true if all nodes in the subtree starting at and including the given node are larger or equal to the given value
	private static <T extends Comparable<T>> boolean subtreeGreaterThan(BBinaryNode<T> tree, T value) {
		if(tree == null)
			return true;
		
		if(tree.data.compareTo(value) < 0)
			return false;
		
		if(!subtreeGreaterThan(tree.left, value) || !subtreeGreaterThan(tree.right, value))
			return false;
		
		return true;
	}
	
	public static <T extends Comparable<T>> boolean verifyBST(BBinaryNode<T> tree) {
		if(tree == null)
			return true;
		
		//if left node exists, verify that all elements in left subtree are less than current
		if(tree.left != null && !subtreeLessThan(tree.left, tree.data))
			return false;
		
		//if right child exists, verify that all elements in right subtree are larger or equal to current
		if(tree.right != null && !subtreeGreaterThan(tree.right, tree.data))
			return false;
		
		//check if left and right subtrees are BSTs
		if(!verifyBST(tree.left) || !verifyBST(tree.right))
			return false;
		
		return true;
	}
	
	public static boolean verifyBST2Helper(BBinaryNode<Integer> current, int min, int max) {
		if(current == null)
			return true;
		if(current.data < min)
			return false;
		if(current.data >= max)
			return false;
		
		return verifyBST2Helper(current.left, min, current.data) && verifyBST2Helper(current.right, current.data, max);
	}
	
	public static boolean verifyBST2(BBinaryNode<Integer> tree) {
		return verifyBST2Helper(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	
	public static void main(String[] args) {
		Test.header("VerifyBST");
		
		Test.header("subtreeLessThan/subtreeGreaterThan");
		BBinaryNode<Integer> tree = new BBinaryNode<Integer>(10);
		Test.assertion(subtreeLessThan(tree, 11));
		Test.assertion(subtreeGreaterThan(tree, 9));
		tree.left = new BBinaryNode<Integer>(4);
		Test.assertion(!subtreeGreaterThan(tree, 9));
		tree.left.left = new BBinaryNode<Integer>(1);
		Test.assertion(subtreeGreaterThan(tree, 0));
		tree.left.right = new BBinaryNode<Integer>(7);
		tree.left.left.left = new BBinaryNode<Integer>(0);
		Test.assertion(subtreeGreaterThan(tree, 0));
		Test.assertion(!subtreeGreaterThan(tree, 1));
		tree.left.left.right = new BBinaryNode<Integer>(2);
		tree.left.right.left = new BBinaryNode<Integer>(6);
		tree.left.right.right = new BBinaryNode<Integer>(9);
		Test.assertion(subtreeLessThan(tree, 11));
		Test.assertion(subtreeGreaterThan(tree, -8));
		tree.left.right.right.left = new BBinaryNode<Integer>(15);
		Test.assertion(!subtreeLessThan(tree, 11));
		Test.assertion(!subtreeGreaterThan(tree, 9));
		
		
		Test.header("verifyBST");
		tree = new BBinaryNode<Integer>(10);
		Test.assertion(verifyBST(tree));
		
		tree.left = new BBinaryNode<Integer>(11);
		Test.assertion(!verifyBST(tree));
		tree.left = new BBinaryNode<Integer>(5);
		Test.assertion(verifyBST(tree));
		tree.right = new BBinaryNode<Integer>(9);
		Test.assertion(!verifyBST(tree));
		tree.right = new BBinaryNode<Integer>(10);
		Test.assertion(verifyBST(tree));
		tree.right = new BBinaryNode<Integer>(15);
		Test.assertion(verifyBST(tree));
		
		tree.left.left = new BBinaryNode<Integer>(6);
		Test.assertion(!verifyBST(tree));
		tree.left.left = new BBinaryNode<Integer>(3);
		Test.assertion(verifyBST(tree));
		
		tree.left.right = new BBinaryNode<Integer>(12);
		Test.assertion(!verifyBST(tree));
		tree.left.right = new BBinaryNode<Integer>(1);
		Test.assertion(!verifyBST(tree));
		tree.left.right = new BBinaryNode<Integer>(7);
		Test.assertion(verifyBST(tree));
		
		tree.right.left = new BBinaryNode<Integer>(16);
		Test.assertion(!verifyBST(tree));
		tree.right.left = new BBinaryNode<Integer>(13);
		Test.assertion(verifyBST(tree));
		
		tree.right.right = new BBinaryNode<Integer>(13);
		Test.assertion(!verifyBST(tree));
		tree.right.right = new BBinaryNode<Integer>(15);
		Test.assertion(verifyBST(tree));
		tree.right.right = new BBinaryNode<Integer>(19);
		Test.assertion(verifyBST(tree));
		
		Test.header("V2");
		tree = new BBinaryNode<Integer>(10);
		Test.assertion(verifyBST2(tree));
		
		tree.left = new BBinaryNode<Integer>(11);
		Test.assertion(!verifyBST2(tree));
		tree.left = new BBinaryNode<Integer>(5);
		Test.assertion(verifyBST2(tree));
		tree.right = new BBinaryNode<Integer>(9);
		Test.assertion(!verifyBST2(tree));
		tree.right = new BBinaryNode<Integer>(10);
		Test.assertion(verifyBST2(tree));
		tree.right = new BBinaryNode<Integer>(15);
		Test.assertion(verifyBST2(tree));
		
		tree.left.left = new BBinaryNode<Integer>(6);
		Test.assertion(!verifyBST2(tree));
		tree.left.left = new BBinaryNode<Integer>(3);
		Test.assertion(verifyBST2(tree));
		
		tree.left.right = new BBinaryNode<Integer>(12);
		Test.assertion(!verifyBST2(tree));
		tree.left.right = new BBinaryNode<Integer>(1);
		Test.assertion(!verifyBST2(tree));
		tree.left.right = new BBinaryNode<Integer>(7);
		Test.assertion(verifyBST2(tree));
		
		tree.right.left = new BBinaryNode<Integer>(16);
		Test.assertion(!verifyBST2(tree));
		tree.right.left = new BBinaryNode<Integer>(13);
		Test.assertion(verifyBST2(tree));
		
		tree.right.right = new BBinaryNode<Integer>(13);
		Test.assertion(!verifyBST2(tree));
		tree.right.right = new BBinaryNode<Integer>(15);
		Test.assertion(verifyBST2(tree));
		tree.right.right = new BBinaryNode<Integer>(19);
		Test.assertion(verifyBST2(tree));
		
		Test.results();
	}
}
