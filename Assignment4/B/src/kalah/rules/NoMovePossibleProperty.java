package kalah.rules;

import kalah.board.Pit;
import kalah.board.PitTopology;
import kalah.board.Side;

/**
 * The property that the specified player has no possible move
 */
public class NoMovePossibleProperty implements BoardStateProperty {
	private Side _player;
	public NoMovePossibleProperty(Side player) {
		_player = player;
	}
	@Override
	public boolean check(Side ignore1, PitTopology boardState, Pit ignore2) {
		for (Pit pit: boardState.getPits(_player)) {
			if (!pit.isStore()) {
				if (pit.nSeeds() > 0) {
					return false;
				}				
			}
		}
		return true;
	}

}
