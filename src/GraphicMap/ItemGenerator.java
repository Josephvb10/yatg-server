package GraphicMap;

import Structures.Malla.*;

import java.util.Random;

import Structures.*;

public class ItemGenerator extends Thread {
	private static ItemGenerator instance;
	private static LinkedMatrix matrix;
	private static int maxItems, numRows, numCols;
	private static GenericQueue<Item> currentItemQueue;

	protected ItemGenerator(){
		for(int i = 0; i<=5; i++){
			currentItemQueue.enqueue(randomItem());
		}
	}

	public static ItemGenerator getInstance() {
		if (instance == null) {
			instance = new ItemGenerator();
		}
		return instance;
	}

	public static LinkedMatrix getMatrix() {
		return matrix;
	}

	public static void setMatrix(LinkedMatrix matrix) {
		ItemGenerator.matrix = matrix;
	}

	public static int getMaxItems() {
		return maxItems;
	}

	public static void setMaxItems(int maxItems) {
		ItemGenerator.maxItems = maxItems;
	}

	public static GenericQueue<Item> getCurrentItemQueue() {
		return currentItemQueue;
	}

	public static void setCurrentItemQueue(GenericQueue<Item> currentItemQueue) {
		ItemGenerator.currentItemQueue = currentItemQueue;
	}

	private static Item randomItem() {
		int indexI = randInt(0, numRows - 1);
		int indexJ = randInt(0, numCols - 1);
		Item newItem = new Item();
		newItem.setIndexI(indexI);
		newItem.setIndexJ(indexJ);
		switch (randInt(1, 5)) {
		case 1:
			newItem.setType(ItemType.bomb);
			break;
		case 2:
			newItem.setType(ItemType.fuel);
			break;
		case 3:
			newItem.setType(ItemType.increaseTail);
			break;
		case 4:
			newItem.setType(ItemType.shield);
			break;
		case 5:
			newItem.setType(ItemType.turbo);
			break;

		default:
			break;
		}

		return newItem;
	}

	private static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	private static void removeMatrixItem(Item itemToRemove) {
		Nodo currentNodo = matrix.getNodo(itemToRemove.getIndexI(), itemToRemove.getIndexJ());
		if (currentNodo.getItem() == itemToRemove) {
			currentNodo.setItem(null);
		}
	}

	private static boolean addMatrixItem(Item itemToAdd) {
		boolean result=false;
		Nodo currentNodo = matrix.getNodo(itemToAdd.getIndexI(), itemToAdd.getIndexJ());
		if (currentNodo.getItem()==null) {
			currentNodo.setItem(itemToAdd);
			result=true;
		}
		return result;
	}
	private static Item tryPlaceItem(){
		Item itemToPlace = randomItem();
		while(!addMatrixItem(itemToPlace)){
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
				this.wait(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		}

	}

}
