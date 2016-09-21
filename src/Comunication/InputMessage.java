package Comunication;

public class InputMessage {
	private int player;
	private String alert;
	private String move;
	
	public InputMessage(int player, String alert, String move) {
		super();
		this.player = player;
		this.alert = alert;
		this.move = move;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}
	
	

}
