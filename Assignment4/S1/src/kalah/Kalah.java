package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import util.Command;
import util.GameVariables;

/**
 * Handles running the entire Mancala game: sets up the board, reacts to user input and
 * keeps track of player turn
 */
public class Kalah {

	private Printer printer;
	private Board board;
	private int currentPlayer;
	private String input;
	private Command command;
	private boolean gameInProgress;

	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}

	public void play(IO io) {
		setupGame(io);
		gameInProgress = true;
		while (gameInProgress) {
			if (board.isGameOver(currentPlayer)) {
				printer.gameComplete(board);
				endGame();
				break;
			}
			printer.printBoard(board);
			input = printer.promptUserInput(currentPlayer);
			command = board.processInput(input, currentPlayer);
			switch (command) {
				case VALID_MOVE:
					boolean anotherTurn = board.makeMove(input, currentPlayer);
					if (!anotherTurn) {
						endTurn();
					}
					break;
				case QUIT:
					printer.gameQuit(board);
					endGame();
					break;
				case EMPTY_HOUSE:
					printer.emptyHouse();
					break;
				case INVALID_MOVE:
					System.out.println("Invalid input supplied!");
					break;
				default:
					System.out.println("*** This should never be reached, as INVALID_MOVE Command "
							+ "handles all other non-valid Command inputs! ***");
					break;
			}
		 }
	}

	private void setupGame(IO io) {
		printer = new Printer(io);
		board = new Board();
		currentPlayer = 1;
	}

	private void endTurn() {
		if (currentPlayer == GameVariables.NUM_PLAYERS) {
			currentPlayer = 1;
		} else {
			currentPlayer++;
		}
	}
	
	private void endGame() {
		gameInProgress = false;
	}
	
}
