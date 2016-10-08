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
				String name = (TronServer.getClients().get(i).getName() == null) ? "" : TronServer.getClients().get(i).getName();
				lista = lista + "- Jugador " + i + ": " + name + "@@#";
			}

			TronServer.getClients().sendAll(lista);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ignored) {

			}
		}
	}
}