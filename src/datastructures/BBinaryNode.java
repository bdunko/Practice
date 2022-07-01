package datastructures;

//Generic binary tree node class
public class BBinaryNode<T> {
	
	public T data;
	public BBinaryNode<T> left, right;
	public BBinaryNode<T> parent;
	
	public BBinaryNode(T data) {
		this(data, null, null, null);
	}
	
	public BBinaryNode(T data, BBinaryNode<T> left, BBinaryNode<T> right) {
		this(data, left, right, null);
	}
	
	public BBinaryNode(T data, BBinaryNode<T> left, BBinaryNode<T> right, BBinaryNode<T> parent) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	
	//determines and returns the height of the given tree
	public static int treeHeight(BBinaryNode<Integer> root) {
		if(root == null)
			return 0;
		
		return treeHeightHelper(root, 0);
	}
	
	private static <T> int treeHeightHelper(BBinaryNode<T> current, int depth) {
		if(current == null)
			return depth;
		
		return Math.max(treeHeightHelper(current.left, depth+1), treeHeightHelper(current.right, depth+1));
	}
	
	//Constructs and returns the sample binary tree from sampletree.png
	public static BBinaryNode<Integer> buildSampleTree() {
		BBinaryNode<Integer> tree = new BBinaryNode<Integer>(1);
		tree.left = new BBinaryNode<Integer>(2);
		tree.left.parent = tree;
		tree.right = new BBinaryNode<Integer>(3);
		tree.right.parent = tree;
		tree.left.left = new BBinaryNode<Integer>(4);
		tree.left.left.parent = tree.left;
		tree.left.right = new BBinaryNode<Integer>(5);
		tree.left.right.parent = tree.left;
		tree.right.left = new BBinaryNode<Integer>(6);
		tree.right.left.parent = tree.right;
		tree.right.right = new BBinaryNode<Integer>(7);
		tree.right.right.parent = tree.right;
		tree.left.left.left = new BBinaryNode<Integer>(8);
		tree.left.left.left.parent = tree.left.left;
		tree.left.right.left = new BBinaryNode<Integer>(9);
		tree.left.right.left.parent = tree.left.right;
		tree.left.right.right = new BBinaryNode<Integer>(10);
		tree.left.right.right.parent = tree.left.right;
		tree.right.left.left = new BBinaryNode<Integer>(11);
		tree.right.left.left.parent = tree.right.left;
		tree.right.right.right = new BBinaryNode<Integer>(12);
		tree.right.right.right.parent = tree.right.right;
		return tree;
	}
	
	//Constructs and returns the sample binary tree from sampletree2.png
	public static BBinaryNode<Integer> buildSampleTree2() {
		BBinaryNode<Integer> tree = new BBinaryNode<Integer>(1);
		tree.left = new BBinaryNode<Integer>(2, null, null, tree);
		tree.left.left = new BBinaryNode<Integer>(3, null, null, tree.left);
		tree.left.right = new BBinaryNode<Integer>(4, null, null, tree.left);
		tree.left.left.left = new BBinaryNode<Integer>(5, null, null, tree.left.left);
		tree.left.left.right = new BBinaryNode<Integer>(6, null, null, tree.left.left);
		tree.left.left.left.left = new BBinaryNode<Integer>(7, null, null, tree.left.left.left);
		tree.left.left.left.left.right = new BBinaryNode<Integer>(8, null, null, tree.left.left.left.left);
		tree.left.left.right.left = new BBinaryNode<Integer>(9, null, null, tree.left.left.right);
		tree.left.left.right.right = new BBinaryNode<Integer>(10, null, null, tree.left.left.right);
		tree.left.left.right.right.left = new BBinaryNode<Integer>(11, null, null, tree.left.left.right.right);
		tree.left.left.right.right.right = new BBinaryNode<Integer>(12, null, null, tree.left.left.right.right);
		tree.left.right.right = new BBinaryNode<Integer>(13, null, null, tree.left.right);
		tree.left.right.right.right = new BBinaryNode<Integer>(14, null, null, tree.left.right.right);
		tree.left.right.right.right.left = new BBinaryNode<Integer>(15, null, null, tree.left.right.right.right);
		tree.left.right.right.right.right = new BBinaryNode<Integer>(16, null, null, tree.left.right.right.right);
		tree.left.right.right.right.right.left = new BBinaryNode<Integer>(17, null, null, tree.left.right.right.right.right);
		tree.left.right.right.right.right.left.right = new BBinaryNode<Integer>(18, null, null, tree.left.right.right.right.right.left);
		return tree;
	}
}













