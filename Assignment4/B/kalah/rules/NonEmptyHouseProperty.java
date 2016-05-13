package kalah.rules;

import kalah.board.Pit;
import kalah.board.PitTopology;
import kalah.board.Side;

/**
 * The property that the specified house is non-empty.
 *
 */
public class NonEmptyHouseProperty implements BoardStateProperty {
	private Side _side;
	private String _pid;
	public NonEmptyHouseProperty(Side side, String pid) {
		_side = side;
		_pid = pid;
	}
	
	@Override
	public boolean check(Side player, PitTopology boardState, Pit start) {
		Pit pit = boardState.getPit(_side, _pid);
		return !emptyHouse(_side, boardState, pit);
	}
	private static boolean emptyHouse(Side player, PitTopology boardState, Pit start) {
		return start.nSeeds() == 0;
	}
}
