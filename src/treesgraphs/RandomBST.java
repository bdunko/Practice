package treesgraphs;

import java.util.HashMap;
import java.util.Random;

import testing.Test;

//BSTTree class with a random function which returns a random value, with equal probability for all values
public class RandomBST<T extends Comparable<T>> {

	private class Node {
		public T data;
		public Node left, right, parent;
		public int nodesBelow;
		
		public Node(T data) {
			this(data, null, null, null, 0);
		}
		
		public Node(T data, Node left, Node right, Node parent, int nodesBelow) {
			this.data = data;
			this.left = left;
			this.right = right;
			this.parent = parent;
			this.nodesBelow = nodesBelow;
		}
	}
	
	private Node root;
	
	public RandomBST() {
		root = null;
	}
	
	public void insert(T elem) {
		Node newNode = new Node(elem);
		
		if(root == null) {
			root = newNode;
			return;
		}
		
		boolean inserted = false;
		Node current = root;
		
		while(!inserted) {
			current.nodesBelow++;
			if(elem.compareTo(current.data) >= 0) { //if new element is larger or equal, go to the right
				if(current.right == null) {
					current.right = newNode;
					newNode.parent = current;
					inserted = true;
				}
				current = current.right;
			} else {
				if(current.left == null) {
					current.left = newNode;
					newNode.parent = current;
					inserted = true;
				}
				current = current.left;
			}
		}

	}
	
	private Node find(T elem) {
		Node current = root;
		
		while(current != null) {
			if(elem.compareTo(current.data) > 0)
				current = current.right;
			else if(elem.compareTo(current.data) < 0)
				current = current.left;
			else 			
				return current;
		}
		
		return null;
	}
	
	public boolean contains(T elem) {
		return find(elem) != null;
	}
	
	private void delete(Node toDelete) {
		if(toDelete == null)
			return;
		
		if(toDelete.left == null && toDelete.right == null) { //deleted node has no children
			if(toDelete.parent.left.equals(toDelete))
				toDelete.parent.left = null;
			else
				toDelete.parent.right = null;
		} else if(toDelete.right == null || toDelete.left == null) { //deleted node has one child
			if(toDelete.parent.left.equals(toDelete))
				toDelete.parent.left = (toDelete.left == null ? toDelete.right : toDelete.left);
			else
				toDelete.parent.right = (toDelete.left == null ? toDelete.right : toDelete.left);
		} else { //deleted node has two children
			Node toCopy = toDelete.right;
			toCopy.nodesBelow--;
			
			while(toCopy.left != null) {
				toCopy = toCopy.left;
				toCopy.nodesBelow--;
			}
			
			toDelete.data = toCopy.data;
			delete(toCopy);
		}
	}
	
	public T remove(T elem) {
		Node toDelete = root;
		
		while(toDelete != null) {
			toDelete.nodesBelow--;
			if(elem.compareTo(toDelete.data) > 0)
				toDelete = toDelete.right;
			else if(elem.compareTo(toDelete.data) < 0)
				toDelete = toDelete.left;
			else 			
				break;
		}
		
		if(toDelete == null)
			return null;
		
		delete(toDelete);
		
		return elem;
	}
	
	private int depthHelper(Node current, int depth) {
		if(current == null)
			return depth-1;
		
		int leftDepth = depthHelper(current.left, depth+1);
		int rightDepth = depthHelper(current.right, depth+1);
		
		return Math.max(leftDepth, rightDepth);
	}
	
	//Returns number of nodes in tree
	public int size() {
		if(root == null)
			return 0;
		return root.nodesBelow + 1;
	}
	
	public int depth() {
		if(root == null)
			return 0;
		
		return depthHelper(root, 1);
	}
	
	private T randomHelper(Node current) {
		Random r = new Random();
		
		int roll = r.nextInt(current.nodesBelow+1);
		int nodesLeft = (current.left != null ? current.left.nodesBelow+1 : 0);
		//int nodesRight = (current.right != null ? current.right.nodesBelow+1 : 0);
		
		//example:
		//10 below
		//3 on the left
		//7 on the right
		
		//1/11 to choose ourself
		//3/11 to go left
		//7/11 to go right
		
		if(roll == 0)
			return current.data;
		else if(roll <= nodesLeft)
			return randomHelper(current.left);
		else
			return randomHelper(current.right);
		
	}
	
	public T random() {
		if(root == null)
			return null;
		
		return randomHelper(root);
	}
	
