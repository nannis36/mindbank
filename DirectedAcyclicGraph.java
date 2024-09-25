package mindBankDAG;

import java.util.HashMap;
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
	
	public Map<String,Integer> findLongestPathFromVertex(Vertex sourceVertex){
		
		// Initialize stack for topological sorting
		Stack<Vertex> stack = new Stack<Vertex>();

		// Initialize a map to store distances to every vertex
		Map<Integer,Integer> distanceMap = new HashMap<Integer,Integer>();
		
		// Initialize map to track visited vertices by their id
		Map<Integer,Boolean> visited = new HashMap<Integer,Boolean>();
		
		// Mark all vertices as not visited
		for(Map.Entry<Integer,Vertex> vertEntry : vertices.entrySet()) {
			visited.put(vertEntry.getKey(),false);
		}
		
		// Call topologicalSort to store sort values from all vertices
		// Traverse all unvisited vertices and call topological sort
		for(Map.Entry<Integer,Boolean> visitedEntry : visited.entrySet()) {
			
			if(visitedEntry.getValue() == false) {
				Vertex startFrom = getVertex(visitedEntry.getKey());
				topologicalSort(startFrom, visited, stack);
			}
			
		}
		
		// Initialize distances to all vertices as a minimum value, marking them as inaccessible at this point in the algorithm
		for(Map.Entry<Integer,Vertex> distanceEntry : vertices.entrySet()) {
			
			Vertex distanceVertex = distanceEntry.getValue();

			int vid = distanceVertex.getId();
			distanceMap.put(vid,Integer.MIN_VALUE);
			
		}
		
		// Set the starting vertex distance to 0
		distanceMap.put(sourceVertex.getId(),0);
		
		// Process vertices in topological order
		while(!stack.isEmpty()) {
			
			Vertex nextStackVertex = stack.pop();
			
			// Update distances of adjacent vertices
			if(distanceMap.get(nextStackVertex.getId()) != Integer.MIN_VALUE) {
				
				Map<Integer,Vertex> childVertices = nextStackVertex.getChildVertices();
				
				for(Map.Entry<Integer,Vertex> nextChildEntry : childVertices.entrySet()) {	
					
					Vertex childVertex = nextChildEntry.getValue();
					
					if(distanceMap.get(childVertex.getId()) <= distanceMap.get(nextStackVertex.getId()) ) {
						distanceMap.put( childVertex.getId() , distanceMap.get(nextStackVertex.getId()) + 1);
					}
				}
			}
		}
		
		//loop through saved distance values to find the longest
		int longest = Integer.MIN_VALUE;
		Integer longestId = null;
		for(Map.Entry<Integer,Integer> distances : distanceMap.entrySet()) {
			Integer lid = distances.getKey();
			int distance = distances.getValue();
			if(distance > longest) {
				longest = distance;
				longestId = lid;
			}
		}
		
		Map<String,Integer> returnMap = new HashMap<String,Integer>();
		returnMap.put("longestId", longestId);
		returnMap.put("distance", longest);
		
		return returnMap;
		
	}
	
	public Map<String,Integer> findLongestPathFromVertex(int id){
		return findLongestPathFromVertex(getVertex(id));
	}
}
