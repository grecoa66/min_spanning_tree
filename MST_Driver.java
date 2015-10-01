import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;

public class MST_Driver {
	private static BufferedReader stdin = new BufferedReader(
			new InputStreamReader(System.in));
	private static boolean run = true;
	private static int numV = 0;
	private static int numE = 0;
	private static String MST = "";

	/**
	 * drives the program and asks for user input When the size of the matrix is
	 * determined, call method to populate a 2d array.
	 * 
	 * @throws IOException
	 */
	public static void run() throws IOException {
		while (run == true) {
			System.out
					.println("Enter number of vertices on graph and number of edges"
							+ ", or type 'exit' and submit:");
			String numVInput = stdin.readLine();
			String numEInput = stdin.readLine();
			if (numVInput == "exit") {
				run = false;
			} else {
				// set up an adjacency list and populate it from input
				numV = Integer.parseInt(numVInput);
				numE = Integer.parseInt(numEInput);
				// Initialize the data structures
				AdjacencyList adj = createAdjList(numV, numE);
				MinHeap heap = createMinHeap(numV);
				// start prims Algo
				prims(numV, adj, heap);
				System.out.println(heap.toString());
				System.out.println(MST);
				System.out.println("Path Value: " + pathValue);
			}
		}

	}// end run()

	/**
	 * read all input from user. No need to add both edges in both orders
	 * because the addEdge method in the adjList class takes care if it. Read
	 * pairs of numbers and add weights for the number of edges divided by 2 for
	 * this same reason.
	 * 
	 * @param numV
	 *            the number of vertices
	 * @param numE
	 *            the number of edges
	 * @throws IOException
	 *             to make the buffered reader work
	 */
	public static AdjacencyList createAdjList(int numV, int numE)
			throws IOException {
		// create new adjList with the correct number of vertices
		AdjacencyList adj = new AdjacencyList(numV);
		// next, loop for numE/2 times and add all the edges the user specifies
		for (int i = 0; i < (numE); i++) {
			// get v1, v2, and w(v1, v2) as input and make them integers
			String v1S = stdin.readLine();
			String v2S = stdin.readLine();
			String wS = stdin.readLine();
			int v1 = Integer.parseInt(v1S);
			int v2 = Integer.parseInt(v2S);
			int w = Integer.parseInt(wS);
			// add edges between points going both ways w/ weights
			adj.addEdge(v1, v2, w);
		}
		System.out.println(adj.toString());
		return adj;
	}

	/**
	 * create a MinHeap that has as many nodes as there are vertices in the
	 * graph. Make all the node have a value of Max_Value.
	 * 
	 * @param numV
	 *            number of vertices
	 */
	public static MinHeap createMinHeap(int numV) {
		// create new MinHeap with correct number of nodes in it
		MinHeap heap = new MinHeap(numV);
		// fill all minHeap nodes with vertices number and set the value to
		// MAXINT
		for (int i = 0; i < numV; i++) {
			Node newNode = new Node(i, Integer.MAX_VALUE, i);
			heap.insert(newNode);
		}
		System.out.println(heap.toString());
		return heap;
	}

	/**
	 * This method completes prims algorithm. I took the methodology from
	 * lecture notes and hacked it so that it fit my data structres.
	 * 
	 * @param numV
	 *            the number of vertices in the graph
	 * @param adj
	 *            the Adjacency List that represents the graph
	 * @param heap
	 *            the Min Heap thats used to complete prims algo
	 */
	public static void prims(int numV, AdjacencyList adj, MinHeap heap) {
		boolean[] visited = new boolean[numV];
		for (int i = 0; i < numV; i++) {
			visited[i] = false;
		}
		heap.decreaseKey(0, 0, 0);
		// to avoid bug of 0 being chosen again
		visited[0] = true;
		while (!heap.isEmpty()) {
			// keep the most recently removed node for the rest of the method
			Node U = heap.deleteMin();

			int currParent = U.getVertex();
			visited[U.getVertex()] = true;
			addToMST(U);
			//find all node in U's adjList, try and add them to the MinHeap
			for (int j = 0; j < adj.listLen(currParent); j++) {
				// check to see if the parents have been visited
				// and check if this new path has a short distance
				Node currNode = adj.getNode(currParent, j);
				if (!visited[currNode.getVertex()]
						&& heap.decreaseKey(currNode.getVertex(),
								currNode.getValue(), currNode.getParent())) {
					// I'm not sure if anything happens here from how my minHeap
					// is structured
				}
			}
		}
	}

	// just to keep track of stuff
	// kept them here because they are only relevent to the method below it
	static int count = 0;
	static int pathValue = 0;

	/**
	 * Prints out the visual version of how all the vertices on the graph are
	 * connected. Also keeps track of the total path cost.
	 * 
	 * @param U
	 *            the vertex being added to the MST
	 */
	public static void addToMST(Node U) {
		// add the source then all the connections afterwords
		if (count == 0) {
			MST += "Source: " + U.getParent() + "\n";
		} else {
			MST += "Parent: " + U.getParent() + " Desitination: "
					+ U.getVertex() + " Distance: " + U.getValue() + "\n";
			pathValue += U.getValue();
		}
		count++;
	}

	public static void main(String[] args) throws IOException {
		run();
	}
}
