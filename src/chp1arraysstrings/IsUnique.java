package chp1arraysstrings;

import java.util.HashSet;
import java.util.Set;

import testing.Test;

public class IsUnique {

	//O(N)
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
	
	//O(N^2)
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
		Test.header("with set");
		Test.assertion(isUnique("ABCDE"));
		Test.assertion(!isUnique("ABCDEFDZ"));
		Test.assertion(!isUnique("ABCDEFGHIJKLA"));
		Test.assertion(!isUnique("ABCDABCD"));
		Test.assertion(!isUnique("AA"));
		Test.assertion(isUnique(""));
		Test.assertion(isUnique("A"));
		Test.assertion(!isUnique("BB"));
		
		Test.results();
		
		Test.reset();
		Test.header("no data structures");
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
