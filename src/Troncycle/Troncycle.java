package Troncycle;

import GraphicMap.Screen;
import Structures.*;

public class Troncycle {
	private Player owner;
	private int speed;
	private double fuel;
	private Direction currentDirection;
	private int extraTrail, powerUpSteps;
	// public PriorityQueue items;
	// public Stack powerUps;
	// public Nodo tail, head;
	private GenericLinkedList<Item> trail;
	private ItemsPriorityQueue itemsQueue = new ItemsPriorityQueue();
	private boolean isDead, powerUpActivated;

	
	public Troncycle() {
		super();
	}


	public Troncycle(Player owner,int indexI, int indexJ) {
		this.isDead = false;
		this.powerUpActivated = false;
		this.owner = owner;
		this.fuel = 100;
		this.speed = 1;
		this.trail = new GenericLinkedList<>();
		this.extraTrail = 6;
		this.powerUpSteps = 0;
		this.addHead(indexI, indexJ);
	}
	
	
	public void setPowerUpSteps(int value){
		this.powerUpSteps=value;
	}
	
	public int getPowerUpSteps(){
		return this.powerUpSteps;
	}
	
	public void setPowerUpActivated(boolean value){
		this.powerUpActivated=value;
	}
	
	public boolean getPowerUpActivated(){
		return this.powerUpActivated;
	}
	
	public boolean getIsDead(){
		return this.isDead;
	}
	
	public void setIsDead(boolean value){
		this.isDead = value;
		if(value == true){
			Screen.RUN = false;
			
		}
	}


	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {
		this.fuel = fuel;
	}

	public void addFuel(int fuel) {
		if((this.fuel + fuel) > 100){
			this.fuel = 100;
		}
		else{
			this.fuel +=fuel;
		}
		
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		Screen.targetTime = 200/speed;
		this.speed = speed;
	}

	public Direction getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(Direction currentDirection) {
		this.currentDirection = currentDirection;
	}
	

	public GenericLinkedList<Item> getTrail() {
		return trail;
	}

	public void setTrail(GenericLinkedList<Item> trail) {
		this.trail = trail;
	}

	public int getExtraTrail() {
		return extraTrail;
	}

	public void setExtraTrail(int extraTrail) {
		this.extraTrail = extraTrail;
	}

	public void addExtraTrail(int extraTrail) {
		this.extraTrail += extraTrail;
	}
/*
	public GenericNode<Item> getHead() {
		return trail.getHead();
	}*/

	public Item deleteTail() {
		Item deleted = null;

		if (trail.getSize() > 1) {
			
			deleted = trail.deleteAtPosition(trail.getSize() - 1);
		}
		return deleted;
	}

	
/*
	public void addHead() {
		Item first = this.getHead().getData();
		int indexI = first.getIndexI();
		int indexJ = first.getIndexJ();

		switch (currentDirection) {// necesario agregar casos especiales
		case down:
			indexI--;
			break;
		case up:
			indexI++;

			break;
		case left:
			indexJ--;

			break;
		case right:
			indexJ++;

			break;

		default:
			break;
		}

		Item newItem = new Item(ItemType.tronTrail, indexI, indexJ, true, this.owner);
		addHead(newItem);
	}*/

	public void addHead(int indexI, int indexJ) {
		Item newItem = new Item(ItemType.tronTrail, indexI, indexJ, true, this.owner);
		addHead(newItem);
	}

	public void addHead(Item item) {
		GenericNode<Item> newHead = new GenericNode<>(item);
		if(!trail.isEmpty()){
		trail.getHead().getData().setIsHead(false);}
		trail.setHead(newHead);
	}

	public Item move(int indexI, int indexJ) {
		Item deleted = null;
		addHead( indexI,  indexJ);
		if (getExtraTrail() > 0) {
			addExtraTrail(-1);
		} else {
			deleted = deleteTail();
		}
		return deleted;
	}
	public void addItem(Item newItem){
		itemsQueue.add(newItem);
	}

	public void useItem() {
		if (!itemsQueue.isEmpty()) {
			Item itemToUse = itemsQueue.poll();
			switch (itemToUse.getType()) {
			case bomb:

				break;
			case fuel:
				this.addFuel(itemToUse.getValue());

				break;
			case increaseTail:
				this.addExtraTrail(itemToUse.getValue());

				break;
			case shield:

				break;
			case turbo:

				break;

			default:
				break;
			}
		}

	}

}
