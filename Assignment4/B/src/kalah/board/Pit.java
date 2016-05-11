package kalah.board;

public class Pit {
	/**
	 * This is a pit that seeds can be taken from
	 */
	private final boolean _canRemove;
	private final boolean _isStore;
	private final String _pid;
	private final Side _side;
	private int _nSeeds = 0;
	
	public Pit(String id, Side side, boolean isStore, boolean canRemove, int nSeeds) {
		_isStore = isStore;
		_canRemove = canRemove;
		_nSeeds = nSeeds;
		_pid = id;
		_side = side;
	}
	public boolean canRemove() {
		return _canRemove;
	}
	public boolean isEmpty() {
		return _nSeeds == 0;
	}
	public int removeAll() {
		if (!canRemove()) {
			throw new IllegalArgumentException("Can't remove from this pit");
		}
		int nSeeds = _nSeeds;
		_nSeeds = 0;
		return nSeeds;
	}
	public void sow(int amount) {
		// Defensive check
		if (amount <= 0) {
			throw new IllegalArgumentException("amount should be positive (was " + amount + ")");			
		}
		_nSeeds += amount;
	}
	public boolean isStore() {
		return _isStore;
	}
	public int nSeeds() {
		return _nSeeds;
	}
	public void capture(int n) {
		if (!isStore()) {
			throw new IllegalArgumentException("Can't can't capture in non-store.");
		}
		_nSeeds += n;
	}
	public boolean is(String id) {
		return _pid.equals(id);
	}
	
	public String getID() {
		return _pid;
	}
	
	public String dump() {
		return _pid + "@" + _side + "(" + _nSeeds + ")";
	}
	public String toString() {
		String spacing = ""; 
		if (_nSeeds < 10) {
			spacing = " ";
		}
		if (_isStore) {
			return spacing + _nSeeds;
		} else {
			return _pid + "[" + spacing + _nSeeds + "]";
		}
	}
	
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		Pit other = (Pit)obj;
		if (!_side.equals(other._side)) {
			return false;
		}
		return _pid.equals(other._pid);
	}
}
