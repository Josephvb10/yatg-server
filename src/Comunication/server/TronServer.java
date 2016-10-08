package Comunication.server;

import GraphicMap.BotGenerator;
import GraphicMap.ItemGenerator;
import Structures.Malla.LinkedMatrix;
import Structures.Malla.Nodo;
import Structures.Troncycle;

import java.net.*;
import java.util.Enumeration;

/**
 * Created by joseph on 10/2/16.
 */
public class TronServer {
	private static final PlayerArray clients = new PlayerArray();
	private static TronServer ourInstance = new TronServer();
	private int PORT;

	private static final int MALLASIZE = 680;
	private static final int NODOSIZE = 20;

	public static LinkedMatrix getMatrix() {
		return matrix;
	}

	private static LinkedMatrix matrix;
	private Nodo currentNode;


	private TronServer() {
	}

	public static TronServer getInstance() {
		return ourInstance;
	}

	public static void main(String[] args) {
		TronServer.getInstance().start(8081);
	}

	public static PlayerArray getClients() {
		return clients;
	}

	private static String getIP() {
		String ip = "no network";
		try {
			Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
			outmost:
			for (; n.hasMoreElements(); ) {
				NetworkInterface e = n.nextElement();
				Enumeration<InetAddress> a = e.getInetAddresses();
				for (; a.hasMoreElements(); ) {
					InetAddress addr = a.nextElement();
					if (addr instanceof Inet4Address) { // return the first IPv4 addr (127.0.1.1 is always last)
						if (addr.isSiteLocalAddress()) {
							ip = addr.getHostAddress();
							break outmost;
						}

					}
				}
			}
		} catch (SocketException ignored) {
		}
		return ip;
	}

	public void start(int PORT) {
		this.PORT = PORT;

		this.matrix = new LinkedMatrix(MALLASIZE / 20, MALLASIZE / 20);

		ItemGenerator itemGenerator = new ItemGenerator(matrix, 10, 2000);
		Thread thread1 = new Thread(itemGenerator);
		thread1.start();

		BotGenerator botGenerator = new BotGenerator(matrix, 4, 500);
		Thread thread2 = new Thread(botGenerator);
		thread2.start();



		try {
			// TODO: 10/2/16 Mostrar la IP visualmente
			System.out.println("IP del server: " + getIP());

			//new ServerWrite().start();

			try (ServerSocket listener = new ServerSocket(PORT)) {
				System.out.println("Servidor iniciado");
				while (true) {
					new ServerRead(listener.accept()).start();
				}
			}
		} catch (Exception e) {
			// TODO: 10/2/16 Agregar un mensaje visual para el error cuando no puede abrir el server
			System.out.println("Error al abrir el socket");
		}

	}
}
