package chp3stacksqueues;

import java.util.Stack;

import testing.Test;

//Implement a Queue using two stacks
public class QueueViaStacks<T> {
	
	//popping elements from stack 1 will return them in the order of a queue
	//stack 2 is purely a temporary storage for enqueue
	private Stack<T> stack1, stack2;
	
	public QueueViaStacks() {
		stack1 = new Stack<T>();
		stack2 = new Stack<T>();
	}
	
	//Enqueues an element. 
	//Moves all elements from stack 1 to stack 2, reversing order (back into a stack). 
	//Then adds new element.
	//Lastly, moves all elements from stack 2 to stack 1, reversing order into a queue.
	//O(N) where N is number of elements in queue
	public void enqueue(T elem) {
		//move everything from stack 1 to stack 2		(A B C)	()
		while(stack1.size() != 0) 
			stack2.push(stack1.pop());
		
		//add new elem onto stack 2						() (C B A)
		stack2.push(elem);						//		() (D C B A)
		
		//move everything from stack 2 back to stack 1	(A B C D) ()
		while(stack2.size() != 0)
			stack1.push(stack2.pop());	
	}
	
	public T dequeue() {
		if(stack1.size() == 0)
			return null;
		
		return stack1.pop();
	}
	
	public int size() {
		return stack1.size();
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
