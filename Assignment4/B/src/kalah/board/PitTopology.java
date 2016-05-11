package kalah.board;

import java.util.Iterator;
import java.util.List;

/**
 * Specifies how the board is organised. A board has two "sides", one for
 * each player. Each player has a set of N+1 "pits". N of the pits are "houses" 
 * and the other pit is a "store". A house has an "id" that is unique for a player,
 * but not unique for the board.  
 */
public interface PitTopology {
	/**
	 * How many houses per player.
	 * @return The number of houses per player
	 */
	int nHouses();
	
	/**
	 * Whether or not the specified pit is owned by the specified player 
	 * @param side The side the player is playing
	 * @param pit The pit
	 * @return true iff the specified pit is owned by the specified player
	 */
	boolean ownedBy(Side player, Pit pit);
	
	/**
	 * Get the pit opposite that specified
	 * @param end The specified pit
	 * @return The pit opposite the specified pit on the board
	 */
	Pit getOpposite(Pit pit);
	
	/**
	 * Get the store for the specified player
	 * @param player The player to get the store for
	 * @return The store for the specified player
	 */
	Pit getStore(Side player);
	
	/**
	 * Get the list of houses, in order, for the specified player.
	 * @param player The specified player
	 * @return The list of house for the specified player.
	 */
	List<Pit> getPits(Side player);
	
	/**
	 * Get the pit specified by the id for the specified player
	 * @param player The player whose pit we want
	 * @param pid The id of the pit
	 * @return The {@link Pit} with the specified id for the specified player.
	 */
	Pit getPit(Side player, String pid);
	
	/**
	 * Get an iterator that will start at the pit with the specified id 
	 * for the specified player and on each call provide the next pit 
	 * (including the store for the specified player) in an anti-clockwise
	 * direction.
	 * @param player The player whose pit the iterator should start at
	 * @param pid The id of the pit
	 * @return An iterator that will visit each pit in an anti-clockwise direction
	 */
	Iterator<Pit> iterator(Side player, String pid);
}
