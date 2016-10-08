package Comunication.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by joseph on 10/2/16.
 */
public class ServerWrite extends Thread {


	public void run() {
		while (true) {
			TronServer.getClients().sendPingAll();


			String lista = "%C";
			for (int i = 1; i <= 4; i++) {
				lista = lista + "- Jugador " + i + ": " + TronServer.getClients().get(i).getName() + "@@#";
			}

			TronServer.getClients().sendAll(lista);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ignored) {

			}
		}
	}
}