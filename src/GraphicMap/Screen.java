package GraphicMap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JPanel;

import Malla.*;
import Troncycle.Troncycle;

@SuppressWarnings("serial")
public class Screen extends JPanel implements KeyListener, Runnable{
	public static final int SIZE=800;
	public boolean RIGHT, LEFT, UP, DOWN, RUN;
	public LinkedMatrix matrix;
	public Nodo currentNode;
	public Troncycle cycle;
	public boolean cycleset, gameover;
	
	private Thread thread;

	public long targetTime =105;
	
	public Screen(){
		setPreferredSize( new Dimension(SIZE, SIZE));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		this.cycleset = false;
		this.matrix = new LinkedMatrix(SIZE/20, SIZE/20, 20);
		this.cycle=new Troncycle("Tavo");
		this.cycle.head.setUnder(this.matrix.getNodo(this.matrix.numRows/2, this.matrix.numCols/2));
		this.cycle.getTail().setUnder(this.matrix.getNodo(this.matrix.numRows/2, this.matrix.numCols/2+2));
		this.cycle.getAntTail().setUnder(this.matrix.getNodo(this.matrix.numRows/2, this.matrix.numCols/2+1));
		
		//this.cycle.head.setNext(this.matrix.getNodo(this.matrix.numRows/2, this.matrix.numCols/2+1));
		//this.cycle.head.getNext().setNext(this.matrix.getNodo(this.matrix.numRows/2, this.matrix.numCols/2+2));
		this.RIGHT=false;
		this.LEFT=false;
		this.DOWN=false;
		this.UP=false;
		this.RUN=true;
		setSpeed();
		repaint();

	}
	

	public void paint(Graphics g){
		if(gameover = true){
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER", 400, 400);
		}
		g.setColor(Color.white);
		g.clearRect(0, 0, SIZE, SIZE);
		g.setColor(Color.BLACK);
		Nodo temp = matrix.head;
		for(int y =0; y < SIZE; y+=matrix.size){
			for(int x=0; x<SIZE; x+=matrix.size){
				
				temp.setVerticeUL(x, y);
				temp.setVerticeDR(x + matrix.size, y + matrix.size);
				temp.setSize(matrix.size);
				
				if(temp.getAbove() != null){
					//System.out.println(temp.getAbove());
					temp.setColor(temp.getAbove().getColor());
					
				}
				


				
				
					
					
				

				g.setColor(temp.getColor());
				//System.out.println(temp.getColor());
				g.fillRect(x, y, matrix.size, matrix.size);
				g.drawString("("+temp.getIndexI()+","+temp.getIndexJ()+")", x+10, y+10);
				g.setColor(Color.magenta);
				g.drawRect(x, y, matrix.size, matrix.size);
				temp = temp.getNext();
			}
			
		}
		
	}
	
	
	@Override
	public void addNotify(){
		super.addNotify();
		thread = new Thread(this);
	}
	
	@Override
	public void run() {
		

		long startTime;
		long elapsed;
		long wait;
		
		while(RUN){
			
			if(cycleset==false){
				if(LEFT){
				this.cycle.getTail().setUnder(this.matrix.getNodo(this.matrix.numRows/2-2, this.matrix.numCols/2-2));
				this.cycle.getAntTail().setUnder(this.matrix.getNodo(this.matrix.numRows/2, this.matrix.numCols/2-1));
			}
				else{
					this.cycle.getTail().setUnder(this.matrix.getNodo(this.matrix.numRows/2, this.matrix.numCols/2+2));
					this.cycle.getAntTail().setUnder(this.matrix.getNodo(this.matrix.numRows/2, this.matrix.numCols/2+1));	
				}
				this.cycleset=true;
			}
			startTime = System.nanoTime();
			
			updateGame();
			
			repaint();
			
			
			elapsed = System.nanoTime() - startTime;
			wait = targetTime - elapsed / 1000000;
			if(wait > 0){
				try{
						Thread.sleep(wait);}
				catch(Exception e){
					e.printStackTrace();
				}
					
			}
		}


	}

