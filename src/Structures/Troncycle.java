package Structures;

import java.util.Random;

import Structures.Item;
/**Clase principal Troncycle, encargada de toda la información de la moto
 *  
 * @author Jimena, Gustavo
 * 
 *
 */
public class Troncycle {

	private Player owner;
	private int speed, normalSpeed;
	private double fuel;
	private Direction currentDirection;
	private int extraTrail, powerUpSteps;
	private GenericLinkedList<Item> trail;
	private ItemsPriorityQueue itemsQueue = new ItemsPriorityQueue();
	private boolean isDead, powerUpActivated, shieldActivated, speedActivated;
    private GenericStack<Item> powerUpStack = new GenericStack<>();

/**
 * Constructor
 */
	public Troncycle() {
		super();
	}

/**
 * 
 * @param owner el jugador que está utilizando la moto
 * @param indexI posición i de la matriz donde iniciará la primera vez
 * @param indexJ posición j de la matriz donde iniciará la primera vez
 */
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
/**
 * Asigna un valor determinado de pasos para la duración de los power ups
 * @param value  cantidad de pasos para powerUps
 */
	public void setPowerUpSteps(int value) {
		if(value <= 0){
			this.powerUpSteps = 0;

		}
		else{
		this.powerUpSteps = value;
	}
		}
/**
 * Obtiene el número de pasos de powerUps
 * @return número de pasos de powerUps
 */
	public int getPowerUpSteps() {
		return this.powerUpSteps;
	}
/**
 * Activa o desactiva el powerUp según el valor de verdad
 * @param value
 */

	public void setPowerUpActivated(boolean value) {
		this.powerUpActivated = value;
	}
/**
 * Obtiene si el powerUp está activado o no
 * @return valor de verdad
 */
	public boolean getPowerUpActivated() {
		return this.powerUpActivated;
	}
/**
 * Obtiene si el jugador está muerto
 * @return valor de verdad
 */
	public boolean getIsDead() {
		return this.isDead;
	}
/**
 * Define si el jugador está muerto o vivo según el valor de verdad
 * @param value valor de verdad
 */
	public void setIsDead(boolean value) {
		this.isDead = value;

	}
/**
 * Obtiene el número de jugador al que corresponde la moto
 * @return número de jugador
 */

	public Player getOwner() {
		return owner;
	}
/**
 * Asigna el número de jugador al que corresponde la moto
 * @param owner número de jugador
 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
/**
 * Obtiene el valor del combustible actual de la moto
 * @return valor del combustible actual de la moto
 */
	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {

		this.fuel = fuel;
	}
/**
 * Obtiene la velocidad inicial con que la moto fue creada
 * @return la velocidad normal de la moto
 */
	
	public int getNormalSpeed(){
		return normalSpeed;
	}
/**
 * Obtiene la velocidad actual de la moto(puede ser cuando esté en turbo)
 * @return velocidad actual de la moto
 */
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {

		this.speed = speed;
		System.out.println("Velocidad actual" + this.speed);
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
			setPowerUpActivated(false);
		}
	}
	
	public boolean isSpeedActivated(){
		return this.speedActivated;
	}
	
	public void setSpeedActivated(boolean value){
		this.speedActivated=value;
		if(!speedActivated){
			setPowerUpSteps(0);
			setPowerUpActivated(false);
		}
		
	}

	public void addFuel(int fuel) {
		if ((this.fuel + fuel) > 100) {
			this.fuel = 100;
		} else {
			this.fuel += fuel;
		}
	}
	
	public void resetSpeed() {
		this.speed = normalSpeed;
		System.out.println("Velocidad actual" + this.speed);
	}

	public int generateSpeed() {
		Random rand = new Random();
		int newSpeed = rand.nextInt(9) + 1;
		return newSpeed;
	}

	public void addExtraTrail(int extraTrail) {
		this.extraTrail += extraTrail;
	}

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
	
	public void changePowerUp(){
		if(!this.getPowerUpStack().isEmpty()){
			Item powerUpChanged = this.getPowerUpStack().pop();
			this.getPowerUpStack().addLast(powerUpChanged);
			}
		System.out.println(this.getPowerUpStack());
	}
	
	public void usePowerUp(){
		
		if(!powerUpStack.isEmpty()){
			if(!this.getPowerUpActivated()){
			this.setPowerUpActivated(true);
			this.setPowerUpSteps(60);
			Item powerUpToUse = powerUpStack.pop();
			switch (powerUpToUse.getType()){
				case turbo:
					int newSpeed = powerUpToUse.getValue();
					this.setSpeed(newSpeed);
						this.setSpeedActivated(true);
						System.out.println("Cambie velocidad a " + this.getSpeed());
					
					break;
					
				case shield:
						this.setShieldActivated(true);
						System.out.println("Tengo escudo ");
					break;
					
				default:
					break;
			}
		}
	}
		System.out.println("No hay powerUps");
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
