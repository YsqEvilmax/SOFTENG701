package kalah.rule;

import kalah.Game;

public interface IRule<T> {
	public void Affect(Game game, T arg);
}
