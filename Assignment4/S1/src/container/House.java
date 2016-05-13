package container;

import util.GameVariables;

/**
 *	Represents Players' Houses which hold seeds on their side of the Board
 */
public class House extends Container {

	public House() {
		numSeeds = GameVariables.NUM_STARTING_SEEDS_PER_HOUSE;
	}

}
