package Structures;

/**
 * Generic queue, extends {@link GenericLinkedList}.
 * 
 * @param <T>
 *            Type of the elements to be contained.
 * @author gsegura96
 */
public class GenericQueue<T> extends GenericLinkedList<T> {

	/**
	 * Constructs an empty queue.
	 */
	public GenericQueue() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Inserts an element at the end of the queue.
	 * 
	 * @param data
	 *            Element to be added to the queue.
	 */
	public void enqueue(T data) {
		this.add(data);
	}

	/**
	 * Deletes and returns the first element of the queue.
	 * 
	 * @return The first element of the list.
	 */
	public T dequeue() {
		T currentData = null;

		if (!this.isEmpty()) {
			currentData = this.getHead().getData();
			this.deleteAtPosition(0);
		}
		return currentData;
	}
}
