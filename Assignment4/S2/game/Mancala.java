package game;

import java.util.LinkedHashMap;

import com.qualitascorpus.testsupport.IO;

import game.board.BoardController;
import game.board.BoardPrinter;
import game.config.Config;
import game.player.Player;
import game.player.PlayerHandler;

public class Mancala implements Game {

	private final IO io;
	private final Config config;
	private BoardController boardController;
	private PlayerHandler playerHandler;

	private boolean printScores = false;

	public Mancala(IO io, Config config) {
		this.io = io;
		this.config = config;
	}

	@Override
	public void play() {
		setup();
		playGame();
		gameOver();
	}

	// creates the components which are required to play the game
	private void setup() {
		playerHandler = new PlayerHandler().withNumberOfPlayers(config.players);
		BoardPrinter printer = new BoardPrinter(io, playerHandler.getAllPlayers());
		boardController = new BoardController(config.houses, config.players, config.stones, printer);
	}

	private void playGame() {
		boolean continuePlay = true;

		while (continuePlay) {
			try {
				continuePlay = takeTurn();
			} catch (InvalidMoveException e) {
				io.println(e.getMessage());
			} catch (NoValidMoveException e) {
				printScores = true; // full game was played so print scores
				break;
			}
		}
	}

	// handles the turn by printing the board state, asking the user for input &
	// updating the state. It then updates the player if necessary
	private boolean takeTurn() throws NoValidMoveException, InvalidMoveException {
		boardController.printBoard();
		Player currentPlayer = playerHandler.getCurrentPlayer();

		String turnPrompt = String.format("Player %s's turn - Specify house number or '%s' to quit: ",
				currentPlayer.getShortName(), config.quitLetter);
		int playerMove = io.readInteger(turnPrompt, 0, config.houses, config.quitNumber, config.quitLetter);
		if (playerMove == config.quitNumber) {
			return false;
		}

		boolean extraTurn = boardController.move(currentPlayer.id(), playerMove);
		if (!extraTurn) {
			playerHandler.nextPlayer();
		}
		return true;
	}

	private void gameOver() {
		io.println("Game over");
		boardController.printBoard();

		if (printScores) {
			LinkedHashMap<Player, Integer> scoreBoard = new LinkedHashMap<Player, Integer>();
			for (Player player : playerHandler.getAllPlayers()) {
				scoreBoard.put(player, boardController.getFinalPlayerScore(player.id()));
			}

			new ScorePrinter(io).printScoreboard(scoreBoard);
		}
	}
}
