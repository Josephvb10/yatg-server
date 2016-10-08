
package GraphicMap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Structures.*;
import Structures.Malla.*;

@SuppressWarnings("serial")
public class Screen1 extends JPanel implements KeyListener, Runnable {

	public static final int MALLASIZE = 680;
	public static final int NODOSIZE = 20;
	public static boolean RIGHT, LEFT, UP, DOWN, RUN, gameover;;
	public LinkedMatrix matrix;
	public Nodo currentNode;
	public Troncycle player1, player2;

	private Thread thread;

	public static long targetTime = 300;

	public Screen1() {
		setPreferredSize(new Dimension(MALLASIZE, MALLASIZE));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);

		this.matrix = new LinkedMatrix(MALLASIZE / 20, MALLASIZE / 20);
		/*
		 * Item item1 = new Item(ItemType.bomb, 1, 0); Item item2 = new
		 * Item(ItemType.shield, 2, 1 ); Item item3 = new Item(ItemType.turbo,
		 * 3, 2); Item item4 = new Item(ItemType.bomb, 1, 1); Item item5 = new
		 * Item(ItemType.increaseTail, 1, 2); Item item6 = new
		 * Item(ItemType.fuel, 5, 3); Item item7 = new
		 * Item(ItemType.increaseTail, 1, 3); Item item8 = new
		 * Item(ItemType.turbo, 4, 2); Item item9 = new Item(ItemType.turbo, 8,
		 * 2); Item item10 = new Item(ItemType.turbo, 6, 2); Item item11 = new
		 * Item(ItemType.turbo, 14, 2); Item item12 = new Item(ItemType.fuel,
		 * 30, 2); Item item13 = new Item(ItemType.fuel, 26, 20);
		 * 
		 * 
		 * 
		 * this.matrix.setNodeItem(item1); this.matrix.setNodeItem(item2);
		 * this.matrix.setNodeItem(item3); this.matrix.setNodeItem(item4);
		 * this.matrix.setNodeItem(item5); this.matrix.setNodeItem(item6);
		 * this.matrix.setNodeItem(item7); this.matrix.setNodeItem(item8);
		 * this.matrix.setNodeItem(item9); this.matrix.setNodeItem(item10);
		 * this.matrix.setNodeItem(item11); this.matrix.setNodeItem(item12);
		 * this.matrix.setNodeItem(item13);
		 */

		this.player1 = new Troncycle(Player.player1, 0, 0);
		this.player1.setCurrentDirection(Direction.right);
		// player1.setSpeed(80);

		RIGHT = false;
		LEFT = false;
		DOWN = false;
		UP = false;
		RUN = true;

		repaint();
		ItemGenerator itemGenerator = new ItemGenerator(matrix, 10, 2000);
		Thread thread1 = new Thread(itemGenerator);
		thread1.start();
		
		BotGenerator botGenerator = new BotGenerator(matrix, 2, 100);
		Thread thread2 = new Thread(botGenerator);
		thread2.start();
	}

	public void paint(Graphics g) {

		g.setColor(Color.white);
		g.clearRect(0, 0, MALLASIZE, MALLASIZE);
		g.setColor(Color.BLACK);
		Nodo temp = matrix.getHead();
		for (int y = 0; y < MALLASIZE; y += NODOSIZE) {
			for (int x = 0; x < MALLASIZE; x += NODOSIZE) {

				if (temp.getItem() == null) {
					g.setColor(Color.BLACK);
				} else {
					if (temp.getItem().getType() == ItemType.tronTrail) {
						g.setColor(Color.red);
					} else if (temp.getItem().getType() == ItemType.bomb) {
						g.setColor(Color.blue);
					} else if (temp.getItem().getType() == ItemType.shield) {
						g.setColor(Color.orange);
					} else if (temp.getItem().getType() == ItemType.turbo) {
						g.setColor(Color.cyan);
					} else if (temp.getItem().getType() == ItemType.increaseTail) {
						g.setColor(Color.GRAY);
					} else if (temp.getItem().getType() == ItemType.fuel) {
						g.setColor(Color.GREEN);
					}

				}

				g.fillRect(x, y, NODOSIZE, NODOSIZE);
				g.drawString("(" + temp.getIndexI() + "," + temp.getIndexJ() + ")", x + 10, y + 10);
				g.setColor(Color.cyan);
				g.drawRect(x, y, NODOSIZE, NODOSIZE);
				temp = temp.getNext();
			}

		}

	}


/*		public static long targetTime =300;
		
		public Screen1(){
			setPreferredSize( new Dimension(MALLASIZE, MALLASIZE));
			setFocusable(true);
			requestFocus();
			addKeyListener(this);
			
			this.matrix = new LinkedMatrix(MALLASIZE/20, MALLASIZE/20);

			
			
			 this.player1 = new Troncycle(Player.player1, 0, 0);
			 this.player1.setCurrentDirection(Direction.right);

		
			 
			RIGHT=false;
			LEFT=false;
			DOWN=false;
			UP=false;
			RUN=true;
			
			repaint();
			new ItemGenerator(matrix,10,2000).start();

	@Override
	public void addNotify() {
		super.addNotify();
		this.thread = new Thread(this);
	}
*/
	
	@Override
	public void addNotify() {
		super.addNotify();
		this.thread = new Thread(this);
	}
	@Override
	public void run() {

		long startTime;
		long elapsed;
		long wait;

		while (RUN) {

			startTime = System.nanoTime();

			updateDirection();

			this.matrix.updatePlayer(this.player1);
			// this.matrix.updatePlayer(this.player2);

			repaint();

			elapsed = System.nanoTime() - startTime;
			wait = targetTime - elapsed / 1000000;
			if (wait > 0) {
				try {
					Thread.sleep(wait);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

	}

	private void updateDirection() {
		Direction prevDirection = player1.getCurrentDirection();
		if (RIGHT && prevDirection != Direction.left) {
			player1.setCurrentDirection(Direction.right);
			// player2.setCurrentDirection(Direction.right);

		}
		if (LEFT && prevDirection != Direction.right) {

			player1.setCurrentDirection(Direction.left);
			// player2.setCurrentDirection(Direction.left);

		}
		if (DOWN && prevDirection != Direction.up) {
			player1.setCurrentDirection(Direction.down);
			// player2.setCurrentDirection(Direction.down);

		}
		if (UP && prevDirection != Direction.down) {

			player1.setCurrentDirection(Direction.up);
			// player2.setCurrentDirection(Direction.up);

		}
	}

	@Override
	public void keyPressed(KeyEvent key) {
		int k = key.getKeyCode();
		if (k == KeyEvent.VK_ENTER)
			this.thread.start();
		if (k == KeyEvent.VK_RIGHT) {
			RIGHT = true;
			DOWN = false;
			LEFT = false;
			UP = false;
		}
		if (k == KeyEvent.VK_DOWN) {
			DOWN = true;
			LEFT = false;
			UP = false;
			RIGHT = false;
		}
		if (k == KeyEvent.VK_LEFT) {
			LEFT = true;
			DOWN = false;
			UP = false;
			RIGHT = false;
		}
		if (k == KeyEvent.VK_UP) {
			UP = true;
			DOWN = false;
			LEFT = false;
			RIGHT = false;
		}
		if (k == KeyEvent.VK_P) {
			player1.usePowerUp();
		}
		if (k == KeyEvent.VK_C) {
			player1.changePowerUp();
		}

	}

	@Override
	public void keyReleased(KeyEvent key) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
