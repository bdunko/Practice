package datastructures;

import testing.Test;

public class BBinarySearchTree<T extends Comparable<T>> {

	private class Node {
		public T data;
		public Node left, right, parent;
		
		public Node(T data) {
			this(data, null, null, null);
		}
		
		public Node(T data, Node left, Node right, Node parent) {
			this.data = data;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
	}
	
	private Node root;
	
	public BBinarySearchTree() {
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
	
	private Node inorderSuccessor(Node node) {
		if(node.right == null)
			return null;
		node = node.right;
		
		while(node.left != null)
			node = node.left;
		
		return node;
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
			Node toCopy = inorderSuccessor(toDelete);
			toDelete.data = toCopy.data;
			delete(toCopy);
		}
	}
	
	public T remove(T elem) {
		Node toDelete = find(elem);
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
	
	public int depth() {
		if(root == null)
			return 0;
		
		return depthHelper(root, 1);
	}
	
	public static void main(String[] args) {
		Test.header("BBinarySearchTree");
		
		BBinarySearchTree<Integer> tree = new BBinarySearchTree<Integer>();
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
		Test.assertion(tree.contains(10));
		Test.assertion(tree.contains(5));
		Test.assertion(tree.contains(15));
		Test.assertion(!tree.contains(500034));
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
		
		Test.equals(tree.depth(), 4);
		
		//delete leaf
		tree.remove(12);
		Test.equals(tree.root.right.data, 15);
		Test.equals(tree.root.right.right.data, 17);
		Test.isNull(tree.root.right.left);
		
		//delete node with 1 child
		tree.remove(15);
		Test.equals(tree.root.right.data, 17);
		Test.isNull(tree.root.right.left);
		Test.isNull(tree.root.right.right);
		
		//delete node with 2 children
		tree.remove(5);
		Test.equals(tree.root.left.data, 6);
		Test.equals(tree.root.left.left.data, 3);
		Test.equals(tree.root.left.right.data, 7);
		Test.isNull(tree.root.left.right.left);
		
		tree.remove(6);
		Test.equals(tree.root.left.data, 7);
		Test.equals(tree.root.left.left.data, 3);
		Test.equals(tree.root.left.right.data, 9);
		
		Test.results();
		
	}
	
}
