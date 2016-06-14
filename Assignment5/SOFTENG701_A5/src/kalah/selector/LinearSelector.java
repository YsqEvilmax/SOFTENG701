package kalah.selector;

public class LinearSelector<T> extends Selector<T> {

	@Override
	public boolean MoveToNext() {
		if(myCurrentIndex.get() < myCollections.size() - 1){
			myCurrentIndex.set(myCurrentIndex.get() + 1);
			return true;
		}
		return false;
	}

	@Override
	public T getNext() {
		return myCollections.get(myCurrentIndex.get() + 1);
	}
}
