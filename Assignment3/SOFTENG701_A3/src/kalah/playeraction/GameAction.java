package kalah.playeraction;

import kalah.Game;

public abstract class GameAction extends GameComponent implements IAction<Object>{

	public GameAction() {
		// TODO Auto-generated constructor stub
	}
	
	public GameAction(String name){
		super(name);
	}
	
	public GameAction(String name, Game game){
		super(name, game);
	}
}
