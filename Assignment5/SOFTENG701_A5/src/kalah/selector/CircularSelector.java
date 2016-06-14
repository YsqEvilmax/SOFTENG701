package kalah.selector;

public class CircularSelector<T> extends Selector<T> {

	@Override
	public boolean MoveToNext() {
		myCurrentIndex.set((myCurrentIndex.get() + 1)% myCollections.size());
		return true;
	}
	
	@Override
	public T getNext(){
		return myCollections.get((myCurrentIndex.get() + 1)% myCollections.size());
	}
}
