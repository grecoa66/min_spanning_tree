/*
 * Purpose: Design and Analysis of Algorithms Assignment 4 Problem 1
 * Status: Complete and thoroughly tested/Incomplete/ Barely started
 * Last update: 04/14/15
 * Submitted:  04/14/15
 * Comment: test suite and sample run attached
 * @author: Alex Greco
 * @version: 2015.04.14
 */
public class AdjacencyList {

	// and array of linked lists
	public LinkedList[] adjList;
	public int numVertice;

	public AdjacencyList(int n) {
		numVertice = n;
		// create an adjacency list with n spots
		adjList = new LinkedList[n];
		// make all entries in the array LinkedLists
		for (int i = 0; i < n; i++) {
			adjList[i] = new LinkedList();
		}
	}

	/**
	 * Specify which vertex you are connecting from. Specify which Node you want
	 * to add. Put the Node at the end of the linkedlist
	 * 
	 * @param V
	 *            What vertex you are adding the edge from
	 * @param vertex
	 *            The node from which this edge is coming from
	 * @param value
	 *            The weighted value for that edge
	 */
	public void addEdge(int V, int vertex, int value) {
		adjList[V].add(adjList[V].size(), vertex, V, value);
		adjList[vertex].add(adjList[vertex].size(), V, vertex, value);
	}

	/**
	 * 
	 * @param V
	 *            the vertice you want to remove an edge from
	 * @param j
	 *            the node that you want to remove
	 * @return
	 */
	public Boolean removeEdge(int V, Node j) {
		boolean isRemoved = false;
		for (int k = 0; k < adjList[V].size(); k++) {
			if (j.equals(adjList[V].get(k))) {
				adjList[V].remove(k);
				isRemoved = true;
			}
		}
		return isRemoved;
	}

	public int getNodeValue(int V, int index) {
		LinkedList L = adjList[V];
		Node node = L.get(index);
		int nodeValue = node.getValue();
		return nodeValue;
	}
	
	public Node getNode(int V, int index){
		LinkedList L = adjList[V];
		Node node = L.get(index);
		return node;
	}
	
	public int listLen(int index){
		LinkedList L = adjList[index];
		return L.size();
	}

	public String toString() {
		String result = "Adj List: \n";
		for (int i = 0; i < numVertice; i++) {
			result += i + ".)" + adjList[i].toString() + "\n";
		}
		return result;
	}

	public static void main(String[] args) {
		
	}

}
