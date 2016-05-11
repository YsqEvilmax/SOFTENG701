package kalah.player;

import kalah.move.Move;
import kalah.move.MoveOptions;

import com.qualitascorpus.testsupport.IO;


public class Human extends Player {
	private int _nHouses;
	public Human(IO io, String id, int nHouses) {
		super(io, id);
		_nHouses = nHouses;
	}
	@Override
	public Move nextMove(MoveOptions options) {
		int choice = _io.readInteger("Player " + _id + "'s turn - Specify house number or 'q' to quit: ", 1, _nHouses, -1, "q");
		if (choice < 0) {
			return null;
		}
		for (Move option: options) {
			if (option.is(""+choice)) {
				return option;
			}
		}
		throw new RuntimeException("Should not be able to get here");
	}
}
