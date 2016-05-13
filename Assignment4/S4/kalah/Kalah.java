package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

/**
 * This class is the starting point for the Modifiability Assignment.
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		// Create board
		GameRules gamerules = MankalaFactory.getGameRules();
		IOManager ioManager = MankalaFactory.getIOManager();
		ioManager.runGame(gamerules, io);
	}
}