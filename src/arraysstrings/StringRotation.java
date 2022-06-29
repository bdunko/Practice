package arraysstrings;

import testing.Test;

//Determines whether a string is rotation of another, using only 1 call to isSubstring
public class StringRotation {
	
	//Returns if s1 contains s2
	//Time: O(N)	Space: O(1)		Where N is the length of the longer string (s1)
	public static boolean isSubstring(String s1, String s2) {
		if(s1.length() < s2.length() || s2.length() == 0)
			return false;
		
		int s1index = 0;
		int s2index = 0;
		
		while(s1index != s1.length()) {
			if(s1.charAt(s1index) == s2.charAt(s2index)) {
				s2index++;
				if(s2index == s2.length())
					return true;
			} else {
				s2index = 0;
			}
			s1index++;
		}
		
		return false;
	}
	
	//Determines whether s1 is a rotation of s2 using 1 call to isSubstring
	//Trick - call isSubstring with s1+s1 as first parameters.
	//All possible rotations of s1 exist as a substring of s1+s1.
	//Time: O(N)	Space: O(1)		Where N is length of s1 * 2.
	public static boolean isRotation(String s1, String s2) {
		if(s1.length() != s2.length())
			return false;

		return isSubstring(s1+s1, s2);
	}
	
	public static void main(String[] args) {
		Test.header("isSubstring");
		Test.assertion(isSubstring("ABCDE", "CD"));
		Test.assertion(!isSubstring("ABCDE", "BE"));
		Test.assertion(isSubstring("ABCDE", "A"));
		Test.assertion(isSubstring("ABCDE", "ABCDE"));
		Test.assertion(isSubstring("ABCDE", "AB"));
		Test.assertion(isSubstring("ABCDE", "E"));
		Test.assertion(isSubstring("ABCDE", "DE"));
		Test.assertion(!isSubstring("ABCDE", "F"));
		Test.assertion(!isSubstring("ABCDE", "ACDE"));
		Test.assertion(isSubstring("ABCDE", "ABCD"));
		Test.assertion(isSubstring("ABCDE", "BCDE"));
		Test.assertion(isSubstring("ABCDEFGHI", "EFGH"));
		Test.assertion(!isSubstring("ABCDE", "ABCDEE"));
		Test.assertion(!isSubstring("A", "ABCDE"));
		Test.assertion(!isSubstring("ABCDE", ""));
		Test.assertion(!isSubstring("", ""));
		Test.header("isRotation");
		Test.assertion(isRotation("waterbottle", "ottlewaterb"));
		Test.assertion(isRotation("waterbottle", "waterbottle"));
		Test.assertion(isRotation("waterbottle", "aterbottlew"));
		Test.assertion(isRotation("waterbottle", "ewaterbottl"));
		Test.assertion(isRotation("waterbottle", "bottlewater"));
		Test.assertion(!isRotation("waterbottle", "ottlewater"));
		Test.assertion(!isRotation("waterbottle", "aterbottle"));
		Test.assertion(!isRotation("waterbottle", "waterbottlewaterbottle"));
		Test.assertion(!isRotation("waterbottle", "a"));
		Test.assertion(!isRotation("waterbottle", ""));
		Test.assertion(!isRotation("waterbottle", "wterbottlea"));
		Test.assertion(!isRotation("waterbottle", "ottlewaterbb"));
		Test.results();
	}
}
