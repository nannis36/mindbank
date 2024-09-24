package mindBankDAG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DirectedAcyclicGraph extends Graph {
	public void connectVertices(Vertex parentVertex, Vertex childVertex) {
		parentVertex.addChildVertex(childVertex);
	}
	
	private void topologicalSort(Vertex v, Map<Integer,Boolean> visited, Stack<Vertex> stack){
		// Mark current vertex as visited
		visited.put(v.getId(), true);
		
		// Traverse all child vertices
		for(Map.Entry<Integer,Vertex> childVertexMapEntry : v.getChildVertices().entrySet()) {
			Vertex child = childVertexMapEntry.getValue();
			if(!visited.get(child.getId())) {
				topologicalSort(child,visited,stack);
			}
		}
		
		//push the current vertex to the stack to save the topological sorting
		stack.push(v);
	}
	
	public List<Vertex> findLongestPathFromVertex(Vertex sourceVertex){
		// Initialize stack for topological sorting
		Stack<Vertex> stack = new Stack<Vertex>();
		Map<Integer,Integer> dist = new HashMap<Integer,Integer>();
		
		// initialize map to track path taken to a given vertex
		Map<Integer,List<Vertex>> paths = new HashMap<Integer,List<Vertex>>();
		
		Map<Integer,Boolean> visited = new HashMap<Integer,Boolean>();
		
		// Mark all vertices as not visited
		for(Map.Entry<Integer,Vertex> vertEntry : vertices.entrySet()) {
			visited.put(vertEntry.getKey(),false);
		}
		
		// Call topologicalSort to store sort values from all vertices
		for(Map.Entry<Integer,Boolean> visitedEntry : visited.entrySet()) {
			if(visitedEntry.getValue() == false) {
				Vertex startFrom = getVertex(visitedEntry.getKey());
				topologicalSort(startFrom, visited, stack);
			}
		}
		
		// Initialize distances to all vertices as infinite and the distance to the source as 0
		for(Map.Entry<Integer,Vertex> distanceEntry : vertices.entrySet()) {
			Vertex distanceVertex = distanceEntry.getValue();
			int vid = distanceVertex.getId();
			dist.put(vid,Integer.MIN_VALUE);
			paths.put(vid, new ArrayList<Vertex>());
			paths.get(vid).add(sourceVertex); //initialize path with starting vertex
		}
		
		dist.put(sourceVertex.getId(),0);
		
		// Process vertices in topoligical order
		while(!stack.isEmpty()) {
			Vertex nextV = stack.pop();
			
			if(dist.get(nextV.getId()) != Integer.MIN_VALUE) {
				Map<Integer,Vertex> childVertices = nextV.getChildVertices();
				for(Map.Entry<Integer,Vertex> nextChildEntry : childVertices.entrySet()) {	
					Vertex childVertex = nextChildEntry.getValue();
					if(dist.get(childVertex.getId()) <= dist.get(nextV.getId()) ) {
						int vid = childVertex.getId();
						dist.put(vid, dist.get(nextV.getId()) + 1);
						paths.get(vid).add(nextV);
					}
				}
			}
		}
		
		//loop through saved distance values to find the longest
		int longest = Integer.MIN_VALUE;
		Integer longestId = null;
		for(Map.Entry<Integer,Integer> distances : dist.entrySet()) {
			Integer lid = distances.getKey();
			int distance = distances.getValue();
			paths.get(lid).add(getVertex(lid)); //path does not have the destination vertex yet, add it here
			if(distance > longest) {
				longest = distance;
				longestId = lid;
			}
		}
		
		//return list of vertices for the longest path
		return paths.get(longestId);
		
	}
	
	public List<Vertex> findLongestPathFromVertex(int id){
		return findLongestPathFromVertex(getVertex(id));
	}
}
