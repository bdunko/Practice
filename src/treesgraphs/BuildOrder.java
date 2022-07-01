package treesgraphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import datastructures.BGraph;
import datastructures.BGraphNode;
import testing.Test;

public class BuildOrder {

	private static void addVertices(BGraph<String> graph, String projects) {
		String[] projectNames = projects.split(", ");
		for(String projectName : projectNames) {
			graph.nodes.add(new BGraphNode<String>(projectName));
		}
	}
	
	private static void addEdges(BGraph<String> graph, String dependencies) {
		String[] pairs = dependencies.split("\\), ");
		for(String pair : pairs) {
			String[] vertices = pair.split(", ");
			BGraphNode<String> project = graph.getVertex(vertices[0].replace("(", ""));
			BGraphNode<String> dependency = graph.getVertex(vertices[1].replace(")", ""));
			project.neighbors.add(dependency);
		}
	}
	
	public static List<BGraphNode<String>> buildOrder(String projects, String dependencies) {
		//construct graph representation of problem
		BGraph<String> graph = new BGraph<String>();
		addVertices(graph, projects);
		addEdges(graph, dependencies);
		int graphSize = graph.nodes.size();
		
		List<BGraphNode<String>> buildOrder = new LinkedList<BGraphNode<String>>();
		Set<BGraphNode<String>> used = new HashSet<BGraphNode<String>>();
		
		//attempt to find a node 
		while(used.size() != graphSize) {
			boolean circular = true;
			
			//find a node with zero in-degree
			for(BGraphNode<String> currentNode : graph.nodes) {
				if(used.contains(currentNode))
					continue;
				
				int indegree = 0;
				for(BGraphNode<String> node : graph.nodes) {
					if(node == currentNode || used.contains(node))
						continue;
					if(node.neighbors.contains(currentNode))
						indegree++;
				}
				
				if(indegree != 0)
					continue;
				
				//add that node to the build order
				used.add(currentNode);
				buildOrder.add(currentNode);
				circular = false;
				break;
			}
			
			//if we iterated over all unused nodes and none have indegree 0, we have circular dependencies
			if(circular)
				return null;
		}
		
		return buildOrder;
	}
	
	private static <T> boolean aBeforeB(List<BGraphNode<T>> list, T a, T b) {
		int aIndex = -1;
		int bIndex = -1;
		
		for(int i = 0; i < list.size(); i++) {
			BGraphNode<T> node = list.get(i);
			if(node.data.equals(a))
				aIndex = i;
			else if (node.data.equals(b))
				bIndex = i;
		}
		
		if(aIndex == -1 || bIndex == -1)
			return false;
		
		return aIndex < bIndex;
	}
	
	public static void main(String[] args) {
		Test.header("BuildOrder");
		Test.header("addVertices");
		BGraph<String> graph = new BGraph<String>();
		addVertices(graph, "a, b, c, d, e, f");
		Test.assertion(graph.contains("a"));
		Test.assertion(graph.contains("b"));
		Test.assertion(graph.contains("c"));
		Test.assertion(graph.contains("d"));
		Test.assertion(graph.contains("e"));
		Test.assertion(graph.contains("f"));
		Test.equals(graph.nodes.size(), 6);
		
		Test.header("addEdges");
		addEdges(graph, "(a, d), (f, b), (b, d), (f, a), (d, c)");
		Test.assertion(graph.getVertex("a").hasNeighbor("d"));
		Test.assertion(graph.getVertex("f").hasNeighbor("b"));
		Test.assertion(graph.getVertex("b").hasNeighbor("d"));
		Test.assertion(graph.getVertex("f").hasNeighbor("a"));
		Test.assertion(graph.getVertex("d").hasNeighbor("c"));
		
		Test.header("buildOrder");
		List<BGraphNode<String>> buildOrder = buildOrder("a, b, c, d, e, f", "(a, d), (f, b), (b, d), (f, a), (d, c)");
		System.out.println(buildOrder);
		Test.assertion(buildOrder.size() == 6);
		Test.assertion(aBeforeB(buildOrder, "a", "d"));
		Test.assertion(aBeforeB(buildOrder, "f", "b"));
		Test.assertion(aBeforeB(buildOrder, "b", "d"));
		Test.assertion(aBeforeB(buildOrder, "f", "a"));
		Test.assertion(aBeforeB(buildOrder, "d", "c"));
		
		Test.results();
	}
}
