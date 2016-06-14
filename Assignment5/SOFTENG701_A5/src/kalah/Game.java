package kalah;

import kalah.dump.DumpSingleton;
import kalah.dump.IArgVisitor;
import kalah.dump.IDumpable;
import kalah.playeraction.ActionPool;
import kalah.playeraction.GamePlayer;
import kalah.selector.CircularSelector;
import kalah.stucture.PlayBoard;

public abstract class Game extends CircularSelector<GamePlayer> implements IDumpable<Object> {
	public Game(PlayBoard playBoard, ActionPool actionPool) {
		this.myActionPool = actionPool;
		this.myBoard = playBoard;
	}
	
	public void Begin(){
		AddActions();
		AddPlayers();
		myBoard.Initialize(myCollections);
		myBoard.Accept(DumpSingleton.getInstance(), null);	
	}
	
	public void End(){
		this.Accept(DumpSingleton.getInstance(), null);
		return;
	}
	
	public void EndWithEvaluation(){
		End();
		GamePlayer winner = this.getCollections().getFirst();
		for(GamePlayer p : this.getCollections()){
			DumpSingleton.getInstance().myIO.get().println("\tplayer " 
					+ (this.getCollections().lastIndexOf(p) + 1)
					+ ":" + p.getScore());
			if(p.getScore() > winner.getScore()){
				winner = p;
			}
		}
		
		int finalScore = winner.getScore();
		if(this.getCollections().stream().allMatch(x -> x.getScore() == finalScore)){
			DumpSingleton.getInstance().myIO.get().println("A tie!");
		}
		else{
			DumpSingleton.getInstance().myIO.get().println("Player " 
					+ (this.getCollections().lastIndexOf(winner) + 1)
					+ " wins!");
		}
	}
	
	protected abstract void AddPlayers();
	
	protected abstract void AddActions();
	
	protected abstract void Idle();
	
	protected PlayBoard myBoard;
	public PlayBoard getPlayBoard(){
		return myBoard;
	}
	
	protected ActionPool myActionPool;
	public ActionPool getActionPool(){
		return myActionPool;
	}
	
	@Override
	public void Accept(IArgVisitor<Object> v, Object arg){
		v.Visit(this, arg);
	}
}
