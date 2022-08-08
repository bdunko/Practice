package sortsearch;

import java.util.Arrays;

import testing.Test;

//Sort an array of strings such that all anagrams are next to one another
public class GroupAnagrams {

	private static class StringPair implements Comparable<StringPair> {
		String sorted;
		String original;
		
		public StringPair(String original) {
			this.original = original;
			this.sorted = sort(original);
		}
		
		public int compareTo(StringPair other) {
			return this.sorted.compareTo(other.sorted);
		}
	}
	
	private static void groupAnagrams(String[] strings) {
		//create stringpairs
		StringPair[] stringPairs = new StringPair[strings.length];
		for(int i = 0; i < strings.length; i++) {
			stringPairs[i] = new StringPair(strings[i]);
		}
		
		//sort by 'sorted' strings
		Arrays.sort(stringPairs);
		
		//place originals back in starting array
		for(int i = 0; i < strings.length; i++) {
			strings[i] = stringPairs[i].original;
		}
	}
	
	private static String sort(String unsorted) {
		char[] c = unsorted.toCharArray();
		Arrays.sort(c);
		return new String(c);
	}
	
	private static boolean isAnagram(String s1, String s2) {
		return sort(s1).equals(sort(s2));
	}
	
	private static void verify(String[] sorted) {
		//Test.log(sorted);
		
		for(int i = 0; i < sorted.length; i++) {
			String current = sorted[i];
			boolean endOfAnagrams = false;
			
			for(int j = i+1; j < sorted.length; j++) {
				if(isAnagram(current, sorted[j])) {
					if(endOfAnagrams) {
						Test.fail("Sorted list contained an string not next to an anagram of itself");
						return;
					}
				} else {
					endOfAnagrams = true;
				}
			}
		}
		
		Test.success("Sorted list is sorted by anagrams");
	}
	
	public static void main(String[] args) {
		Test.header("Group Anagrams");
		
		String[][] tests = {
				{"ABCD", "EFGH", "IJKL", "MNOP"},
				{"ABCD", "EFGH", "IJKL", "MNOP", "ABCD", "IJKL"},
				{"ABCD", "EFGH", "IJKL", "MNOP", "DCBA", "GHFE", "CBAD"},
				{"ABCD", "EFGH", "IJKL", "MNOP", "ACBD", "FGHE", "JKLI", "PONM", "CDBA"},
				{"ABCD", "EFGH", "IJKL", "MNOP", "AABC", "DDAB", "FGHE", "FFFG", "BCAA", "IJKL"},
				{"CAKE", "ACT", "ARC", "HAT", "BAT", "ACKE", "CAT", "BELOW", "VASE", "GRAB", "SAVE", "BRAG", "CAR", "ELBOW", "BOWEL"}
		};
		
		for(String[] test : tests) {
			groupAnagrams(test);
			verify(test);
		}
		
		Test.results();
	}
}
