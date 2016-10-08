package Structures;

/**
 * Generic node structure used by generic linked lists and other classes.
 * @author gsegura96
 * 
 */

public class GenericNode<T>  {
	private T data;
	private GenericNode<T> next;
	
	
	/**
	 * Constructs an empty node.
	 */
	public GenericNode(){
		data=null;
		next=null;
	}
	
	/**
	 * Constructs a node containing one element of the specified type..
	 * @param data Element to be contained by the node.
	 */
	public GenericNode(T data) {
		super();
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public GenericNode<T> getNext() {
		return next;
	}

	public void setNext(GenericNode<T> next) {
		this.next = next;
	}
	 
	
	
}