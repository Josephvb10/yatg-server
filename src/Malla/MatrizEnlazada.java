package Malla;

import Malla.*;

public class MatrizEnlazada {
	private Nodo head;
	private int numRows, numCols, numNodes;

	public MatrizEnlazada(int numRows, int numCols) {
		this.head = null;
		this.numRows = numRows;
		this.numCols = numCols;
		this.numNodes = numCols * numRows;
		initRowOne();

	}

	public void initRowOne() {
		this.head = new Nodo(30);
		Nodo temp = this.head;
		temp.getIndex();
		for (int j = 1; j < this.numCols; j++) {
			Nodo nuevoNodo = new Nodo(30);
			temp.setRight(nuevoNodo);
			nuevoNodo.setLeft(temp);
			nuevoNodo.setRight(this.head);
			nuevoNodo.setIndex(0, j);
			nuevoNodo.getIndex();
			System.out.println("Mi izquierda " + nuevoNodo.getLeft().getIndexI() + "," + nuevoNodo.getIndexJ());
			System.out.println("Mi derecha " + nuevoNodo.getRight().getIndexI() + "," + nuevoNodo.getIndexJ());
			if (j == this.numCols)
				this.head.setLeft(nuevoNodo);
			else {
				temp = nuevoNodo;
			}

		}
		System.out.println("Termine con columnas");

		initColumns();

	}

	public void display() {
		Nodo temp = this.head;
		Nodo currentRowHead = this.head;
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numCols; j++) {
				temp.getIndex();
				temp = temp.getRight();

			}
			System.out.println();
			currentRowHead = currentRowHead.getDown();
			temp = currentRowHead;

		}

	}

	public void initColumns() {
		Nodo temp = this.head;
		Nodo currentColHead = this.head;
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numCols; j++) {
				Nodo nuevoNodo = new Nodo(30);
				nuevoNodo.setIndex(i, j);
				temp.setDown(nuevoNodo);
				nuevoNodo.setUp(temp);
				nuevoNodo.setDown(currentColHead);
				System.out.print("[" + nuevoNodo.getIndexI() + "," + nuevoNodo.getIndexJ() + "]");
				if (j == this.numCols - 1) {
					currentColHead.setUp(nuevoNodo);
					nuevoNodo.getDown().getIndex();
				} else {
					temp = nuevoNodo;
				}

			}
			currentColHead = currentColHead.getRight();
			System.out.println();
		}
		this.head.getDown().getIndex();
		// display();
	}

}
