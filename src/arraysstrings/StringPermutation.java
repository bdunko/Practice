package arraysstrings;

import java.util.HashMap;

import testing.Test;

//Determines whether a string is a permutation of another string
public class StringPermutation {
	
	//Adds all characters in string A to a hashmap, and string B to a different hashmap.
	//Then ensures that the hashmaps are the same size, and that each character shows up the
	//same number of times in each hashmap.
	//Time: O(N)	Space: O(N)		Where N is the length of the strings
	public static boolean isPermutation(String a, String b) {
		if(a == null || b == null || a.length() != b.length())
			return false;
		
		HashMap<Character, Integer> mapA = new HashMap<Character, Integer>();
		HashMap<Character, Integer> mapB = new HashMap<Character, Integer>();
		
		for(int i = 0; i < a.length(); i++) {
			Character c = a.charAt(i);
			mapA.put(c, mapA.getOrDefault(c, 0)+1);
		}
		
		for(int i = 0; i < b.length(); i++) {
			Character c = b.charAt(i);
			mapB.put(c, mapB.getOrDefault(c, 0)+1);
		}
		
		if(mapA.size() != mapB.size())
			return false;
		
		for(Character c : mapA.keySet()) {
			if(!mapA.get(c).equals(mapB.get(c)))
				return false;
		}
		
		return true;
	}

	public static void main(String[] args) {
		Test.header("Permutation");
		Test.assertion(isPermutation("ABCDE", "BCDAE"));
		Test.assertion(!isPermutation("ABCDE", "BCDAEZ"));
		Test.assertion(!isPermutation("ABCDE", "BCDAA"));
		Test.assertion(!isPermutation("ABCDE", "QERTY"));
		Test.assertion(isPermutation("AAABEBCDE", "BABAAEECD"));
		Test.results();
	}
}
