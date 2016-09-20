package Malla;
import Malla.*;

public class LinkedMatrix {
	private Nodo head;
	private int numRows, numCols, numNodes;
	
	public LinkedMatrix(int numRows, int numCols){
		this.head = null;
		this.numRows = numRows;
		this.numCols = numCols;
		this.numNodes = numRows * numCols;


		simpleUnlinkedList();
		
	}
	

	public Nodo simpleUnlinkedList(){
		System.out.println(this.numNodes);
		int i, jj;
		int deb = 101;
		int nodos =1;
		this.head = new Nodo(30);
		Nodo temp = this.head;
		temp.setType("Yo soy la cabeza");
		while(nodos < this.numNodes) {
			for(i =0; i < this.numRows; i++){
				if(i==0)jj=1;
				else{jj=0;}
				for(int j = jj; j< this.numCols ; j++){
					Nodo nuevoNodo = new Nodo(30);
					nuevoNodo.setIndex(i, j);
					nuevoNodo.setType("Soy el nodo " + deb );
					temp.setNext(nuevoNodo);
					temp = temp.getNext();
					nodos ++;
					deb ++;
					if(nodos == this.numNodes)break;
			}
				System.out.println("despues del ciclo j hay" + nodos + "nodos");
				if(nodos == this.numNodes)break;
			
		}
			System.out.println("despues del ciclo i hay" + nodos + "nodos");
		
	}
		System.out.println(this.numCols + this.numRows);
		
		System.out.println("holi");
		this.head.getNext().getIndex();
		displayList(this.head);
		fourWayLinkedList();
		return this.head;}
	
	public Nodo getNodo(int indexI, int indexJ){
		Nodo temp = this.head;
		Nodo result = null;
		while(temp != null){
			if(temp.getIndexI() == indexI && temp.getIndexJ() == indexJ){
				result = temp;
				break;
			}
			temp = temp.getNext();
			
		}
		if(result == null){
			System.out.println("No esta el indice");
			
		}

		return result;
		
		
	}
	
	public void fourWayLinkedList(){
		Nodo originalHead = new Nodo(30);
		originalHead.setNext(this.head.getNext());
		Nodo temp = this.head;
		// up
		while(temp != null){
			if(temp.getIndexI() == 0) {temp.setUp(getNodo(this.numRows-1, temp.getIndexJ()));		}
			else{temp.setUp(getNodo(temp.getIndexI()-1, temp.getIndexJ()));							}
			System.out.println("El arriba de (" + temp.getIndexI() + "," + temp.getIndexJ() + ") es "+ temp.getUp().getIndexI() +","+temp.getUp().getIndexJ());
			temp = temp.getNext();		
		}
		
		//down
		temp = this.head;
		while(temp != null){
			if(temp.getIndexI() == this.numRows-1) {temp.setDown(getNodo(0, temp.getIndexJ()));		}
			else{temp.setDown(getNodo(temp.getIndexI()+1, temp.getIndexJ()));						}
			System.out.println("El abajo de (" + temp.getIndexI() + "," + temp.getIndexJ() + ") es "+ temp.getDown().getIndexI() +","+temp.getDown().getIndexJ());
			temp = temp.getNext();		
		}
		
		//right
		temp = this.head;
		while(temp != null){
			if(temp.getIndexJ() == this.numCols-1) {temp.setRight(getNodo(temp.getIndexI(), 0));		}
			else{temp.setRight(getNodo(temp.getIndexI(), temp.getIndexJ()+1));							}
			System.out.println("El derecha de (" + temp.getIndexI() + "," + temp.getIndexJ() + ") es "+ temp.getRight().getIndexI() +","+temp.getRight().getIndexJ());
			temp = temp.getNext();		
		}
		
		//left
		temp = this.head;
		while(temp != null){
			if(temp.getIndexJ() == 0) {temp.setLeft(getNodo(temp.getIndexI(), this.numCols-1));		}
			else{temp.setLeft(getNodo(temp.getIndexI(), temp.getIndexJ()-1));							}
			System.out.println("El izquierda de (" + temp.getIndexI() + "," + temp.getIndexJ() + ") es "+ temp.getLeft().getIndexI() +","+temp.getLeft().getIndexJ());
			temp = temp.getNext();		
		}
		
		
		this.head.getUp().getIndex();
		this.head.getDown().getIndex();
		this.head.getRight().getIndex();
		this.head.getLeft().getIndex();

		}

		
		
	

	
	
	public void displayList(Nodo lista){
		Nodo temp = this.head;
		while(temp != null){
			System.out.print(temp.getType());
			temp.getIndex();
			temp = temp.getNext();
		}
	}
	
	
	
	
	
	
	
	
	
	

}
