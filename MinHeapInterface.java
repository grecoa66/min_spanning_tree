public interface MinHeapInterface {

   
public boolean isEmpty();
// returns true if MinHeap is empty, false otherwise

public int insert(Node vertex);
//inserts Node in MinHeap and returns index in the array where it ended up

public Node deleteMin();
//returns true if min was removed successfully, false otherwise

public boolean decreaseKey(int index, int value, int newParent);
//decreases to value the priority of the item in the specified index and returns the index in the array where the item ended up


}// end MinHeapInterface