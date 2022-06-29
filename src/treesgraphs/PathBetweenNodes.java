package treesgraphs;

import datastructures.BGraph;
import datastructures.BGraphNode;
import testing.Test;

//Determine if there is a path between two nodes in a directed graph
public class PathBetweenNodes {

	//Determines if there is a path between two nodes in a directed graph using DFS
	public static <T> boolean isPathBetween(BGraphNode<T> current, BGraphNode<T> target) {
		if(current == null || target == null)
			return false;
		
		if(current.equals(target))
			return true;
		
		current.marked = true;
		for(BGraphNode<T> neighbor : current.neighbors) {
			if(!neighbor.marked) {
				if(isPathBetween(neighbor, target))
					return true;
			}
		}
		
		return false;
	}
	
	public static <T> void testCase(BGraph<T> graph, BGraphNode<T> node1, BGraphNode<T> node2, boolean expected) {
		BGraph.unmarkAll(graph);
		Test.assertion(isPathBetween(node1, node2) == expected);
	}
	
	public static void main(String[] args) {
		Test.header("PathBetweenNodes");
		
		BGraph<Integer> graph = BGraph.buildSampleGraph();
		
		testCase(graph, graph.nodes.get(0), graph.nodes.get(1), true);
		testCase(graph, graph.nodes.get(0), graph.nodes.get(2), true);
		testCase(graph, graph.nodes.get(0), graph.nodes.get(3), true);
		testCase(graph, graph.nodes.get(0), graph.nodes.get(6), true);
		testCase(graph, graph.nodes.get(0), graph.nodes.get(7), true);
		testCase(graph, graph.nodes.get(8), graph.nodes.get(10), true);
		testCase(graph, graph.nodes.get(10), graph.nodes.get(8), true);
		testCase(graph, graph.nodes.get(6), graph.nodes.get(6), true);
		testCase(graph, graph.nodes.get(4), graph.nodes.get(7), true);
		
		testCase(graph, graph.nodes.get(7), graph.nodes.get(4), false);
		testCase(graph, graph.nodes.get(6), graph.nodes.get(0), false);
		testCase(graph, graph.nodes.get(1), graph.nodes.get(0), false);
		testCase(graph, graph.nodes.get(11), graph.nodes.get(6), false);
		
		Test.results();
	}
}
