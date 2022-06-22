package datastructures;

import testing.Test;

//Generic linkedlist implementation using sentinel nodes, with iterator
public class BLinkedList<E> {

	private class Node {
		public E data;
		public Node next;
		public Node prev;
		
		private Node(E data) {
			this(data, null, null);
		}
		
		private Node(E data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		
		@Override
		public String toString() {
			return new StringBuilder().append("(").append(data == null ? "NULL" : data.toString()).append(")").toString();
		}
		
		@Override
		public boolean equals(Object o) {
			if(!(o instanceof BLinkedList.Node))
				return false;
			
			@SuppressWarnings("unchecked")
			Node other = (Node) o;
			
			return other.data.equals(this.data);
		}
	}
	
	private class LinkedListIterator implements BListIterator<E> {

		private BLinkedList<E> list;
		private int position;
		private Node current;
		private Node last;
		
		public LinkedListIterator(BLinkedList<E> list) {
			this.position = 0;
			this.current = list.HEAD.next;
			this.list = list;
			this.last = null;
		}
		
		@Override
		public E next() {
			if(current == list.TAIL)
				return null;
			
			E data = current.data;
			last = current;
			current = current.next;
			position++;
			
			return data;
		}

		@Override
		public E prev() {
			if(current.prev == list.HEAD)
				return null;
			
			current = current.prev;
			E data = current.data;
			last = current;
			position--;
			
			return data;
		}

		@Override
		public boolean hasNext() {
			return current != list.TAIL;
		}

		@Override
		public boolean hasPrev() {
			return current.prev != list.HEAD;
		}

		@Override
		public int nextIndex() {
			return position;
		}

		@Override
		public int prevIndex() {
			return position-1;
		}

		@Override
		public void add(E elem) {
			Node added = new Node(elem, current, current.prev);
			added.next.prev = added;
			added.prev.next = added;
			current = added.next;
			position++;
			list.size++;
			last = null;
		}

		@Override
		public boolean remove() {
			if(last == null)
				return false;
			
			Node removed = last;
			removed.next.prev = removed.prev;
			removed.prev.next = removed.next;
			current = removed.next;
			position--;
			list.size--;
			last = null;
			
			return true;
		}

		@Override
		public boolean set(E data) {
			if(last == null)
				return false;
			
			Node changed = last;
			changed.data = data;
			
			return true;
		}
		
	}
	
	private Node HEAD;
	private Node TAIL;
	private int size;
	
	public BLinkedList() {
		this.HEAD = new Node(null);
		this.TAIL = new Node(null);
		clear();
	}
	
	public E getFirst() {
		return HEAD.next.data;
	}
	
	public E getLast() {
		return TAIL.prev.data;
	}
	
	public E get(int index) {
		if(index > size || index < 0)
			return null;
		
		Node n = HEAD.next;
		assert n != TAIL;
		
		for(int i = 0; i < index; i++) {
			n = n.next;
		}
		
		assert n != TAIL;
		
		return n.data;
	}
	
	public boolean add(E elem) {
		if(elem == null)
			return false;
		
		Node added = new Node(elem, TAIL, TAIL.prev);
		added.prev.next = added;
		added.next.prev = added;
		
		size++;
		
		return true;
	}
	
	public boolean add(E elem, int index) {
		if(index > size || index < 0)
			return false;
		
		Node n = HEAD.next;
		
		for(int i = 0; i < index; i++)
			n = n.next;
		
		Node added = new Node(elem, n, n.prev);
		added.prev.next = added;
		added.next.prev = added;
		
		size++;
		
		return true;
	}
	
	public E remove(int index) {
		if(index > size)
			return null;
		
		Node n = HEAD.next;
		assert n != TAIL;
		
		for(int i = 0; i < index; i++) {
			n = n.next;
		}
		assert n != TAIL;
		n.prev.next = n.next;
		n.next.prev = n.prev;
		
		size--;
		
		return n.data;
	}
	
	public BListIterator<E> iterator() {
		return new LinkedListIterator(this);
	}
	
