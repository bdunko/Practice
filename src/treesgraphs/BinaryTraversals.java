package treesgraphs;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import datastructures.BBinaryNode;
import testing.Test;

//Inorder, Preorder, and Postorder traversals of a binary tree
public class BinaryTraversals {
	
	//left -> self -> right
	public static <T> void inorder(BBinaryNode<T> tree) {
		if(tree == null)
			return;
		inorder(tree.left);
		System.out.print(tree.data + " ");
		inorder(tree.right);
	}
	
	//self -> left -> right
	public static <T> void preorder(BBinaryNode<T> tree) {
		if(tree == null)
			return;
		System.out.print(tree.data + " ");
		preorder(tree.left);
		preorder(tree.right);
	}
	
	//left -> right -> self
	public static <T> void postorder(BBinaryNode<T> tree) {
		if(tree == null)
			return;
		postorder(tree.left);
		postorder(tree.right);
		System.out.print(tree.data + " ");
	}
	
	public static void main(String[] args) {
		BBinaryNode<Integer> tree = BBinaryNode.buildSampleTree();
		BBinaryNode<Integer> tree2 = BBinaryNode.buildSampleTree2();
		
		Test.header("BinaryTraversals");
		Test.header("inorder");
		
		Test.redirectStdoutToString();
		inorder(tree);
		Test.restoreStdoutAndTestStdoutEquals("8 4 2 9 5 10 1 11 6 3 7 12 ");
		
		Test.redirectStdoutToString();
		inorder(tree2);
		Test.restoreStdoutAndTestStdoutEquals("7 8 5 3 9 6 11 10 12 2 4 13 15 14 17 18 16 1 ");
		
		
		Test.header("preorder");
		Test.redirectStdoutToString();
		preorder(tree);
		Test.restoreStdoutAndTestStdoutEquals("1 2 4 8 5 9 10 3 6 11 7 12 ");
		
		
		Test.header("postorder");
		Test.redirectStdoutToString();
		postorder(tree);
		Test.restoreStdoutAndTestStdoutEquals("8 4 9 10 5 2 11 6 12 7 3 1 ");
		
		Test.results();
	}
}
