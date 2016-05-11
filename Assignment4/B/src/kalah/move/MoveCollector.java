package kalah.move;

import kalah.rules.BoardStateProcessor;

/**
 * Provides the means to collect the possible moves that
 * a player might make.
 */
public interface MoveCollector extends BoardStateProcessor {
	MoveOptions getMoveOptions();
}
