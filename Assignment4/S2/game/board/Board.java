package game.board;

public class Board {
	private final int houseCount;
	private final int playerCount;
	private final Pit[][] board;

	public Board(int houseCount, int playerCount) {
		this.houseCount = houseCount;
		this.playerCount = playerCount;
		// Adding store to each row
		this.board = new Pit[houseCount + 1][playerCount];
	}

	public void placeStones(int numberOfStones) {
		for (int i = 0; i < playerCount; i++) {
			for (int j = 0; j < houseCount; j++) {
				board[j][i] = new House(numberOfStones);
			}
			board[houseCount][i] = new Store();
		}
	}

	public Pit get(int x, int y) {
		return board[x][y];
	}
}
