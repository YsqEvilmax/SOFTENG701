package kalah;

import kalah.move.MoveStatus;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

public class Kalah {
	private static String _gameConfiguration = "";
	public static void main(String[] args) {
		IO mock = new MockIO();
		new Kalah().play(mock);
	}
    public void play(IO io) {
    	Game game = GameFactory.getGame(io, _gameConfiguration);
		while (true) {
			MoveStatus result = game.move();
			if (result == MoveStatus.Quit || result == MoveStatus.GameOver) {
				game.endGame(result == MoveStatus.GameOver);
				break;
			}
		}
	}
}
