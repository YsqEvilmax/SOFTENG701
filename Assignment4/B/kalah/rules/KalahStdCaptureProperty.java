package kalah.rules;

import kalah.board.Pit;
import kalah.board.PitTopology;
import kalah.board.Side;

/**
 * Determines whether the proposed move will lead to a capture in the
 * standard Kalah rules, that is, the house the move ends in is empty
 * and the opposite house is non-empty.
 */
public class KalahStdCaptureProperty implements BoardStateProperty {
	/**
	 * @param player
	 * @param boardState
	 * @param end
	 * @return
	 */
	public boolean check(Side player, PitTopology boardState, Pit end) {
		if (!boardState.ownedBy(player, end)) {
			return false;
		}
		if (end.nSeeds() != 1) {
			return false;
		}
		Pit opposite = boardState.getOpposite(end);
		if (opposite.nSeeds() > 0) {
			return true;
		}
		return false;
	}
}
