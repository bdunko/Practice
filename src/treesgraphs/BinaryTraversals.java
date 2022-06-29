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
		
		Test.header("BinaryTraversals");
		Test.header("inorder");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		inorder(tree);
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		Test.equals(out.toString(), "8 4 2 9 5 10 1 11 6 3 7 12 ");
		
		
		Test.header("preorder");
		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		preorder(tree);
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		Test.equals(out.toString(), "1 2 4 8 5 9 10 3 6 11 7 12 ");
		
		
		Test.header("postorder");
		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		postorder(tree);
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		Test.equals(out.toString(), "8 4 9 10 5 2 11 6 12 7 3 1 ");
		
		Test.results();
	}
}
