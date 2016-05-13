package kalah.rule;

import kalah.Game;

public class CaptureOpponent implements IRule<Object> {

	public CaptureOpponent() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Affect(Game game, Object arg) {
		int curIndex = game.getCurrent().myGroup.get().myCurrentIndex.get();
		int oppIndex = game.getCurrent().myGroup.get().getHouses().size() - curIndex - 1;
		int num = game.getNext().myGroup.get().getHouses().get(oppIndex).mySeedNum.get();
		if(num > 0){
			game.getCurrent().myGroup.get().getCurrent().mySeedNum.Decrease(1);
			game.getCurrent().myGroup.get().getStore().mySeedNum.Increase(1);
			
			game.getCurrent().myGroup.get().getStore().mySeedNum.Increase(num);
			game.getNext().myGroup.get().getHouses().get(oppIndex).mySeedNum.set(0);
		}
		game.getCurrent().myGroup.get().removeStore();
		game.MoveToNext();
	}
}
