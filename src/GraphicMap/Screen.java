package GraphicMap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import Malla.*;

@SuppressWarnings("serial")
public class Screen extends JPanel implements MouseListener, KeyListener, Runnable{
	public static final int SIZE=900;
	public boolean RIGHT, LEFT, UP, DOWN, RUN;
	public LinkedMatrix matrix;
	public Nodo currentNode;
	
	private Thread thread;

	public long targetTime;
	public Screen(){
		setPreferredSize( new Dimension(SIZE, SIZE));
		setFocusable(true);
		requestFocus();
		addMouseListener(this);
		addKeyListener(this);
		this.matrix = new LinkedMatrix(SIZE/50, SIZE/50, 50);
		this.currentNode = this.matrix.getNodo(this.matrix.numRows/2, this.matrix.numCols/2);
		this.RIGHT=false;
		this.LEFT=false;
		this.DOWN=false;
		this.UP=false;
		this.RUN=true;
		
		

		repaint();

	}
	public void paint(Graphics g){
		g.setColor(Color.white);
		g.clearRect(0, 0, SIZE, SIZE);
		g.setColor(Color.BLACK);
		Nodo temp = matrix.head;
		for(int y =0; y < SIZE; y+=matrix.size){
			for(int x=0; x<SIZE; x+=matrix.size){
				temp.setVerticeUL(x, y);
				temp.setVerticeDR(x + matrix.size, y + matrix.size);
				temp.setSize(matrix.size);

				
				if(temp.getColor() != null){
					g.setColor(temp.getColor());
					g.fillRect(x, y, matrix.size, matrix.size);
					g.setColor(Color.black);}
				
				else{
				g.fillRect(x, y, matrix.size, matrix.size);}
				g.drawString("("+temp.getIndexI()+","+temp.getIndexJ()+")", x+10, y+10);
				temp = temp.getNext();
			}
			
		}
		
	}
	

	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Nodo temp = matrix.head;
		while(temp != null){
			if(e.getX()>temp.getXL() && e.getX()<temp.getXR() && e.getY()< temp.getYR() && e.getY()>temp.getYL()){
				temp.getIndex();
				if(temp.getColor()==null){
				temp.getRight().setColor(Color.blue);
				temp.getLeft().setColor(Color.green);
				temp.getUp().setColor(Color.CYAN);
				temp.getDown().setColor(Color.magenta);
				temp.setColor(Color.red);}
				else{temp.setColor(Color.LIGHT_GRAY);}
				repaint();
				
				
				
			}
			temp = temp.getNext();
				
		}
		
	    }
	
	@Override
	public void addNotify(){

		super.addNotify();
		thread = new Thread(this);
		//thread.start();
	}
	
	@Override
	public void run() {
		

		long startTime;
		long elapsed;
		long wait;
		
		while(RUN){
			startTime = System.nanoTime();
			
			updateGame();
			
			repaint();
			
			
			elapsed = System.nanoTime() - startTime;
			wait = 100 - elapsed / 1000000;
			if(wait > 0){
				try{
						Thread.sleep(175);}
				catch(Exception e){
					e.printStackTrace();
				}
					
			}
		}


	}

	private void updateGame() {
		if(RIGHT){
			this.currentNode = this.currentNode.getRight();
			this.currentNode.setColor(Color.BLUE);
			this.currentNode.getLeft().setColor(Color.white);
		}
		if(LEFT){
			this.currentNode = this.currentNode.getLeft();
			this.currentNode.setColor(Color.BLUE);
			this.currentNode.getRight().setColor(Color.white);
		}
		if(DOWN){
			this.currentNode = this.currentNode.getDown();
			this.currentNode.setColor(Color.BLUE);
			this.currentNode.getUp().setColor(Color.white);
		}
		if(UP){
			this.currentNode = this.currentNode.getUp();
			this.currentNode.setColor(Color.BLUE);
			this.currentNode.getDown().setColor(Color.white);
		}
		currentNode.getIndex();
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent key) {
		int k = key.getKeyCode();
		if(k == KeyEvent.VK_ENTER) thread.start();
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
