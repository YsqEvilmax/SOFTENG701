package kalah.playeraction;

import kalah.Game;
import kalah.wrapper.Wrapper;

public abstract class GameComponent {

	public GameComponent() {
		// TODO Auto-generated constructor stub
	}
	
	public GameComponent(String name){
		myName = new Wrapper<String>(name);
	}
	
	public GameComponent(String name, Game game){
		this(name);
		myGame = new Wrapper<Game>(game);
	}

	public Wrapper<String> myName = new Wrapper<String>();
	
	public Wrapper<Game> myGame = new Wrapper<Game>();
}
