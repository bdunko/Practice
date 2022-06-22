package chp3stacksqueues;

import testing.Test;

//Implement 3 stacks using a single array
public class ThreeInOne<T> {
	
	private static class StackIndex {
		public int index;
		
		public StackIndex(int start) {
			this.index = start;
		}
	}
	
	private T[] array;
	StackIndex[] stackIndices;
	
	public ThreeInOne(T[] array) {
		this.array = array;
		for(int i = 0; i < array.length; i++) {
			this.array[i] = null;
		}
		this.stackIndices = new StackIndex[3];
		for(int i = 0; i < stackIndices.length; i++) {
			stackIndices[i] = new StackIndex(stackStart(i));
		}
	}
	
	//Returns the first index (inclusive) of the given stack
	private int stackStart(int stack) {
		if(stack > 2 || stack < 0)
			return -1;
		return (int)(array.length / 3.0 * (stack));
	}
	
	//Returns that last index (inclusive) of the given stack
	private int stackEnd(int stack) {
		if(stack > 2 || stack < 0)
			return -1;
		return (int)(array.length / 3.0 * (stack+1))-1;
	}
	
	//Pushes an element onto the given stack. Returns true if successful, false if not (stack is full)
	public boolean push(int stack, T elem) {
		if(stack > 2 || stack < 0)
			return false;
		
		if(stackIndices[stack].index > stackEnd(stack))
			return false;
		
		array[stackIndices[stack].index] = elem;
		stackIndices[stack].index++;
		
		return true;
	}
	
	//Pops the top element of the given stack and returns it. Returns null if stack is empty.
	public T pop(int stack) {
		if(stack > 2 || stack < 0)
			return null;
		
		if(stackIndices[stack].index == stackStart(stack))
			return null;
		
		stackIndices[stack].index--;
		T elem = array[stackIndices[stack].index ];
		array[stackIndices[stack].index ] = null;
		
		return elem;
	}
	
	//Returns the top element of the given stack without removing it. Returns null if stack is empty.
	public T peek(int stack) {
		if(stack > 2 || stack < 0)
			return null;
		
		if(stackIndices[stack].index  == stackStart(stack))
			return null;
		
		return array[stackIndices[stack].index -1];
	}
	
	//Returns whether a stack is empty.
	public boolean isEmpty(int stack) {
		if(stack > 2 || stack < 0)
			return true;
		
		return array[stackStart(stack)] == null;
	}
	
	public static void main(String[] args) {
		ThreeInOne<Integer> threeBalanced = new ThreeInOne<Integer>(new Integer[9]);
		ThreeInOne<Integer> threeImbalanced = new ThreeInOne<Integer>(new Integer[10]);
		ThreeInOne<Integer> threeImbalanced2 = new ThreeInOne<Integer>(new Integer[11]);
		ThreeInOne<Integer> threeBalanced2 = new ThreeInOne<Integer>(new Integer[12]);
		
		Test.header("ThreeInOne");
		Test.header("stackStart/stackEnd");
		Test.equals(threeBalanced.stackStart(0), 0); //stacks: 3 3 3
		Test.equals(threeBalanced.stackStart(1), 3);
		Test.equals(threeBalanced.stackStart(2), 6);
		Test.equals(threeBalanced.stackEnd(0), 2);
		Test.equals(threeBalanced.stackEnd(1), 5);
		Test.equals(threeBalanced.stackEnd(2), 8);
		Test.equals(threeImbalanced.stackStart(0), 0); //stacks: 3 3 4
		Test.equals(threeImbalanced.stackStart(1), 3);
		Test.equals(threeImbalanced.stackStart(2), 6);
		Test.equals(threeImbalanced.stackEnd(0), 2);
		Test.equals(threeImbalanced.stackEnd(1), 5);
		Test.equals(threeImbalanced.stackEnd(2), 9); 
		Test.equals(threeImbalanced2.stackStart(0), 0); //stacks: 3 4 4
		Test.equals(threeImbalanced2.stackStart(1), 3);
		Test.equals(threeImbalanced2.stackStart(2), 7); 
		Test.equals(threeImbalanced2.stackEnd(0), 2);
		Test.equals(threeImbalanced2.stackEnd(1), 6);
		Test.equals(threeImbalanced2.stackEnd(2), 10); 
		Test.equals(threeBalanced2.stackStart(0), 0); //stacks: 4 4 4
		Test.equals(threeBalanced2.stackStart(1), 4);
		Test.equals(threeBalanced2.stackStart(2), 8); 
		Test.equals(threeBalanced2.stackEnd(0), 3);
		Test.equals(threeBalanced2.stackEnd(1), 7);
		Test.equals(threeBalanced2.stackEnd(2), 11); 
		
		Test.header("stackEmpty");
		Test.assertion(threeBalanced.isEmpty(0));
		Test.assertion(threeBalanced.isEmpty(1));
		Test.assertion(threeBalanced.isEmpty(2));
		
		Test.header("push");
		Test.assertion(threeImbalanced.push(0, 1));
		Test.assertion(!threeImbalanced.isEmpty(0));
		Test.assertion(threeImbalanced.push(0, 2));
		Test.assertion(threeImbalanced.push(0, 3));
		Test.assertion(!threeImbalanced.push(0, 4));
		Test.assertion(!threeImbalanced.push(0, 5));
		Test.assertion(threeImbalanced.push(2, 30));
		Test.assertion(!threeImbalanced.isEmpty(2));
		Test.assertion(threeImbalanced.push(2, 31));
		Test.assertion(threeImbalanced.push(2, 32));
		Test.assertion(threeImbalanced.push(2, 33));
		Test.assertion(!threeImbalanced.push(2, 34));
		
		Test.header("pop");
		Test.isNull(threeImbalanced.pop(1));
		Test.equals(threeImbalanced.pop(0), 3);
		Test.equals(threeImbalanced.pop(2), 33);
		Test.equals(threeImbalanced.pop(0), 2);
		Test.equals(threeImbalanced.pop(2), 32);
		Test.equals(threeImbalanced.pop(0), 1);
		Test.equals(threeImbalanced.pop(2), 31);
		Test.isNull(threeImbalanced.pop(0));
		
		Test.header("peek");
		Test.isNull(threeImbalanced.peek(1));
		Test.equals(threeImbalanced.peek(2), 30);
		Test.equals(threeImbalanced.pop(2), 30);
		Test.isNull(threeImbalanced.pop(2));
		Test.isNull(threeImbalanced.peek(2));
		Test.assertion(threeImbalanced.isEmpty(2));
		
		Test.results();
	}
}
