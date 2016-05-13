package game.player;

public class Player {

	private final int id;
	private final String nameToken;

	protected Player(int id, String nameToken) {
		this.id = id;
		this.nameToken = nameToken;
	}

	public int id() {
		return id;
	}

	public String getShortName() {
		String initial = nameToken.substring(0, 1).toUpperCase();
		int displayId = id + 1;

		return initial + displayId;
	}

	public String getLongName() {
		int displayId = id + 1;

		return nameToken + " " + displayId;
	}
}
