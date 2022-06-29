package datastructures;

//Generic binary tree node class
public class BBinaryNode<T> {
	
	public T data;
	public BBinaryNode<T> left, right;
	
	public BBinaryNode(T data) {
		this(data, null, null);
	}
	
	public BBinaryNode(T data, BBinaryNode<T> left, BBinaryNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
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
		tree.right = new BBinaryNode<Integer>(3);
		tree.left.left = new BBinaryNode<Integer>(4);
		tree.left.right = new BBinaryNode<Integer>(5);
		tree.right.left = new BBinaryNode<Integer>(6);
		tree.right.right = new BBinaryNode<Integer>(7);
		tree.left.left.left = new BBinaryNode<Integer>(8);
		tree.left.right.left = new BBinaryNode<Integer>(9);
		tree.left.right.right = new BBinaryNode<Integer>(10);
		tree.right.left.left = new BBinaryNode<Integer>(11);
		tree.right.right.right = new BBinaryNode<Integer>(12);
		return tree;
	}
}
