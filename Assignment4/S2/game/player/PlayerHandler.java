package game.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerHandler {

	private final String NAME_PREFIX = "Player";

	private int nextId;
	private List<Player> players;
	private Player currentPlayer;

	public PlayerHandler() {
		this.players = new ArrayList<Player>();
		this.nextId = 0;
	}

	// will add the specified number of players and reset the player turn order
	public PlayerHandler withNumberOfPlayers(int desiredPlayers) {
		for (int i = 0; i < desiredPlayers; i++) {
			players.add(new Player(getNextId(), NAME_PREFIX));
		}
		return this;
	}

	private int getNextId() {
		return nextId++;
	}

	public Player getCurrentPlayer() {
		if (currentPlayer == null) {
			nextPlayer();
		}
		return currentPlayer;
	}

	public void nextPlayer() {
		if (currentPlayer == null) {
			currentPlayer = players.get(0);
		} else {
			int nextIndex = players.indexOf(currentPlayer) + 1;
			if (nextIndex < players.size()) {
				currentPlayer = players.get(nextIndex);
			} else {
				currentPlayer = players.get(0);
			}
		}
	}

	public List<Player> getAllPlayers() {
		return players;
	}
}
