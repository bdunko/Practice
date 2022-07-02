package treesgraphs;

import datastructures.BBinaryNode;
import testing.Test;

//Find and return the first common ancestor of two given binary tree nodes
//Do not store additional nodes in a data structure
public class FirstCommonAncestor {

	//determines if a node exists anywhere in the given tree
	private static <T> boolean treeContains(BBinaryNode<T> tree, BBinaryNode<T> node1) {
		if(tree == null)
			return false;
		
		if(tree == node1)
			return true;
		
		if(treeContains(tree.left, node1))
			return true;
		
		if(treeContains(tree.right, node1))
			return true;
		
		return false;
	}
	
	//Finds the first common ancestor of two binary tree nodes
	//By (starting from the root) walking down the tree until the nodes are not
	//in the same subtree (left or right) of the current node
	//Then the current node is the first common ancestor.
	public static <T> BBinaryNode<T> firstCommonAncestor(BBinaryNode<T> root, BBinaryNode<T> node1, BBinaryNode<T> node2) {
		if(node1 == node2)
			return node1; 
		
		//if either node doesn't actually exist in the tree
		if(!treeContains(root, node1) || !treeContains(root, node2))
			return null;
		
		BBinaryNode<T> fca = root; //first common ancestor
		boolean leftContainsBoth = treeContains(fca.left, node1) && treeContains(fca.left, node2);
		boolean rightContainsBoth = treeContains(fca.right, node1) && treeContains(fca.right, node2);
		
		while(leftContainsBoth || rightContainsBoth) {
			if(leftContainsBoth)
				fca = fca.left;
			if(rightContainsBoth)
				fca = fca.right;
				
			leftContainsBoth = treeContains(fca.left, node1) && treeContains(fca.left, node2);
			rightContainsBoth = treeContains(fca.right, node1) && treeContains(fca.right, node2);
		}
		
		return fca;
	}
	
	public static void main(String[] args) {
		Test.header("FirstCommonAncestor");
		
		Test.header("treeContains");
		BBinaryNode<Integer> tree = BBinaryNode.buildSampleTree2();
		Test.assertion(treeContains(tree, tree.getNode(8)));
		Test.assertion(treeContains(tree, tree.getNode(18)));
		Test.assertion(treeContains(tree, tree.getNode(15)));
		Test.assertion(treeContains(tree, tree.getNode(3)));
		Test.assertion(treeContains(tree, tree.getNode(2)));
		Test.assertion(!treeContains(tree.left.left, tree.getNode(1)));
		Test.assertion(!treeContains(tree.left.left, tree.getNode(4)));
		Test.assertion(!treeContains(tree.left.left, tree.getNode(2)));
		Test.assertion(treeContains(tree.left.left, tree.getNode(5)));
		Test.assertion(treeContains(tree.left.left, tree.getNode(11)));
		Test.assertion(!treeContains(tree.left.right, tree.getNode(3)));
		Test.assertion(!treeContains(tree.left.right, tree.getNode(12)));
		Test.assertion(treeContains(tree.left.right, tree.getNode(16)));
		
		Test.equals(firstCommonAncestor(tree, tree.getNode(5), tree.getNode(10)).data, 3);
		Test.equals(firstCommonAncestor(tree, tree.getNode(2), tree.getNode(2)).data, 2);
		Test.equals(firstCommonAncestor(tree, tree.getNode(8), tree.getNode(18)).data, 2);
		Test.equals(firstCommonAncestor(tree, tree.getNode(3), tree.getNode(2)).data, 2);
		Test.equals(firstCommonAncestor(tree, tree.getNode(11), tree.getNode(12)).data, 10);
		Test.equals(firstCommonAncestor(tree, tree.getNode(12), tree.getNode(8)).data, 3);
		Test.equals(firstCommonAncestor(tree, tree.getNode(15), tree.getNode(18)).data, 14);
		Test.equals(firstCommonAncestor(tree, tree.getNode(4), tree.getNode(8)).data, 2);
		Test.equals(firstCommonAncestor(tree, tree.getNode(9), tree.getNode(11)).data, 6);
		Test.equals(firstCommonAncestor(tree, tree.getNode(10), tree.getNode(8)).data, 3);
		Test.equals(firstCommonAncestor(tree, tree.getNode(12), tree.getNode(18)).data, 2);
		
		Test.results();
	}
}
