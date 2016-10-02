package Comunication;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.ListIterator;

import Structures.GenericLinkedList;
import Structures.Troncycle;


public class OutputServer{
	
	private int port;
	private GenericLinkedList<Troncycle> players;
	private OutputMessage outputMessage;
	private int maxTimeOut=10;


	

	
	
	
	public void sendToAll(OutputMessage message){
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
