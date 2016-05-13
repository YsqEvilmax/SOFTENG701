package game.board;

import game.InvalidMoveException;
import game.NoValidMoveException;

public class BoardController {

	private final int houseCount;
	private final int playerCount;
	private final Board board;
	private final BoardPrinter printer;

	public BoardController(int numberOfHouses, int numberOfPlayers, int numberOfStones, BoardPrinter printer) {
		this.houseCount = numberOfHouses;
		this.playerCount = numberOfPlayers;
		this.printer = printer;
		this.board = new Board(numberOfHouses, numberOfPlayers);
		this.board.placeStones(numberOfStones);
	}

	// Moves the stones in the specified location and returns the landing area
	public boolean move(int playerId, int house) throws InvalidMoveException, NoValidMoveException {
		int x = house - 1; // start at 0
		int y = playerId;
		int stones = board.get(x, y).reset();

		checkEmptyHouseRule(stones);

		while (stones > 0) {
			if (x < houseCount) {
				x++;
			} else if (y < playerCount - 1) {
				y++;
				x = 0;
			} else {
				x = 0;
				y = 0;
			}

			if (!((board.get(x, y) instanceof Store) && y != playerId)) {
				board.get(x, y).increment();
				stones--;
			}
		}

		checkEmptyRowRule();
		checkCaptureRule(playerId, x, y);

		return checkTakeAnotherTurnRule(x, y);
	}

	public void printBoard() {
		printer.printBoard(this, houseCount);
	}

	public int getFinalPlayerScore(int id) {
		return getPlayerScore(id) + getRowTotal(id);
	}

	protected int getHouseTotal(int house, int playerId) {
		int houseIndex = house - 1;
		return board.get(houseIndex, playerId).total();
	}

	protected int getPlayerScore(int playerId) {
		int storeIndex = houseCount + 1;
		return getHouseTotal(storeIndex, playerId);
	}

	private int getRowTotal(int playerId) {
		int rowTotal = 0;
		for (int j = 1; j <= houseCount; j++) {
			rowTotal += getHouseTotal(j, playerId);
		}
		return rowTotal;
	}

	private void checkEmptyHouseRule(int value) throws InvalidMoveException {
		if (value == 0) {
			throw new InvalidMoveException("House is empty. Move again.");
		}
	}

	private void checkEmptyRowRule() throws NoValidMoveException {
		for (int i = 0; i < playerCount; i++) {
			int rowTotal = 0;
			for (int j = 0; j < houseCount; j++) {
				rowTotal += board.get(j, i).total();
			}
			if (rowTotal == 0) {
				printBoard();
				throw new NoValidMoveException();
			}
		}
	}

	// 2-player implementation of capture rule
	private void checkCaptureRule(int playerId, int x, int y) {

		if (playerCount != 2) {
			return;
		}

		boolean selfOwned = (y == playerId);
		boolean wasEmpty = (board.get(x, y).total() == 1);
		boolean isHouse = (board.get(x, y) instanceof House);

		if (selfOwned && wasEmpty && isHouse) {
			int sum = 0;
			// the opposite house is calculated by its index
			int oppositeHouse = houseCount - 1 - x;
			int opponentId = 1 - playerId;

			if (board.get(oppositeHouse, opponentId).total() != 0) {
				sum += board.get(oppositeHouse, opponentId).reset();
				sum += board.get(x, playerId).reset();
				board.get(houseCount, playerId).add(sum); // update goal
			}
		}
	}

	// check if landing in (x, y) will grant another turn to the player
	public boolean checkTakeAnotherTurnRule(int x, int y) {

		if (board.get(x, y) instanceof Store) {
			return true;
		} else {
			return false;
		}
	}
}
