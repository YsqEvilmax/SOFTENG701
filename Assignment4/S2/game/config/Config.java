package game.config;

// stores the variables for a specific game instance
public class Config {

	public final int players;
	public final int stones;
	public final int houses;
	public final int quitNumber;
	public final String quitLetter;

	public Config(int players, int stones, int houses, int quitNumber, String quitLetter) {
		this.players = players;
		this.stones = stones;
		this.houses = houses;
		this.quitNumber = quitNumber;
		this.quitLetter = quitLetter;
	}
}
