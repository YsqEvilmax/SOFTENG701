package kalah.stucture;

import java.util.LinkedList;

import kalah.dump.DumpVisitor;
import kalah.dump.IDumpable;
import kalah.playeraction.GamePlayer;
import kalah.selector.CircularSelector;

public class PlayBoard extends CircularSelector<Group> implements IDumpable{
	
	static public final int initalHouseNum = 6;
	
	static public final int initalSeedNum = 4;

	public PlayBoard() {	
	}
	
	public void Initialize(LinkedList<GamePlayer> players){	
		for(int i = 0; i < players.size(); i++){
			Group group = new Group();
			group.Initialize(PlayBoard.initalHouseNum, PlayBoard.initalSeedNum);
			group.myPlayer.set(players.get(i));
			
			myCollections.add(group);
			
			players.get(i).myGroup.set(group);
		}
	}
	
	public LinkedList<Group> getGroups(){
		return myCollections;
	}

	@Override
	public void Accept(DumpVisitor v, Object arg) {
		v.Visit(this, arg);
	}
}
