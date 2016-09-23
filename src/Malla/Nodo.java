package Malla;

import java.awt.Color;

public class Nodo {
	private Nodo up, down, left, right, next;
	private int indexI, indexJ, size, xl, yl, xr, yr;
	private String type;
	private Color color;
	
	public Nodo(int size){
		this.next = null;
		this.up = null;
		this.down = null;
		this.left = null;
		this.right = null;
		this.type = "Empty";
		this.color = null;
		this.indexI = 0;
		this.indexJ = 0;
		this.size = size;
		this.xl =0;
		this.yl=0;
		this.xr=0;
		this.yr=0;
		
		
	}

	public int getXL(){ return this.xl;}
	public int getYL(){ return this.yl;}
	public void setVerticeUL(int xl, int yl){
		this.xl = xl;
		this.yl = yl;
	}
	
	public int getXR(){ return this.xr;}
	public int getYR(){ return this.yr;}
	public void setVerticeDR(int xr, int yr){
		this.xr = xr;
		this.yr = yr;
	}
	
	
	
			
	
	public Nodo getNext(){
		return next;
	}
	
	public void setNext(Nodo nodo){
		this.next = nodo;
	}
	
	public Nodo getUp() {
		return up;
	}

	public void setUp(Nodo up) {
		this.up = up;
	}

	public Nodo getDown() {
		return down;
	}

	public void setDown(Nodo down) {
		this.down = down;
	}

	public Nodo getLeft() {
		return left;
	}

	public void setLeft(Nodo left) {
		this.left = left;
	}

	public Nodo getRight() {
		return right;
	}

	public void setRight(Nodo right) {
		this.right = right;
	}
	
	public void setIndex(int i, int j){
		this.indexI = i;
		this.indexJ = j;
	}
	
	public void getIndex(){ System.out.println( "[" + this.indexI + " , " + this.indexJ + "]");

	}

	public int getIndexI() {
		return indexI;
	}

	public void setIndexI(int indexI) {
		this.indexI = indexI;
	}

	public int getIndexJ() {
		return indexJ;
	}

	public void setIndexJ(int indexJ) {
		this.indexJ = indexJ;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public int getSize() {return size;}
	public void setSize(int size) {this.size = size;}
	
	}
	
	
	
	
	
	


