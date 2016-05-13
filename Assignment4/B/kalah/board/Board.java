package kalah.board;

import java.util.Iterator;

import kalah.move.Move;
import kalah.move.MoveStatus;
import kalah.rules.BoardStateProcessor;
import kalah.rules.BoardStateProperty;

/**
 * Represents the state of the board.
 */
public class Board  {
	private PitTopology _pitTopology;
	public Board(PitTopology pitTopology) {
		_pitTopology = pitTopology;
	}

	public MoveStatus move(BoardStateProperty captureProperty, BoardStateProcessor captureProcessor, Move nextMove) {
		Iterator<Pit> sequence = _pitTopology.iterator(nextMove.getSide(), nextMove.getID());
		Pit start = sequence.next();
		int nSeeds = start.removeAll();
		Pit current = sequence.next();
		// nSeeds must be positive at this point as empty houses cannot be chosen
		while (true) {
			current.sow(1);
			nSeeds--;
			if (nSeeds == 0) {
				break;
			}
			current = sequence.next();
		}
		MoveStatus status = null;
		if (current.isStore()) {
			status = MoveStatus.Continue;
		} else {
			if (captureProperty.check(nextMove.getSide(), _pitTopology, current)) {
				captureProcessor.process(nextMove.getSide(), _pitTopology, current);
			}
			status = MoveStatus.MoveEnded;
		}
		return status;
	}

	/**
	 * Detemine whether the whole state has a property.
	 * @param property The property to determine
	 * @return whether or not the property holds
	 */
	public boolean has(BoardStateProperty property, Side side, Pit pit) {
		return property.check(side, _pitTopology, pit);
	}

	/**
	 * Apply a processor to the current board state.
	 * @param processor The processor to apply
	 */
	public void process(BoardStateProcessor processor, Side side, Pit pit) {
		processor.process(side, _pitTopology, pit);
	}
}
