package Structures;

import java.util.Random;

import Structures.Item;
/**
 * Clase principal Troncycle, encargada de toda la informaci�n de la moto
 *  
 * @author Jimena
 * @author gsegura96
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
 * @param owner player that uses the troncycle
 * @param indexI i position where the troncycle will be placed
 * @param indexJ j position where the troncycle will be placed
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
 * Asigna un valor determinado de pasos para la duraci�n de los power ups
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
 * Obtiene el n�mero de pasos de powerUps
 * @return n�mero de pasos de powerUps
 */
	public int getPowerUpSteps() {
		return this.powerUpSteps;
	}


	public void setPowerUpActivated(boolean value) {
		this.powerUpActivated = value;
	}
/**
 * Obtiene si el powerUp est� activado o no
 * @return valor de verdad
 */
	public boolean getPowerUpActivated() {
		return this.powerUpActivated;
	}
/**
 * Obtiene si el jugador est� muerto
 * @return valor de verdad
 */
	public boolean getIsDead() {
		return this.isDead;
	}



	public void setIsDead(boolean value) {
		this.isDead = value;

	}
/**
 * Obtiene el n�mero de jugador al que corresponde la moto
 * @return n�mero de jugador
 */


	public Player getOwner() {
		return owner;
	}
/**
 * Asigna el n�mero de jugador al que corresponde la moto
 * @param owner n�mero de jugador
 */

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {

		this.fuel = fuel;
	}

	
	public int getNormalSpeed(){
		return normalSpeed;
	}
/**
 * Obtiene la velocidad actual de la moto(puede ser cuando est� en turbo)
 * @return velocidad actual de la moto
 */

	public int getSpeed() {
		return speed;
	}

	
	public void setSpeed(int speed) {

		this.speed = speed;
		System.out.println("Velocidad actual" + this.speed);
	}
/**
 * Asigna una direcci�n a la moto
 * @param currentDirection nueva direcci�n de la moto
 */

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
/**
 * Obtiene el valor de la cola extra que se est� generando
 * @return cola extra gener�ndose
 */
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

	
/**

 * It kills the player once it steps into a bomb or the tail of another troncycle. Also, it
 * validates if the player has or not a shield, if it does the player doesn't die but the shield
 * is deactivated.
 * 
 * @return if the player is alive or not 
 */
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

/**
 * Obtiene si el escudo est� activo
 * @return estado del escudo
 */

	public boolean isShieldActivated() {
		return shieldActivated;
	}
/**
 * Assigns if the shield is activated and sets the number of steps that it will last
 * @param shieldActivated if the shield is activated or not

 */
	public void setShieldActivated(boolean shieldActivated) {
		this.shieldActivated = shieldActivated;
		if (!shieldActivated){
			setPowerUpSteps(0);
			setPowerUpActivated(false);
		}
	}
/**
 * Obtiene si el turbo est� actuvo
 * @return estado del turbo
 */
	public boolean isSpeedActivated(){
		return this.speedActivated;
	}
/**
 * Asigna si el turbo est� activado y define el n�mero de pasos por el cual se activar�
 * @param value valor de verdad del estado de activaci�n del turbo
 */

	public void setSpeedActivated(boolean value){
		this.speedActivated=value;
		if(!speedActivated){
			setPowerUpSteps(0);
			setPowerUpActivated(false);
		}
		
	}
/**
 * Aumenta el combustible hasta 100 o menos
 * @param fuel valor que se le agregar� al combustible
 */
	public void addFuel(int fuel) {
		if ((this.fuel + fuel) > 100) {
			this.fuel = 100;
		} else {
			this.fuel += fuel;
		}
	}
/**
 * 	Asigna la velocidad inicial a la moto
 */
	public void resetSpeed() {
		this.speed = normalSpeed;
		System.out.println("Velocidad actual" + this.speed);
	}
/**
 * Genera un valor random para la nueva velocidad
 * @return valor para nueva velocidad
 */
	public int generateSpeed() {
		Random rand = new Random();
		int newSpeed = rand.nextInt(9) + 1;
		return newSpeed;
	}
/**
 * Agrega una cantidad de estelas a la estela actual
 * @param extraTrail cantidad de estelas a agregar
 */
	public void addExtraTrail(int extraTrail) {
		this.extraTrail += extraTrail;
	}
/**
 * Elimina la �ltima estela de la moto
 * @return la estela eliminada
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
/**
 * Agrega una nueva cabeza a la moto(m�todo que simula movimiento)
 * @param indexI posici�n i en la que se pondr� la nueva cabeza
 * @param indexJ posici�n j en la que se pondr� la nueva cabeza
 */
	public void addHead(int indexI, int indexJ) {
		Item newItem = new Item(ItemType.tronTrail, indexI, indexJ, true, this.owner);
		addHead(newItem);
	}
/**
 * Agrega la cabeza como un tipo item a la estela
 * @param item la nueva cabeza de la moto
 */
	public void addHead(Item item) {
		GenericNode<Item> newHead = new GenericNode<>(item);
		if (!trail.isEmpty()) {
			trail.getHead().getData().setFirst(false);
		}
		trail.setHead(newHead);
	}
/**
 * Agrega un nuevo item a la cola de prioridad
 * @param newItem el item que se agregar� a la cola
 */
	public void addItem(Item newItem) {
		itemsQueue.add(newItem);
		System.out.println("Cola actual" + getItemsQueue());
		useItem();		
	}
/**
 * Agrega un nuevo powerUp a la pila de poderes
 * @param newPowerUp el powerUp que se agregar� a la pila
 */
	public void addPowerUp(Item newPowerUp) {
		powerUpStack.push(newPowerUp);
		System.out.println("Pila actual" + getPowerUpStack());
	}
/**
 * 	Cambia el orden de la pila de poderes, todos se corren una posici�n para arriba
 */
	public void changePowerUp(){
		if(!this.getPowerUpStack().isEmpty()){
			Item powerUpChanged = this.getPowerUpStack().pop();
			this.getPowerUpStack().addLast(powerUpChanged);
			}
		System.out.println(this.getPowerUpStack());
	}
/**
 * Utiliza el powerUp que est� en el top de la pila,  y activa sus respectivos flags,
 * valida que si hay un powerUp activado no se pueda usar otro
 */
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
/**
 * Utiliza el item que est� de primero en la cola de prioridad inmediatamente luego de que se agarra
 * valida que si la moto tiene el combustible , entonces el combustible pasar� a estar en la
 * parte inferios de la cola	
 */
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
/**
 * Se encarga de ir agotando los pasos restantes de powerUps que le quedan a la moto, sea
 * de escudo o de turbo	
 */
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
