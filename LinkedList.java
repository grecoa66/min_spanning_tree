/*
 *  * Purpose: Data Structure and Algorithms Lab 3 Problem 1
 *   * Status: Complete and thoroughly tested
 *    * Last update: 09/21/14
 *     * Submitted:  09/22/14
 *      * Comment: test suite and sample run attached
 *       * @author: Alex Greco
 *        * @version: 2014.09.21
 *         */
public class LinkedList {
	// reference to linked list of items
	private Node head;

	// private int numItems; //Need to get code working with only one data field
	public LinkedList() {
		// numItems = 0;
		head = null;
	} // end default constructor

	public boolean isEmpty()
    {
        boolean emptyCheck = true;
        if(size() == 0) { 
            emptyCheck = true;
        }
        else {
            emptyCheck = false;
        }
        return emptyCheck;

    }  // end isEmpty

	/**
	 * this needs to be fixed, still working on the logic to traverse through
	 * list and count each node along the way
	 */

	public int size() {
		Node curr = head;// making a curr node out of the head
		int counter = 0;// establishing a count of items in list
		if (head == null) { // when there is no head the list will be empty
			counter = 0;
		} else {
			counter = 1;// if the list isnt empty it has at least 1 item
			while (curr.getNext() != null) { // then this counts how many more
												// items than1 it has
				curr = curr.getNext();
				counter++;
			}
		}
		return counter;
	} // end size

	public Node find(int index) {
		// --------------------------------------------------
		// Locates a specified node in a linked list.
		// Precondition: index is the number of the desired
		// node. Assumes that 0 <= index <= numItems
		// Postcondition: Returns a reference to the desired
		// node.
		// --------------------------------------------------
		Node curr = head;
		for (int skip = 0; skip < index; skip++) {
			curr = curr.getNext();
		} // end for
		return curr;
	} // end find

	public Node get(int index) throws ListIndexOutOfBoundsException {
		if (index >= 0){
			// get reference to node, then data in node
			Node curr = find(index);
//			int dataItem = curr.getValue();
			return curr;
		} else {
			throw new ListIndexOutOfBoundsException(
					"List index out of bounds exception on get");
		} // end if
	} // end get
	
	

	public void add(int index, int vertex, int parent, int value)
			throws ListIndexOutOfBoundsException {
		if (index >= 0)// && index < numItems+1)//once size works use that
						// instead of numItems
		{
			if (index == 0) {
				// insert the new node containing item at
				// beginning of list
				Node newNode = new Node(vertex, value, parent, head);
				head = newNode;
			} else {
				Node prev = find(index - 1);
				// insert the new node containing item after
				// the node that prev references
				Node newNode = new Node(vertex, value,parent, prev.getNext());
				prev.setNext(newNode);
			} // end if
				// numItems++;
		} else {
			throw new ListIndexOutOfBoundsException(
					"List index out of bounds exception on add");
		} // end if
	} // end add

	public void remove(int index) throws ListIndexOutOfBoundsException {
		if (index >= 0)// && index < numItems)
		{
			if (index == 0) {
				// delete the first node from the list
				head = head.getNext();
			} else {
				Node prev = find(index - 1);
				// delete the node after the node that prev
				// references, save reference to node
				Node curr = prev.getNext();
				prev.setNext(curr.getNext());
			} // end if

		} // end if
		else {
			throw new ListIndexOutOfBoundsException(
					"List index out of bounds exception on remove");
		} // end if
	} // end remove

	public void removeAll() {
		// setting head to null causes list to be
		// unreachable and thus marked for garbage
		// collection
		head = null;
		// numItems = 0;
	} // end removeAll

	public String toString() {
		String result = " ";
		Node curr = head;
		if (head != null) {
			result += head.getData();
			while (curr.getNext() != null) {
				curr = curr.getNext();
				result += curr.getData() + " " ;
			}

		} else {
			result = (" The list is empty");
		}
		return result;
	}

	public void setHead(Node newHead) {
		head = newHead;
	}
	
	public static void main(String[]args){
		
	}
} // end ListReferenceBased