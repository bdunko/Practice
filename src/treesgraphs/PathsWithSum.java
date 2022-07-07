package treesgraphs;

import java.util.LinkedList;
import java.util.List;

import datastructures.BBinaryNode;
import testing.Test;

public class PathsWithSum {

	private static int pathsHelper(BBinaryNode<Integer> current, int sum, List<Integer> paths) {
		if(current == null)
			return 0;

		//add current to all existing paths
		for(int i = 0; i < paths.size(); i++) {
			paths.set(i, paths.get(i) + current.data);
		}
		//add current as start of new path
		paths.add(current.data);
		
		//check how many paths are correct
		int correctSums = 0;
		for(Integer path : paths) {
			if(path == sum)
				correctSums++;
		}
		
		//now repeat for left and right child
		correctSums += pathsHelper(current.left, sum, new LinkedList<Integer>(paths));
		correctSums += pathsHelper(current.right, sum, new LinkedList<Integer>(paths));
		
		return correctSums;
	}
	
	public static int pathsWithSum(BBinaryNode<Integer> root, int sum) {
		return pathsHelper(root, sum, new LinkedList<Integer>());
	}
	
	public static void main(String[] args) {
		Test.header("PathsWithSum");
		
		BBinaryNode<Integer> tree1 = BBinaryNode.buildSampleTree();
		
		Test.equals(pathsWithSum(tree1, -100), 0);
		Test.equals(pathsWithSum(tree1, 3), 2);
		Test.equals(pathsWithSum(tree1, 7), 3);
		Test.equals(pathsWithSum(tree1, 10), 3);
		
		//            -5
		//      5            3
		//    0   -7              2
		//            2
		BBinaryNode<Integer> tree2 = new BBinaryNode<Integer>(-5);
		tree2.left = new BBinaryNode<Integer>(5);
		tree2.left.right = new BBinaryNode<Integer>(-7);
		tree2.left.right.left = new BBinaryNode<Integer>(2);
		tree2.left.left = new BBinaryNode<Integer>(0);
		tree2.right = new BBinaryNode<Integer>(3);
		tree2.right.right = new BBinaryNode<Integer>(2);
		
		Test.equals(pathsWithSum(tree2, 0), 5);
		Test.equals(pathsWithSum(tree2, -5), 3);
		Test.equals(pathsWithSum(tree2, 5), 3);
		
		Test.results();
	}
}
