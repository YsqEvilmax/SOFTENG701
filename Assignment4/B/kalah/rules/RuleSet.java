package kalah.rules;

import kalah.board.Side;
import kalah.move.MoveCollector;

public interface RuleSet {
	MoveCollector getMoveOptionCollector();
	BoardStateProperty moveLegality(Side side, String pid);
	BoardStateProperty getCaptureStateProperty();
	BoardStateProperty getEndGameProperty(Side player);
	BoardStateProcessor getCaptureProcessor();
	BoardStateProcessor getScoreProcessor();
}
