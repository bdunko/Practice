package treesgraphs;

import java.util.LinkedList;
import java.util.List;

import datastructures.BBinaryNode;
import testing.Test;

//Given a BST, output all possible input arrays that would create that BST
public class BSTSequences {
	
	//recursive helper for bstSequences
	private static void sequenceHelper(List<List<Integer>> sequences, BBinaryNode<Integer> current, List<Integer> sequence, List<BBinaryNode<Integer>> nextPossibleNodes) {		
		//build sequence by adding ourself
		sequence.add(current.data);
		
		//remove ourself from list of possible nodes to go to next
		nextPossibleNodes.remove(current);
		
		//add children (if non-null) to list of next nodes to visit
		if(current.left != null) 
			nextPossibleNodes.add(current.left);
		if(current.right != null)
			nextPossibleNodes.add(current.right);
		
		//if there's nowhere else to go, sequence is complete; add to list of sequences
		if(nextPossibleNodes.size() == 0) { 
			sequences.add(sequence);
		} else { 
			//otherwise, branch off for each possible next node
			for(BBinaryNode<Integer> next : nextPossibleNodes) {
				sequenceHelper(sequences, next, new LinkedList<Integer>(sequence), new LinkedList<BBinaryNode<Integer>>(nextPossibleNodes));
			}
		}
	}
	
	//Determine all possible input arrays that would create the given bst
	//Start with the root.
	//Now, either child could come next. 
	//Then, any children of that child or the other child could come next, and so on
	//By determining every possible order the tree could be built, we get all input arrays that make this bst
	public static List<List<Integer>> bstSequences(BBinaryNode<Integer> bst) {
		if(bst == null)
			return null;
		
		List<BBinaryNode<Integer>> nextNodes = new LinkedList<BBinaryNode<Integer>>();
		nextNodes.add(bst);
		
		List<List<Integer>> sequences = new LinkedList<List<Integer>>();
		
		sequenceHelper(sequences, bst, new LinkedList<Integer>(), nextNodes);
		
		return sequences;
	}
	
	public static void main(String[] args) {
		Test.header("BSTSequences");
		
		BBinaryNode<Integer> root = new BBinaryNode<Integer>(10);
		List<List<Integer>> seqs = bstSequences(root);
		Test.equals(seqs.size(), 1);
		Test.assertion(seqs.contains(List.of(10)));
		
		BBinaryNode<Integer> simple = new BBinaryNode<Integer>(2);
		simple.left = new BBinaryNode<Integer>(1);
		simple.right = new BBinaryNode<Integer>(3);
		simple.left.left = new BBinaryNode<Integer>(0);
		seqs = bstSequences(simple);
		Test.equals(seqs.size(), 3);
		Test.assertion(seqs.contains(List.of(2, 1, 3, 0)));
		Test.assertion(seqs.contains(List.of(2, 1, 0, 3)));
		Test.assertion(seqs.contains(List.of(2, 3, 1, 0)));
		
		BBinaryNode<Integer> simple2 = new BBinaryNode<Integer>(5);
		simple2.left = new BBinaryNode<Integer>(2);
		simple2.right = new BBinaryNode<Integer>(10);
		simple2.left.left = new BBinaryNode<Integer>(0);
		simple2.left.right = new BBinaryNode<Integer>(3);
		seqs = bstSequences(simple2);
		Test.equals(seqs.size(), 8);
		Test.assertion(seqs.contains(List.of(5, 2, 10, 0, 3)));
		Test.assertion(seqs.contains(List.of(5, 2, 10, 3, 0)));
		Test.assertion(seqs.contains(List.of(5, 2, 0, 10, 3)));
		Test.assertion(seqs.contains(List.of(5, 2, 0, 3, 10)));
		Test.assertion(seqs.contains(List.of(5, 2, 3, 10, 0)));
		Test.assertion(seqs.contains(List.of(5, 2, 3, 0, 10)));
		Test.assertion(seqs.contains(List.of(5, 10, 2, 0, 3)));
		Test.assertion(seqs.contains(List.of(5, 10, 2, 3, 0)));
		
		Test.results();
	}
	
}
