package chp1arraysstrings;

import testing.Test;

public class OneAway {
	
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
		Test.assertion(oneAway("pale", "ple"));
		Test.assertion(!oneAway("pale", "pe"));
		Test.assertion(oneAway("pale", "pales"));
		Test.assertion(oneAway("pake", "pale"));
		Test.assertion(!oneAway("pale", "bake"));
		Test.results();
	}
}
