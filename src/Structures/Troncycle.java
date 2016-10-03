package Structures;

import java.util.Random;

import Structures.Item;

public class Troncycle {
	/**
	 * 
	 */
	private Player owner;
	private int speed, normalSpeed;
	private double fuel;
	private Direction currentDirection;
	private int extraTrail, powerUpSteps;
	private GenericLinkedList<Item> trail;
	private ItemsPriorityQueue itemsQueue = new ItemsPriorityQueue();
	private boolean isDead, powerUpActivated, shieldActivated;
    private GenericStack<Item> powerUpStack = new GenericStack<>();


	public Troncycle() {
		super();
	}


	public Troncycle(Player owner, int indexI, int indexJ) {
		this.isDead = false;
		this.powerUpActivated = false;
		this.owner = owner;
		this.fuel = 100;
		this.speed = generateSpeed();
		this.normalSpeed = this.speed;
		this.trail = new GenericLinkedList<>();
		this.extraTrail =7;
		this.powerUpSteps = 0;
		this.addHead(indexI, indexJ);
		;

	}

	public void setPowerUpSteps(int value) {
		this.powerUpSteps = value;
	}

	public int getPowerUpSteps() {
		return this.powerUpSteps;
	}

	public void setPowerUpActivated(boolean value) {
		this.powerUpActivated = value;
		if(value==false){
			setShieldActivated(value);
		}
	}

	public boolean getPowerUpActivated() {
		return this.powerUpActivated;
	}

	public boolean getIsDead() {
		return this.isDead;
	}

	public void setIsDead(boolean value) {
		this.isDead = value;

	}

	public boolean killPlayer() {
		if (getIsDead() == false) {
			if (this.isShieldActivated()) {
				setShieldActivated(false);
				setPowerUpActivated(false);
			} else {
				this.isDead = true;
			}
		}
		return getIsDead();
	}

	public boolean isShieldActivated() {
		return shieldActivated;
	}

	public void setShieldActivated(boolean shieldActivated) {
		this.shieldActivated = shieldActivated;
		if (!shieldActivated){
			setPowerUpSteps(0);
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
		if ((this.fuel + fuel) > 100) {
			this.fuel = 100;
		} else {
			this.fuel += fuel;
		}
	}
	
	public int getNormalSpeed(){
		return normalSpeed;
	}

	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void resetSpeed() {
		this.speed = normalSpeed;
	}

	public int generateSpeed() {
		Random rand = new Random();
		int newSpeed = rand.nextInt(9) + 1;
		return newSpeed;
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
	 * public GenericNode<Item> getHead() { return trail.getHead(); }
	 */

	public Item deleteTail() {
		Item deleted = null;

		if (getExtraTrail() > 0) {
			addExtraTrail(-1);
		} else {
			deleted = trail.deleteLast();
		}

		return deleted;
	}

	public void addHead(int indexI, int indexJ) {
		Item newItem = new Item(ItemType.tronTrail, indexI, indexJ, true, this.owner);
		addHead(newItem);
	}

	public void addHead(Item item) {
		GenericNode<Item> newHead = new GenericNode<>(item);
		if (!trail.isEmpty()) {
			trail.getHead().getData().setFirst(false);
		}
		trail.setHead(newHead);
	}

	public void addItem(Item newItem) {
		itemsQueue.add(newItem);
		System.out.println("Cola actual" + getItemsQueue());
		useItem();
		

		
	}

	public void addPowerUp(Item newPowerUp) {
		powerUpStack.push(newPowerUp);
		System.out.println("Pila actual" + getPowerUpStack());
	}
	
	public void usePowerUp(){
		if(!powerUpStack.isEmpty()){
			Item powerUpToUse = powerUpStack.pop();
			switch (powerUpToUse.getType()){
				case turbo:
					int newSpeed = powerUpToUse.getValue();
					this.setSpeed(newSpeed);
					if (!this.getPowerUpActivated()) {
						this.setPowerUpActivated(true);
						this.setPowerUpSteps(60);
						System.out.println("Cambie velocidad a " + this.getSpeed());
					}
					break;
				case shield:
					if (!this.getPowerUpActivated()) {
						this.setPowerUpActivated(true);
						this.setPowerUpSteps(60);
						this.setShieldActivated(true);
						System.out.println("Tengo escudo ");
			}
					break;
					
				default:
					break;
		}
	}
	}
	
	

	public void useItem() {

		if (!itemsQueue.isEmpty()) {
			Item itemToUse = itemsQueue.poll();
			switch (itemToUse.getType()) {
			case bomb:

				break;
			case fuel:
				if (this.getFuel() > 99) {
					this.addItem(itemToUse);
				}else{
					System.out.println("Mi viejo combustible es" + this.fuel);
					System.out.println("Obtuve un bonus de " + itemToUse.getValue());
					this.addFuel(itemToUse.getValue());
					System.out.println("Mi nuevo combustible es" + this.fuel);
				}
				break;
				
			case increaseTail:
				this.addExtraTrail(itemToUse.getValue());
				break;
				
			default:
				break;

			}
			System.out.println(this.getItemsQueue());
		}

	}

	public ItemsPriorityQueue getItemsQueue() {
		return itemsQueue;
	}

	public void setItemsQueue(ItemsPriorityQueue itemsQueue) {
		this.itemsQueue = itemsQueue;
	}
	
	public GenericStack<Item> getPowerUpStack() {
		return powerUpStack;
	}

	public void setPowerUpStack(GenericStack<Item> powerUpStack) {
		this.powerUpStack = powerUpStack;
	}
	
	public void reducePowerUp(){
	if (getPowerUpActivated()) {
		setPowerUpSteps(getPowerUpSteps() - 1);
		System.out.println("Me quedan " + getPowerUpSteps() + "pasos de powerup");
		if (getPowerUpSteps() == 0) {
			setPowerUpActivated(false);
			resetSpeed();
			System.out.println("Velocidad normal");
		}
	}
	}

}
