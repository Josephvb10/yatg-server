package Malla;
import Malla.*;

public class LinkedMatrix {
	private Nodo head;
	private int numRows, numCols, numNodes;
	
	public LinkedMatrix(int numRows, int numCols){
		this.head = null;
		this.numRows = numRows;
		this.numCols = numCols;
		this.numNodes = numCols * numRows;
		initRowOne();
		
	}
	
	
	public void insertLast(Nodo nodo){
		if(this.head==null){
			this.head = nodo;			
		}
		else{
			Nodo temp = this.head;
			while(temp.getRight() != null){
				temp = temp.getRight();
			}
			temp.setRight(nodo);
			temp.getRight().setRight(this.head);

		}
	}
	
	
	public void displayLists(){
		Nodo temp = this.head;
		while(temp != null){
			
		}
	}
	
	
	public void initRowOne(){
		this.head = new Nodo(30);
		Nodo temp = this.head;
		temp.getIndex();
		for(int j= 1; j < this.numCols; j++){
			Nodo nuevoNodo = new Nodo(30);
			
			nuevoNodo.setLeft(temp);
			nuevoNodo.setRight(this.head);
			nuevoNodo.setIndex(0, j);
			nuevoNodo.getIndex();
			temp.setRight(nuevoNodo);
			System.out.println("Mi izquierda " + nuevoNodo.getLeft().getIndexI() + "," + nuevoNodo.getLeft().getIndexJ());
			System.out.println("Mi derecha " + nuevoNodo.getRight().getIndexI() + "," + nuevoNodo.getRight().getIndexJ());
			if(j == this.numCols) this.head.setLeft(nuevoNodo);
			else {
				temp = nuevoNodo;}
	
			
			
			
			
			
			
		}
		System.out.println("Termine con columnas");
		
		initColumns();
		
		
	}
	
	public void display(){
		Nodo temp = this.head;
		Nodo currentRowHead = this.head;	
		for(int i =0; i<this.numRows; i++){
			for(int j=0; j<this.numCols; j++){
				temp.getIndex();
				temp = temp.getRight();
				
			}
			System.out.println();
			currentRowHead = currentRowHead.getDown();
			temp = currentRowHead;
			
		}
		
	}
	
	public void initColumns(){
		Nodo temp = this.head;
		Nodo currentColHead = this.head;
		for(int i = 0; i < this.numRows; i++){
			for(int j = 0; j < this.numCols; j++){

				Nodo nuevoNodo = new Nodo(30);
				nuevoNodo.setIndex(i, j);
				temp.setDown(nuevoNodo);
				nuevoNodo.setUp(temp);
				nuevoNodo.setDown(currentColHead);	
				System.out.print("[" + nuevoNodo.getIndexI() +"," + nuevoNodo.getIndexJ() + "]"); 
				if(j == this.numCols-1) 
					{currentColHead.setUp(nuevoNodo);
					nuevoNodo.getDown().getIndex();
					}
				else {
					temp = nuevoNodo;}
				}
			}
			currentColHead = currentColHead.getRight();
			System.out.println();
		
		this.head.getDown().getIndex();
		//display();
	}
	
	
	

}
