package kalah;

public abstract class PlayerBoard implements Comparable<PlayerBoard> {

	abstract void initilise();

	abstract void addSeed(int position, int count);

	abstract void incrementScore(int count);

	abstract void removeSeed(int position, int count);

	abstract int getSeedCount(int position);

	abstract void clearHouse(int position);

	abstract Player getPlayer();

	abstract int getBoardSize();

	abstract int getScore();

	abstract int getHouseSeedCount();

	@Override
	public int compareTo(PlayerBoard arg0) {
		return getScore() - arg0.getScore();
	}

}