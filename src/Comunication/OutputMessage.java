package Comunication;
import java.util.ArrayList;

import Malla.LinkedMatrix;


class OutputMessage {
	private int id;
	private static int nextid = 1;
	private String player;
	private String alert;
	private LinkedMatrix map;
	
	public OutputMessage(String player, String alert, LinkedMatrix map) {
		this.player = player;
		this.alert = alert;
		this.map = map;
		this.id = nextid;
		nextid++;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static int getNextid() {
		return nextid;
	}
	public static void setNextid(int nextid) {
		OutputMessage.nextid = nextid;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public LinkedMatrix getMap() {
		return map;
	}
	public void setMap(LinkedMatrix map) {
		this.map = map;
	}
	
}
