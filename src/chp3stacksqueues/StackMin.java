package chp3stacksqueues;

import testing.Test;

public class StackMin<T extends Comparable<T>> {
	
	private class StackNode {
		public T elem;
		public StackNode next;
		public T min;
		
		public StackNode(T elem, StackNode next, T min) {
			this.elem = elem;
			this.next = next;
			this.min = min;
		}
	}
	
	private StackNode head;
	
	public StackMin() {
		head = null;
	}
	
	public void push(T elem) {
		if(head == null) {
			head = new StackNode(elem, null, elem);
		} else {
			head = new StackNode(elem, head, elem.compareTo(head.elem) < 0 ? elem : head.min);
		}
	}
	
	public T pop() {
		if(head == null)
			return null;
		
		T elem = head.elem;
		head = head.next;
		
		return elem;
	}
	
	public T peek() {
		if(head == null)
			return null;
		
		return head.elem;
	}
	
	public T min() {
		if(head == null)
			return null;
		
		return head.min;
	}
	
	public static void main(String[] args) {
		Test.header("StackMin");
		StackMin<Integer> smin = new StackMin<Integer>();
		Test.isNull(smin.peek());
		Test.isNull(smin.min());
		Test.isNull(smin.pop());
		smin.push(10);
		smin.push(5);
		smin.push(7);
		smin.push(12);
		smin.push(1);
		smin.push(11);
		
		//stack: 11 1 12 7 5 10
		Test.equals(smin.peek(), 11);
		Test.equals(smin.min(), 1);
		Test.equals(smin.pop(), 11);
		
		//stack: 1 12 7 5 10
		Test.equals(smin.peek(), 1);
		Test.equals(smin.min(), 1);
		Test.equals(smin.pop(), 1);
		
		//stack: 12 7 5 10
		Test.equals(smin.peek(), 12);
		Test.equals(smin.min(), 5);
		Test.equals(smin.pop(), 12);
		
		//stack: 7 5 10
		Test.equals(smin.peek(), 7);
		Test.equals(smin.min(), 5);
		Test.equals(smin.pop(), 7);
		
		//stack: 5 10
		Test.equals(smin.peek(), 5);
		Test.equals(smin.min(), 5);
		Test.equals(smin.pop(), 5);
		
		//stack: 10
		Test.equals(smin.peek(), 10);
		Test.equals(smin.min(), 10);
		Test.equals(smin.pop(), 10);
		
		//stack: 
		Test.isNull(smin.peek());
		Test.isNull(smin.min());
		Test.isNull(smin.pop());
		
		Test.results();
	}
}
