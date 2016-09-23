package GraphicMap;

import java.awt.Dimension;

import javax.swing.JFrame;



public class GameWindow {
	public static void main(String[] args){
	
	JFrame frame = new JFrame("TRON");
	frame.setContentPane(new Screen());
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(true);
	frame.pack();
	frame.setPreferredSize(new Dimension(Screen.SIZE, Screen.SIZE));
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	}
}
