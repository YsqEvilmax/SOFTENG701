package kalah.rules;

import kalah.board.Side;
import kalah.display.Display;
import kalah.move.KalahStdMoveOptionProcessor;
import kalah.move.MoveCollector;

/**
 * Implements the standard set of rules for Kalah, specifically:
 * - capture is only possible if the opposite house is non-empty
 * - the game ends when the player whose turn it is cannot move
 */
public class KalahStdRuleSet implements RuleSet {
	private Display _display;

	public KalahStdRuleSet(Display display) {
		_display = display;
	}

	@Override
	public MoveCollector getMoveOptionCollector() {
		return new KalahStdMoveOptionProcessor();
	}
	
	@Override
	public BoardStateProcessor getCaptureProcessor() {
		return new KalahStdCaptureProcessor();
	}
	
	@Override
	public BoardStateProcessor getScoreProcessor() {
		return new KalahStdScoreProcessor(_display);
	}
	
	@Override
	public BoardStateProperty moveLegality(Side side, String pid) {
		return new NonEmptyHouseProperty(side, pid);
	}
	
	@Override
	public BoardStateProperty getEndGameProperty(Side player) {
		return new NoMovePossibleProperty(player);
	}

	@Override
	public BoardStateProperty getCaptureStateProperty() {
		return new KalahStdCaptureProperty();
	}
}
