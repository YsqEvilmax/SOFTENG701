package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import game.Game;
import game.Mancala;
import game.config.Config;

/**
 * This class is the starting point for the Modifiability Assignment.
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}

	public void play(IO io) {
		Config config = new Config(2, 4, 6, -1, "q");
		Game game = new Mancala(io, config);
		game.play();
	}
}
