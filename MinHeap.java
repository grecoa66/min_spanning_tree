/*
 * Purpose: Design and Analysis of Algorithms Assignment 4 Problem 1
 * Status: Complete and thoroughly tested
 * Last update: 04/14/15
 * Submitted:  04/22/15
 * Comment: test suite and sample run attached
 * @author: Alex Greco
 * @version: 2015.04.15
 */
public class MinHeap implements MinHeapInterface {
	private Node[] heap;// keep track of the values in the nodes.
	// private int[] nodeTracker;//keep track of which node is where.
	private int heapSize;

	public MinHeap(int size) {
		heap = new Node[size];
		// nodeTracker = new int[size];
		heapSize = 0;
	}

	/**
	 * Returns the smallest item in the min heap
	 * 
	 * @return the top of the min heap
	 */
	public int getMinimum() {
		if (isEmpty())
			throw new HeapException("Heap is empty");
		else
			return heap[0].getValue();
	}

	/**
	 * Check to see of the min heap is empty or not
	 */
	public boolean isEmpty() {
		return (heapSize == 0);
	}

	/**
	 * This gets the left child of the current node that you are looking at
	 * 
	 * @param nodeIndex
	 *            The node that you are looking at
	 * @return the index of the left child of the current node that you are
	 *         looking at
	 */
	private int getLeftChildIndex(int nodeIndex) {
		return 2 * nodeIndex + 1;
	}

	/**
	 * This gets the right child of the current node that you are looking at
	 * 
	 * @param nodeIndex
	 *            The node that you are looking at
	 * @return the index of the right child of the current node that you are
	 *         looking at
	 */
	private int getRightChildIndex(int nodeIndex) {
		return 2 * nodeIndex + 2;
	}

	/**
	 * This gets the parent node of the current node that you are looking at.
	 * This method will be useful when you are trying to bubble up nodes to
	 * retain the order of the min heap.
	 * 
	 * @param nodeIndex
	 *            The node that you are loooing at.
	 * @return the index of the parent node of the current node you are looking
	 *         at.
	 */
	private int getParentIndex(int nodeIndex) {
		return (nodeIndex - 1) / 2;
	}

	/**
	 * Insert items into the min heap at the last possible spot. If it is
	 * smaller than its parent, bubble it up. Repeat the last step until the
	 * item is in the correct spot
	 */
	public int insert(Node vertex) {
		int returnIndex = 0;
		if (heapSize == heap.length) {
			throw new HeapException("The Heap is Full");
		} else {
			heapSize++;
			// keeps track of values for distances
			heap[heapSize - 1] = vertex;
			// bubble up the current node and return the index where it ends up
			boolean correctSpot = false;
			int nodeIndex = heapSize - 1;// Initialize for the first time
			// until the node is in the correct spot, bubble it up
			while (correctSpot == false) {
				int parentIndex;
				Node tempVal;
				// go until a root is established or the nodes in the right
				// spot
				if (nodeIndex != 0) {
					parentIndex = getParentIndex(nodeIndex);
					if (heap[parentIndex].getValue() > heap[nodeIndex]
							.getValue()) {
						// copy the parent because it needs to be trickled down
						tempVal = heap[parentIndex];
						// put the smaller value in the parents spot
						heap[parentIndex] = heap[nodeIndex];
						// put the old parent where the smaller value was
						heap[nodeIndex] = tempVal;
						returnIndex = parentIndex;
						nodeIndex = parentIndex;
					} else {
						correctSpot = true;
					}
				}
				// if its the root node, its in the right spot
				else {
					correctSpot = true;
				}
			}
			return returnIndex;
		}
	}

	/**
	 * Remove the root node. Place the last node in the root and compare it to
	 * its children. Swap it with the smaller of the two. Continue this step
	 * until all of the nodes are in the correct spots.
	 */
	public Node deleteMin() {
		Node minNode;
		if (isEmpty()) {
			System.out.println("The Heap is Empty");
			Node falseNode = new Node(0, 0, 0);
			return falseNode;
		} else {
			minNode = heap[0];
			heap[0] = heap[heapSize - 1];
			heapSize--;
			if (heapSize > 0) {
				int nodeIndex = 0;
				boolean correctSpot = false;
				int minIndex = 0;// min of the two children
				Node tempIndex;
				// trickle down the new root node until it is in the correct
				// spot
				while (correctSpot == false) {
					int leftChildIndex = getLeftChildIndex(nodeIndex);
					int rightChildIndex = getRightChildIndex(nodeIndex);
					if (rightChildIndex >= heapSize) {
						// if the left child is not in the heap, you have
						// trickled down as far as you can
						if (leftChildIndex >= heapSize) {
							break;
						} else {
							minIndex = leftChildIndex;
						}
					} else {
						// see which child is smaller, will be used to trickle
						// down
						if (heap[leftChildIndex].getValue() <= heap[rightChildIndex]
								.getValue())
							minIndex = leftChildIndex;
						else
							minIndex = rightChildIndex;
					}
					// if the current node is bigger then one of its children,
					// swap them
					if (heap[nodeIndex].getValue() > heap[minIndex].getValue()) {
						//
						tempIndex = heap[minIndex];
						//
						heap[minIndex] = heap[nodeIndex];

						//
						heap[nodeIndex] = tempIndex;
						nodeIndex = minIndex;
					} else {
						correctSpot = true;
					}
				}

			}

		}
		return minNode;
	}

	public boolean decreaseKey(int index, int value, int newParent) {
		// heap[index].setValue(value);
		boolean found = false;
		int i = 0;
		while (!found) {
			//loop untill you find correct index
			if (heap[i].getVertex() == index) {
				//check if the new value is smaller than current value
				if (value > heap[i].getValue()) {
					//if its not, return false and exit the method
					return false;
				}
				//If it is replace the value and bubble up
				else {
					heap[i].setValue(value);
					heap[i].setParent(newParent);
					found = true;
				}
			} else {
				i++;
			}
		}
		boolean correctSpot = false;
		int nodeIndex = i;// Initialize for the first time
		// until the node is in the correct spot, bubble it up
		while (correctSpot == false) {
			int parentIndex;
			Node tempVal;
			// go until a root is established or the nodes in the right
			// spot
			if (nodeIndex != 0) {
				parentIndex = getParentIndex(nodeIndex);
				if (heap[parentIndex].getValue() > heap[nodeIndex].getValue()) {
					// copy the parent because it needs to be trickled down
					tempVal = heap[parentIndex];
					// put the smaller value in the parents spot
					heap[parentIndex] = heap[nodeIndex];
					// put the old parent where the smaller value was
					heap[nodeIndex] = tempVal;
					// returnIndex = parentIndex;
					nodeIndex = parentIndex;
				} else {
					correctSpot = true;
				}
			}
			// if its the root node, its in the right spot
			else {
				correctSpot = true;
			}
		}
		return correctSpot;
	}

	public String toString() {
		String heapSummary = "";
		for (int i = 0; i < heapSize; i++) {
			heapSummary += (heap[i].getData());
		}
		return heapSummary;
	}

	public static void main(String[] args) {
		// MinHeap minHeap = new MinHeap(10);
		// Node n1 = new Node(0, 5);
		// Node n2 = new Node(0, 7);
		// Node n3 = new Node(0, 3);
		// Node n4 = new Node(0, 12);
		// Node n5 = new Node(0, 2);
		//
		// minHeap.insert(n1);
		// minHeap.insert(n2);
		// minHeap.insert(n3);
		// minHeap.insert(n4);
		// minHeap.insert(n5);
		//
		// minHeap.deleteMin();
		//
		// System.out.println(minHeap.toString());
	}

}
