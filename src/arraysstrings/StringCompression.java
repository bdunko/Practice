package arraysstrings;

import testing.Test;

//Compresses a string such that, ex: aabcccccaaa -> a2b1c5a3
//If the compressed string is the same length or shorter than the original, return the original.
public class StringCompression {
	
	//Performs the string compression using a StringBuilder
	//Time: O(N)	Space: O(N)		Where N is the length of the string.
	public static String compress(String s) {
		if(s.length() == 0)
			return s;
		
		StringBuilder sb = new StringBuilder();
		
		char previous = s.charAt(0);
		int run = 1;
		
		for(int i = 1; i < s.length(); i++) {
			char current = s.charAt(i);
			if(current == previous) {
				run++;
			}
			else {
				sb.append(previous).append(run);
				previous = current;
				run = 1;
			}
		}
		
		if(run != 0)
			sb.append(previous).append(run);
		
		if(sb.length() < s.length())
			return sb.toString();
		
		return s;
	}
	
	public static void main(String[] args) {
		Test.header("stringCompression");
		Test.equals(compress("aabcccccaaa"), "a2b1c5a3");
		Test.equals(compress("abbbbbcddddddaaaa"), "a1b5c1d6a4");
		Test.equals(compress("abc"), "abc");
		Test.equals(compress("abcc"), "abcc");
		Test.equals(compress("abbcccc"), "a1b2c4");
		Test.results();
	}
}
