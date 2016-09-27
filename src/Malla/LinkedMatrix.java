package Malla;

import Structures.*;

public class LinkedMatrix {
	public Nodo head;
	public int numRows, numCols, numNodes;
	
	public LinkedMatrix(int numRows, int numCols, int size){
		this.head = null;
		this.numRows = numRows;
		this.numCols = numCols;
		this.numNodes = numRows * numCols;

		simpleUnlinkedList();	
		return;
	}
	
	public Nodo simpleUnlinkedList(){
		int i, jj;
		int nodos =1;
		this.head = new Nodo();
		Nodo temp = this.head;
		while(nodos < this.numNodes) {
			for(i =0; i < this.numRows; i++){
				if(i==0)jj=1;
				else{jj=0;}
				for(int j = jj; j< this.numCols ; j++){
					Nodo nuevoNodo = new Nodo();
					nuevoNodo.setIndex(i, j);
					temp.setNext(nuevoNodo);
					temp = temp.getNext();
					nodos ++;
					if(nodos == this.numNodes)break;
			}
				if(nodos == this.numNodes)break;			
		}		
	}
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
			for(int i =0; i<this.numRows; i++){
				for(int j=0; j<this.numCols; j++){
					temp.getIndex();
					temp = temp.getNext();
				}
				System.out.println();
			}		
		}
	}
	public Nodo getIndexNode(int indexI, int indexJ){
		Nodo current = this.head;
		if (checkIndexI(indexI)&& checkIndexJ(indexJ)){
		while(indexI>0){
			current = current.getDown();
			indexI--;
		}
		while(indexJ>0){
			current = current.getRight();
			indexJ--;
		}
		}
		return current;

	}
	public void setNodeItem(Item item){
		int indexI = item.getIndexI();
		int indexJ = item.getIndexJ();
		getIndexNode(indexI, indexJ).setItem(item);
	}
	
	public void resetNodeItem(int indexI, int indexJ){
		getIndexNode(indexI, indexJ).setItem(null);
	}
	public void resetNodeItem(Item item){
		int indexI = item.getIndexI();
		int indexJ = item.getIndexJ();
		getIndexNode(indexI, indexJ).setItem(null);
	}
	
	public boolean checkIndexI(int indexI){
		boolean result = false;
		if(0<=indexI && indexI<this.numCols){
			result = true;
		}
		return result;
	}
	
	public boolean checkIndexJ(int indexJ){
		boolean result = false;
		if(0<=indexJ && indexJ<this.numCols){
			result = true;
		}
		return result;
	}
	
}
