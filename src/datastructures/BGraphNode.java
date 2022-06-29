package datastructures;

import java.util.LinkedList;
import java.util.List;

//Generic graph node class, where a node may have any number of children
public class BGraphNode<T> {
	
	public T data;
	public List<BGraphNode<T>> neighbors;
	public boolean marked;
	
	public BGraphNode(T data) {
		this(data, new LinkedList<BGraphNode<T>>());
	}
	
	public BGraphNode(T data, List<BGraphNode<T>> neighbors) {
		this.data = data;
		this.neighbors = neighbors;
		this.marked = false;
	}
}
