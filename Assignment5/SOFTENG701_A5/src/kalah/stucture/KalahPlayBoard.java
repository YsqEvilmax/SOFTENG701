package kalah.stucture;

import java.util.LinkedList;

import kalah.playeraction.GamePlayer;

public class KalahPlayBoard extends PlayBoard {

	static public final int initalHouseNum = 6;
	
	static public final int initalSeedNum = 4;

	public KalahPlayBoard() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void Initialize(LinkedList<GamePlayer> players){	
		for(int i = 0; i < players.size(); i++){
			Group group = new Group();
			group.Initialize(KalahPlayBoard.initalHouseNum, KalahPlayBoard.initalSeedNum);
			group.myPlayer.set(players.get(i));
			
			myCollections.add(group);
			
			players.get(i).myGroup.set(group);
		}
	}
}
