package kalah.playeraction;

import java.util.Collection;
import java.util.LinkedList;

public abstract class ActionPool {

	public ActionPool() {
		// TODO Auto-generated constructor stub
	}
	
	public GameAction getAction(String name){
		return myActions.stream().filter(x -> x.myName.get() == name).findFirst().get();
	}
	
	public void LoadAction(GameAction action){
		if(action != null 
		&& (!myActions.stream().anyMatch(x -> x.myName.get() == action.myName.get()))){
			myActions.add(action);
		}
	}

	private Collection<GameAction> myActions = new LinkedList<GameAction>();
}
