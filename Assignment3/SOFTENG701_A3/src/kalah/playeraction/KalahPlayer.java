package kalah.playeraction;

import kalah.Game;
import kalah.rule.EmptyHouse;
import kalah.rule.RuleObserSingleton;
import kalah.stucture.Container;

public class KalahPlayer extends GamePlayer {

	public KalahPlayer() {
		// TODO Auto-generated constructor stub
	}

	public KalahPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public KalahPlayer(String name, Game game) {
		super(name, game);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getScore(){
		int score = 0;
		for(Container c : myGroup.get().getContainers()){
			score += c.mySeedNum.get().intValue();
		}
		return score;
	}

	@Override
	public void Act(IAction<Object> action, Object arg) {
		// TODO Auto-generated method stub
		int index = ((Integer)arg).intValue();
		if(myGroup.get().getHouses().get(index).mySeedNum.get() == 0){
			RuleObserSingleton.getInstance().Observe(new EmptyHouse());
		}
		myGroup.get().addStore();
		myGroup.get().myCurrentIndex.set(index);
		
		action.Do(arg);
	}

}
