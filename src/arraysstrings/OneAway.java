package arraysstrings;

import testing.Test;

//Determine whether a string is 1 or 0 edits away from another
//Where there are 3 types of edits - delete a character, add a character, and change a character
public class OneAway {
	
	//Determines whether a is one-away from b
	//Do this by tracking two indices in the strings
	//Walk the strings 1 character at a time, if a difference is found
	// * If the length is the same, must be an edit. Flip a boolean flag saying we found 1 difference.
	// * If the length is not the same, this must be a deletion/insertion (which are the same operation, really). 
	//   Increment the index of the longer string and flip the boolean saying we found 1 difference.
	//Then if a second difference is ever found, return false.
	//Otherwise at the end return true.
	//Time: O(N)	Space: O(1)		Where N is length of the shorter string
	public static boolean oneAway(String a, String b) {
		String longer = a.length() > b.length() ? a : b;
		String shorter = a.length() > b.length() ? b : a;
		
		if(longer.length() - shorter.length() > 1)
			return false;
		
		int longPtr = 0;
		int shortPtr = 0;
		boolean oneAway = false;
		
		while(shortPtr != shorter.length()) {
			if(longer.charAt(longPtr) != shorter.charAt(shortPtr)) {
				if(oneAway)
					return false;
				oneAway = true;
				
				if(longer.length() == shorter.length()) {
					longPtr++;
					shortPtr++;
				} else {
					longPtr++;
				}
			} else {
				longPtr++;
				shortPtr++;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		Test.header("oneAway");
		Test.assertion(oneAway("pale", "ple"));
		Test.assertion(!oneAway("pale", "pe"));
		Test.assertion(oneAway("pale", "pales"));
		Test.assertion(oneAway("pake", "pale"));
		Test.assertion(!oneAway("pale", "bake"));
		Test.results();
	}
}