	public void clear() {
		HEAD.next = TAIL;
		TAIL.prev = HEAD;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("LinkedList - Size = ").append(size).append(" - [ ");
		
		sb.append("(HEAD)<->");
		Node n = HEAD.next;
		while(n != TAIL) {
			sb.append(n.toString()).append("<->");
			n = n.next;
		}
		sb.append("(TAIL) ]");
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Test.header("add/size");
		BLinkedList<Integer> list = new BLinkedList<Integer>();
		Test.equals(list.size(), 0);
		Test.assertion(list.isEmpty());
		for(int i = 0; i < 10; i++) {
			Test.assertion(list.add(i));
			Test.equals(list.size(), i+1);
		}
		Test.assertion(!list.isEmpty());
		
		Test.header("get/getFirst/getLast");
		Test.equals(list.getFirst(), Integer.valueOf(0));
		Test.equals(list.getLast(), Integer.valueOf(9));
		for(int i = 0; i < 10; i++) {
			Test.equals(list.get(i), Integer.valueOf(i));
		}
		
		Test.header("remove");
		Test.equals(list.remove(0), Integer.valueOf(0));
		Test.equals(list.size(), 9);
		Test.equals(list.remove(list.size()-1), Integer.valueOf(9));
		Test.equals(list.size(), 8);
		Test.equals(list.remove(3), Integer.valueOf(4));
		Test.equals(list.size(), 7);
		Test.isNull(list.remove(1000));
		Test.equals(list.size(), 7);
		
		while(!list.isEmpty()) {
			list.remove(0);
		}
		Test.equals(list.size(), 0);
		
		Test.header("add(index)/clear");
		Test.assertion(list.add(1));
		Test.assertion(list.add(3));
		Test.assertion(list.add(5));
		Test.assertion(list.add(0, 0));
		Test.assertion(list.add(2, 2));
		Test.assertion(list.add(4, 4));
		Test.log(list);
		Test.equals(list.size(), 6);
		Test.assertion(list.add(6, 6));
		Test.equals(list.size(), 7);
		Test.assertion(!list.add(10, 10));
		Test.isNull(list.get(100));
		for(int i = 0; i < list.size(); i++) {
			Test.equals(list.get(i), Integer.valueOf(i));
		}
		list.clear();
		Test.isNull(list.getFirst());
		Test.isNull(list.getLast());
		Test.isNull(list.get(0));
		Test.isNull(list.get(1));
		Test.equals(list.size(), 0);
		Test.assertion(list.isEmpty());
		
		Test.log(list);
		
		Test.results();
		
		Test.header("Iterator testing");
		list.clear();
		Test.assertion(list.isEmpty());
		for(int i = 0; i < 10; i++) {
			list.add(i);
		}
		
		Test.header("next/nextIndex/hasNext");
		Test.equals(list.size(), 10);
		BListIterator<Integer> iter = list.iterator();
		for(int i = 0; i < 10; i++) {
			Test.assertion(iter.hasNext());
			Test.equals(iter.nextIndex(), i);
			Test.equals(iter.next(), Integer.valueOf(i));
		}
		Test.assertion(!iter.hasNext());
		
		Test.header("prev/prevIndex/hasPrev");
		for(int i = 9; i >= 0; i--) {
			Test.assertion(iter.hasPrev());
			Test.equals(iter.prevIndex(), i);
			Test.equals(iter.prev(), Integer.valueOf(i));
		}
		Test.assertion(!iter.hasPrev());
		Test.equals(list.size(), 10);
		iter = list.iterator();
		int n = 0;
		while(iter.hasNext()) {
			iter.next();
			n++;
		}
		Test.equals(10, n);
		while(iter.hasPrev()) {
			iter.prev();
			n--;
		}
		Test.equals(0, n);
		
		Test.header("add");
		iter = list.iterator();
		Test.equals(list.size(), 10);
		iter.add(10000);
		Test.equals(list.size(), 11);
		Test.log(list);
		Test.equals(iter.prev(), Integer.valueOf(10000));
		Test.equals(iter.next(), Integer.valueOf(10000));
		Test.equals(iter.next(), Integer.valueOf(0));
		Test.equals(iter.next(), Integer.valueOf(1));
		iter = list.iterator();
		Test.equals(iter.next(), Integer.valueOf(10000));
		
		Test.header("set");
		iter = list.iterator();
		Test.assertion(!iter.set(333));
		iter.next();
		Test.assertion(iter.set(111));
		Test.assertion(iter.set(222));
		Test.assertion(iter.set(333));
		Test.equals(iter.next(), Integer.valueOf(0));
		Test.equals(iter.prev(), Integer.valueOf(0));
		Test.equals(iter.prev(), Integer.valueOf(333));
		Test.equals(list.size(), 11);
		
		
		Test.header("remove");
		iter = list.iterator();
		Test.assertion(!iter.remove());
		Test.equals(iter.next(), Integer.valueOf(333));
		Test.assertion(iter.remove());
		Test.assertion(!iter.remove());
		Test.assertion(!iter.set(111));
		Test.equals(list.size(), 10);
		Test.equals(iter.next(), Integer.valueOf(0));
		Test.equals(iter.next(), Integer.valueOf(1));
		Test.equals(iter.next(), Integer.valueOf(2));
		Test.equals(iter.prev(), Integer.valueOf(2));
		Test.equals(iter.prev(), Integer.valueOf(1));
		Test.assertion(iter.remove());
		Test.equals(iter.next(), Integer.valueOf(2));
		Test.equals(iter.prev(), Integer.valueOf(2));
		Test.equals(iter.prev(), Integer.valueOf(0));
		Test.log(list.toString());
		
		iter = list.iterator();
		while(iter.hasNext()) {
			iter.next();
			iter.remove();
		}
		Test.assertion(list.isEmpty());
		
		Test.results();
	}
}


