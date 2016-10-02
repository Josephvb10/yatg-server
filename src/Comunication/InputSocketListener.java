package Comunication;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Structures.*;

public class InputSocketListener implements Runnable {
	private static InputSocketListener instance = null;
	private static GenericQueue<String> messageQueue;
	private static int port;

	protected InputSocketListener() {
		run();
		// TODO Auto-generated constructor stub
	}

	public static InputSocketListener getInstance() {
		if (instance == null) {
			instance = new InputSocketListener();
		}
		return instance;
	}

	public static InputSocketListener resetInstance() {
		instance = null;
		return getInstance();
	}

	public static boolean isEmpty() {
		return messageQueue.isEmpty();
	}

	public static String getFirstMessage() {
		return messageQueue.dequeue();
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		InputSocketListener.port = port;
	}

	//@SuppressWarnings("resource")
	@Override
	public void run() {
		System.out.println("listening port" + port);
		try {
			ServerSocket listener = new ServerSocket(1313);
			while (true) {
				Socket listenerSocket = listener.accept();
				DataInputStream inputData = new DataInputStream(listenerSocket.getInputStream());
				String message = inputData.readUTF();
				//System.out.println(message);
				messageQueue.enqueue(message);
				listenerSocket.close();
			}
			//listener.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
