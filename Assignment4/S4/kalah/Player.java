package kalah;

public class Player {
	private int playerNumber;
	
	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public Player(int number){
		playerNumber = number;
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if ((o instanceof Player) && (((Player) o).getPlayerNumber() == this.playerNumber)) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	 
	 @Override
	 public String toString(){
		 return this.playerNumber+"";
	 }

}
