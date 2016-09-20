package GraphicMap;

import java.awt.Dimension;

import javax.swing.JFrame;



public class GameWindow {
	public static void main(String[] args){
	
	JFrame frame = new JFrame("TRON");
	frame.setContentPane(new TronGamePanel());
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	frame.pack();
	frame.setPreferredSize(new Dimension(TronGamePanel.SIZE, TronGamePanel.SIZE));
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	}
}
