package uncategorized;

import java.util.HashMap;
import java.util.Random;

import testing.Test;

//Given rand5, make rand7
public class Rand7FromRand5 {

	private static int rand5() {
		return Math.abs(new Random().nextInt() % 5);
	}
	
	private static int rand7() {
		while(true) {
			//equal probability of generating 0-24
			int r = (5 * rand5()) + rand5();
			
			//0, 7, 14 = 0
			//1, 8, 15 = 1
			//2, 9, 16 = 2
			//3, 10, 17 = 3
			//4, 11, 18 = 4
			//5, 12, 19 = 5
			//6, 13, 20 = 6
			if(r < 21)
				return r % 7;
		}
	}
	
	public static void main(String[] args) {
		Test.header("Rand7FromRand5");
		
		//do rand7 70,000 times and make sure each number shows up 9,500 times at least
		HashMap<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < 70000; i++) {
			int gen = rand7();
			
			if(gen < 0 || gen > 6) {
				Test.fail("Generated a number outside of 0-6! " + gen);
			}
			else {
				frequencyMap.put(gen, frequencyMap.getOrDefault(gen, 0)+1);
			}
		}
		
		for(int i = 0; i < 7; i++) {
			if(!frequencyMap.containsKey(i))
				Test.fail("Number " + i + " not generated at all!");
			else
				Test.assertion(frequencyMap.get(i) > 9500 && frequencyMap.get(i) < 10500);
		}
		
		Test.results();
	}
	
}
