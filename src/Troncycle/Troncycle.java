package Troncycle;

import Structures.*;

public class Troncycle {
	private Player owner;
	private int fuel, lenght, speed;
	// public PriorityQueue items;
	// public Stack powerUps;
	// public Nodo tail, head;
	private GenericLinkedList<Item> trail;
	private ItemsPriorityQueue itemsQueue = new ItemsPriorityQueue();

	public Troncycle(Player owner) {
		this.owner = owner;
		this.fuel = 100;
		this.speed = 5;
		this.trail = new GenericLinkedList<>();
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getFuel() {
		return fuel;
	}

	public void setFuel(int fuel) {
		this.fuel = fuel;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public GenericNode<Item> getHead() {
		return trail.getHead();
	}

	public void deleteTail() {
		trail.deleteAtPosition(trail.getSize() - 1);
	}

	public Item getTail() {
		return trail.getAtPosition(trail.getSize() - 1);
	}

	public void addHead(int indexI, int indexJ) {
		Item newItem = new Item(ItemType.tronTrail, indexI, indexJ, true, this.owner);
		addHead(newItem);
	}

	public void addHead(Item item) {
		GenericNode<Item> newHead = new GenericNode<>(item);
		trail.getHead().getData().setIsHead(false);
		trail.setHead(newHead);
	}

	public void useItem() {
		if (!itemsQueue.isEmpty()) {
			Item itemToUse = itemsQueue.poll();
			switch (itemToUse.getType()) {
			case bomb:

				break;
			case bomb:

				break;
			case bomb:

				break;
			case bomb:

				break;
			case bomb:

				break;

			default:
				break;
			}
		}

	}

}
