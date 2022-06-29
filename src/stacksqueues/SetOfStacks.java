package stacksqueues;

import java.util.ArrayList;
import java.util.List;

import testing.Test;

//Implement a set of stacks, where each stack has a limited capacity.
//Once a stack exceeds capacity, a new stack must be created and used for future push operations.
//Pop and push behave as if there were only 1 stack.
//SetOfStacks implements popAt, which allows a user to pop the top element of an internal stack
//Stacks are allowed to be empty after popAt. However, the 'front' overall stack will never be empty.
//Later calls to pop or popAt will clean up any excess empty stacks if there are no elements on stacks above those stacks in the set.
//ie there can never be an empty stack before the top element of the global stack, however there can be empty stacks in other locations.
public class SetOfStacks<T> {

	private class StackNode {
		public T elem;
		public StackNode next;
		public int size;
		
		public StackNode(T elem, StackNode next, int size) {
			this.elem = elem;
			this.next = next;
			this.size = size;
		}
	}
	
	private int capacity;
	private List<StackNode> stacks;
	private int currentStackIndex;
	
	public SetOfStacks(int capacity) {
		this.capacity = capacity;
		this.stacks = new ArrayList<StackNode>();
		this.currentStackIndex = -1;
	}
	
	//Adds an element on top of the global stack.
	//O(1)
	public void push(T elem) {
		if(currentStackIndex == -1 || stacks.get(currentStackIndex).size == capacity) {
			stacks.add(new StackNode(elem, null, 1));
			currentStackIndex++;
		} else {
			StackNode previous = stacks.get(currentStackIndex);
			StackNode newNode = new StackNode(elem, previous, previous.size + 1);
			stacks.set(currentStackIndex, newNode);
		}
	}
	
	//Removes the top element of the global stack
	//O(1)
	public T pop() {
		if(currentStackIndex == -1)
			return null;
		
		StackNode popped = stacks.get(currentStackIndex);
		if(popped == null) {
			stacks.remove(currentStackIndex);
			return null;
		}
		if(popped.next == null) { //last element in stack, must delete stack from list of stacks
			stacks.set(currentStackIndex, null);
			while(currentStackIndex > -1 && stacks.get(currentStackIndex) == null) {
				stacks.remove(currentStackIndex);
				currentStackIndex--;
			}
		}
		else {
			stacks.set(currentStackIndex, popped.next);
		}
		
		return popped.elem;
	}
	
	//Pops an element of an internal stack.
	//It is possible to pop all elements off of an internal stack, which will leave an empty stack.
	//Empty stacks at the front of the global stack are removed automatically when calls to pop or popAt are made.
	//O(1)
	public T popAt(int stack) {
		if(stack > currentStackIndex)
			return null;
		
		if(stack == currentStackIndex)
			return pop();
		
		StackNode popped = stacks.get(stack);
		if(popped == null)
			return null;
		
		stacks.set(stack, popped.next);
		
		return popped.elem;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for(StackNode stack : stacks) {
			StackNode node = stack;
			sb.append("(");
			while(node != null) {
				sb.append(node.elem.toString());
				node = node.next;
				sb.append(",");
			}
			if(sb.charAt(sb.length()-1) == ',')
				sb.deleteCharAt(sb.length()-1);
			sb.append(") ");
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Test.header("SetOfStacks");
		
		SetOfStacks<Integer> sos = new SetOfStacks<Integer>(2);
		Test.isNull(sos.pop());
		sos.push(1);
		sos.push(2);
		sos.push(3);
		sos.push(4);
		sos.push(5);
		sos.push(6);
		sos.push(7);
		sos.push(8);
		Test.equals(sos.toString(), "[ (2,1) (4,3) (6,5) (8,7) ]");
		
		Test.equals(sos.pop(), 8);
		Test.equals(sos.pop(), 7);
		Test.equals(sos.pop(), 6);
		Test.equals(sos.pop(), 5);
		Test.equals(sos.pop(), 4);
		Test.equals(sos.toString(), "[ (2,1) (3) ]");
		
		sos.push(10);
		sos.push(20);
		
		Test.equals(sos.toString(), "[ (2,1) (10,3) (20) ]");
		
		Test.equals(sos.pop(), 20);
		Test.equals(sos.pop(), 10);
		Test.equals(sos.pop(), 3);
		Test.equals(sos.pop(), 2);
		Test.equals(sos.pop(), 1);
		Test.isNull(sos.pop());
		
		sos = new SetOfStacks<Integer>(3);
		sos.push(1);
		sos.push(2);
		sos.push(3);
		sos.push(4);
		sos.push(5);
		sos.push(6);
		sos.push(7);
		sos.push(8);
		sos.push(9);
		sos.push(10);
		Test.equals(sos.toString(), "[ (3,2,1) (6,5,4) (9,8,7) (10) ]");
		Test.equals(sos.popAt(0), 3);
		Test.equals(sos.popAt(0), 2);
		Test.equals(sos.popAt(0), 1);
		Test.isNull(sos.popAt(0));
		Test.equals(sos.popAt(1), 6);
		Test.equals(sos.popAt(1), 5);
		Test.equals(sos.popAt(2), 9);
		Test.equals(sos.toString(), "[ () (4) (8,7) (10) ]");
		Test.equals(sos.pop(), 10);
		Test.equals(sos.toString(), "[ () (4) (8,7) ]");
		Test.isNull(sos.popAt(3));
		Test.equals(sos.pop(), 8);
		Test.equals(sos.popAt(2), 7);
		Test.equals(sos.toString(), "[ () (4) ]");
		Test.equals(sos.pop(), 4);
		Test.equals(sos.toString(), "[ ]");
		sos.push(100);
		Test.equals(sos.toString(), "[ (100) ]");
		
		Test.results();
	}
}
