package Malla;

public class Nodo {
	private Nodo up, down, left, right;
	private int indexI, indexJ, size;
	private String type, color;
	
	public Nodo(int size){
		this.up = null;
		this.down = null;
		this.left = null;
		this.right = null;
		this.type = "Empty";
		this.color = "None";
		this.indexI = 0;
		this.indexJ = 0;
		this.size = size;
		
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
	
	public void getIndex(){ System.out.print( "(" + this.indexI + " , " + this.indexJ + ")");

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public int getSize() {return size;}
	public void setSize(int size) {this.size = size;}
	
	}
	
	
	
	
	
	


