package kalah;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

import kalah.Config.Property;

public class MankalaPlayerBoard extends PlayerBoard {

	private int[] board;
	private int score;
	private Player player;
	
	public MankalaPlayerBoard(Player player){
		this.player = player;
		initilise();
	}

	@Override
	public void initilise() {
		this.score = 0;
		//Get settings object to define board size;
		board = new int[Config.getProperty(Property.BOARDSIZE)];
		//Get settings to define initial seed count
		int seedCount = Config.getProperty(Property.STARTINGSEEDS);
			Arrays.fill(board, seedCount);
	}
	
	@Override
	public void addSeed(int position, int count){
		board[position] += count;
	}
	
	@Override
	public void incrementScore(int count){
		this.score += count;
	}
	
	@Override
	public void removeSeed(int position, int count){
		board[position] -= count;
	}
	
	@Override
	public int getSeedCount(int position){
		int count = board[position];
		return count;
	}
	
	@Override
	public void clearHouse(int position){
		board[position]= 0;
	}
	
	@Override
	public Player getPlayer(){
		return this.player;
	}
	
	@Override
	public int getBoardSize(){
		return this.board.length;
	}
	
	@Override
	public int getScore(){
		return this.score;
	}

	@Override
	public int getHouseSeedCount() {
		return IntStream.of(board).sum();
	}

	@Override
	public int compareTo(PlayerBoard arg0) {
		return getScore() - arg0.getScore();
	}


}
