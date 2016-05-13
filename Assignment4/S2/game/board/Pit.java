package game.board;

public abstract class Pit {

	private int value;

	public Pit(int startingValue) {
		this.value = startingValue;
	}

	public void add(int amount) {
		value += amount;
	}

	public int total() {
		return value;
	}

	public int reset() {
		int init = value;
		value = 0;
		return init;
	}

	public void increment() {
		value++;
	}
}
