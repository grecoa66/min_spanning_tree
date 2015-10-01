/**
 * This is just an easy exception class for things like, trying to add to a full
 * heap
 * 
 * @author Alex Greco
 *
 */
public class HeapException extends RuntimeException {
	public HeapException(String message) {
		super(message);
	}

}