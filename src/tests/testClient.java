package tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.*;
import java.net.*;


public class testClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MarcoCliente mimarco=new MarcoCliente();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serverListener oyente = new serverListener();
		oyente.run();


	}

}


class MarcoCliente extends JFrame{
	
	public MarcoCliente(){
		
		setBounds(600,300,280,350);
				
		LaminaMarcoCliente milamina=new LaminaMarcoCliente();
		
		add(milamina);
		
		setVisible(true);
		}	
	
}

class LaminaMarcoCliente extends JPanel{
	
	public LaminaMarcoCliente(){
	
		JLabel texto=new JLabel("CLIENTE");
		
		add(texto);
	
		campo1=new JTextField(20);
	
		add(campo1);		
	
		miboton=new JButton("Enviar");
		EnviaTexto mievento = new EnviaTexto();
		miboton.addActionListener(mievento);
		
		
		add(miboton);	
		
	}
		
	private JTextField campo1;
	
	private JButton miboton;
	
	private class EnviaTexto implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			System.out.println(campo1.getText());
			try {
				Socket misocket = new Socket("192.168.1.10",1313);
				DataOutputStream flujo_salida = new DataOutputStream(misocket.getOutputStream());
				flujo_salida.writeUTF(campo1.getText());
				flujo_salida.close();
				campo1.setText("");
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				System.out.println(e1.getMessage());
			}
			
					
		}
		
	}
	
}


class serverListener implements Runnable {
	public serverListener(){
	}
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
			System.out.println(mensaje);
			misocket.close();
			//outputData = new DataOutputStream(misocket.getOutputStream());
			//outputData.writeUTF("test output\n");
			//misocket.close();
			
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



