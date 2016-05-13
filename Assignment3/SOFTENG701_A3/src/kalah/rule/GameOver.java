package kalah.rule;

import kalah.Game;

public class GameOver implements IRule<Object> {

	public GameOver() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Affect(Game game, Object arg) {
		//game.Accept(DumpSingleton.getInstance(), null);
		game.EndWithEvaluation();
	}
}
