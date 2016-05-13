package kalah.rules;

import kalah.board.Pit;
import kalah.board.PitTopology;
import kalah.board.Side;

/**
 * Process the board state in some way. The expectation is that 
 * the board state may change state due to this processing, but it is
 * not required.
 */
public interface BoardStateProcessor {
	/**
	 * Process the board state.
	 * @param player Process with respect to a player (null if it does not matter)
	 * @param boardState The state of the board
	 * @param pit Process with respect to a pit (null if it does not matter)
	 */
	public void process(Side player, PitTopology boardState, Pit pit);
}
