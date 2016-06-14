/**
 * 
 */
package kalah.playeraction;

import kalah.Game;
import kalah.stucture.Group;
import kalah.wrapper.Wrapper;

/**
 * @author ysqev
 *
 */
public abstract class GamePlayer extends GameComponent implements IPerson<Object> {

	/**
	 * 
	 */
	public GamePlayer() {
	}
	
	public GamePlayer(String name){
		super(name);
	}
	
	public GamePlayer(String name, Game game){
		super(name, game);
	}
	
	public abstract int getScore();
	
	@Override
	public String toString(){
		return myName.get() + " : " + myGroup.toString();
	}
		
	public Wrapper<Group> myGroup = new Wrapper<Group>();
}
