package kalah.rules;

import kalah.board.Pit;
import kalah.board.PitTopology;
import kalah.board.Side;

/**
 * Provides the means to determine whether the board state meets some property.
 */
public interface BoardStateProperty {
	/**
	 * Determine whether a property of the board state holds
	 * @param player Determine the property with respect to a player (null if it does not matter)
	 * @param boardState The state of the board
	 * @param pit Determine the property with respect to a pit (null if it does not matter)
	 */
	public boolean check(Side player, PitTopology boardState, Pit end);
}
