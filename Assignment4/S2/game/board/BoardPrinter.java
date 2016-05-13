package game.board;

import java.util.List;

import com.qualitascorpus.testsupport.IO;

import game.player.Player;

public class BoardPrinter {

	private int COLUMN_TEXT_WIDTH = 2;

	private final IO io;
	private Player p1;
	private Player p2;

	public BoardPrinter(IO io, List<Player> players) {
		this.io = io;
		this.p1 = players.get(0);
		this.p2 = players.get(1);
	}

	protected void printBoard(BoardController board, int houseCount) {
		io.println(buildBorder(houseCount));
		io.println(buildMirroredPlayerRow(houseCount, board));
		io.println(buildDivider(houseCount));
		io.println(buildPlayerRow(houseCount, board));
		io.println(buildBorder(houseCount));
	}

	private String buildBorder(int columns) {
		StringBuilder border = new StringBuilder();

		final String shortSection = "+----";
		final String longSection = "+-------";

		border.append(shortSection);
		for (int i = 0; i < columns; i++) {
			border.append(longSection);
		}
		border.append(shortSection + "+");

		return border.toString();
	}

	private String buildPlayerRow(int columns, BoardController board) {
		StringBuilder playerRow = new StringBuilder();

		String p2Score = format(Integer.toString(board.getPlayerScore(p2.id())));
		String p1Name = p1.getShortName();

		playerRow.append("| " + p2Score + " ");
		for (int i = 1; i <= columns; i++) {
			String value = format(Integer.toString(board.getHouseTotal(i, p1.id())));
			playerRow.append("| " + i + "[" + value + "] ");
		}
		playerRow.append("| " + p1Name + " |");

		return playerRow.toString();
	}

	private String buildMirroredPlayerRow(int columns, BoardController board) {
		StringBuilder playerRow = new StringBuilder();

		String p1Score = format(Integer.toString(board.getPlayerScore(p1.id())));
		String p2Name = p2.getShortName();

		playerRow.append("| " + p2Name + " ");
		for (int i = columns; i > 0; i--) {
			String value = format(Integer.toString(board.getHouseTotal(i, p2.id())));
			playerRow.append("| " + i + "[" + value + "] ");
		}
		playerRow.append("| " + p1Score + " |");

		return playerRow.toString();
	}

	private String buildDivider(int columns) {
		StringBuilder divider = new StringBuilder();

		divider.append("|    |");
		divider.append("-------");
		for (int i = 1; i < columns; i++) {
			divider.append("+-------");
		}
		divider.append("|    |");

		return divider.toString();
	}

	// returns a left-padded version of the input in string format
	private String format(String value) {
		while (value.length() < COLUMN_TEXT_WIDTH) {
			value = " " + value;
		}
		return value;
	}
}
