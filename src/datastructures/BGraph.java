package datastructures;

import java.util.LinkedList;
import java.util.List;

//Generic graph
public class BGraph<T> {
	public List<BGraphNode<T>> nodes;
	
	public BGraph() {
		this.nodes = new LinkedList<BGraphNode<T>>();
	}
	
	public BGraph(List<BGraphNode<T>> nodes) {
		this.nodes = nodes;
	}
	
	public static <E> void unmarkAll(BGraph<E> graph) {
		for(BGraphNode<E> node : graph.nodes)
			node.marked = false;
	}
	
	
	//Creates the sample graph depicted in samplegraph.png
	//Notably, node neighbors are ordered in ascending order for consistent DFS/BFS testing
	public static BGraph<Integer> buildSampleGraph() {
		BGraph<Integer> graph = new BGraph<Integer>();
		
		BGraphNode<Integer> zero = new BGraphNode<Integer>(0);
		BGraphNode<Integer> one = new BGraphNode<Integer>(1);
		BGraphNode<Integer> two = new BGraphNode<Integer>(2);
		BGraphNode<Integer> three = new BGraphNode<Integer>(3);
		BGraphNode<Integer> four = new BGraphNode<Integer>(4);
		BGraphNode<Integer> five = new BGraphNode<Integer>(5);
		BGraphNode<Integer> six = new BGraphNode<Integer>(6);
		BGraphNode<Integer> seven = new BGraphNode<Integer>(7);
		BGraphNode<Integer> eight = new BGraphNode<Integer>(8);
		BGraphNode<Integer> nine = new BGraphNode<Integer>(9);
		BGraphNode<Integer> ten = new BGraphNode<Integer>(10);
		BGraphNode<Integer> eleven = new BGraphNode<Integer>(11);
		
		zero.neighbors.add(one);
		one.neighbors.add(two);
		one.neighbors.add(three);
		two.neighbors.add(four);
		two.neighbors.add(seven);
		three.neighbors.add(two);
		three.neighbors.add(three);
		four.neighbors.add(five);
		four.neighbors.add(seven);
		five.neighbors.add(one);
		five.neighbors.add(six);
		six.neighbors.add(five);
		
		eight.neighbors.add(nine);
		nine.neighbors.add(eight);
		nine.neighbors.add(ten);
		ten.neighbors.add(eight);
		
		graph.nodes.add(zero);
		graph.nodes.add(one);
		graph.nodes.add(two);
		graph.nodes.add(three);
		graph.nodes.add(four);
		graph.nodes.add(five);
		graph.nodes.add(six);
		graph.nodes.add(seven);
		graph.nodes.add(eight);
		graph.nodes.add(nine);
		graph.nodes.add(ten);
		graph.nodes.add(eleven);
		
		return graph;
	}
}
