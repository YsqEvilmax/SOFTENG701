package kalah.stucture;

import java.util.LinkedList;
import kalah.dump.IArgVisitor;
import kalah.dump.IDumpable;
import kalah.playeraction.GamePlayer;
import kalah.selector.CircularSelector;

public abstract class PlayBoard extends CircularSelector<Group> implements IDumpable<Object>{

	public PlayBoard() {	
	}
	
	public abstract void Initialize(LinkedList<GamePlayer> players);
	
	public LinkedList<Group> getGroups(){
		return myCollections;
	}

	@Override
	public void Accept(IArgVisitor<Object> v, Object arg) {
		v.Visit(this, arg);
	}
}
