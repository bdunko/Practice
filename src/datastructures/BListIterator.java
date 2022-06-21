package datastructures;

public interface BListIterator<E> {
	E next();
	E prev();
	boolean hasNext();
	boolean hasPrev();
	int nextIndex();
	int prevIndex();
	void add(E elem);
	boolean remove();
	boolean set(E elem);
}
