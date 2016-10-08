package Comunication.server;

import Comunication.OutputMessage;
import Structures.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Structures.Player;
/** Clase encargada de leer los Sockets y procesarlos
 * Created by joseph on 10/2/16.
 */
public class ServerRead extends Thread {
	private Socket socket;
	private String ip;
	private BufferedReader in;
	private PrintWriter out;
	private String name;
	private boolean running = true;
	private boolean joined = false;

	private OutputMessage outMsg;
	private Troncycle playerCycle;

	ServerRead(Socket socket) {
		try {
			this.socket = socket;
			this.ip = socket.getRemoteSocketAddress().toString();
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println("Cliente " + ip + " se intenta conectar");
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al intentar conectarse con " + ip);
			this.running = false;
			Thread.currentThread().interrupt();
		}
	}

	private Player getPlayerNumber() {
		int size = TronServer.getClients().getSize();

		if (size == 1) {
			return Player.player2;
		} else if (size == 2) {
			return Player.player3;
		} else if (size == 3) {
			return Player.player4;
		} else {
			return Player.player1;
		}
	}


	private void addPlayer() {
		synchronized (TronServer.getClients()) {
			if (!TronServer.getClients().contains(name)) {
				TronServer.getClients().insertAvailable(name, out);
			}
		}
		System.out.println("Cliente " + ip + " se ha unido al juego y se llama " + name);
		System.out.println("Actualmente hay " + TronServer.getClients().getSize() + " clientes conectados");

		this.playerCycle = new Troncycle(getPlayerNumber(), 5, 5);
		this.playerCycle.setCurrentDirection(Direction.down);
		playerCycle.setSpeed(80);
		//botGenerator.tryPlaceHead(playerCycle.getTrail().getHead().getData());


	}

	public void run() {
		try {
			String line;

			// Este loop se ejecuta al inicio antes de que se una
			while (true) {
				line = in.readLine();
				if (line.equals("TECPING")) {
					out.println("TECPONG");
				}

				if (line.substring(0, 2).equals("%J")) {
					this.name = line.substring(2).trim();
					if (TronServer.getClients().getSize() >= 4) {
						System.out.println("Cliente removido. Ya hay más de 4");
						out.println("Lo sentimos. Este servidor ya está lleno.");
						Thread.currentThread().interrupt();
						running = false;
						break;
					} else if (TronServer.getClients().contains(name)) {
						System.out.println("Cliente removido. Se intenta llamar " + name + " pero ya existe con ese nombre");
						out.println("Ese nombre ya está en uso, por favor elige otro.");
						Thread.currentThread().interrupt();
						running = false;
						break;

					} else {
						addPlayer();
						out.println("OK");
						this.joined = true;
						TronServer.getClients().sendAll("%P" +  TronServer.getClients().getSize());
						break;
					}

				}
			}
			new Thread() {
				public void run() {
					long startTime;
					long elapsed;
					long wait;
					long targetTime = 20;

					while (running && joined) {
						startTime = System.nanoTime();
						outMsg = new OutputMessage(playerCycle, TronServer.getMatrix().getSimpleItemList(), new GenericLinkedList<Item>());
						String jsonMens = outMsg.toJson();
						TronServer.getClients().sendTo(name, jsonMens);

						elapsed = System.nanoTime() - startTime;
						wait = targetTime - elapsed / 1000000;
						if (wait > 0) {
							try {
								Thread.sleep(wait);
							} catch (Exception e) {
								e.printStackTrace();
							}

						}

					}
				}
			}.start();

			new Thread() {
				public void run() {
					long startTime;
					long elapsed;
					long wait;
					long targetTime = 200;

					while (running && joined) {
						startTime = System.nanoTime();

						if (playerCycle.getIsDead()) {
							TronServer.getClients().sendTo(name, "%D");
							running = false;
							joined = false;
							logoutUser();
							return;
						}

						TronServer.getMatrix().updatePlayer(playerCycle);


						elapsed = System.nanoTime() - startTime;
						wait = targetTime - elapsed / 1000000;
						if (wait > 0) {
							try {
								Thread.sleep(wait);
							} catch (Exception e) {
								e.printStackTrace();
							}

						}
					}
				}
			}.start();



			// Este loop se ejecuta cuando está unido
			while (running && joined) {
				while ((line = in.readLine()) != null) {
					System.out.println(line);
					if (line.substring(0, 1).equals("%")) {
						String cmd = line.substring(1, 2);
						switch (cmd){
							case "D":
								String dir = line.substring(2,3);
								System.out.println("Direccion: " + dir);
								switch (dir) {
									case "D":
										playerCycle.setCurrentDirection(Direction.down);
										break;
									case "U":
										playerCycle.setCurrentDirection(Direction.up);
										break;
									case "L":
										playerCycle.setCurrentDirection(Direction.left);
										break;
									case "R":
										System.out.println("Movido a la derecha");
										playerCycle.setCurrentDirection(Direction.right);
										break;
									default:
										break;
								}
								break;
							case "P":
								break;
							default:
								break;
						}
					} else {
						// Hacer algo
						System.out.println(name + " dijo: " + line);
					}

				}
			}

			logoutUser();


		} catch (Exception e) {
			// TODO: 10/2/16 Mostrar error gráficamente por si se despicha el thread
			System.out.println("Desconexión: " + e.getMessage());
			logoutUser();
		} finally {
				logoutUser();
				try {
					socket.close();
				} catch (IOException e) {
					// No se hace nada si hay un error al cerrar el thread, ya que de verdad no importa
				}
		}
	}

	public void logoutUser() {
		playerCycle.setIsDead(true);
		TronServer.getMatrix().cleanDeadPlayer(playerCycle);

		System.out.println(name + " se ha desconectado");

		TronServer.getClients().remove(name);

		System.out.println("Actualmente hay " + TronServer.getClients().getSize() + " clientes conectados");

		TronServer.getClients().sendAll("%P" + TronServer.getClients().getSize());

		this.running = false;
		this.joined = false;
	}
}
