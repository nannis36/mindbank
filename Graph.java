package mindBankDAG;

import java.util.HashMap;
import java.util.Map;

class Graph{
	protected Map<Integer,Vertex> vertices;
	
	public Graph(){
		vertices = new HashMap<Integer,Vertex>();
	}
	
	public Vertex createOrGetVertex(int id) {
		// Graph only allows unique ids for vertices and will create a new vertex or return an existing vertex for a given id
		Vertex v = vertices.get(id);
		if(v == null) {
			v = new Vertex(id);
			vertices.put(id,v);
		}
		return v;
	}
	
	public Vertex createVertex(int id) {
		return createOrGetVertex(id);
	}
	
	public void createVertices(int[] idArray) {
		for(int i=0; i<idArray.length;i++) {
			createVertex(idArray[i]);
		}
	}
	
	public Vertex getVertex(int id) {
		//return vertices.get(id);
		return createOrGetVertex(id);
	}
	
	public boolean vertexExists(int id) {
		return vertices.get(id) != null;
	}
	
	public void connectVertices(Vertex parentVertex, Vertex childVertex) {
		// should this create a new vertex if it isn't found? - if not, boolean return?
		parentVertex.addChildVertex(childVertex);
		childVertex.addParentVertex(parentVertex);
	}
	
	public void connectVertices(int parentVertexId, int childVertexId) {
		// should this create a new vertex if it isn't found? - if not, boolean return?
		connectVertices(getVertex(parentVertexId),getVertex(childVertexId));
		
	}
}
