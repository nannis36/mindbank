package mindBankDAG;

import java.util.HashMap;
import java.util.Map;

class Vertex {
	private int id;
	private Map<Integer,Vertex> parentVertices;
	private Map<Integer,Vertex> childVertices;
	
	public Vertex(int id) {
		this.id = id;
		parentVertices = new HashMap<Integer,Vertex>();
		childVertices = new HashMap<Integer,Vertex>();
	}
	
	public int getId() {
		return id;
	}
	
	public Vertex getParentVertex(int id) {
		return parentVertices.get(id);
	}
	
	public Map<Integer,Vertex> getParentVertices(){
		return parentVertices;
	}
	
	public Vertex getChildVertex(int id) {
		return childVertices.get(id);
	}
	
	public Map<Integer,Vertex> getChildVertices(){
		return childVertices;
	}
	
	public boolean hasParentVertex(Vertex v) {
		return parentVertices.get(v.getId()) != null;
	}
	
	public boolean hasChildVertex(Vertex v) {
		return childVertices.get(v.getId()) != null;
	}
	
	public void addParentVertex(Vertex v) {
		parentVertices.put(v.getId(),v);
	}
	
	public void addChildVertex(Vertex v) {
		childVertices.put(v.getId(),v);
	}
	
	public void removeParentVertex(Vertex v) {
		parentVertices.remove(v.getId());
	}
	
	public void removeChildVertex(Vertex v) {
		childVertices.remove(v.getId());
	}
	
	public void delete() {
		// remove self from all parent vertex references
		for(Vertex v : parentVertices.values()) {
			v.removeChildVertex(this);
		}
		// remove self from all child vertex references
		for(Vertex v : childVertices.values()) {
			v.removeParentVertex(this);
		}
		// null out own parent and child vertex references
		parentVertices = null;
		childVertices = null;
	}
	
}