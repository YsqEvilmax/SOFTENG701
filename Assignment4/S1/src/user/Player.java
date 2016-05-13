package user;

import java.util.ArrayList;

import container.House;
import container.Store;
import util.GameVariables;

public class Player implements User {

	private ArrayList<House> houses = new ArrayList<House>();
	private Store store;

	public Player() {
		for (int i = 0; i < GameVariables.NUM_HOUSES_PER_PLAYER; i++) {
			houses.add(new House());
		}
		store = new Store();
	}
	
	public ArrayList<House> getHouses() {
		return houses;
	}
	
	public House getFirstHouse() {
		return houses.get(0);
	}
	
	public Store getStore() {
		return store;
	}
	
	public boolean hasAnotherHouse(int index) {
		if (index < houses.size()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isNextContainerAStore(int index) {
		if (index == houses.size()) {
			return true;
		} else {
			return false;
		}
	}
	
}
