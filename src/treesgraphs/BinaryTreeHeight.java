package treesgraphs;

import datastructures.BBinaryNode;
import testing.Test;

//Determine the height of a binary tree
public class BinaryTreeHeight {
	
	private static <T> int binaryTreeHeightHelper(BBinaryNode<T> current, int depth) {
		if(current == null)
			return depth;
		
		return Math.max(binaryTreeHeightHelper(current.left, depth+1), binaryTreeHeightHelper(current.right, depth+1));
	}
	
	public static <T> int binaryTreeHeight(BBinaryNode<T> root) {
		if(root == null)
			return 0;
		
		return binaryTreeHeightHelper(root, 0);
	}
	
	public static void main(String[] args) {
		Test.header("BinaryTreeHeight");
		
		BBinaryNode<Integer> root = new BBinaryNode<Integer>(0);
		Test.equals(binaryTreeHeight(root), 1);
		
		root.left = new BBinaryNode<Integer>(1);
		root.right = new BBinaryNode<Integer>(2);
		Test.equals(binaryTreeHeight(root), 2);
		
		root.left.left = new BBinaryNode<Integer>(3);
		root.left.right = new BBinaryNode<Integer>(4);
		root.right.left = new BBinaryNode<Integer>(5);
		root.right.right = new BBinaryNode<Integer>(6);
		Test.equals(binaryTreeHeight(root), 3);
		
		root.left.right.left = new BBinaryNode<Integer>(7);
		Test.equals(binaryTreeHeight(root), 4);
		
		root.left.left.left = new BBinaryNode<Integer>(8);
		root.left.left.right = new BBinaryNode<Integer>(9);
		root.left.right.left.right = new BBinaryNode<Integer>(10);
		Test.equals(binaryTreeHeight(root), 5);
		
		root.right.right.right = new BBinaryNode<Integer>(11);
		root.right.right.right.right = new BBinaryNode<Integer>(12);
		root.right.right.right.right.right = new BBinaryNode<Integer>(13);
		Test.equals(binaryTreeHeight(root), 6);
		
		Test.results();
	}
}
