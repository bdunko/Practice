package recursiondynamic;

import java.util.Stack;

import testing.Test;

public class TowersOfHanoi {

	public static void hanoi(Stack<Integer> tower1, Stack<Integer> tower2, Stack<Integer> tower3) {
		moveDisks(tower1.size(), tower1, tower3, tower2);
	}
	
	private static void moveDisks(int n, Stack<Integer> start, Stack<Integer> goal, Stack<Integer> temp) {
		if(n == 0)
			return;
		moveDisks(n-1, start, temp, goal); 
		goal.push(start.pop());
		moveDisks(n-1, temp, goal, start);
	}
	
	private static void verify(Stack<Integer> tower1, Stack<Integer> tower2, Stack<Integer> tower3, int size) {
		//System.out.printf("verifying...\nTower1: %s\nTower2: %s\nTower3: %s\n", tower1, tower2, tower3);
		Test.assertion(tower1.size() == 0);
		Test.assertion(tower2.size() == 0);
		Test.assertion(tower3.size() == size);

		Integer last = Integer.MIN_VALUE;
		while(tower3.size() != 0) {
			Integer cur = tower3.pop();
			Test.assertion(cur > last);
			last = cur;
		}
	}
	
	public static void main(String[] args) {
		Test.header("Towers of Hanoi");
		
		Stack<Integer> tower1 = new Stack<Integer>();
		Stack<Integer> tower2 = new Stack<Integer>();
		Stack<Integer> tower3 = new Stack<Integer>();
		int nDisks = 25;
		for(int i = nDisks; i > 0; i--)
			tower1.push(i);
		
		hanoi(tower1, tower2, tower3);
		verify(tower1, tower2, tower3, nDisks);
		
		Test.results();
	}
}
