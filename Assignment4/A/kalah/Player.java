package kalah;

import com.qualitascorpus.testsupport.IO;

public class Player {
	private IO _io;
    
    public Player(IO io) {
		_io = io;
    }

	public int nextMove() {
		return _io.readInteger("Specify house number or 'q' to quit: ", 1, 6, -1, "q");
	}
}
