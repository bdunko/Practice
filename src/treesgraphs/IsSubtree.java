package treesgraphs;

import datastructures.BBinaryNode;
import testing.Test;

public class IsSubtree {

	private static <T> boolean treeEquals(BBinaryNode<T> tree1, BBinaryNode<T> tree2) {
		if(tree1 == null && tree2 == null)
			return true;
		else if (tree1 == null || tree2 == null)
			return false;
		
		if(!tree1.data.equals(tree2.data))
			return false;
		
		return treeEquals(tree1.left, tree2.left) && treeEquals(tree1.right, tree2.right);
	}
	
	public static <T> boolean isSubtree(BBinaryNode<T> larger, BBinaryNode<T> smaller) {
		if(larger == null || smaller == null)
			return false;
		
		if(treeEquals(larger, smaller))
			return true;
		
		return isSubtree(larger.left, smaller) || isSubtree(larger.right, smaller);
	}
	
	public static void main(String[] args) {
		Test.header("IsSubtree");
		BBinaryNode<Integer> tree1 = BBinaryNode.buildSampleTree();
		BBinaryNode<Integer> tree2 = BBinaryNode.buildSampleTree2();
		
		Test.header("treeEquals");
		Test.assertion(!treeEquals(tree1, tree2));
		Test.assertion(treeEquals(tree1, tree1));
		Test.assertion(treeEquals(tree2, tree2));
		Test.assertion(treeEquals(tree1.left, tree1.left));
		Test.assertion(treeEquals(tree1.right, tree1.right));
		BBinaryNode<Integer> tree1broken = BBinaryNode.buildSampleTree();
		Test.assertion(treeEquals(tree1, tree1broken));
		tree1broken.left.left.left = null;
		Test.assertion(!treeEquals(tree1, tree1broken));
	
		Test.header("isSubtree");
		Test.assertion(isSubtree(tree1, tree1));
		Test.assertion(isSubtree(tree1, tree1.left.left));
		Test.assertion(isSubtree(tree1, tree1.right));
		Test.assertion(!isSubtree(tree1, tree2));
		Test.assertion(isSubtree(tree2, tree2.left.left.right));
		Test.assertion(isSubtree(tree2, tree2.left));
		Test.assertion(isSubtree(tree2, tree2.left.left.left.left.right));
		Test.assertion(isSubtree(tree2, tree2.left.right));
		Test.assertion(!isSubtree(tree2, tree1.left));
		Test.assertion(!isSubtree(tree2, tree1.right));
		
		Test.results();
	}
}
