package treesgraphs;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;

import datastructures.BGraph;
import datastructures.BGraphNode;
import testing.Test;

public class GraphTraversals {

	public static <T> void DFS(BGraphNode<T> node) {
		if(node == null)
			return;
		
		//mark current node as visited
		node.marked = true;
		
		//print out current node
		System.out.print(node.data + " ");
		
		//perform DFS on all unvisited neighbors
		for(BGraphNode<T> neighbor : node.neighbors) {
			if(!neighbor.marked)
				DFS(neighbor);
		}
	}
	
	public static <T> void BFS(BGraphNode<T> node) {
		if(node == null)
			return;
		
		Queue<BGraphNode<T>> queue = new LinkedList<BGraphNode<T>>();
		node.marked = true;
		queue.add(node);
		
		//until queue is empty, continue traversing
		while(!queue.isEmpty()) {
			//get next node to visit
			BGraphNode<T> current = queue.remove(); 
			
			//print it out
			System.out.print(current.data + " ");
			
			//add each of its unvisited neighbors to queue, and mark them as visited (...basically to be visited)
			for(BGraphNode<T> neighbor : current.neighbors) {
				if(!neighbor.marked) {
					neighbor.marked = true;
					queue.add(neighbor);
				}
			}
		}
	}
	
	private static <T> void testCaseDFS(BGraph<T> graph, BGraphNode<T> startingNode, String expectedOutput) {
		BGraph.unmarkAll(graph);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		DFS(startingNode);
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		Test.equals(out.toString(), expectedOutput);
	}
	
	private static <T> void testCaseBFS(BGraph<T> graph, BGraphNode<T> startingNode, String expectedOutput) {
		BGraph.unmarkAll(graph);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		BFS(startingNode);
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		Test.equals(out.toString(), expectedOutput);
	}
	
	public static void main(String[] args) {
		BGraph<Integer> graph = BGraph.buildSampleGraph();
		Test.header("GraphTraversals");
		Test.header("DFS");
		testCaseDFS(graph, graph.nodes.get(0), "0 1 2 4 5 6 7 3 ");
		testCaseDFS(graph, graph.nodes.get(5), "5 1 2 4 7 3 6 ");
		testCaseDFS(graph, graph.nodes.get(9), "9 8 10 ");
		testCaseDFS(graph, graph.nodes.get(11), "11 ");
		
		Test.header("BFS");
		testCaseBFS(graph, graph.nodes.get(0), "0 1 2 3 4 7 5 6 ");
		testCaseBFS(graph, graph.nodes.get(2), "2 4 7 5 1 6 3 ");
		testCaseBFS(graph, graph.nodes.get(8), "8 9 10 ");
		testCaseBFS(graph, graph.nodes.get(11), "11 ");
		
		Test.results();
	}
}
