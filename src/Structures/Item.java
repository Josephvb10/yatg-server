package Structures;

import java.util.Random;

import sun.awt.windows.ThemeReader;

/**
 * @author gsegura96
 *
 */

public class Item implements Comparable<Item> {
	private ItemType type;
	private int indexI;
	private int indexJ;
	private int value;
	private boolean first;
	private Player owner;

	/**
	 * Constructs an empty item.
	 */
	public Item() {
		super();
	}

	/**
	 * Constructs an item of the specified type..
	 * 
	 * @param type
	 *            Type of the Item to be created.
	 */
	public Item(ItemType type) {
		super();
		setType(type);
	}

	/**
	 * Constructs an item with a specified type and index location.
	 * 
	 * @param type
	 *            Type of the Item to be created.
	 * @param indexI
	 *            Y coordinate of the new item.
	 * @param indexJ
	 *            X coordinate of the new item.
	 */
	public Item(ItemType type, int indexI, int indexJ) {
		super();
		setType(type);
		this.setIndexI(indexI);
		this.setIndexJ(indexJ);
	}

	/**
	 * Constructs an item with a specified type, index location, if it's a
	 * {@link Troncycle} trail head and the owner.
	 * 
	 * @param type
	 *            Type of the item to be created.
	 * @param indexI
	 *            Y coordinate of the new item.
	 * @param indexJ
	 *            X coordinate of the new item.
	 * @param first
	 *            Sets if the item will be the head of a {@link Troncycle}
	 *            trail.
	 * @param owner
	 *            Sets the {@link Player} of the new item.
	 */
	public Item(ItemType type, int indexI, int indexJ, boolean first, Player owner) {
		super();
		this.type = type;
		this.indexI = indexI;
		this.indexJ = indexJ;
		this.first = first;
		this.owner = owner;
	}

	/**
	 * 
	 * @return
	 */
	public int getValue() {
		return value;
	}

	/**
	 * 
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * 
	 * @return
	 */
	public ItemType getType() {
		return type;
	}

	/**
	 * Sets the item type and defines its value.
	 * 
	 * @param type
	 *            Type to set to the item.
	 */
	public void setType(ItemType type) {
		this.type = type;
		if (type == ItemType.fuel) {
			setValue(randInt(10, 100));
		} else if (type == ItemType.increaseTail) {
			setValue(randInt(1, 10));
		} else if (type == ItemType.shield) {
			setValue(randInt(5, 20));
		} else if (type == ItemType.turbo) {
			setValue(randInt(1, 10));
		}
	}

	/**
	 * Generates an random {@link Integer} between the specified min and max
	 * values.
	 * 
	 * @param min
	 *            Minimum value of the number to generate.
	 * @param max
	 *            Maximum value of the number to generate.
	 * @return Random {@link Integer} between the specified min and max values.
	 */
	private static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	
	@Override
	public int compareTo(Item o) {
		return this.type.compareTo(o.getType());
	}

	
	public int getIndexI() {
		return indexI;
	}

	
	public void setIndexI(int indexI) {
		this.indexI = indexI;
	}

	
	public int getIndexJ() {
		return indexJ;
	}

	
	public void setIndexJ(int indexJ) {
		this.indexJ = indexJ;
	}

	
	public boolean getFirst() {
		return first;
	}

	
	public void setFirst(boolean first) {
		this.first = first;
	}

	
	public Player getOwner() {
		return owner;
	}

	
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	
	@Override
	public String toString() {
		String result = getType().name();
		// String result = "("+getType().name()+"
		// "+getIndexI()+""+getIndexJ()+")";
		return result;
	}

}
