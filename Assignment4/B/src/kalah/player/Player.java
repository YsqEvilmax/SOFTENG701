package kalah.player;

import kalah.move.Move;
import kalah.move.MoveOptions;

import com.qualitascorpus.testsupport.IO;

abstract public class Player {
	protected IO _io;
	protected String _id;
	
	public Player(IO io, String id) {
		_io = io;
		_id = id;
	}
	
	/**
	 * What is the next move the player makes
	 */
	abstract public Move nextMove(MoveOptions options);
	
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		Player other = (Player)obj;
		return _id == other._id;
	}
	
	public String toString() {
		return "Player " + _id;
	}

	public String getID() {
		return _id;
	}
}
