package GraphicMap;

import java.util.Random;
import Structures.*;
import Structures.Malla.*;

/**
 * Class in charge of controlling the game bots.
 * @author gsegura96
 *
 */
public class BotGenerator implements Runnable {
	private LinkedMatrix matrix;
	private GenericLinkedList<Troncycle> currentBotList;
	private int maxBots, numRows, numCols;
	private int waitTime = 5000;

	/**
	 * Constructs an empty BotGenerator
	 */
	public BotGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a BotGenerator with the specified properties.
	 * @param matrix Current game matrix.
	 * @param maxBots Max number of bots playing at the same time.
	 * @param waitTime Wait time for the bots after each step.
	 */
	public BotGenerator(LinkedMatrix matrix, int maxBots, int waitTime) {
		super();
		this.matrix = matrix;
		this.maxBots = maxBots;
		this.waitTime = waitTime;
		this.numRows = matrix.getNumRows();
		this.numCols = matrix.getNumCols();
		this.currentBotList = new GenericLinkedList<>();

	}

	/**
	 * Creates and place a new bot on the matrix.
	 * @return
	 */
	public Troncycle randomBot() {
		Troncycle newBot = new Troncycle(Player.bot, 0, 0);
		tryPlaceHead(newBot.getTrail().getHead().getData());
		newBot.setCurrentDirection(Direction.up);
		setRandomDirection(newBot);
		return newBot;
	}

	/**Generates a new direction for the specified bot.
	 * @param bot to set a new direction.
	 */
	public void setRandomDirection(Troncycle bot) {
		switch (randInt(0, 10)) {
		case 0:
			if (bot.getCurrentDirection() != Direction.down) {
				bot.setCurrentDirection(Direction.up);
			}
			break;
		case 1:
			if (bot.getCurrentDirection() != Direction.up) {
				bot.setCurrentDirection(Direction.down);
			}
			break;
		case 2:
			if (bot.getCurrentDirection() != Direction.right) {
				bot.setCurrentDirection(Direction.left);
			}
			break;
		case 3:
			if (bot.getCurrentDirection() != Direction.left) {
				bot.setCurrentDirection(Direction.right);
			}
			break;

		default:
			break;
		}
	}

	/**
	 * Tries to place the specified item at it's coordinates(indexI, indexJ).
	 * @param itemToAdd Item to try placing
	 * @return true if the item was placed in an empty node.
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
	public Item tryPlaceHead(Item headToPlace) {
		// Item headToPlace = bot.getTrail().getHead().getData();
		while (!addMatrixItem(headToPlace)) {
			int indexI = randInt(0, numRows - 1);
			int indexJ = randInt(0, numCols - 1);
			headToPlace.setIndexI(indexI);
			headToPlace.setIndexJ(indexJ);
		}
		return headToPlace;
	}

	/**
	 * Deletes all the dead bots from the list.
	 */
	private void checkBotList() {
		GenericNode<Troncycle> current = currentBotList.getHead();
		int cont = 0;
		while (current != null) {
			if (current.getData().getIsDead()) {
				currentBotList.deleteAtPosition(cont);
			} else {
				cont++;
			}
			current = current.getNext();
		}
	}

	/**
	 * Refill the list with new bots.
	 */
	private void fillBotList() {
		int botsToAdd = this.maxBots - currentBotList.getSize();
		for (int i = 0; i < botsToAdd; i++) {
			currentBotList.add(randomBot());
		}

	}

	private int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	/**
	 * Controls the workflow of the bots. cleans the dead ones, refill the list and applies UpdatePlayer to all bots.
	 */
	private void updateBots() {
		checkBotList();
		fillBotList();
		GenericNode<Troncycle> current = currentBotList.getHead();
		while (current != null) {
			
			setRandomDirection(current.getData());

			matrix.updatePlayer(current.getData());
			current = current.getNext();
		}
	}

	@Override
	public void run() {
		while(true){
		updateBots();
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}}

}
