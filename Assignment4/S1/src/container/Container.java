package container;

/**
 * Provides blueprints for potential future Containers
 */
public abstract class Container {

	protected int numSeeds;
	
	public Container() {
		numSeeds = 0;
	}
	
	public Boolean isEmpty() { 
		if (numSeeds == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void addSeeds(int seeds) {
		numSeeds += seeds;
	}
	
	public void addSeed() {
		numSeeds++;
	}
	
	public void removeAllSeeds() {
		numSeeds = 0;
	}
	
	public int getNumSeeds() {
		return numSeeds;
	}
	
}
