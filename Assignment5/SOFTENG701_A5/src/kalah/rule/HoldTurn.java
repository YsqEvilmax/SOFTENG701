package kalah.rule;

import kalah.Game;

public class HoldTurn implements IRule<Object> {

	public HoldTurn() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Affect(Game game, Object arg) {
		game.getCurrent().myGroup.get().removeStore();
		//game.TakeTurn();
	}
}
