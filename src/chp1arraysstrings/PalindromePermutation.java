package chp1arraysstrings;

import java.util.HashMap;
import java.util.Map;

import testing.Test;

public class PalindromePermutation {
	
	public static boolean palindromePermutation(String s) {
		Map<Character, Integer> frequencyMap = new HashMap<Character, Integer>();
		s = s.replace(" ", "").toLowerCase(); //remove spaces and captalization
		
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
		}
		
		boolean oneOdd = false;
		
		for(Character key : frequencyMap.keySet()) {
			if(frequencyMap.get(key) % 2 != 0) {
				if(oneOdd == true)
					return false;
				else
					oneOdd = true;
			}		
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		Test.assertion(palindromePermutation("Tact Coa"));
		Test.assertion(palindromePermutation("AAAABDDDd CDCDAAbe"));
		Test.assertion(!palindromePermutation("ABCDEF"));
		Test.assertion(!palindromePermutation("ABCAAADD"));
		Test.results();
	}
}
