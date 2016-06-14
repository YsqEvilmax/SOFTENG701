package kalah.playeraction;

import kalah.Game;
import kalah.rule.CaptureOpponent;
import kalah.rule.ChangeTurn;
import kalah.rule.HoldTurn;
import kalah.rule.RuleObserSingleton;
import kalah.stucture.House;
import kalah.stucture.Store;

public class SowAction extends GameAction {

	public SowAction() {
		// TODO Auto-generated constructor stub
	}

	public SowAction(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public SowAction(String name, Game game) {
		super(name, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Do(Object arg) {
		House h = (House)myGame.get().getCurrent().myGroup.get().getCurrent();
		myGame.get().getPlayBoard().myCurrentIndex.set(myGame.get().getPlayBoard().getGroups().indexOf(h.getGroup()));
		myGame.get().getPlayBoard().getCurrent().myCurrentIndex.set(myGame.get().getPlayBoard().getCurrent().getHouses().indexOf(h));
		int count = h.mySeedNum.get();
		h.mySeedNum.set(0);
		for(int i= 0 ; i < count; i++){
			if(!myGame.get().getPlayBoard().getCurrent().MoveToNext()){
				myGame.get().getPlayBoard().MoveToNext();
				myGame.get().getPlayBoard().getCurrent().myCurrentIndex.set(0);
			}
			myGame.get().getPlayBoard().getCurrent().getCurrent().mySeedNum.Increase(1);
			
			if(i == count - 1){
				if(myGame.get().getPlayBoard().getCurrent().getCurrent() instanceof Store){
					RuleObserSingleton.getInstance().Observe(new HoldTurn());
					//game.getCurrent().myGroup.get().removeStore();
				}
				else{
					if(myGame.get().getPlayBoard().getCurrent().getCurrent().getGroup()
							.equals(myGame.get().getCurrent().myGroup.get())
							&& myGame.get().getPlayBoard().getCurrent().getCurrent().mySeedNum.get() == 1){
						//CaptureOpponent co = new CaptureOpponent();
						//co.Affect(game, arg);
						RuleObserSingleton.getInstance().Observe(new CaptureOpponent());
					}	
					else{
						RuleObserSingleton.getInstance().Observe(new ChangeTurn());
						//game.getCurrent().myGroup.get().removeStore();
						//game.MoveToNext();
					}
				}			
			}			
		}
	}

}
