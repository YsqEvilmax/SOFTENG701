package kalah.rule;

import kalah.Game;
import kalah.dump.DumpSingleton;

public class EmptyHouse implements IRule<Object> {

	public EmptyHouse() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Affect(Game game, Object arg) {
		DumpSingleton.getInstance().Visit("House is empty. Move again.", arg);
	}
}
