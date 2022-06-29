package arraysstrings;

import java.util.HashMap;
import java.util.Map;

import testing.Test;

//Determine whether a string is a permutation of a palindrome
public class PalindromePermutation {
	
	//Determines if a string is a permutation of a palindrome by storing the frequency
	//of each character in a hashmap.
	//If all but one characters in the hashmap show up an even number of times,
	//then the string is a permutation of a palindrome.
	//Time: O(N)	Space: O(N)		Where N is the length of the string
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
		Test.header("palindromePermutation");
		Test.assertion(palindromePermutation("Tact Coa"));
		Test.assertion(palindromePermutation("AAAABDDDd CDCDAAbe"));
		Test.assertion(!palindromePermutation("ABCDEF"));
		Test.assertion(!palindromePermutation("ABCAAADD"));
		Test.results();
	}
}
