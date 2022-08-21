package sortsearch;

import testing.Test;

//Implement a method track which accepts integers from a stream
//And a method rankOf which returns the number of integers smaller than or equal to
//the given integer from the integers passed so far to track
public class RankFromStream {

	private static class Node {
		int value;
		int nodes_to_left;
		Node left, right;
		
		public Node(int value) {
			this.value = value;
			this.nodes_to_left = 0;
			this.left = null;
			this.right = null;
		}
		
		private void insert(int n) {
			if(n >= value) { //insert in right subtree
				//create node if no right child, otherwise recur down
				if(right == null) 
					right = new Node(n);
				else 
					right.insert(n);
			} else {
				nodes_to_left++; //increment rank
				
				//create node if no left child, otherwise recur down
				if(left == null)
					left = new Node(n);
				else
					left.insert(n);
			}
		}
	}
	
	private static Node root;
	
	private static void track(int n) {
		if(root == null) 
			root = new Node(n);
		else 
			root.insert(n);
	}
	
	private static int rankOf(int n) {
		if(root == null)
			return 0;
		
		Node current = root;
		int rank = 0;
		
		//Iterate over binary search tree
		//Compare current value to n
		//if larger or equal, the current node and all elements in left subtree are smaller than n, so add that to rank and check right subtree
		//otherwise check left subtree
		//continue until reaching null
		while(current != null) {
			if(n >= current.value) {
				rank += current.nodes_to_left + 1;
				current = current.right;
			} else {
				current = current.left;
			}
		}
		
		return rank;
	}
	
	public static void main(String[] args) {
		root = null;
		
		Test.header("RankFromStream");
		
		Test.equals(rankOf(1), 0);
		Test.equals(rankOf(5), 0);
		Test.equals(rankOf(6), 0);
		
		track(5);
		Test.equals(rankOf(1), 0);
		Test.equals(rankOf(5), 1);
		Test.equals(rankOf(8), 1);
		
		track(8);
		Test.equals(rankOf(1), 0);
		Test.equals(rankOf(5), 1);
		Test.equals(rankOf(6), 1);
		Test.equals(rankOf(8), 2);
		Test.equals(rankOf(9), 2);
		
		track(12);
		Test.equals(rankOf(0), 0);
		Test.equals(rankOf(8), 2);
		Test.equals(rankOf(9), 2);
		Test.equals(rankOf(13), 3);
		
		track(15);
		Test.equals(rankOf(3), 0);
		Test.equals(rankOf(5), 1);
		Test.equals(rankOf(13), 3);
		Test.equals(rankOf(15), 4);
		Test.equals(rankOf(2000), 4);
		
		track(0);
		Test.equals(rankOf(3), 1);
		Test.equals(rankOf(5), 2);
		Test.equals(rankOf(13), 4);
		Test.equals(rankOf(15), 5);
		Test.equals(rankOf(2000), 5);
		
		track(12);
		Test.equals(rankOf(3), 1);
		Test.equals(rankOf(5), 2);
		Test.equals(rankOf(8), 3);
		Test.equals(rankOf(13), 5);
		Test.equals(rankOf(15), 6);
		Test.equals(rankOf(17), 6);
		Test.equals(rankOf(2000), 6);
		
		track(2001);
		Test.equals(rankOf(3), 1);
		Test.equals(rankOf(5), 2);
		Test.equals(rankOf(8), 3);
		Test.equals(rankOf(13), 5);
		Test.equals(rankOf(15), 6);
		Test.equals(rankOf(2000), 6);
		Test.equals(rankOf(2002), 7);
		
		Test.results();
	}
}
