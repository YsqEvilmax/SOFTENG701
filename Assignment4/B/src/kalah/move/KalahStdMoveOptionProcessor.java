package kalah.move;

import java.util.List;

import kalah.board.Pit;
import kalah.board.PitTopology;
import kalah.board.Side;

/**
 * Determine which moves a player can make. This provides all possible houses
 * belonging to the player, whether or not they contain seeds.
 */
public class KalahStdMoveOptionProcessor implements MoveCollector {
	private MoveOptions _options;
	public KalahStdMoveOptionProcessor() {
		_options = new MoveOptions();
	}
	
	@Override
	public void process(Side side, PitTopology boardState, Pit ignore2) {
		List<Pit> pits = boardState.getPits(side);
		for (Pit pit: pits) {
			_options.addMove(new Move(pit.getID(), side));
			
		}
	}

	@Override
	public MoveOptions getMoveOptions() {
		return _options;
	}
}
