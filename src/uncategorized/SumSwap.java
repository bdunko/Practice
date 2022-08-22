package uncategorized;

import java.util.HashMap;

import testing.Test;
import utility.Util.Pair;

//Given two arrays, find a pair of values you can swap from each array to give them the same sum
public class SumSwap {
	
	public static Pair sumSwap(int[] array1, int[] array2) {
		//calculate sum of arrays
		int array1Sum = 0;
		for(int i = 0; i < array1.length; i++) {
			array1Sum += array1[i];
		}
		
		int array2Sum = 0;
		for(int i = 0; i < array2.length; i++) {
			array2Sum += array2[i];
		}
		
		//determine the difference
		int difference = Math.abs(array1Sum - array2Sum);
		
		System.out.println(difference);
		
		//create map of possible pairings
		HashMap<Integer, Integer> swaps = new HashMap<Integer, Integer>();
		for(int n : array1) {
			//put matching pair as key with n as value
			int matching = difference - n;
			swaps.put(matching, n);
			
			System.out.println("Pair: " + new Pair(n, matching));
		}
		
		//see if any elements in second array make a pair
		for(int m : array2) {
			if(swaps.containsKey(m))
				return new Pair(swaps.get(m), m);
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Test.header("SumSwap");
		
		int[][][] inputs = {
				{
					{4, 1, 2, 1, 1, 2}, //11
					{3, 6, 3, 3}        //15
				},
				{
					{3, 6, 3, 3},       //15
					{4, 1, 2, 1, 1, 2}  //11
				},
				{
					{4, 1, 10, 9, 2}, 
					{20, 5, 2},
				}, 
				{
					{4, 1, 4, 9, 2}, 
					{20, 5, 2}   
				}
		};
		
		Pair[] answers = {
				new Pair(1, 3),
				new Pair(3, 1),
				null,
				new Pair(2, 5),
		};
		
		for(int i = 0; i < inputs.length; i++) {
			int[][] input = inputs[i];
			Pair expected = answers[i];
			
			Test.equals(sumSwap(input[0], input[1]), expected);
		}
		
		Test.results();
	}
	
}
