package GraphicMap;

import java.util.Random;
import Structures.*;
import Structures.Malla.*;

public class BotGenerator implements Runnable {
	private LinkedMatrix matrix;
	private GenericLinkedList<Troncycle> currentBotList;
	private int maxBots, numRows, numCols;
	private int waitTime = 5000;

	public BotGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BotGenerator(LinkedMatrix matrix, int maxBots, int waitTime) {
		super();
		this.matrix = matrix;
		this.maxBots = maxBots;
		this.waitTime = waitTime;
		this.numRows = matrix.getNumRows();
		this.numCols = matrix.getNumCols();
		this.currentBotList = new GenericLinkedList<>();

	}

	public Troncycle randomBot() {
		Troncycle newBot = new Troncycle(Player.bot, 0, 0);
		tryPlaceHead(newBot.getTrail().getHead().getData());
		newBot.setCurrentDirection(Direction.up);
		setRandomDirection(newBot);
		return newBot;
	}

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

	private boolean addMatrixItem(Item itemToAdd) {
		boolean result = false;
		Nodo currentNodo = matrix.getNodo(itemToAdd.getIndexI(), itemToAdd.getIndexJ());
		if (currentNodo.getItem() == null) {
			currentNodo.setItem(itemToAdd);
			result = true;
		}
		return result;
	}

	private Item tryPlaceHead(Item headToPlace) {
		// Item headToPlace = bot.getTrail().getHead().getData();
		while (!addMatrixItem(headToPlace)) {
			int indexI = randInt(0, numRows - 1);
			int indexJ = randInt(0, numCols - 1);
			headToPlace.setIndexI(indexI);
			headToPlace.setIndexJ(indexJ);
		}
		return headToPlace;
	}

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

	private void fillBotList() {
		int botsToAdd = this.maxBots - currentBotList.getSize();
		for (int i = 0; i <= botsToAdd; i++) {
			currentBotList.add(randomBot());
		}

	}

	private int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

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
