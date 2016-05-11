package kalah.move;

import kalah.board.Side;

/**
 * Represents a move by a player, specifically which {@link Side} the player 
 * is on and what house the player choose.
 */
public class Move {
	private String _id;
	private Side _player;
	public Move(String id, Side player) {
		_id = id;
		_player = player;
	}
	public boolean is(String id) {
		return _id.equals(id);
	}
	public Side getSide() {
		return _player;
	}
	
	public String getID() {
		return _id;
	}
	
	public String toString() {
		return "Move[" + _player + "," + _id + "]";
	}
}
