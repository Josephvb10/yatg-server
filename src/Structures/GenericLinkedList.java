package Structures;

public class GenericLinkedList<T> {
	private GenericNode<T> head;

	public GenericLinkedList() {
		super();
	}

	public GenericNode<T> getHead() {
		return head;
	}

	public void setHead(GenericNode<T> head) {
		this.head = head;
	}

	public void add(GenericNode<T> newNode) {
		this.getLast().setNext(newNode);
	}

	private GenericNode<T> getLast() {
		GenericNode<T> current = head;
		if (head != null) {
			while (current.getNext() != null) {
				current = current.getNext();
			}

		}
		return current;
	}

	public int getSize() {
		GenericNode<T> current = head;
		int cont = 0;
		while (current != null) {
			cont++;
			current = current.getNext();
		}
		return cont;
	}

	public GenericNode<T> getAtPosition(int position) {
		GenericNode<T> current = head;
		GenericNode<T> result = null;
		int cont = 0;

		if (this.checkPosition(position)) {
			while (cont < position) {
				current = current.getNext();
				cont++;
			}
			result = current;
		}

		return result;
	}

	public boolean insertAtPosition(GenericNode<T> newNode, int position) {
		GenericNode<T> current = head;
		int cont = 0;
		boolean result = false;
		if (this.checkPosition(position)) {
			if (position == 0) {
				this.insertAtBeggining(newNode);
				result = true;
			} else {
				while (cont < position) {
					current = current.getNext();
					cont++;
				}
				newNode.setNext(current.getNext());
				current.setNext(newNode);
				result = true;
			}
		}
		return result;
	}

	public boolean deleteAtPosition(int position) {
		GenericNode<T> current = head;
		boolean result = false;
		if (this.checkPosition(position)) {
			if (position == 0) {
				head = head.getNext();
				result = true;
			} else if (position == this.getSize() - 1) {
				this.getAtPosition(position - 1).setNext(null);
			}

			else {
				current = this.getAtPosition(position - 1);
				current.setNext(current.getNext().getNext());
			}

			result = true;
		}
		return result;
	}

	

	private boolean checkPosition(int position) {
		return position >= 0 && position < this.getSize();
	}

	public void insertAtBeggining(GenericNode<T> newNode) {
		newNode.setNext(this.head);
		this.head = newNode;
	}

}
