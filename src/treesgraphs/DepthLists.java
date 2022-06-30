package treesgraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import datastructures.BBinaryNode;
import testing.Test;

//Return a list which contains a linked list of each node at each depth in the tree, such that there is one linked list per depth in the tree.
public class DepthLists {
	
	//recursive helper function for getDepthLists
	//Basically just performs a full traversal of the tree, tracking depth as we go and adding current to appropriate list (creating it if needed)
	private static <T> void depthListHelper(List<List<BBinaryNode<T>>> depthLists, BBinaryNode<T> current, int depth) {
		if(current == null)
			return;
		
		if(depthLists.size() == depth) //if we need to create a new list (this is first node at a certain depth)
			depthLists.add(new LinkedList<BBinaryNode<T>>());
		
		depthLists.get(depth).add(current);
		
		depthListHelper(depthLists, current.left, depth+1);
		depthListHelper(depthLists, current.right, depth+1);
	}

	//Returns a list of lists, where each list contains all nodes at a certain depth
	//Time: O(N) where N is number of nodes in tree
	public static <T> List<List<BBinaryNode<T>>> getDepthLists(BBinaryNode<T> root) {
		if(root == null)
			return null;
		
		List<List<BBinaryNode<T>>> depthLists = new ArrayList<List<BBinaryNode<T>>>();
		
		depthListHelper(depthLists, root, 0);
		
		return depthLists;
	}
	
	//Testing helper function
	private static boolean listContains(List<BBinaryNode<Integer>> list, int value) {
		for(BBinaryNode<Integer> node : list) {
			if(node.data == value)
				return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Test.header("DepthLists");
		List<List<BBinaryNode<Integer>>> depthLists = getDepthLists(BBinaryNode.buildSampleTree());
		
		Test.equals(depthLists.size(), 4);
		Test.equals(depthLists.get(0).size(), 1);
		Test.equals(depthLists.get(1).size(), 2);
		Test.equals(depthLists.get(2).size(), 4);
		Test.equals(depthLists.get(3).size(), 5);
		Test.assertion(listContains(depthLists.get(0), 1));
		Test.assertion(listContains(depthLists.get(1), 2));
		Test.assertion(listContains(depthLists.get(1), 3));
		Test.assertion(listContains(depthLists.get(2), 4));
		Test.assertion(listContains(depthLists.get(2), 5));
		Test.assertion(listContains(depthLists.get(2), 6));
		Test.assertion(listContains(depthLists.get(2), 7));
		Test.assertion(listContains(depthLists.get(3), 8));
		Test.assertion(listContains(depthLists.get(3), 9));
		Test.assertion(listContains(depthLists.get(3), 10));
		Test.assertion(listContains(depthLists.get(3), 11));
		Test.assertion(listContains(depthLists.get(3), 12));
		
		Test.results();
	}
}
