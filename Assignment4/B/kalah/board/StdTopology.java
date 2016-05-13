package kalah.board;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;

/**
 * Represents the connection topology of pits in a Standard Mancala game,
 * that is, two sides, 6 houses + 1 store each, with the houses aligned
 * in reverse order (so house 1 of side 1 is opposite house 6 of side 2).
 * Side 1 is the one where the first move of the game is made.
 */
public class StdTopology implements PitTopology {
	private Map<Side,List<Pit>> _houses;
	private Map<Side,Pit> _stores;
	private Map<Pit,Pit> _opposites;
	
	public StdTopology(int nHouses, int initSeeds) {
		_houses = new HashMap<Side,List<Pit>>();
		_stores = new HashMap<Side,Pit>();
		setupSide(Side.First, nHouses, initSeeds);
		setupSide(Side.Second, nHouses, initSeeds);

		_opposites = new HashMap<Pit,Pit>();

		Iterator<Pit> h1 = _houses.get(Side.First).iterator();
		List<Pit> houses = _houses.get(Side.Second);
		ListIterator<Pit> h2 = houses.listIterator(houses.size());
		while (h2.hasPrevious()) {
			Pit pit1 = h1.next();
			Pit pit2 = h2.previous();
			addOpposite(pit1, pit2);
			addOpposite(pit2, pit1);
		}
	}
	private void setupSide(Side side, int nHouses, int initSeeds) {
		List<Pit> houses = new Vector<Pit>();
		for (int i = 0; i < nHouses; i++) {
			houses.add(new Pit(""+(i+1), side, false, true, initSeeds));
		}
		_houses.put(side, houses);
		_stores.put(side, new Pit("0", side, true, false, 0));		
	}
	
	public int nHouses() {
		return _houses.get(Side.First).size();
	}

	public boolean ownedBy(Side side, Pit end) {
		return _houses.get(side).contains(end);
	}

	public Pit getPit(Side side, String pid) {
		for (Pit pit: _houses.get(side)) {
			if (pid.equals(pit.getID())) {
				return pit;
			}
		}
		return null;
	}
	
	public Iterator<Pit> iterator(Side side, String pid) {
		return iterator(side, getPit(side, pid));
	}
		
	/**
	 * Create an iterator that will iterate over all legal pits for the specified
	 * player, unless start is null, start at the first house for the First player
	 */
	private Iterator<Pit> iterator(final Side playerSide, final Pit start) {
		return new Iterator<Pit>() {
			private Pit _next = start;
			@Override
			public boolean hasNext() {
				return true;
			}
			@Override
			public Pit next() {
				Pit result = _next;
				// Figure out which side we're on
				Side side = null;
				List<Pit> houses = null;
				// Are we at a store?
				if (_next == _stores.get(Side.First)) {
					side = Side.Second;
					houses = _houses.get(Side.Second);
				} else if (_next == _stores.get(Side.Second)) {
					side = Side.First;
					houses = _houses.get(Side.First);
				} else {
					houses = _houses.get(Side.First);
					side = Side.First;
					if (!houses.contains(_next)) {
						houses = _houses.get(Side.Second);
						side = Side.Second;
					}
				}

				int index = houses.indexOf(_next);
				if (index < 0) {
					// Must be dealing with a store
					_next = houses.get(0);
				} else if (index == houses.size()-1) {
					// End of houses. If houses belong to player, then next is the store
					if (playerSide == side) {
						if (side == Side.First) {
							_next = _stores.get(Side.First);
						} else {
							_next = _stores.get(Side.Second);
						}
					} else {
						// Otherwise we change houses
						if (side == Side.First) {
							houses = _houses.get(Side.Second);
						} else {
							houses = _houses.get(Side.First);
						}
						_next = houses.get(0);
					}
				} else {
					// next house in sequence
					_next = houses.get(index+1);
				}
				return result;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException(); 
			}
		};
	}

	public Pit getOpposite(Pit end) {
		return _opposites.get(end);
	}
	private void addOpposite(Pit pit1, Pit pit2) {
		_opposites.put(pit1, pit2);
	}

	public Pit getStore(Side side) {
		return _stores.get(side);
	}
	
	public List<Pit> getPits(Side side) {
		return _houses.get(side);
	}
	
	/**
	 * Debugging only. Note uses System.out not IO, so shouldn't
	 * interfere with tests
	 */
	public void dump() {
		for (Side side: Side.values()) {
			System.out.println("Side " + side + "\n======");
			for (Pit pit:_houses.get(side)) {
				System.out.print("\t" + pit.dump());
			}
			System.out.println("\tStore:" + _stores.get(side).dump());
			for (Pit pit: _houses.get(side)) {
				System.out.print("\t" + pit + "->" + _opposites.get(pit));
			}
			System.out.println();			
		}
	}
}
