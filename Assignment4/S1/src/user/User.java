package user;

import java.util.ArrayList;
import container.House;
import container.Store;

/**
 * Provides contract for different types of Users
 */
public interface User {
	
	public ArrayList<House> getHouses();
	public Store getStore();

}