	public static void main(String[] args) {
		Test.header("BBinarySearchTree");
		
		RandomBST<Integer> tree = new RandomBST<Integer>();
		Test.assertion(!tree.contains(0));
		Test.equals(tree.depth(), 0);
		tree.insert(10);
		Test.assertion(tree.contains(10));
		Test.equals(tree.depth(), 1);
		tree.insert(5);
		Test.equals(tree.depth(), 2);
		tree.insert(15);
		Test.equals(tree.depth(), 2);
		Test.equals(tree.root.data, 10);
		Test.equals(tree.root.left.data, 5);
		Test.equals(tree.root.right.data, 15);
		Test.equals(tree.root.left.parent, tree.root);
		Test.equals(tree.root.right.parent, tree.root);
		Test.equals(tree.root.nodesBelow, 2);
		Test.equals(tree.root.left.nodesBelow, 0);
		Test.equals(tree.root.right.nodesBelow, 0);
		Test.assertion(tree.contains(10));
		Test.assertion(tree.contains(5));
		Test.assertion(tree.contains(15));
		Test.assertion(!tree.contains(500034));
		Test.equals(tree.size(), 3);
		tree.insert(7);
		tree.insert(3);
		tree.insert(9);
		tree.insert(6);
		tree.insert(17);
		tree.insert(12);
		//          10
		//    5                 15
		//3       7         12      17
		//      6    9
		Test.equals(tree.root.nodesBelow, 8);
		Test.equals(tree.root.left.nodesBelow, 4);
		Test.equals(tree.root.left.left.nodesBelow, 0);
		Test.equals(tree.root.left.right.nodesBelow, 2);
		Test.equals(tree.root.left.right.left.nodesBelow, 0);
		Test.equals(tree.root.left.right.right.nodesBelow, 0);
		Test.equals(tree.root.right.nodesBelow, 2);
		Test.equals(tree.root.right.left.nodesBelow, 0);
		Test.equals(tree.root.right.right.nodesBelow, 0);
		Test.equals(tree.size(), 9);
		Test.equals(tree.depth(), 4);
		
		//delete leaf
		tree.remove(12);
		//          10
		//    5                 15
		//3       7                 17
		//      6    9
		Test.equals(tree.root.right.data, 15);
		Test.equals(tree.root.right.right.data, 17);
		Test.isNull(tree.root.right.left);
		Test.equals(tree.root.nodesBelow, 7);
		Test.equals(tree.root.left.nodesBelow, 4);
		Test.equals(tree.root.left.left.nodesBelow, 0);
		Test.equals(tree.root.left.right.nodesBelow, 2);
		Test.equals(tree.root.left.right.left.nodesBelow, 0);
		Test.equals(tree.root.left.right.right.nodesBelow, 0);
		Test.equals(tree.root.right.nodesBelow, 1);
		Test.equals(tree.root.right.right.nodesBelow, 0);
		Test.equals(tree.size(), 8);
		
		//delete node with 1 child
		tree.remove(15);
		//          10
		//    5                 17
		//3       7                 
		//      6    9
		Test.equals(tree.root.right.data, 17);
		Test.isNull(tree.root.right.left);
		Test.isNull(tree.root.right.right);
		Test.equals(tree.root.nodesBelow, 6);
		Test.equals(tree.root.left.nodesBelow, 4);
		Test.equals(tree.root.left.left.nodesBelow, 0);
		Test.equals(tree.root.left.right.nodesBelow, 2);
		Test.equals(tree.root.left.right.left.nodesBelow, 0);
		Test.equals(tree.root.left.right.right.nodesBelow, 0);
		Test.equals(tree.root.right.nodesBelow, 0);
		Test.equals(tree.size(), 7);
		
		//delete node with 2 children
		tree.remove(5);
		//          10
		//    6                 17
		//3       7                 
		//           9
		Test.equals(tree.root.left.data, 6);
		Test.equals(tree.root.left.left.data, 3);
		Test.equals(tree.root.left.right.data, 7);
		Test.isNull(tree.root.left.right.left);
		Test.equals(tree.root.nodesBelow, 5);
		Test.equals(tree.root.left.nodesBelow, 3);
		Test.equals(tree.root.left.left.nodesBelow, 0);
		Test.equals(tree.root.left.right.nodesBelow, 1);
		Test.equals(tree.root.left.right.right.nodesBelow, 0);
		Test.equals(tree.root.right.nodesBelow, 0);
		Test.equals(tree.size(), 6);
		
		tree.remove(6);
		//          10
		//    7                 17
		//3       9                 
		Test.equals(tree.root.data, 10);
		Test.equals(tree.root.left.data, 7);
		Test.equals(tree.root.left.left.data, 3);
		Test.equals(tree.root.left.right.data, 9);
		Test.equals(tree.root.nodesBelow, 4);
		Test.equals(tree.root.left.nodesBelow, 2);
		Test.equals(tree.root.left.left.nodesBelow, 0);
		Test.equals(tree.root.left.right.nodesBelow, 0);
		Test.equals(tree.root.right.nodesBelow, 0);
		Test.isNull(tree.root.left.right.left);
		Test.isNull(tree.root.left.right.right);
		Test.equals(tree.depth(), 3);
		Test.equals(tree.size(), 5);
		
		HashMap<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
		
		int nRolls = 10000;
		int nPossibilities = tree.size();
		for(int i = 0; i < nRolls; i++) {
			Integer r = tree.random();
			freqMap.put(r, freqMap.getOrDefault(r, 0) + 1);
		}
		
		for(Integer key : freqMap.keySet()) {
			Test.print(key + " : " + freqMap.get(key));
			Test.assertion(freqMap.get(key) > (nRolls / nPossibilities * 0.9));
		}
		
		Test.results();
		
	}
	
}
