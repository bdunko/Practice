package arraysstrings;

import java.util.HashSet;
import java.util.Set;

import testing.Test;

//Determine if each character in a string is unique (ie 'are there duplicate characters in the string?')
public class IsUnique {

	//If we can use data structures, we iterate over the string and put each character into a hash set which is O(1) lookup
	//Before adding a character to the set, check if it is already in the set. If it is, the string is not unique.
	//Time: O(N)	Space: O(N)		Where N is the string's length
	public static boolean isUnique(String s) {
		Set<Character> uniqueSet = new HashSet<Character>();
		
		for(int i = 0; i < s.length(); i++) {
			Character c = s.charAt(i);
			if(uniqueSet.contains(c))
				return false;
			uniqueSet.add(c);
		}
		
		return true;
	}
	
	//If we cannot use data structures (memory constraints), we can do this in another way.
	//Iterate over each character in the string. For each, iterate over the remaining characters
	//and ensure that none of them match the current character.
	//Time: O(N^2)	Space: O(1)		Where N is the string's length
	public static boolean isUniqueNoDS(String s) {
		for(int i = 0; i < s.length(); i++) {
			for(int j = i+1; j < s.length(); j++) {
				if(s.charAt(i) == s.charAt(j))
					return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Test.header("isUnique (with set)");
		Test.assertion(isUnique("ABCDE"));
		Test.assertion(!isUnique("ABCDEFDZ"));
		Test.assertion(!isUnique("ABCDEFGHIJKLA"));
		Test.assertion(!isUnique("ABCDABCD"));
		Test.assertion(!isUnique("AA"));
		Test.assertion(isUnique(""));
		Test.assertion(isUnique("A"));
		Test.assertion(!isUnique("BB"));
		Test.header("isUnique (no data structures)");
		Test.assertion(isUniqueNoDS("ABCDE"));
		Test.assertion(!isUniqueNoDS("ABCDEFDZ"));
		Test.assertion(!isUniqueNoDS("ABCDEFGHIJKLA"));
		Test.assertion(!isUniqueNoDS("ABCDABCD"));
		Test.assertion(!isUniqueNoDS("AA"));
		Test.assertion(isUniqueNoDS(""));
		Test.assertion(isUniqueNoDS("A"));
		Test.assertion(!isUniqueNoDS("BB"));
		Test.results();
	}
}
