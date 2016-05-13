package kalah.rule;

import kalah.Game;

public class ChangeTurn implements IRule<Object> {

	public ChangeTurn() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Affect(Game game, Object arg) {
		game.getCurrent().myGroup.get().removeStore();
		game.MoveToNext();
		//game.TakeTurn();
	}
}
