package uncategorized;

import java.util.HashMap;
import java.util.Map;

import testing.Test;

//In the game of MasterMind, the user is trying to guess a hidden sequence of 4 balls that can be one of 4 colors (RGBY)
//The user will input a guess.
//The program must return the number of correct colors in the correct slots (hits), and correct colors in incorrect slots (pseudo-hits)
public class MasterMind {
	
	private static int guess(String solution, String guess) {
		if(solution.length() != guess.length())
			return -1;
		
		Map<Character, Integer> solutionMap = new HashMap<Character, Integer>();
		Map<Character, Integer> guessMap = new HashMap<Character, Integer>();
		
		//check hits, and for each incorrect, store in map
		int hits = 0;
		for(int i = 0; i < solution.length(); i++) {
			char solutionChar = solution.charAt(i);
			char guessChar = guess.charAt(i);
			
			if(solution.charAt(i) == guess.charAt(i)) {
				hits++;
			} else {
				solutionMap.put(solutionChar, solutionMap.getOrDefault(solutionChar, 0)+1);
				guessMap.put(guessChar, guessMap.getOrDefault(guessChar, 0)+1);
			}
		}
		
		//calculate pseudo hits by comparing solution to guessed
		int pseudohits = 0;
		
		for(Character c : solutionMap.keySet()) {
			pseudohits += guessMap.getOrDefault(c, 0);
		}
		
		return hits * 10 + pseudohits;
	}
	
	public static void main(String[] args) {
		Test.header("MasterMind");
		
		String[][] inputs = {
				{"RRRR", "BBBB"},
				{"RRRR", "RRRR"},
				{"RRRR", "RRBB"},
				{"RBBR", "BRRB"},
				{"RRBB", "YRYR"},
				{"RGBY", "RGYB"}, 
				{"RGBY", "RGBY"}, 
				{"GRBR", "GRRR"}, 
		};
		
		int[] solutions = {
				0, 40, 20, 4, 11, 22, 40, 30
		};
		
		for(int i = 0; i < inputs.length; i++) {
			String[] input = inputs[i];
			int expected = solutions[i];
			Test.equals(guess(input[0], input[1]), expected);
		}
		
		Test.results();
	}
}
