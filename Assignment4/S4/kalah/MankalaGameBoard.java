package kalah;

import java.util.ArrayList;
import java.util.List;

public class MankalaGameBoard implements Board {
	private List<PlayerBoard> playerBoards = new ArrayList<PlayerBoard>();
	
	public MankalaGameBoard(int boardCount){
		for (int i = 1; i <= boardCount; i++){
			PlayerBoard board = new MankalaPlayerBoard(new Player(i));
			this.playerBoards.add(board);
		}
	}
	
	@Override
	public List<PlayerBoard> getPlayerBoards() {
		return playerBoards;
	}

	@Override
	public void setBoards(List<PlayerBoard> boards) {
		this.playerBoards = boards;
	}

	
	
}
