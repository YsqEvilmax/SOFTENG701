package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import kalah.dump.DumpSingleton;
import kalah.playeraction.KalahActionPool;
import kalah.playeraction.KalahPlayer;
import kalah.playeraction.SowAction;
import kalah.rule.RuleObserSingleton;
import kalah.stucture.KalahPlayBoard;


/**
 * This class is the starting point for the Modifiability Assignment.
 */
public class Kalah extends Game {

	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	
	public Kalah() {
		super(new KalahPlayBoard(), new KalahActionPool());
		// TODO Auto-generated constructor stub
	}

	public void play(IO io) {
		DumpSingleton.getInstance().myIO.set(io);
		Begin();
		Idle();
		//End();
	}

	@Override
	protected void AddPlayers() {
		myCollections.clear();
		myCollections.add(new KalahPlayer("P1", this));
		myCollections.add(new KalahPlayer("P2", this));
	}
	
	@Override
	protected void AddActions() {
		myActionPool.LoadAction(new SowAction("sow", this));
	}
	
	@Override
	protected void Idle(){		
			int input = -1;
			while(true){
				if(getCurrent().myGroup.get().getHouses().stream().allMatch(x -> x.mySeedNum.get() == 0)){
					EndWithEvaluation(); break;}
				if((input = ParseInput()) == -1){End(); break;}
				getCurrent().Act(myActionPool.getAction("sow"), Integer.valueOf(input - 1));
				RuleObserSingleton.getInstance().ApplyRules(this, null);
				getPlayBoard().Accept(DumpSingleton.getInstance(), null);
			}
	}
	
	private int ParseInput() {	
		String content = "Player " + getCurrent().myName.get() + "\'s turn - Specify house number or 'q' to quit: ";
		return DumpSingleton.getInstance().myIO.get().readInteger(content, 1, 6, -1, "q");
	}
}
