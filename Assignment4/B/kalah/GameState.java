package kalah;

import java.util.HashMap;
import java.util.Map;

import kalah.board.Board;
import kalah.board.Side;
import kalah.move.Move;
import kalah.move.MoveCollector;
import kalah.move.MoveOptions;
import kalah.move.MoveStatus;
import kalah.player.Player;
import kalah.rules.BoardStateProcessor;
import kalah.rules.BoardStateProperty;
import kalah.rules.RuleSet;

/**
 * Represents the state of the game.
 */
public class GameState {
	private Board _board;
	private Map<Side,Player> _players;
	private Side _whoseTurn;
	
	public GameState(Player player1, Player player2, Board board) {
		_board = board;
		_players = new HashMap<Side,Player>();
		_players.put(Side.First, player1);
		_players.put(Side.Second, player2);
		_whoseTurn = Side.First;
	}

	public MoveStatus nextMove(RuleSet ruleSet, MoveOptions options) {
		Player current = _players.get(_whoseTurn);
		BoardStateProperty endGame = ruleSet.getEndGameProperty(_whoseTurn);
		if (_board.has(endGame, _whoseTurn, null)) {
			return MoveStatus.GameOver;
		}
		Move nextMove = current.nextMove(options);
		if (nextMove == null) {
			return MoveStatus.Quit;
		}
		BoardStateProperty moveLegality = ruleSet.moveLegality(nextMove.getSide(), nextMove.getID());
		if (!_board.has(moveLegality, _whoseTurn, null)) {
			return MoveStatus.EmptyHouse;
		}
		BoardStateProperty captureProperty = ruleSet.getCaptureStateProperty();
		BoardStateProcessor captureProcessor = ruleSet.getCaptureProcessor();
		MoveStatus status = _board.move(captureProperty, captureProcessor, nextMove);
		if (status == MoveStatus.MoveEnded) {
			_whoseTurn = _whoseTurn.next();
		}
		return status;
	}
	public MoveOptions getMoveOptions(RuleSet ruleSet) {
		MoveCollector collector = ruleSet.getMoveOptionCollector();
		_board.process(collector, _whoseTurn, null);
		return collector.getMoveOptions();
	}
	public Player getPlayer(Side side) {
		return _players.get(side);
	}
	public void displayScore(RuleSet ruleSet) {
		BoardStateProcessor scoreProcessor = ruleSet.getScoreProcessor();
		_board.process(scoreProcessor, null, null);
	}
}
