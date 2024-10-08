package mindBankDAG;

import java.io.IOException;
import java.io.*;
import java.util.Map;
import java.util.stream.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		readInputs();
		//runTestInputs();
	}
	
	/**
	 * Readinputs will read user inputs from system.in in order to construct a graph and run the longest path algorithm for a given vertex id.
	 * it expects the input to have the following format:
	 * 		int n: the first line is the number of graph edges to construct 
	 * 		the next n lines are pairs of ints representing a "from" vertex id and a "to" vertex id separated by a single space. example:
	 * 			1 2
	 * 		int id: the final line is a single integer representing a vertex id to run the longest path algorithm from followed by a newline
	 * 		example input:
	 * 			10
	 *			0 1
	 *			0 2
	 *			1 3
	 *			1 2
	 *			2 4
	 *			2 5
	 *			2 3
	 *			3 5
	 *			3 4
	 *			4 5
	 *			1
	 *
	 * The output from the algorithm is provided by System.out after all inputs are received. An example from the above input is provided below:
  	 *
	 * 	The longest path from vertex 1 is 4 steps to vertex 5
	 */
	public static void readInputs() {
		DirectedAcyclicGraph dag = new DirectedAcyclicGraph();
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
			int inputLength = Integer.parseInt(bufferedReader.readLine().trim());
			
			IntStream.range(0, inputLength).forEach(tItr -> {
			    try {
			    	String[] edgeConnectionInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
			
			    	int from = Integer.parseInt(edgeConnectionInput[0]);
			
			    	int to = Integer.parseInt(edgeConnectionInput[1]);
	
		            	dag.connectVertices(from, to);
			
			        //System.out.println("Added connection: "+from+" to "+to);
			
			    } catch (IOException ex) {
			        throw new RuntimeException(ex);
			    }
			});
	
	        	int sourceVertexId = Integer.parseInt( bufferedReader.readLine().trim() );
	        	Map<String,Integer> result = dag.findLongestPathFromVertex( sourceVertexId );
			
			System.out.println("The longest path from vertex "+sourceVertexId+" is "+result.get("distance")+" steps to vertex "+result.get("longestId"));
	        
			bufferedReader.close();
		} catch(Exception e) {
			System.out.println("Unhandled exception: "+e.getMessage());
		}
	}
	
	public static void runTestInputs() {
		DirectedAcyclicGraph dag = new DirectedAcyclicGraph();
		
		//String[] input1 = {"3", "1 2", "1 4", "1 3", "1"};
		String[] input = {
				"10", 
				"0 1", 
				"0 2", 
				"1 3",  
				"1 2", 
				"2 4", 
				"2 5", 
				"2 3", 
				"3 5", 
				"3 4", 
				"4 5",
				"1"};
		
	    	int inputLength = Integer.parseInt(input[0].trim());
	
	        for(int i = 1; i < inputLength+1; i++) {
	            String[] edgeConnectionInput = input[i].replaceAll("\\s+$", "").split(" ");
	
	            int from = Integer.parseInt(edgeConnectionInput[0]);
	
	            int to = Integer.parseInt(edgeConnectionInput[1]);
	
	            dag.connectVertices(from, to);
	
	            System.out.println("Added connection: "+from+" to "+to);
	        };
	        
	        int sourceVertexId = Integer.parseInt( input[input.length-1].trim() );;
	
	    	Map<String,Integer> result = dag.findLongestPathFromVertex( sourceVertexId );
		
		System.out.println("The longest path from vertex "+sourceVertexId+" is "+result.get("distance")+" steps to vertex "+result.get("longestId"));
        
	}
}
