package treesgraphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import datastructures.BGraph;
import datastructures.BGraphNode;
import testing.Test;

//Given a list of projects and a list of dependency (where each dependency is represented as a pair of projects such that the second project depends on the first)
//Find and return an order that allows all projects to be built, or null if no order exists
public class BuildOrder {
	
	//Solution using graphs.
	//Represent the problem as a graph, where each node is a project
	//And each edge is a dependency
	public static List<String> buildOrder(String projects, String dependencies) {
		//construct graph representation of problem
		BGraph<String> graph = new BGraph<String>();
		addVertices(graph, projects);
		addEdges(graph, dependencies);
		int graphSize = graph.nodes.size();
		
		List<String> buildOrder = new LinkedList<String>();
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
				buildOrder.add(currentNode.data);
				circular = false;
				break;
			}
			
			//if we iterated over all unused nodes and none have indegree 0, we have circular dependencies
			if(circular)
				return null;
		}
		
		return buildOrder;
	}
	
	//Helper method that assists in constructing the graph by parsing an input string
	private static void addVertices(BGraph<String> graph, String projects) {
		String[] projectNames = projects.split(", ");
		for(String projectName : projectNames) {
			graph.nodes.add(new BGraphNode<String>(projectName));
		}
	}
	
	//Helper method that assists in constructing the graph by parsing an input string
	private static void addEdges(BGraph<String> graph, String dependencies) {
		String[] pairs = dependencies.split("\\), ");
		for(String pair : pairs) {
			String[] vertices = pair.split(", ");
			BGraphNode<String> project = graph.getVertex(vertices[0].replace("(", ""));
			BGraphNode<String> dependency = graph.getVertex(vertices[1].replace(")", ""));
			project.neighbors.add(dependency);
		}
	}
	
	//More efficient version that uses a HashMap
	//Keys in the HashMap are projects. Value is a HashSet containing all dependencies for the project.
	//Then we can iterate over the set of keys and find projects with no dependencies, build them
	//And remove them as dependencies from other projects
	//Repeating until a circular dependency is found or all projects have been built
	private static List<String> buildOrderV2(String projects, String dependencies) {
		HashMap<String, HashSet<String>> dependencyMap = new HashMap<String, HashSet<String>>();
		
		//parse project names
		for(String projectName : projects.split(", ")) {
			dependencyMap.put(projectName, new HashSet<String>());
		}
		
		//parse dependencies
		for(String pair : dependencies.split("\\), ")) {
			String[] projectPair = pair.split(", ");
			String project = projectPair[1].replace(")", "");
			String dependency = projectPair[0].replace("(", "");
			dependencyMap.get(project).add(dependency);
		}
		
		List<String> buildOrder = new LinkedList<String>();
		
		//until all projects have been added to build order...
		while(dependencyMap.size() > 0) {
			boolean circular = true;
			
			//find a project with zero dependencies
			Iterator<String> iter = dependencyMap.keySet().iterator();
			while(iter.hasNext()) {
				String project = iter.next();
				if(dependencyMap.get(project).size() == 0) {
					//add that project to build order
					buildOrder.add(project);
					
					//remove this project from set of projects left to build 
					iter.remove();
					circular = false;
					
					//remove this project from dependencies of each other project
					Iterator<String> iter2 = dependencyMap.keySet().iterator();
					while(iter2.hasNext()) {
						dependencyMap.get(iter2.next()).remove(project);
					}
				}
			}
			
			//if, after checking all projects, there are none we can build
			//there are circular dependencies; return null
			if(circular)
				return null;
		}
		
		return buildOrder;
	}
	
	//Testing helper method that checks if a given element comes before another element in a list
	private static <T> boolean aBeforeB(List<T> list, T a, T b) {
		int aIndex = list.indexOf(a);
		int bIndex = list.indexOf(b);
		
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
		List<String> buildOrder = buildOrder("a, b, c, d, e, f", "(a, d), (f, b), (b, d), (f, a), (d, c)");
		Test.assertion(buildOrder.size() == 6);
		Test.assertion(aBeforeB(buildOrder, "a", "d"));
		Test.assertion(aBeforeB(buildOrder, "f", "b"));
		Test.assertion(aBeforeB(buildOrder, "b", "d"));
		Test.assertion(aBeforeB(buildOrder, "f", "a"));
		Test.assertion(aBeforeB(buildOrder, "d", "c"));
		
		Test.header("buildOrderV2");
		buildOrder = buildOrderV2("a, b, c, d, e, f", "(a, d), (f, b), (b, d), (f, a), (d, c)");
		Test.assertion(buildOrder.size() == 6);
		Test.assertion(aBeforeB(buildOrder, "a", "d"));
		Test.assertion(aBeforeB(buildOrder, "f", "b"));
		Test.assertion(aBeforeB(buildOrder, "b", "d"));
		Test.assertion(aBeforeB(buildOrder, "f", "a"));
		Test.assertion(aBeforeB(buildOrder, "d", "c"));
		
		Test.isNull(buildOrderV2("a, b, c, d, e, f", "(a, d), (f, b), (b, d), (f, a), (d, c), (a, a)"));
		Test.isNull(buildOrderV2("a, b, c", "(a, b), (b, c), (c, a)"));
		
		buildOrder = buildOrderV2("a, b, c, d", "(a, b), (b, c)");
		Test.assertion(buildOrder.size() == 4);
		Test.assertion(aBeforeB(buildOrder, "a", "b"));
		Test.assertion(aBeforeB(buildOrder, "b", "c"));
		
		Test.results();
	}
}
