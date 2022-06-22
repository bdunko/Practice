package datastructures;

import testing.Test;

//Generic deque (stack + queue) class, implemented internally as a linked list
public class BDeque<T> {
	
	private BNode<T> head;
	private BNode<T> tail;
	private int size;
	
	public BDeque() {
		clear();
	}
	
	public T popFront() {
		if(size == 0)
			return null;
		
		T elem = head.elem;
		head = head.next;
		size--;
		if(size == 0) {
			tail = null;
		}
		return elem;
	}
	
	public void pushFront(T elem) {
		if(size == 0) {
			head = new BNode<T>(elem);
			tail = head;
		} else {
			head.prev = new BNode<T>(elem);
			head.prev.next = head;
			head = head.prev;
		}
		size++;
	}
	
	public T popBack() {
		if(size == 0)
			return null;
		
		T elem = tail.elem;
		tail = tail.prev;
		size--;
		if(size == 0) {
			head = null;
		}
		return elem;
	}
	
	public void pushBack(T elem) {
		if(size == 0) {
			tail = new BNode<T>(elem);
			head = tail;
		} else {
			tail.next = new BNode<T>(elem);
			tail.next.prev = tail;
			tail = tail.next;
		}
		size++;
	}
	
	public T peekFront() {
		if(size == 0)
			return null;
		return head.elem;
	}
	
	public T peekBack() {
		if(size == 0)
			return null;
		return tail.elem;
	}
	
	public int size() {
		return size;
	}
	
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof BDeque<?>))
			return false;
		
		BDeque<?> other = (BDeque<?>)o;
		if(other.size() != size())
			return false;
		
		BNode<T> myCurrent = head;
		BNode<?> otherCurrent = other.head;
		while(myCurrent != null) {
			if(!myCurrent.elem.equals(otherCurrent.elem))
				return false;
			
			myCurrent = myCurrent.next;
			otherCurrent = otherCurrent.next;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return "";
	}
	
	public static void main(String[] args) {
		Test.header("Deque");
		
		Test.header("Constructor/size");
		BDeque<Integer> deque = new BDeque<Integer>();
		Test.equals(deque.size(), 0);
		Test.isNull(deque.popBack());
		Test.isNull(deque.popFront());
		Test.isNull(deque.peekFront());
		Test.isNull(deque.peekBack());
		
		Test.header("pushFront/pushBack/peekFront/peekBack/popFront/popBack");
		deque.pushFront(1); // 1
		deque.pushFront(2); // 2 1
		deque.pushFront(3); // 3 2 1
		deque.pushFront(4); // 4 3 2 1
		Test.equals(deque.size(), 4);
		Test.equals(deque.peekFront(), 4);
		Test.equals(deque.popFront(), 4); //3 2 1
		Test.equals(deque.peekFront(), 3);
		Test.equals(deque.peekBack(), 1);
		Test.equals(deque.popBack(), 1); //3 2
		Test.equals(deque.peekBack(), 2);
		Test.equals(deque.size(), 2);
		Test.equals(deque.popFront(), 3); //2
		Test.equals(deque.popBack(), 2); //(empty)
		Test.equals(deque.size(), 0);
		Test.isNull(deque.popBack());
		Test.isNull(deque.popFront());
		deque.pushBack(1); //1
		deque.pushBack(2); //1 2
		deque.pushBack(3); //1 2 3
		Test.equals(deque.popFront(), 1);
		Test.equals(deque.popFront(), 2);
		Test.equals(deque.popFront(), 3);
		Test.equals(deque.size(), 0);
		Test.isNull(deque.popBack());
		Test.isNull(deque.popFront());
		
		Test.header("clear");
		deque.pushFront(1000);
		deque.clear();
		Test.equals(deque.size(), 0);
		Test.isNull(deque.popBack());
		Test.isNull(deque.popFront());
		
		Test.header("equals");
		Test.assertion(deque.equals(new BDeque<Integer>()));
		BDeque<Integer> diff = new BDeque<Integer>();
		diff.pushFront(100);
		diff.pushFront(200);
		BDeque<Integer> same = new BDeque<Integer>();
		same.pushFront(1);
		deque.pushFront(1);
		same.pushFront(2);
		deque.pushFront(2);
		same.pushFront(3);
		deque.pushFront(3);
		Test.assertion(deque.equals(same));
		Test.assertion(!deque.equals(diff));
		
		Test.results();
	}
}
