package GraphicMap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;



public class TronGamePanel extends JPanel implements KeyListener, Runnable {
	
	public static final int SIZE = 800;
	private Graphics2D g;
	private BufferedImage image;
	
	
	public TronGamePanel(){
		setPreferredSize(new Dimension(SIZE, SIZE));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		
	}
	
	private void init() {
		image = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_ARGB);
		g = image.createGraphics();
	
	}
	
	private void requestRender() {
		render(g);
		Graphics g = getGraphics();
		g.drawImage(image, 0,0, null);
		g.dispose();
		

		
	}
	public void render(Graphics2D g){
		g.setColor(Color.BLACK);
		g.clearRect(0, 0, SIZE, SIZE);


	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
