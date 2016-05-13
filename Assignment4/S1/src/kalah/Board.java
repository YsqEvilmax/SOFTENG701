package kalah;

import java.util.ArrayList;
import container.Container;
import container.House;
import container.Store;
import user.Player;
import util.Command;
import util.GameVariables;

/**
 * Keeps track of the board state
 */
public class Board {

	private ArrayList<Player> players = new ArrayList<Player>();
	private int sideOfBoard;
	private int currentHouseNumber;

	public Board() {
		for (int i = 0; i < GameVariables.NUM_PLAYERS; i++) {
			players.add(new Player());
		}
	}
	
	public Player getPlayer(int playerIndex) {
		return players.get(playerIndex);
	}
	
	public Player getOtherPlayer(int player) {
		if (player == 1) {
			return getSecondPlayer();
		} else {
			return getFirstPlayer();
		}
	}
	
	public Player getFirstPlayer() {
		return players.get(0);
	}
	
	public Player getSecondPlayer() {
		return players.get(1);
	}

	public Command processInput(String input, int currentPlayer) {
		if (isValidMove(input, currentPlayer)) {
			return Command.VALID_MOVE;
		} else if (isQuit(input)) {
			return Command.QUIT;
		} else if (isEmptyHouse(input, currentPlayer)) {
			return Command.EMPTY_HOUSE;
		} else {
			return Command.INVALID_MOVE;
		}
	}

	private boolean isValidMove(String input, int currentPlayer) {
		try {
			int houseNumber = Integer.parseInt(input);
			if (houseNumber >0 && houseNumber <= GameVariables.NUM_HOUSES_PER_PLAYER) {
				if (!houseIsEmpty(houseNumber, currentPlayer)) {
					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	private boolean isQuit(String input) {
		if (input.equals("q")) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isEmptyHouse(String input, int currentPlayer) {
		try {
			int houseNumber = Integer.parseInt(input);
			if (houseIsEmpty(houseNumber, currentPlayer)) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	private boolean houseIsEmpty(int input, int currentPlayer) {
		return getPlayer(currentPlayer - 1).getHouses().get(input - 1).isEmpty();
	}

	public boolean makeMove(String input, int currentPlayer) {
		sideOfBoard = currentPlayer;
		currentHouseNumber = Integer.parseInt(input);
		int remainingSeeds = getPlayer(currentPlayer - 1).getHouses().get(currentHouseNumber - 1).getNumSeeds();
		Container container = getPlayer(currentPlayer - 1).getHouses().get(currentHouseNumber - 1);
		container.removeAllSeeds();
		
		while (remainingSeeds > 0) {
			container = getNextContainer(container, sideOfBoard, currentPlayer);
			container.addSeed();
			remainingSeeds--;
			currentHouseNumber++;
		}
		
		// Check capture
		if (finalHouseWasEmpty(container)) {
			if (playerOwnsFinalHouse(container, currentPlayer)) {
				captureSeeds(container, currentPlayer);
			}
		}
		
		// Check if player gets another turn
		if (finalSeedLandedInOwnStore(container, currentPlayer)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean finalHouseWasEmpty(Container container) {
		if (isFirstPlayerStore(container) || isSecondPlayerStore(container)) {
			return false;
		}
		if (container.getNumSeeds() == 1) {
			return true;
		}
		return false;
	}
	
	private boolean playerOwnsFinalHouse(Container container, int currentPlayer) {
		if (currentPlayer == sideOfBoard) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 'Capture' rule: if the last seed is sown in one of the player's own houses that was empty and the
	 * opposite house owned by the opponent has at least one seed in it, add the seed just sown and
	 * all the seeds in the opposite house to the player's store
	 */
	private void captureSeeds(Container container, int currentPlayer) {
		Player player = getPlayer(currentPlayer - 1);
		Store playerStore = player.getStore();
		Player oppositePlayer = getOtherPlayer(currentPlayer);
		House oppositeHouse = oppositePlayer.getHouses().get(GameVariables.NUM_HOUSES_PER_PLAYER - currentHouseNumber);
		
		if (oppositeHouse.getNumSeeds() == 0) {
			return;
		}
		
		// Add captured seeds to store
		playerStore.addSeeds(container.getNumSeeds() + oppositeHouse.getNumSeeds());
		
		// Remove captured seeds from previous containers
		container.removeAllSeeds();
		oppositeHouse.removeAllSeeds();
	}

	private boolean finalSeedLandedInOwnStore(Container container, int currentPlayer) {
		if (currentPlayer == 1) {
			if (isFirstPlayerStore(container)) {
				return true;
			}
		} else if (currentPlayer == 2) {
			if (isSecondPlayerStore(container)) {
				return true;
			}
		}
		return false;
	}

	private boolean isFirstPlayerStore(Container container) {
		if (container.equals(getFirstPlayer().getStore())) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isSecondPlayerStore(Container container) {
		if (container.equals(getSecondPlayer().getStore())) {
			return true;
		} else {
			return false;
		}
	}

	private Container getNextContainer(Container container, int sideOfBoard, int currentPlayer) {
		Player player = getPlayer(sideOfBoard - 1);
		if (player.isNextContainerAStore(currentHouseNumber)) {
			if (isCurrentPlayersSideOfBoard(currentPlayer)) {
				swapBoardSides();
				return player.getStore();
			} else {
				swapBoardSides();
				currentHouseNumber = 0;
				return getPlayer(currentPlayer - 1).getFirstHouse();
			}
		} else {
			return player.getHouses().get(currentHouseNumber);
		}
	}
	
	private boolean isCurrentPlayersSideOfBoard(int currentPlayer) {
		return (currentPlayer == sideOfBoard) ? true : false;
	}

	private void swapBoardSides() {
		if (sideOfBoard == 1) {
			sideOfBoard = 2;
			currentHouseNumber = -1;
		} else if (sideOfBoard == 2) {
			sideOfBoard = 1;
			currentHouseNumber = -1;
		}
	}

	/**
	 *	Checks if game is over (i.e. all houses on one side of the board are empty)
	 */
	public boolean isGameOver(int currentPlayer) {
		int emptyHouses = 0;
		Player player = getPlayer(currentPlayer - 1);
		for (House house : player.getHouses()) {
			if (house.isEmpty()) {
				emptyHouses++;
			}
		}
		if (emptyHouses == player.getHouses().size()) {
			return true;
		}
		return false;
	}
	
}
