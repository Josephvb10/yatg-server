package GraphicMap;

import Structures.Malla.*;

import java.util.Random;

import Structures.*;

/**
 * Class in charge of controlling the game items and powerups.
 * @author gsegura96
 */
public class ItemGenerator implements  Runnable {
	 
	private LinkedMatrix matrix;
	private int maxItems, numRows, numCols;
	private GenericQueue<Item> currentItemQueue;
	private int waitTime = 5000;

	/**
	 * Constructs an empty ItemGenerator
	 */
	public ItemGenerator() {

		for (int i = 0; i <= maxItems; i++) {
			currentItemQueue.enqueue(randomItem());
		}
	}

	/**
	 * Constructs a ItemGenerator with the specified properties.
	 * @param matrix Current game matrix.
	 * @param maxBots Max number of Item placed at the same time.
	 * @param waitTime Wait time to refresh the itemList.
	 */
	public ItemGenerator(LinkedMatrix matrix, int maxItems, int waitTime) {
		super();
		this.matrix = matrix;
		this.maxItems = maxItems;
		this.waitTime = waitTime;
		this.numRows = matrix.getNumRows();
		this.numCols = matrix.getNumCols();

		currentItemQueue = new GenericQueue<>();
		for (int i = 0; i <= maxItems; i++) {
			currentItemQueue.enqueue(randomItem());
		}
	}

	public LinkedMatrix getMatrix() {
		return matrix;
	}

	public void setMatrix(LinkedMatrix matrix) {
		this.matrix = matrix;
	}

	public int getMaxItems() {
		return maxItems;
	}

	public void setMaxItems(int maxItems) {
		this.maxItems = maxItems;
	}

	public GenericQueue<Item> getCurrentItemQueue() {
		return currentItemQueue;
	}

	public void setCurrentItemQueue(GenericQueue<Item> currentItemQueue) {
		this.currentItemQueue = currentItemQueue;
	}

	/**
	 * Generates a random Item.
	 * @return A random Item
	 */
	private Item randomItem() {
		int indexI = randInt(0, numRows - 1);
		int indexJ = randInt(0, numCols - 1);
		Item newItem = new Item();
		newItem.setIndexI(indexI);
		newItem.setIndexJ(indexJ);
		switch (randInt(1, 8)) {
		case 1:
			newItem.setType(ItemType.bomb);
			break;
		case 2:
			newItem.setType(ItemType.turbo);
			break;
			
		case 3:
			newItem.setType(ItemType.increaseTail);
			break;
		case 4:
			newItem.setType(ItemType.shield);
			break;
		
		default:
			newItem.setType(ItemType.fuel);
			break;
		}

		return newItem;
	}

	private int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	/*Removes an item from the matrix.
	 * @param itemToRemove Item to be removed.
	 */
	private void removeMatrixItem(Item itemToRemove) {
		Nodo currentNodo = matrix.getNodo(itemToRemove.getIndexI(), itemToRemove.getIndexJ());
		if (currentNodo.getItem() == itemToRemove) {
			currentNodo.setItem(null);
		}
	}

	/**
	 * Try to place an item on the matrix.
	 * @param itemToAdd Item to be placed
	 * @return true if the item was placed
	 */
	private boolean addMatrixItem(Item itemToAdd) {
		boolean result = false;
		Nodo currentNodo = matrix.getNodo(itemToAdd.getIndexI(), itemToAdd.getIndexJ());
		if (currentNodo.getItem() == null) {
			currentNodo.setItem(itemToAdd);
			result = true;
		}
		return result;
	}

	/**Tries to place an Item until it's placed.
	 * @param headToPlace Item to place.
	 * @return The placed item.
	 */
	private Item tryPlaceItem() {
		Item itemToPlace = randomItem();
		while (!addMatrixItem(itemToPlace)) {
			int indexI = randInt(0, numRows - 1);
			int indexJ = randInt(0, numCols - 1);
			itemToPlace.setIndexI(indexI);
			itemToPlace.setIndexJ(indexJ);
		}
		return itemToPlace;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			currentItemQueue.enqueue(tryPlaceItem());
			removeMatrixItem(currentItemQueue.dequeue());
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
