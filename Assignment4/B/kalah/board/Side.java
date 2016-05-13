package kalah.board;

/**
 * A board is made up of "sides". Each side belongs to a player. The 
 * identification of the sides relates to the playing order of the
 * players.
 */
public enum Side {
	/**
	 * The side belonging to the player that makes the first move of the game.
	 */
	First,
	/**
	 * The side belong to the player who does not make the first move of the game.
	 */
	Second;
	
	public Side next() {
		if (this == First) {
			return Second;
		} else {
			return First;
		}
	}
}
