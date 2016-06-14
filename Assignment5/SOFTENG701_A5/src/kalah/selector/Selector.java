package kalah.selector;

import java.util.LinkedList;

import kalah.wrapper.Wrapper;

public abstract class Selector<T> implements ISelectable<T> {

	public Selector() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public T getCurrent(){
		return this.myCollections.get(myCurrentIndex.get());
	}

	public Wrapper<Integer> myCurrentIndex = new Wrapper<Integer>(0);

	protected LinkedList<T> myCollections = new LinkedList<T>();
	public LinkedList<T> getCollections(){
		return myCollections;
	}
}
