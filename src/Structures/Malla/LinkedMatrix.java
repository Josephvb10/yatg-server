package Structures.Malla;

import GraphicMap.Screen1;
import Structures.*;

public class LinkedMatrix {
	private Nodo head;
	private int numRows, numCols, numNodes;

	public LinkedMatrix(int numRows, int numCols) {
		this.head = null;
		this.numRows = numRows;
		this.numCols = numCols;
		this.numNodes = numRows * numCols;
		simpleUnlinkedList();
		return;
	}

	public Nodo getHead() {
		return head;
	}

	public void setHead(Nodo head) {
		this.head = head;
	}

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getNumCols() {
		return numCols;
	}

	public void setNumCols(int numCols) {
		this.numCols = numCols;
	}

	public int getNumNodes() {
		return numNodes;
	}

	public void setNumNodes(int numNodes) {
		this.numNodes = numNodes;
	}

	public Nodo simpleUnlinkedList() {
		int i, jj;
		int nodos = 1;
		this.head = new Nodo();
		Nodo temp = this.head;
		while (nodos < this.numNodes) {
			for (i = 0; i < this.numRows; i++) {
				if (i == 0)
					jj = 1;
				else {
					jj = 0;
				}
				for (int j = jj; j < this.numCols; j++) {
					Nodo nuevoNodo = new Nodo();
					nuevoNodo.setIndex(i, j);
					temp.setNext(nuevoNodo);
					temp = temp.getNext();
					nodos++;
					if (nodos == this.numNodes)
						break;
				}
				if (nodos == this.numNodes)
					break;
			}
		}

		fourWayLinkedList();
		return this.head;
	}

	public Nodo getNodo(int indexI, int indexJ) {
		Nodo temp = this.head;
		Nodo result = null;
		while (temp != null) {
			if (temp.getIndexI() == indexI && temp.getIndexJ() == indexJ) {
				result = temp;
				break;
			}
			temp = temp.getNext();
		}
		if (result == null) {
			System.out.println("No esta el indice");
		}
		return result;
	}

	public void fourWayLinkedList() {
		Nodo temp = this.head;
		// up
		while (temp != null) {
			if (temp.getIndexI() == 0) {
				temp.setUp(getNodo(this.numRows - 1, temp.getIndexJ()));
			} else {
				temp.setUp(getNodo(temp.getIndexI() - 1, temp.getIndexJ()));
			}
			temp = temp.getNext();
		}

		// down
		temp = this.head;
		while (temp != null) {
			if (temp.getIndexI() == this.numRows - 1) {
				temp.setDown(getNodo(0, temp.getIndexJ()));
			} else {
				temp.setDown(getNodo(temp.getIndexI() + 1, temp.getIndexJ()));
			}

			temp = temp.getNext();
		}

		// right
		temp = this.head;
		while (temp != null) {
			if (temp.getIndexJ() == this.numCols - 1) {
				temp.setRight(getNodo(temp.getIndexI(), 0));
			} else {
				temp.setRight(getNodo(temp.getIndexI(), temp.getIndexJ() + 1));
			}

			temp = temp.getNext();
		}

		// left
		temp = this.head;
		while (temp != null) {
			if (temp.getIndexJ() == 0) {
				temp.setLeft(getNodo(temp.getIndexI(), this.numCols - 1));
			} else {
				temp.setLeft(getNodo(temp.getIndexI(), temp.getIndexJ() - 1));
			}

			temp = temp.getNext();
		}

	}

	public void setNodeItem(Item item) {
		int indexI = item.getIndexI();
		int indexJ = item.getIndexJ();
		getNodo(indexI, indexJ).setItem(item);
	}

	public void resetNodeItem(int indexI, int indexJ) {
		getNodo(indexI, indexJ).setItem(null);
	}

	public void resetNodeItem(Item item) {
		int indexI = item.getIndexI();
		int indexJ = item.getIndexJ();
		getNodo(indexI, indexJ).setItem(null);
	}

	public void updatePlayer(Troncycle player) {
		int threadTime = 300/player.getSpeed();
		Screen1.targetTime = threadTime;
		if (player.getIsDead() == false && checkPlayerFuel(player)) {
			player.reducePowerUp();

			GenericNode<Item> current = player.getTrail().getHead();

			current = player.getTrail().getHead();
			Item first = current.getData();
			int indexI = first.getIndexI();
			int indexJ = first.getIndexJ();
			System.out.println(player.getCurrentDirection());

			Nodo nodoToCheck = getNextNode(indexI, indexJ, player.getCurrentDirection());
			Item itemToCheck = nodoToCheck.getItem();
			if (itemToCheck != null) {
				switch (itemToCheck.getType()) {

				case increaseTail:
					player.addItem(nodoToCheck.getItem());

					break;
				case fuel:
					player.addItem(nodoToCheck.getItem());

					break;
				case shield:
					player.addPowerUp(nodoToCheck.getItem());

					break;
				case turbo:
					player.addPowerUp(nodoToCheck.getItem());

					break;
				case tronTrail:
					if (player.killPlayer()) {
						this.cleanDeadPlayer(player);
					}
					break;
				case bomb:
					if (player.killPlayer()) {
						this.cleanDeadPlayer(player);
					}

					break;

				default:
					break;
				}
			}
			if (player.getIsDead() == false) {
				player.setFuel(player.getFuel() - 0.2);
				Item deleted = player.deleteTail();
				if (deleted != null) {
					this.resetNodeItem(deleted);
				}
				player.addHead(nodoToCheck.getIndexI(), nodoToCheck.getIndexJ());
				this.setNodeItem(player.getTrail().getHead().getData());

			}
		}

	}

	private void cleanDeadPlayer(Troncycle player) {
		GenericNode<Item> current = player.getTrail().getHead();
		while (current != null) {
			this.resetNodeItem(current.getData());
			current = current.getNext();

		}
	}

	private boolean checkPlayerFuel(Troncycle player) {
		boolean result = true;
		if (player.getFuel() <= 0) {
			player.setIsDead(true);
			this.cleanDeadPlayer(player);
			System.out.println("Me mori por combustible");
			result = false;

		}
		return result;
	}

	public GenericLinkedList<Item> getSimpleItemList() {
		GenericLinkedList<Item> result = new GenericLinkedList<>();
		Nodo current = this.getHead();
		while (current != null) {
			if (current.getItem() != null) {
				result.add(current.getItem());
			}
			current = current.getNext();
		}
		return result;

	}

	public Nodo getNextNode(int indexI, int indexJ, Direction direction) {
		Nodo currentNodo = this.getNodo(indexI, indexJ);
		Nodo result = null;
		System.out.println(direction);
		switch (direction) {
		case down:
			result = currentNodo.getDown();
			break;
		case up:
			result = currentNodo.getUp();

			break;
		case left:
			result = currentNodo.getLeft();

			break;
		case right:
			result = currentNodo.getRight();
			break;

		default:
			break;

		}
		return result;

	}

}
