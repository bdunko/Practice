package stacksqueues;

import java.util.Random;
import java.util.Stack;

import testing.Test;

//Sort a given stack. You can use an additional stack as temporary storage, but no other data structure.
public class SortStack {
	
	//inserts the given element into the 
	private static <T extends Comparable<T>> void insertSorted(Stack <T> sorted, T elem) {
		if(sorted.size() == 0 || sorted.peek().compareTo(elem) < 0) {
			sorted.push(elem);
			return;
		}
		T temp = sorted.pop();
		insertSorted(sorted, elem);
		sorted.push(temp);
		return;
	}
	
	public static <T extends Comparable<T>> void sortStack(Stack<T> stack) {
		Stack<T> sortedMinToMax = new Stack<T>();
		while(stack.size() != 0)
			insertSorted(sortedMinToMax, stack.pop());
		while(sortedMinToMax.size() != 0)
			stack.push(sortedMinToMax.pop());
	}
	
	public static void main(String[] args) {
		Test.header("sortStack");
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(5);
		stack.push(7);
		stack.push(2);
		stack.push(3);
		stack.push(1);
		Test.equals(stack.toString(), "[5, 7, 2, 3, 1]");
		sortStack(stack);
		Test.equals(stack.toString(), "[7, 5, 3, 2, 1]");
		Test.equals(stack.pop(), 1);
		Test.equals(stack.pop(), 2);
		Test.equals(stack.pop(), 3);
		Test.equals(stack.pop(), 5);
		Test.equals(stack.pop(), 7);
		
		Random r = new Random();
		for(int i = 0; i < 100; i++) {
			stack.push(Math.abs(r.nextInt(10000)));
		}
		sortStack(stack);
		int prev = Integer.MIN_VALUE;
		while(!stack.isEmpty()) {
			Test.assertion(prev <= stack.peek());
			prev = stack.pop();
		}
		Test.results();
	}
	
}
