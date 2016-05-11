package kalah;

import com.qualitascorpus.testsupport.IO;

public class Board {
	private static final int HOUSES = 6;
	private static final int INIT_SEEDS = 4;
	private int[][] _houses;
	private int[] _stores;
	public Board() {
		_houses = new int[2][HOUSES];
		for (int i = 0; i < HOUSES; i++) {
			_houses[0][i] = INIT_SEEDS;
			_houses[1][i] = INIT_SEEDS;
		}
		_stores = new int[2];
	}
	public MoveResult move(int player, int house) {
		if (player != 1 && player != 2) {
			throw new RuntimeException("Invalid player on move {" + player + "}");
		}
		if (house < 1 || house > HOUSES) {
			throw new RuntimeException("Invalid house on move {" + house + "}");
		}
		int seeds = _houses[player-1][house-1];
		if (seeds == 0) {
			return MoveResult.EmptyHouse;
		}
		int current = house;
		_houses[player-1][house-1] = 0;
		while (seeds > 0) {
			// Do the player's houses
			int currentSeeds = 0;
			while (seeds > 0 && current < HOUSES) {
				current++;
				currentSeeds = _houses[player-1][current-1];
				_houses[player-1][current-1]++;
				seeds--;
			}
			// Did finish in an empty house and is the opposite house empty?
			if (seeds == 0) {
				if (currentSeeds == 0 && _houses[2-player][HOUSES-current] != 0) {
					_stores[player-1] += _houses[player-1][current-1];
					_houses[player-1][current-1] = 0;
					_stores[player-1] += _houses[2-player][HOUSES-current];
					_houses[2-player][HOUSES-current] = 0;
				}
				return MoveResult.MoveEnded;
			}
			// Player's store
			_stores[player-1]++;
			seeds--;
			if (seeds == 0) {
				return MoveResult.Continue;
			}
			// Do the opponent's houses
			current = 0;
			int opponent = 3 - player;
			while (seeds > 0 && current < HOUSES) {
				current++;
				_houses[opponent-1][current-1]++;
				seeds--;
			}

			current = 0;
		}
		return MoveResult.MoveEnded;
	}

	public boolean isGameOver(int player) {
		boolean[] nonEmptyHouse = new boolean[2];
		for (int i = 0; i < 6; i++) {
			for (int p = 1; p <= 2; p++) {
				if (_houses[p-1][i] > 0) {
					nonEmptyHouse[p-1] = true;
				}
			}
		}
		return !nonEmptyHouse[player-1];
	}
	
	public int[] getScore() {
		int[] result = new int[2];
		for (int i = 0; i < 6; i++) {
			result[0] += _houses[0][i];
			result[1] += _houses[1][i];
		}
		result[0] += _stores[0];
		result[1] += _stores[1];
		return result;
	}
	
	public void print(IO io) {
		io.print("+----");
		for (int i = 0; i < HOUSES; i++) {
			io.print("+-------");
		}
		io.println("+----+");

		io.print("| P2 ");
		for (int i = HOUSES-1; i >= 0; i--) {
			io.print("| " + (i+1) + "[" + formatNumber(_houses[1][i]) + "] ");
		}
		io.println("| " + formatNumber(_stores[0]) + " |");
		
		io.print("|    ");
		io.print("|-------");
		for (int i = 1; i < HOUSES; i++) {
			io.print("+-------");
		}
		io.println("|    |");
		
		io.print("| " + formatNumber(_stores[1]) + " ");
		for (int i = 0; i < HOUSES; i++) {
			io.print("| " + (i+1) + "[" + formatNumber(_houses[0][i]) + "] ");
		}
		io.println("| P1 |");

		io.print("+----");
		for (int i = 0; i < HOUSES; i++) {
			io.print("+-------");
		}
		io.println("+----+");
	}
	
	private static String formatNumber(int n) {
		if (n < 10) {
			return " " + n;
		} else {
			return "" + n;
		}
	}
}
