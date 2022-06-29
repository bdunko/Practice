package datastructures;

import java.util.List;

import testing.Test;

//Simple node class used to build doubly-linked lists
public class BLinkedNode<E> {
	
	public BLinkedNode<E> prev;
	public BLinkedNode<E> next;
	public E elem;
	
	public BLinkedNode(E elem) {
		this(elem, null, null);
	}
	
	public BLinkedNode(E elem, BLinkedNode<E> next, BLinkedNode<E> prev) {
		this.elem = elem;
		this.next = next;
		this.prev = prev;
	}
	
	//Returns the length of the list starting at this node
	public int length() {
		BLinkedNode<E> runner = this;
		int length = 0;
		while(runner != null) {
			runner = runner.next;
			length++;
		}
		return length;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("(").append(elem.toString()).append(")").toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof BLinkedNode<?>))
			return false;
		BLinkedNode<?> other = (BLinkedNode<?>)o;
		
		return other.elem.equals(elem);
	}
	
	public static <T> String listToString(BLinkedNode<T> head) {
		if(head == null)
			return "[]";
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		while(head != null) {
			sb.append(head.toString()).append("-");
			head = head.next;
		}
		
		sb.delete(sb.length()-1, sb.length());
		sb.append("]");
		
		return sb.toString();
	}
	
	
	@SuppressWarnings("unlikely-arg-type")
	public static <T, E> boolean listEquals(BLinkedNode<T> n1, BLinkedNode<E> n2) {
		
		while(n1 != null && n2 != null) {
			if(!n1.elem.equals(n2.elem))
				return false;
				
			n1 = n1.next;
			n2 = n2.next;
		}
		
		//different lengths
		if((n1 == null && n2 != null) || (n1 != null && n2 == null))
			return false;
		
		return true;
	}
	
	public static <T> BLinkedNode<T> createList(List<T> elems) {
		if(elems.size() == 0)
			return null;
		
		BLinkedNode<T> head = new BLinkedNode<T>(elems.get(0));
		BLinkedNode<T> current = head;
		
		for(int i = 1; i < elems.size(); i++) {
			current.next = new BLinkedNode<T>(elems.get(i));
			current.next.prev = current;
			current = current.next;
		}
		
		return head;
	}
	
	public static void main(String[] args) {
		Test.header("BNode");
		
		BLinkedNode<Integer> b1 = new BLinkedNode<Integer>(10);
		Test.equals(b1.elem, Integer.valueOf(10));
		Test.isNull(b1.next);
		b1.next = new BLinkedNode<Integer>(20);
		b1.next.next = new BLinkedNode<Integer>(30);
		BLinkedNode<Integer> temp = b1;
		for(int i = 10; i <= 30; i+=10) {
			Test.equals(temp.elem, Integer.valueOf(i));
			temp = temp.next;
		}
		Test.isNull(temp);
		
		Test.header("createList");
		BLinkedNode<String> list = createList(List.of("one", "two", "three", "four", "five"));
		Test.equals(list.elem, "one");
		Test.equals(list.next.elem, "two");
		Test.equals(list.next.next.elem, "three");
		Test.equals(list.next.next.next.elem, "four");
		Test.equals(list.next.next.next.next.elem, "five");
		Test.equals(list.next.prev.elem, "one");
		Test.equals(list.next.next.prev.elem, "two");
		Test.equals(list.next.next.next.prev.elem, "three");
		Test.equals(list.next.next.next.next.prev.elem, "four");
		Test.equals(list.next.next.next.next.prev.prev.prev.prev.elem, "one");
		Test.isNull(list.next.next.next.next.next);
		
		Test.header("listEquals");
		BLinkedNode<String> listEquals = createList(List.of("one", "two", "three", "four", "five"));
		BLinkedNode<String> listWrongSize1 = createList(List.of("one", "two", "three", "four", "five", "six"));
		BLinkedNode<String> listWrongSize2 = createList(List.of("one", "two", "three", "four"));
		BLinkedNode<String> listDiffContents1 = createList(List.of("one", "two", "WRONG", "four", "three"));
		BLinkedNode<String> listDiffContents2 = createList(List.of("WRONG", "two", "three", "four", "five"));
		BLinkedNode<String> listDiffContents3 = createList(List.of("one", "two", "three", "four", "WRONG"));
		BLinkedNode<Integer> listDiffType = createList(List.of(1, 2, 3, 4, 5));
		Test.assertion(BLinkedNode.listEquals(list, listEquals));
		Test.assertion(!BLinkedNode.listEquals(list, listWrongSize1));
		Test.assertion(!BLinkedNode.listEquals(list, listWrongSize2));
		Test.assertion(!BLinkedNode.listEquals(list, listDiffContents1));
		Test.assertion(!BLinkedNode.listEquals(list, listDiffContents2));
		Test.assertion(!BLinkedNode.listEquals(list, listDiffContents3));
		Test.assertion(!BLinkedNode.listEquals(list, listDiffType));
		Test.assertion(!BLinkedNode.listEquals(list, null));
		Test.assertion(BLinkedNode.listEquals(null, null));
		
		Test.header("listToString");
		Test.equals(BLinkedNode.listToString(null), "[]");
		Test.equals(BLinkedNode.listToString(list), "[(one)-(two)-(three)-(four)-(five)]");
		
		Test.header("length");
		Test.equals(createList(List.of(1, 2, 3, 4, 5)).length(), 5);
		Test.equals(createList(List.of(1, 2)).length(), 2);
		Test.equals(createList(List.of(1)).length(), 1);
		
		Test.results();
	}
}
