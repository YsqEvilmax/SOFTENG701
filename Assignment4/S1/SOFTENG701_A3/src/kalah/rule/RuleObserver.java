package kalah.rule;

import java.util.ArrayList;

import kalah.Game;

public class RuleObserver<T> {

	public RuleObserver() {
		// TODO Auto-generated constructor stub
	}
	
	public void Observe(IRule<T> rule){
		myRules.add(rule);
	}
	
	public void ApplyRules(Game game, T arg){
		for(int i = 0; i < myRules.size(); i++){
			myRules.get(i).Affect(game, arg);
		}
		myRules.clear();
	}
	
	private ArrayList<IRule<T>> myRules = new ArrayList<IRule<T>>();
}
