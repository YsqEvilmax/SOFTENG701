package kalah;

import java.util.List;
import java.util.stream.Collectors;

import kalah.Config.Property;

public class StandardMankalaGameRules implements GameRules {
	
	private Board board;
	private PlayerBoard activePlayerBoard;


	public StandardMankalaGameRules(Board board){
		this.board = board;
		this.activePlayerBoard = this.board.getPlayerBoards().get(0);
	}
	
	public PlayerBoard getActivePlayer() {
		return activePlayerBoard;
	}

	public void setActivePlayer(PlayerBoard activePlayerBoard) {
		this.activePlayerBoard = activePlayerBoard;
	}
	
	@Override
	public boolean PlayTurn(int move){
		if(this.activePlayerBoard.getSeedCount(move) > 0){
			this.activePlayerBoard = moveSeed(move);
			return true;
		}else{
			return false;
		}
	}

	
	// takes in origin and destination to move seed
		// Returns the next player to play
		private PlayerBoard moveSeed(int origin){
			PlayerBoard activeBoard = this.activePlayerBoard;
			PlayerBoard nextPlayerBoard = getNextBoard(activeBoard);
			int seedCount = this.activePlayerBoard.getSeedCount(origin);
			this.activePlayerBoard.clearHouse(origin);
			int position = origin+1;
			for (int i = 0; i < seedCount; i++){
				// check if final seed
				if (i == seedCount-1){
					//Check for extra turn
					if (position >= Config.getProperty(Property.BOARDSIZE)){
						if(this.activePlayerBoard.getPlayer() == activeBoard.getPlayer()){
							nextPlayerBoard = activeBoard;
						}
						// Crazy Mankala rule that captures opponents seeds
					}else if(this.activePlayerBoard.getPlayer() == activeBoard.getPlayer()){
						if (activeBoard.getSeedCount(position) == 0){
							int oppositePosition = getOppositePosition(position);
							PlayerBoard nextBoard = getNextBoard(activeBoard);
							if (nextBoard.getSeedCount(oppositePosition) > 0){
								int oppositionSeeds = nextBoard.getSeedCount(oppositePosition);
								nextBoard.clearHouse(oppositePosition);
								int houseSeeds = this.activePlayerBoard.getSeedCount(position)+1;
								this.activePlayerBoard.clearHouse(position);
								this.activePlayerBoard.incrementScore(houseSeeds + oppositionSeeds);
								continue;
							}
						}
					}
				}
				
				if (position >= Config.getProperty(Property.BOARDSIZE)){
					// outside of board boundaries add to score if player house
					if(this.activePlayerBoard.getPlayer() == activeBoard.getPlayer()){
						position = -1;
						this.activePlayerBoard.incrementScore(1);
						activeBoard = getNextBoard(activeBoard);
					}else{
						position = 0;
						//Toggle boards
						activeBoard = getNextBoard(activeBoard);
						activeBoard.addSeed(position, 1);
					}
				}else{
					// simply add a seed;
					activeBoard.addSeed(position, 1);
				}

				position++;
			}
			return nextPlayerBoard;
		}
		
		private int getOppositePosition(int position) {
			return (Config.getProperty(Property.BOARDSIZE)-1) - position;
		}

		private PlayerBoard getNextBoard(PlayerBoard activeBoard) {
			boolean boardFound = false;
			PlayerBoard board = this.board.getPlayerBoards().get(0); // base case get first
			
			for (PlayerBoard b : this.board.getPlayerBoards()){
				if (b.getPlayer() == activeBoard.getPlayer()){
					boardFound = true;
				}else if (boardFound){
					return b;
				}
			}
			
			return board;
		}
		
		@Override
		public boolean isGameOver(){
			if (this.activePlayerBoard.getHouseSeedCount() == 0){
				return true;
			}
			return false;
		}
		
		@Override
		public boolean calculateGameOverScore(){
			List<PlayerBoard> noMoreMoves = this.board.getPlayerBoards().stream().filter( b -> b.getHouseSeedCount() == 0).collect(Collectors.toList());;
			if (noMoreMoves.size() > 0){
				for (PlayerBoard board : this.board.getPlayerBoards()){
					if (board.getPlayer() != noMoreMoves.get(0).getPlayer()){
							board.incrementScore(board.getHouseSeedCount());
						}
					}
					return true;
				}
			return false;
		}
		
		@Override
		public List<PlayerBoard> getWinners(){
			int highestScore = board.getPlayerBoards().stream().mapToInt( x -> x.getScore()).max().orElse(-1);
			List<PlayerBoard> winners = board.getPlayerBoards().stream().filter( b -> b.getScore() == highestScore).collect(Collectors.toList());
			return winners;
		}
		
		@Override
		public Board getBoard() {
			return board;
		}
		
		@Override
		public void setBoard(Board board) {
			this.board = board;
		}
		
		@Override
		public PlayerBoard getActivePlayerBoard() {
			return activePlayerBoard;
		}

		@Override
		public void setActivePlayerBoard(PlayerBoard activePlayerBoard) {
			this.activePlayerBoard = activePlayerBoard;
		}

}
