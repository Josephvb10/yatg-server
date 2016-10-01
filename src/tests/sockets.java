package tests;



import javax.swing.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class sockets  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}

class MarcoServidor extends JFrame implements Runnable {
	
	public MarcoServidor(){
		
		setBounds(1200,300,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
		Thread mithread = new Thread(this);
		mithread.start();
		
		}
	
	private	JTextArea areatexto;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		DataOutputStream outputData;
		System.out.println("estoy a la espera");
		try {
			ServerSocket servidor = new ServerSocket(1313);
			while(true){
			Socket misocket = servidor.accept();
			DataInputStream flujoentrada = new DataInputStream(misocket.getInputStream());
			String mensaje = flujoentrada.readUTF();
			areatexto.append("\n"+mensaje);
			
			outputData = new DataOutputStream(misocket.getOutputStream());
			outputData.writeUTF("test output\n");
			misocket.close();
			
			/*server = new ServerSocket(port);
			socket = new Socket();
			
			while(true){
			socket = server.accept();
			inputData = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			inputData.close();
			String mensaje = inputData.readLine();

			System.out.println(mensaje);
			outputData = new DataOutputStream(socket.getOutputStream());
			outputData.writeUTF("test output\n");
			socket.close();
			*/
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
	}
}
