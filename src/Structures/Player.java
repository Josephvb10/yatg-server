package Structures;
/**
 * Enum the contains the possible players tags(identifiers).
 * @author gsegura96
 *
 */
public enum Player {
		player1(1), player2(2),player3(3),player4(4),bot(5);
	public int value;

	Player(int value) {
		this.value = value;

	}
}
