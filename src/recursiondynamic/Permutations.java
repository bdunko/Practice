package recursiondynamic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import testing.Test;

public class Permutations {

	//permutation without duplicates
	public static List<String> permute(String s) {
		List<String> permutations = new LinkedList<String>();
		
		permuteHelper(s, "", permutations);
		
		return permutations;
	}
	
	public static void permuteHelper(String s, String prefix, List<String> permutations) {
		if(s.length() == 0)
			permutations.add(prefix);
		else {
			for(int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				String nString = s.replace(""+c, "");
				String nPrefix = prefix + c;
				permuteHelper(nString, nPrefix, permutations);
			}
		}
	}
	
	//permutation with duplicates
	public static List<String> permuteDup(String s) {
		List<String> permutations = new LinkedList<String>();
		
		//create frequency map
		Map<Character, Integer> freqMap = new HashMap<Character, Integer>();
		for(int i = 0; i < s.length(); i++) {
			freqMap.put(s.charAt(i), freqMap.getOrDefault(s.charAt(i), 0)+1);
		}
		
		permuteDupHelper(freqMap, "", s.length(), permutations);
		
		return permutations;
	}
	
	public static void permuteDupHelper(Map<Character, Integer> freqMap, String prefix, int remaining, List<String> permutations) {
		if(remaining == 0)
			permutations.add(prefix);
		else {
			for(Character c : freqMap.keySet()) {
				int count = freqMap.get(c);
				if(count > 0) {
					freqMap.put(c, count-1);
					permuteDupHelper(freqMap, prefix+c, remaining-1, permutations);
					freqMap.put(c, count);
				}
			}
		}
	}
	
	private static void verify(List<String> permutations, int expected) {
		Test.assertion(permutations.size() == expected);
		
		for(int i = 0; i < permutations.size(); i++) {
			for(int j = i+1; j < permutations.size(); j++) {
				String p1 = permutations.get(i);
				String p2 = permutations.get(j);
				if(p1.equals(p2)) {
					Test.assertion(false);
					return;
				}
			}
		}
	}
	
	private static int factorial(int n) {
		if(n == 1)
			return n;
		return n * factorial(n-1);
	}
	
	public static void main(String[] args) {
		Test.header("Permutations (no dups)");
		
		verify(permute("A"), 1);
		verify(permute("AB"), factorial(2));
		verify(permute("ABC"), factorial(3));
		verify(permute("ABCD"), factorial(4));
		verify(permute("ABCDE"), factorial(5));
		verify(permute("ABCDEF"), factorial(6));
		
		Test.header("Permutation (with dups)");
		verify(permuteDup("A"), 1);
		verify(permuteDup("AA"), 1);
		verify(permuteDup("AAB"), factorial(3)/factorial(2));
		verify(permuteDup("AAAB"), factorial(4)/factorial(3));
		verify(permuteDup("AABBC"), factorial(5)/(factorial(2)*factorial(2)));
		verify(permuteDup("AAABBC"), factorial(6)/(factorial(2)*factorial(3)));
		
		Test.results();
	}
}
