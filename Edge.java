package mindBankDAG;

//===================================================================== Edge Class ===================================================================== \\
class Edge{
	private Vertex parent;
	private Vertex child;
	
	public Edge(Vertex parent, Vertex child) {
		this.parent = parent;
		this.child = child;
	}
	
	public Vertex getParent() {
		return parent;
	}
	
	public Vertex getChild() {
		return child;
	}
	
	public int getParentId(){
		return parent.getId();
	}
	
	public int getChildId(){
		return child.getId();
	}
}