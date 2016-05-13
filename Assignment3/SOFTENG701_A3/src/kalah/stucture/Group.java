package kalah.stucture;

import kalah.playeraction.GamePlayer;
import kalah.selector.ContainerSelector;
import kalah.wrapper.Wrapper;

public class Group extends ContainerSelector{

	public Group() {
		// TODO Auto-generated constructor stub
	}
	
	public void Initialize(int houseNum, int seedNum){
		if(houseNum % 2 == 0){
			myStore = new Store(this);
			myCollections.clear();
			for(int i = 0; i < houseNum; i++){
				House h = new House(seedNum, this);
				h.setIndex(i + 1);
				myCollections.add(h);
			}
		}
	}
	
	public Wrapper<GamePlayer> myPlayer = new Wrapper<GamePlayer>();
}
