package tests;

import Structures.ItemType;

/****
 * Clase Carro para pruebas
 */
public class Carro implements Comparable<Carro> {
	private int placa;
	private String marca;
	private Importancia importancia;
	private ItemType type;

	public Carro() {
		super();
		// TODO Auto-generated constructor stub
	}

	/****
	 * Constructor completo
	 */
	public Carro(int placa, String marca, Importancia importancia) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.importancia = importancia;
	}

	public int getPlaca() {
		return placa;
	}

	public void setPlaca(int placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Importancia getImportancia() {
		return importancia;
	}

	public void setImportancia(Importancia importancia) {
		this.importancia = importancia;
	}

	/**
	 * permite comparar los carros segun su tipo
	 * 
	 */
	@Override
	public int compareTo(Carro o) {
		return this.importancia.compareTo(o.importancia);
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	/**
	 * Enum que contiene los tipos de carro
	 * 
	 */
	public enum Importancia {
		taxi(1), uber(2), patrulla(3), particular(4);
		/** asdsad */
		private int value;

		/**
		 * constructor privado
		 */
		private Importancia(int value) {
			this.value = value;
		}

	}
	/**
	 * Retorna la manera de pitar del {@link Carro}.
	
	 * @param sonido How loud.
	 * @param tiempo How long will it sound.
	 * @return retorna tiempo*sonido
	 */
	public int pitar(int sonido, int tiempo) {
		System.out.println(sonido + "  " + tiempo);
		return sonido * tiempo;
	}
}
