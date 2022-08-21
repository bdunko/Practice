package uncategorized;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import testing.Test;

public class DivingBoard {

	private static Set<Integer> divingBoardLengths(int numBoards, int shorterLength, int longerLength) {
		Set<Integer> lengths = new HashSet<Integer>();
		
		for(int i = 0; i <= numBoards; i++) {
			int length = (shorterLength * i) + (longerLength * (numBoards-i));
			lengths.add(length);
		}
		
		return lengths;
	}
	
	public static void main(String[] args) {
		Test.header("DivingBoard");
		
		int[][] inputs = {
				{1, 3, 6},
				{2, 3, 6},
				{3, 3, 6},
				{4, 3, 6},
				{5, 1, 10},
				{8, 1, 2},
		};
		
		List<Set<Integer>> answers = List.of(
				Set.of(3, 6), Set.of(6, 9, 12), Set.of(9, 12, 15, 18), Set.of(12, 15, 18, 21, 24), Set.of(5, 14, 23, 32, 41, 50), Set.of(8, 9, 10, 11, 12, 13, 14, 15, 16)
		);
		
		for(int i = 0; i < inputs.length; i++) {
			int[] input = inputs[i];
			Set<Integer> expected = answers.get(i);
			Test.equals(divingBoardLengths(input[0], input[1], input[2]), expected);
		}
		
		Test.results();
	}
}
