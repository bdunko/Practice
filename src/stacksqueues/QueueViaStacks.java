package stacksqueues;

import java.util.Stack;

import testing.Test;

//Implement a Queue using two stacks
public class QueueViaStacks<T> {
	
	//queue is a stack where the elements can be popped off in the order of a queue
	//enqueued is a stack of elements which have been enqueued
	//once a dequeue happens, if the queue is empty, the enqueued elements will be pushed onto the stack
	//then the elements will be popped off that stack in reverse order, emulating a queue
	private Stack<T> queue, enqueued;
	
	public QueueViaStacks() {
		queue = new Stack<T>();
		enqueued = new Stack<T>();
	}
	
	//Enqueues an element. O(1)
	public void enqueue(T elem) {
		enqueued.push(elem);
	}
	
	private void shiftStacks() {
		while(!enqueued.isEmpty())
			queue.push(enqueued.pop());
	}
	
	//Dequeues an element. 
	//O(N), but often O(1) depending on usage
	public T dequeue() {
		if(queue.size() == 0)
			shiftStacks();
		
		if(queue.size() == 0)
			return null;
		
		return queue.pop();
	}
	
	public int size() {
		return queue.size() + enqueued.size();
	}
	
	public static void main(String[] args) {
		Test.header("QueueViaStacks");
		
		QueueViaStacks<Integer> qvs = new QueueViaStacks<Integer>();
		Test.isNull(qvs.dequeue());
		qvs.enqueue(1);
		Test.equals(qvs.size(), 1);
		qvs.enqueue(2);
		Test.equals(qvs.size(), 2);
		qvs.enqueue(3);
		Test.equals(qvs.size(), 3);
		qvs.enqueue(4);
		Test.equals(qvs.size(), 4);
		qvs.enqueue(5);
		Test.equals(qvs.size(), 5);
		Test.equals(qvs.dequeue(), 1);
		Test.equals(qvs.dequeue(), 2);
		Test.equals(qvs.dequeue(), 3);
		Test.equals(qvs.dequeue(), 4);
		Test.equals(qvs.dequeue(), 5);
		Test.equals(qvs.size(), 0);
		
		Test.results();
	}
}
