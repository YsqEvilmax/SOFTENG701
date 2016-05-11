package kalah.move;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * The set of moves a player can choose from.
 */
public class MoveOptions implements Iterable<Move> {
	private List<Move> _moveOptions;
	public MoveOptions() {
		_moveOptions = new Vector<Move>();
	}
	public void addMove(Move option) {
		_moveOptions.add(option);
	}
	public Iterator<Move> iterator() {
		return _moveOptions.iterator();
	}
	public int number() {
		return _moveOptions.size();
	}
}
