package kalah.dump;

import kalah.Game;
import kalah.stucture.PlayBoard;

public interface IArgVisitor<A> {
	public void Visit(Game n, A arg);
	public void Visit(PlayBoard n, A arg);
}