	private void updateGame() {
		Nodo temp = this.cycle.head;
		if(RIGHT){
			

			
			
			
			this.cycle.addHead();			
			temp.getUnder().getRight().setAbove(this.cycle.head);
			this.cycle.head.setUnder(temp.getUnder().getRight());
			this.cycle.head.getUnder().setType("Estela");
			System.out.println("mi nueva cabeza esta en " + this.cycle.head.getUnder().getIndexI() + "," + this.cycle.head.getUnder().getIndexJ());
			System.out.println("la cabeza esta encima del nodo " + this.cycle.head.getUnder().getIndexI() + "," + this.cycle.head.getUnder().getIndexJ());
			this.cycle.getTail().getUnder().setAbove(null);
			this.cycle.getAntTail().setUnder(this.cycle.getTail().getUnder().getRight());
			System.out.println(this.cycle.getAntTail().getColor());
			this.cycle.getAntTail().getUnder().setAbove(this.cycle.getTail());
			System.out.println("mi cola esta en " + this.cycle.getTail().getUnder().getIndexI() + "," + this.cycle.getTail().getUnder().getIndexJ());
			this.cycle.getTail().setColor(Color.black);
			this.cycle.deleteTail();
			System.out.println("mi cola esta en " + this.cycle.getTail().getUnder().getIndexI() + "," + this.cycle.getTail().getUnder().getIndexJ());


			
			//this.cycle.head=(this.cycle.head.getRight());
			//System.out.println("red");
			//this.cycle.getTail().setColor(Color.black);;
			//this.cycle.head.setColor(Color.red);

			
			
			//System.out.println(RUN);
			
			//this.currentNode = this.currentNode.getRight();
			//this.currentNode.setColor(Color.BLUE);
			//this.currentNode.getLeft().setColor(Color.white);
		}
		if(LEFT){

			
			this.cycle.addHead();
			temp.getUnder().getLeft().setAbove(this.cycle.head);
			this.cycle.head.setUnder(temp.getUnder().getLeft());
			System.out.println("mi nueva cabeza esta en " + this.cycle.head.getUnder().getIndexI() + "," + this.cycle.head.getUnder().getIndexJ());
			System.out.println("la cabeza esta encima del nodo " + this.cycle.head.getUnder().getIndexI() + "," + this.cycle.head.getUnder().getIndexJ());
			this.cycle.getTail().getUnder().setAbove(null);
			this.cycle.getAntTail().setUnder(this.cycle.getTail().getUnder().getLeft());
			System.out.println(this.cycle.getAntTail().getColor());
			this.cycle.getAntTail().getUnder().setAbove(this.cycle.getTail());
			System.out.println("mi cola esta en " + this.cycle.getTail().getUnder().getIndexI() + "," + this.cycle.getTail().getUnder().getIndexJ());
			this.cycle.getTail().setColor(Color.black);
			this.cycle.deleteTail();
			System.out.println("mi cola esta en " + this.cycle.getTail().getUnder().getIndexI() + "," + this.cycle.getTail().getUnder().getIndexJ());
			System.out.println(cycle.lenght);
		}
		if(DOWN){

			
			this.cycle.addHead();
			temp.getUnder().getDown().setAbove(this.cycle.head);
			this.cycle.head.setUnder(temp.getUnder().getDown());
			System.out.println("mi nueva cabeza esta en " + this.cycle.head.getUnder().getIndexI() + "," + this.cycle.head.getUnder().getIndexJ());
			System.out.println("la cabeza esta encima del nodo " + this.cycle.head.getUnder().getIndexI() + "," + this.cycle.head.getUnder().getIndexJ());
			this.cycle.getTail().getUnder().setAbove(null);
			this.cycle.getAntTail().setUnder(this.cycle.getTail().getUnder().getDown());
			System.out.println(this.cycle.getAntTail().getColor());
			this.cycle.getAntTail().getUnder().setAbove(this.cycle.getTail());
			System.out.println("mi cola esta en " + this.cycle.getTail().getUnder().getIndexI() + "," + this.cycle.getTail().getUnder().getIndexJ());
			this.cycle.getTail().setColor(Color.black);
			this.cycle.deleteTail();
			System.out.println("mi cola esta en " + this.cycle.getTail().getUnder().getIndexI() + "," + this.cycle.getTail().getUnder().getIndexJ());
			System.out.println(cycle.lenght);
		}
		if(UP){

			
			this.cycle.addHead();
			temp.getUnder().getUp().setAbove(this.cycle.head);
			this.cycle.head.setUnder(temp.getUnder().getUp());
			System.out.println("mi nueva cabeza esta en " + this.cycle.head.getUnder().getIndexI() + "," + this.cycle.head.getUnder().getIndexJ());
			System.out.println("la cabeza esta encima del nodo " + this.cycle.head.getUnder().getIndexI() + "," + this.cycle.head.getUnder().getIndexJ());
			this.cycle.getTail().getUnder().setAbove(null);
			this.cycle.getAntTail().setUnder(this.cycle.getTail().getUnder().getDown());
			System.out.println(this.cycle.getAntTail().getColor());
			this.cycle.getAntTail().getUnder().setAbove(this.cycle.getTail());
			System.out.println("mi cola esta en " + this.cycle.getTail().getUnder().getIndexI() + "," + this.cycle.getTail().getUnder().getIndexJ());
			this.cycle.getTail().setColor(Color.black);
			this.cycle.deleteTail();
			System.out.println("mi cola esta en " + this.cycle.getTail().getUnder().getIndexI() + "," + this.cycle.getTail().getUnder().getIndexJ());
			System.out.println(cycle.lenght);
		}
		this.cycle.head.getIndex();}
	
	
	public void grow(){
		Nodo temp = this.cycle.head;
		this.cycle.addHead();
		if(UP){
			temp.getUnder().getUp().setAbove(this.cycle.head);
			this.cycle.head.setUnder(temp.getUnder().getUp());
		}
		if(DOWN){
			temp.getUnder().getDown().setAbove(this.cycle.head);
			this.cycle.head.setUnder(temp.getUnder().getDown());
		}
		if(LEFT){
			temp.getUnder().getLeft().setAbove(this.cycle.head);
			this.cycle.head.setUnder(temp.getUnder().getLeft());
		}
		if(RIGHT){
			temp.getUnder().getRight().setAbove(this.cycle.head);
			this.cycle.head.setUnder(temp.getUnder().getRight());
		}
			
	}
	
	private void setSpeed(){
		targetTime -= 5;
		System.out.print(targetTime);
	}
	

		
	@Override
	public void keyPressed(KeyEvent key) {
		int k = key.getKeyCode();
		if(k == KeyEvent.VK_ENTER) thread.start();
		if(k == KeyEvent.VK_SPACE) grow();
		if(k== KeyEvent.VK_BACK_SPACE)setSpeed();

		if(k == KeyEvent.VK_RIGHT){RIGHT=true;DOWN=false;LEFT=false;UP=false;}
		if(k == KeyEvent.VK_DOWN){DOWN=true;LEFT=false;UP=false;RIGHT=false;}
		if(k == KeyEvent.VK_LEFT){LEFT=true;DOWN=false;UP=false;RIGHT=false;}
		if(k == KeyEvent.VK_UP){UP=true;DOWN=false;LEFT=false;RIGHT=false;}
		
		
	}
	@Override
	public void keyReleased(KeyEvent key){ 

	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
