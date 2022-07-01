package treesgraphs;

import datastructures.BBinaryNode;
import testing.Test;

//find the next node (in-order successor) of a binary node
//nodes have a pointer to their parent
public class InorderSuccessor {

	//returns the inorder successor of a given node
	//inorder: left, current, right
	public static <T> BBinaryNode<T> successorOf(BBinaryNode<T> node) {
		if(node == null)
			return null;
		
		//if we have a right subtree, current->right->left... so go right, then left until leaf
		if(node.right != null) {
			BBinaryNode<T> current = node.right;
			while(current.left != null)
				current = current.left;
			return current;
		}
		
		//if we don't have a right subtree, climb up until we are a left child, then return parent
		BBinaryNode<T> current = node;
		while(current.parent != null) {
			//as long as we are right child of parent, climb up tree
			if(current.parent.right == current)
				current = current.parent;
			else //if we are not right child, we are left child. So return parent.
				return current.parent;
		}
		
		//if we reached root of tree while always being the right child, 
		//this is last node in an inorder traversal
		//so there is no successor
		return null; 
	}
	
	public static void main(String[] args) {
		Test.header("InorderSuccessor");
		
		Test.header("tree1");
		BBinaryNode<Integer> tree = BBinaryNode.buildSampleTree();
		Test.equals(successorOf(tree).data, 11);
		Test.equals(successorOf(tree.left).data, 9);
		Test.equals(successorOf(tree.left.left).data, 2);
		Test.equals(successorOf(tree.left.left.left).data, 4);
		Test.equals(successorOf(tree.left.right).data, 10);
		Test.equals(successorOf(tree.left.right.left).data, 5);
		Test.equals(successorOf(tree.left.right.right).data, 1);
		Test.equals(successorOf(tree.right).data, 7);
		Test.equals(successorOf(tree.right.left).data, 3);
		Test.equals(successorOf(tree.right.left.left).data, 6);
		Test.equals(successorOf(tree.right.right).data, 12);
		Test.isNull(successorOf(tree.right.right.right));
		
		Test.header("tree2");
		BBinaryNode<Integer> tree2 = BBinaryNode.buildSampleTree2();
		Test.isNull(successorOf(tree2));
		Test.equals(successorOf(tree2.left).data, 4);
		Test.equals(successorOf(tree2.left.left).data, 9);
		Test.equals(successorOf(tree2.left.left.left).data, 3);
		Test.equals(successorOf(tree2.left.left.left.left).data, 8);
		Test.equals(successorOf(tree2.left.left.left.left.right).data, 5);
		Test.equals(successorOf(tree2.left.left.right).data, 11);
		Test.equals(successorOf(tree2.left.left.right.left).data, 6);
		Test.equals(successorOf(tree2.left.left.right.right).data, 12);
		Test.equals(successorOf(tree2.left.left.right.right.left).data, 10);
		Test.equals(successorOf(tree2.left.left.right.right.right).data, 2);
		Test.equals(successorOf(tree2.left.right).data, 13);
		Test.equals(successorOf(tree2.left.right.right).data, 15);
		Test.equals(successorOf(tree2.left.right.right.right).data, 17);
		Test.equals(successorOf(tree2.left.right.right.right.left).data, 14);
		Test.equals(successorOf(tree2.left.right.right.right.right).data, 1);
		Test.equals(successorOf(tree2.left.right.right.right.right.left).data, 18);
		Test.equals(successorOf(tree2.left.right.right.right.right.left.right).data, 16);
		
		Test.results();
	}
}
