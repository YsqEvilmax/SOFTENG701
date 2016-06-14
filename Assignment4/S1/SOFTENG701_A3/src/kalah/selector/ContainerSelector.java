package kalah.selector;

import java.util.LinkedList;
import kalah.stucture.Container;
import kalah.stucture.House;
import kalah.stucture.Store;

public class ContainerSelector extends LinearSelector<Container> {
	
	public void addStore(){
		if(!myCollections.contains(getStore())){
			myCollections.addLast(getStore());
		}	
	}
	
	public void removeStore(){
		if(myCollections.getLast().equals(getStore())){
			myCollections.removeLast();
		}		
	}
	
	protected Store myStore;
	public Store getStore(){
		return myStore;
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<House> getHouses(){
		LinkedList<Container> result = new LinkedList<Container>(myCollections);
		if(result.getLast().equals(getStore())){
			result.removeLast();
		}
		return (LinkedList<House>)(LinkedList<?>)result;
	}
	
	public LinkedList<Container> getContainers(){
		LinkedList<Container> result = new LinkedList<Container>(myCollections);
		if(!result.contains(getStore())){
			result.addLast(getStore());
		}
		return result;
	}
}
