package kalah;

import kalah.display.Display;
import kalah.move.MoveOptions;
import kalah.move.MoveStatus;
import kalah.rules.RuleSet;

public class Game {
	private final RuleSet _ruleSet;
	private final Display _display;
	private final GameState _gameState;

	public Game(RuleSet ruleSet, GameState gameState, Display display) {
		_ruleSet = ruleSet;
		_gameState = gameState;
		_display = display;
	}
	public MoveStatus move() {
		_display.boardState();
		MoveOptions options = _gameState.getMoveOptions(_ruleSet);
		MoveStatus status = _gameState.nextMove(_ruleSet, options);
		if (status == MoveStatus.EmptyHouse) {
			_display.message("House is empty. Move again.");
			return MoveStatus.Continue;
		} else if (status == MoveStatus.IllegalNotOwner) {
			_display.message("House is not owned by player. Move again");
			return MoveStatus.Continue;
		} else if (status == MoveStatus.GameOver) {
			return status;
		} else if (status == MoveStatus.MoveEnded) {
			return status;
		} else if (status == MoveStatus.Quit) {
			return status;
		} else if (status == MoveStatus.Continue) {
			return status;
		} else {
			// Here for completeness. Should never get here.
			_display.message("Illegal move:" + status);
			return MoveStatus.Continue;
		}		
	}

	public void endGame(boolean gameOver) {
		_display.message("Game over");
		_display.boardState();
		if (gameOver) {
			_gameState.displayScore(_ruleSet);
		}
	}

}
