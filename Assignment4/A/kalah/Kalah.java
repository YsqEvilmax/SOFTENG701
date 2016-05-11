package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;


public class Kalah {
	public static void main(String[] args) {
		IO mock = new MockIO();
		new Kalah().play(mock);
	}
	public void play(IO io) {
		GamePlay game = new GamePlay(io, new Player(io), new Player(io));
		while (true) {
			MoveResult result = game.nextMove();
			if (result == MoveResult.Quit || result == MoveResult.GameOver) {
				game.endGame(result == MoveResult.GameOver);
				break;
			}
		}		
	}
}
