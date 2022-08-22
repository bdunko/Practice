package uncategorized;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import testing.Test;
import utility.Util.Pair;

public class PairsWithSum {

	public static List<Pair> pairsWithSum(int[] array1, int [] array2, int sum) {
		List<Pair> pairs = new LinkedList<Pair>();
		
		HashMap<Integer, Integer> pairMap = new HashMap<Integer, Integer>();
		
		for(int n : array1) {
			//n + m = s
			//so m = s - n
			int m = sum - n;
			pairMap.put(m, n);
			
			System.out.println(n + " + " + m + " = " + sum);
		}
		
		//check if any answers exist in a2
		for(int m : array2) {
			if(pairMap.containsKey(m))
				pairs.add(new Pair(pairMap.get(m), m));
		}
		
		return pairs;
	}
	
	public static void main(String[] args) {
		Test.header("PairsWithSum");
		
		int[][][] inputs = {
				{
					{1, 5, 8, 10},
					{5, 10, 7, 15},
					{15}
				},
				{
					{1, 2, 3, 4, 5},
					{1, 2, 3, 4, 5},
					{6}
				}
		};
		
		List<List<Pair>> answers = new ArrayList<List<Pair>>();
		List<Pair> ans1 = new LinkedList<Pair>();
		ans1.add(new Pair(5, 10));
		ans1.add(new Pair(8, 7));
		ans1.add(new Pair(10, 5));
		List<Pair> ans2 = new LinkedList<Pair>();
		ans2.add(new Pair(1, 5));
		ans2.add(new Pair(2, 4));
		ans2.add(new Pair(3, 3));
		ans2.add(new Pair(4, 2));
		ans2.add(new Pair(5, 1));
		answers.add(ans1);
		answers.add(ans2);
		
		for(int i = 0; i < inputs.length; i++) {
			int[] a1 = inputs[i][0];
			int[] a2 = inputs[i][1];
			int sum = inputs[i][2][0];
			List<Pair> expected = answers.get(i);
			
			List<Pair> answer = pairsWithSum(a1, a2, sum);
			
			for(Pair pair : answer) {
				if(expected.remove(pair))
					Test.success("Pair " + pair + " was correct.");
				else
					Test.fail("Pair " + pair + " is not an valid answer.");
			}
			
			for(Pair remaining : expected) {
				Test.fail("Pair " + remaining + " is an answer, but not found.");
			}
		}
		
		Test.results();
	}
	
}
