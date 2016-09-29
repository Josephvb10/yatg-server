package Comunication;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.ListIterator;


public class OutputServer{
	
	private int port;
	private ArrayList<String> listeners;
	private OutputMessage2 outputMessage;
	private int maxTimeOut=10;
	
	public OutputServer(int port, ArrayList<String> listeners, OutputMessage2 outputMessage) {
		super();
		this.port = port;
		this.listeners = listeners;
		this.outputMessage = outputMessage;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ArrayList<String> getListeners() {
		return listeners;
	}

	public void setListeners(ArrayList<String> listeners) {
		this.listeners = listeners;
	}

	public OutputMessage2 getOutputMessage() {
		return outputMessage;
	}

	public void setOutpuMessage(OutputMessage2 outputMessage) {
		this.outputMessage = outputMessage;
	}

	public int getMaxTimeOut() {
		return maxTimeOut;
	}

	public void setMaxTimeOut(int maxTimeOut) {
		this.maxTimeOut = maxTimeOut;
	}
	
	public void sendToAll(OutputMessage2 message){
	ListIterator<String> listenersIterator= listeners.listIterator();
		while(listenersIterator.hasNext()){
			try {
				Socket listenerSocket = new Socket(listenersIterator.next(), port);
				DataOutputStream outputFlow = new DataOutputStream(listenerSocket.getOutputStream());
				outputFlow.writeUTF(outputMessage.getJson());
				outputFlow.close();
				listenerSocket.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e.getMessage());

			}
			
		}
	}


}
