/**
 * Node class for adjacency list
 * 
 * @author Alex Greco
 *
 */
public class Node {
	private int vertex;
	private int value;
	private int parent;
	private Node next;

	public Node(int newVertex, int newValue, int newParent) {
		vertex = newVertex;
		value = newValue;
		parent = newParent;
		next = null;
	} // end constructor

	public Node(int newVertex, int newValue,int newParent, Node nextNode) {
		vertex = newVertex;
		value = newValue;
		parent = newParent;
		next = nextNode;
	} // end constructor

	public int getVertex() {
		return vertex;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public void setNext(Node N){
		next = N;
	}

	public Node getNext() {
		return next;
	} // end getNext

	public String getData() {

		String data = getParent() +"(" + getVertex() + ", " + getValue() + "), ";
		return data;
	}

} // end class Node