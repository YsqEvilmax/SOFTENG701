package kalah;

import java.util.ArrayList;

import com.qualitascorpus.testsupport.IO;

import container.House;
import container.Store;
import user.Player;
import util.GameVariables;
import util.Order;

/**
 * Handles printing board state and prompting user input
 */
public class Printer {
	
	private IO io;

	public Printer(IO io) {
		this.io = io;
	}
	
	public void print(String string) {
		io.print(string);
	}
	
	public void println(String string) {
		io.println(string);
	}
	
	public void printBoard(Board board) {
		printOuterBoundary();
		printPlayerTwo(board);
		printPlayerOne(board);
		printOuterBoundary();
	}
	
	private void printOuterBoundary() {
		println("+----+-------+-------+-------+-------+-------+-------+----+");		
	}
	
	private void printInnerBoundary() {
		println("|    |-------+-------+-------+-------+-------+-------|    |");		
	}

	private void printPlayerOne(Board board) {
		Player playerOne = board.getFirstPlayer();
		Player playerTwo = board.getSecondPlayer();
		ArrayList<House> houses = playerOne.getHouses();
		Store store = playerTwo.getStore();
		int storeSeeds = store.getNumSeeds();

		print("|");
		if (isOneDigit(storeSeeds)) {
			print("  ");
		} else if (isTwoDigits(storeSeeds)) {
			print(" ");
		}
		print(intToString(storeSeeds));
		print(" |");
		printHouses(houses, Order.STANDARD);
		print(" P1");
		println(" |");
	}

	private void printPlayerTwo(Board board) {
		Player playerOne = board.getFirstPlayer();
		Player playerTwo = board.getSecondPlayer();
		ArrayList<House> houses = playerTwo.getHouses();
		Store store = playerOne.getStore();
		int storeSeeds = store.getNumSeeds();
		
		print("| P2 |");
		printHouses(houses, Order.REVERSE);
		if (isOneDigit(storeSeeds)) {
			print("  ");
		} else if (isTwoDigits(storeSeeds)) {
			print(" ");
		}
		print(intToString(storeSeeds));
		println(" |");
		printInnerBoundary();
	}

	/**
	 * Prints houses for given player according to specified order
	 */
	private void printHouses(ArrayList<House> houses, Order order) {
		int startIndex = 0;
		int endIndex = 0;
		switch (order) {
			case STANDARD:
				startIndex = 0;
				endIndex  = GameVariables.NUM_HOUSES_PER_PLAYER;
				break;
			case REVERSE:
				startIndex = GameVariables.NUM_HOUSES_PER_PLAYER - 1;
				endIndex  = -1;
				break;
			default:
				println("Invalid Order specified!");
				break;
		}
		
		int index = startIndex;
		while (index != endIndex) {
			House house = houses.get(index);
			int seeds = house.getNumSeeds();
			
			if (isOneDigit(index)) {
				print(" ");
			}
			print(intToString(index + 1));
			print("[");
			if (isOneDigit(seeds)) {
				print(" ");
			}
			print(intToString(seeds));
			print("] |");
			
			switch (order) {
			case STANDARD:
				index++;
				break;
			case REVERSE:
				index--;
				break;
			}
		}
	}

	private Boolean isOneDigit(int number) {
		if (number < 10 && number >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private Boolean isTwoDigits(int number) {
		if (number < 100 && number >= 10) {
			return true;
		} else {
			return false;
		}
	}
	
	private String intToString(int integer) {
		return Integer.toString(integer);
	}

	public String promptUserInput(int currentPlayer) {
		String input = io.readFromKeyboard("Player P" + currentPlayer + "'s turn - Specify house number or 'q' to quit: ");
		return input;
	}

	public void gameQuit(Board board) {
		println("Game over");
		printBoard(board);
	}

	public void emptyHouse() {
		println("House is empty. Move again.");
	}

	public void gameComplete(Board board) {
		int playerOnePoints = board.getFirstPlayer().getStore().getNumSeeds();
		int playerTwoPoints = board.getSecondPlayer().getStore().getNumSeeds();
		int winner;
		
		// Add player 1 points
		for (House house : board.getFirstPlayer().getHouses()) {
			playerOnePoints += house.getNumSeeds();
		}
		
		// Add player 2 points
		for (House house : board.getSecondPlayer().getHouses()) {
			playerTwoPoints += house.getNumSeeds();
		}
		
		if (playerOnePoints > playerTwoPoints) {
			winner = 1;
		} else if (playerTwoPoints > playerOnePoints) {
			winner = 2;
		} else {
			winner = 0;
		}

		printBoard(board);
		println("Game over");
		printBoard(board);
		println("\tplayer 1:" + playerOnePoints);
		println("\tplayer 2:" + playerTwoPoints);
		printWinner(winner);
	}

	private void printWinner(int winner) {
		if (winner == 0) {
			println("A tie!");
		} else {
			println("Player " + winner + " wins!");
		}
	}
	
}
