package Structures.Malla;

import GraphicMap.Screen1;
import Structures.*;
/**
 * Matrix class that generates the map of the game
 *  
 *  
 * @author Jimena
 * @author gsegura96
 * 
 *
 */

public class LinkedMatrix {
	private Nodo head;
	private int numRows, numCols, numNodes;
/**
 * Constructor that takes the number of columns and rows of the matrix
 * @param numRows
 * @param numCols
 */
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
/**
 * Creates a simple list and assigns one index to each (@Nodo), then it calls the function that
 * joins the nodes
 * @return  the four-way linked list
 */
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
/**
 * It looks for a (@Nodo) in the matrix using its index  and returns it
 * @param indexI position i in the matrix of the node
 * @param indexJ position i in the matrix of the node
 * @return the node that has the i and j index that was introduced in the parameters
 */
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
/**
 * It is in charge of assigning the pointers that reference other (@Nodo) according to the direction and index
 */
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
/**
 * It assigns to a (@Nodo) an (@Item)
 * @param item the item that will be assigned 
 */
	public void setNodeItem(Item item) {
		int indexI = item.getIndexI();
		int indexJ = item.getIndexJ();
		getNodo(indexI, indexJ).setItem(item);
	}
/**
 * Removes the item of a specific (@Nodo)
 * @param indexI position i in the matrix of the node
 * @param indexJ position j in the matrix of the node
 */
	public void resetNodeItem(int indexI, int indexJ) {
		getNodo(indexI, indexJ).setItem(null);
	}

/**
 * Removes the item of a specific (@Nodo)
 * @item  item to be removed
 */
	public void resetNodeItem(Item item) {
		int indexI = item.getIndexI();
		int indexJ = item.getIndexJ();
		resetNodeItem(indexI, indexJ);
	}

/**
 * It is in charge  of verifying the (@Nodo) in which the Troncycle step, it adds the powerUp
 * or the item to the queue or the stack depending on the type.	
 * @param player
 */
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
			//System.out.println(player.getCurrentDirection());

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

/**
 * It removes the player of the screen.
 * @param player player that's going to be removed
 */
	private void cleanDeadPlayer(Troncycle player) {
		GenericNode<Item> current = player.getTrail().getHead();
		while (current != null) {
			this.resetNodeItem(current.getData());
			current = current.getNext();

		}
	}

/**
 * Checks the amount of fuel that the player has left, if it is less than zero, it cleans the dead player.
 * @param player the (@SimplePlayer) that will be checked
 * @return whether the player is out of fuel or not
 */
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
		//System.out.println(direction);
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
