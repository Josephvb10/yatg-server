package Comunication;

import java.util.ArrayList;

import Structures.*;

/**
 * Message structure created to be parsed into a json without problems. Contains
 * the information about the matrix and the player.
 * 
 * @author gsegura96
 *
 */
public class OutputMessage {
	private int id;
	private static int nextid = 0;
	private SimplePlayer player;
	private ArrayList<Item> itemList, powerupsList;

	/**
	 * Constructs an empty OutputMessage.
	 */
	public OutputMessage() {
		super();
	}

	/**
	 * Constructs an OutputMessage with the specified properties.
	 * @param player {@link SimplePlayer} containing the data of the player.
	 * @param itemList List of all the items placed in the matrix.
	 * @param powerupsList powerupsStack of the player
	 */
	public OutputMessage(SimplePlayer player, ArrayList<Item> itemList, ArrayList<Item> powerupsList) {
		super();
		this.player = player;
		this.itemList = itemList;
	}

	/**Constructs an OutputMessage  importing the data from an {@link Troncycle} object and a {@link GenericLinkedList} for the matrix items.
	 * @param player Player to be imported.
	 * @param itemList GenericLinkedList to be imported.
	 */
	public OutputMessage(Troncycle player, GenericLinkedList<Item> itemList) {
		super();
		id = getNextid();
		importPlayer(player);
		importItemList(itemList);
		importPowerupsList(player.getPowerUpStack());

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private static int getNextid() {
		return ++nextid;
	}

	public static void setNextid(int nextid) {
		OutputMessage.nextid = nextid;
	}

	public SimplePlayer getPlayer() {
		return player;
	}

	public void setPlayer(SimplePlayer player) {
		this.player = player;
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;

	}

	public ArrayList<Item> getpowerupsList() {
		return powerupsList;
	}

	public void setpowerupsList(ArrayList<Item> powerupsList) {
		this.powerupsList = powerupsList;
	}

	/**
	 * Creates and sets a {@link SimplePlayer} from a {@link Troncycle} object.
	 * @param player Player to be imported.
	 */
	public void importPlayer(Troncycle player) {
		SimplePlayer simplePlayer = new SimplePlayer(player.getOwner(), player.getSpeed(), player.getFuel(),
				player.getCurrentDirection(), player.getExtraTrail(), player.getPowerUpSteps(), player.getIsDead(),
				player.getPowerUpActivated(), player.isShieldActivated(), player.isSpeedActivated());
		setPlayer(simplePlayer);

	}

	/**
	 * Creates and sets a {@link ArrayList} from a {@link GenericLinkedList} object, as the itemList.
	 * @param itemList List to be imported.
	 */
	public void importItemList(GenericLinkedList<Item> itemList) {
		GenericNode<Item> current = itemList.getHead();
		ArrayList<Item> newItemList = new ArrayList<>();
		while (current != null) {
			newItemList.add(current.getData());

			current = current.getNext();

		}
		this.itemList = newItemList;
	}

	/**
	 * Creates and sets a {@link ArrayList} from a {@link GenericStack} object, as the powerupsList.
	 * @param genericStack Stack to be imported.
	 */
	public void importPowerupsList(GenericStack<Item> genericStack) {
		GenericNode<Item> current = genericStack.getHead();
		ArrayList<Item> newPowerupsList = new ArrayList<>();
		while (current != null) {
			newPowerupsList.add(current.getData());
			current = current.getNext();
		}
		this.setpowerupsList(newPowerupsList);
	}

	/**
	 * Constructs a json structure from the current OutputMessage instance.
	 * @return
	 */
	public String toJson() {
		String messageJson = JsonConverter.objectToJson(this);
		return messageJson;
	}

}
