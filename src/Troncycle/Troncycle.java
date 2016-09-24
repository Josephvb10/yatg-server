package Troncycle;

import java.awt.Color;

import Malla.Nodo;

public class Troncycle {
	public String ownerName;
	public int fuel, lenght, speed;
	//public PriorityQueue items;
	//public Stack powerUps;
	public Nodo tail, head;
	
	public Troncycle(String name){
		this.ownerName =  name;
		this.fuel = 100;
		this.lenght = 0;
		this.speed =5;
		this.head=null;
		this.tail=null;
		addHead();
		addHead();
		addHead();
	}
	
	
	public void deleteTail(){
		Nodo temp = this.head;
		while(temp.getNext().getNext()!=null){
			temp = temp.getNext();
		}
		temp.setNext(null);
		this.lenght--;
		
	}
	
	public Nodo getTail(){
		Nodo temp = this.head;
		while(temp.getNext()!=null){
			temp = temp.getNext();
		}	
		return temp;
	}
	
	public Nodo getAntTail(){
		Nodo temp = this.head;
		while(temp.getNext().getNext()!=null){
			temp = temp.getNext();
		}	
		return temp;	
	}
	
	public void addHead(){
		if (this.head==null){
			this.head = new Nodo(20);
			this.head.setColor(Color.cyan);
		}
		else{
			Nodo newHead = new Nodo(20);
			newHead.setNext(this.head);
			this.head = newHead;
			this.head.setColor(Color.cyan);
			
			
		}
		this.lenght++;
	}
	
	
	
	
}
