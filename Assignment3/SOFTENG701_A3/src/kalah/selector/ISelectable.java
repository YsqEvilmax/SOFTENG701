package kalah.selector;

import java.util.LinkedList;

public interface ISelectable<T> {
	public boolean MoveToNext();
	public T getCurrent();
	public T getNext();
	public LinkedList<T> getCollections();
}
