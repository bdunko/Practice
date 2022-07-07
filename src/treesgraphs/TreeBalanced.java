package treesgraphs;

import datastructures.BBinaryNode;
import testing.Test;

//Determine if a binary tree is balanced
//A tree is balanced if the heights of the two subtrees of any node differ by no more than one
public class TreeBalanced {
	
	//returns the height of the tree starting at the given node
	public static <T> int treeHeight(BBinaryNode<T> node, int depth) {		
		if(node == null) 
			return depth;
		
		return Math.max(treeHeight(node.left, depth+1), treeHeight(node.right, depth+1));
	}
	
	public static <T> boolean isTreeBalanced(BBinaryNode<T> root) {
		if(root == null)
			return true;
		
		//first, check left and right of current node
		int leftSubtreeHeight = treeHeight(root.left, 0);
		int rightSubtreeHeight = treeHeight(root.right, 0);
		
		if(Math.abs(leftSubtreeHeight - rightSubtreeHeight) > 1)
			return false;
		
		//check if tree starting from left child is balanced
		if(!isTreeBalanced(root.left))
			return false;
		
		//check if tree starting from right child is balanced
		if(!isTreeBalanced(root.right))
			return false;
		
		return true;
	}
	
	public static <T> int checkHeight(BBinaryNode<T> current) {
		if(current == null)
			return -1;
		
		//get height of left subtree
		int leftHeight = checkHeight(current.left);
		if(leftHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		
		//get height of right subtree
		int rightHeight = checkHeight(current.right);
		if(rightHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		
		//check if height differs
		int diff = leftHeight - rightHeight;
		if(Math.abs(diff) > 1)
			return Integer.MIN_VALUE;
		
		//return height of this subtree
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	public static <T> boolean isTreeBalanced2(BBinaryNode<T> root) {
		return checkHeight(root) != Integer.MIN_VALUE;
	}
	
	public static void main(String[] args) {
		Test.header("TreeBalanced");
		
		BBinaryNode<Integer> tree = BBinaryNode.buildSampleTree();
		Test.assertion(isTreeBalanced(tree));
		tree.left.left = null;
		Test.assertion(!isTreeBalanced(tree));
		tree = BBinaryNode.buildSampleTree();
		tree.right.right = null;
		Test.assertion(!isTreeBalanced(tree));
		
		tree = BBinaryNode.buildSampleTree();
		tree.left.left.left.left = new BBinaryNode<Integer>(100);
		Test.assertion(!isTreeBalanced(tree));
		tree.left.left.right = new BBinaryNode<Integer>(101);
		Test.assertion(isTreeBalanced(tree));
		tree.left.left.left.left.right = new BBinaryNode<Integer>(102);
		Test.assertion(!isTreeBalanced(tree));
		
		tree = BBinaryNode.buildSampleTree();
		tree.left.right.right.right = new BBinaryNode<Integer>(103);
		Test.assertion(isTreeBalanced(tree));
		tree.left.right.right.right.left = new BBinaryNode<Integer>(104);
		Test.assertion(!isTreeBalanced(tree));
		
		tree = BBinaryNode.buildSampleTree();
		tree.right.right.right.right = new BBinaryNode<Integer>(105);
		Test.assertion(!isTreeBalanced(tree));
		tree.right.right.left = new BBinaryNode<Integer>(106);
		Test.assertion(isTreeBalanced(tree));
		
		
		Test.header("V2");
		tree = BBinaryNode.buildSampleTree();
		Test.assertion(isTreeBalanced2(tree));
		tree.left.left = null;
		Test.assertion(!isTreeBalanced2(tree));
		tree = BBinaryNode.buildSampleTree();
		tree.right.right = null;
		Test.assertion(!isTreeBalanced2(tree));
		
		tree = BBinaryNode.buildSampleTree();
		tree.left.left.left.left = new BBinaryNode<Integer>(100);
		Test.assertion(!isTreeBalanced2(tree));
		tree.left.left.right = new BBinaryNode<Integer>(101);
		Test.assertion(isTreeBalanced2(tree));
		tree.left.left.left.left.right = new BBinaryNode<Integer>(102);
		Test.assertion(!isTreeBalanced2(tree));
		
		tree = BBinaryNode.buildSampleTree();
		tree.left.right.right.right = new BBinaryNode<Integer>(103);
		Test.assertion(isTreeBalanced2(tree));
		tree.left.right.right.right.left = new BBinaryNode<Integer>(104);
		Test.assertion(!isTreeBalanced2(tree));
		
		tree = BBinaryNode.buildSampleTree();
		tree.right.right.right.right = new BBinaryNode<Integer>(105);
		Test.assertion(!isTreeBalanced2(tree));
		tree.right.right.left = new BBinaryNode<Integer>(106);
		Test.assertion(isTreeBalanced2(tree));
		
		Test.results();
	}
	
}
