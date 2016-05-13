package kalah;

import java.util.List;

public interface GameRules {
	
	public boolean isGameOver();
	
	public boolean calculateGameOverScore();
	
	public List<PlayerBoard> getWinners();
	
	public boolean PlayTurn(int move);

	public Board getBoard();

	public void setBoard(Board board);

	PlayerBoard getActivePlayerBoard();

	void setActivePlayerBoard(PlayerBoard activePlayerBoard);

}
