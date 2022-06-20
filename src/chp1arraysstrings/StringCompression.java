package chp1arraysstrings;

import testing.Test;

public class StringCompression {
	
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
		Test.equals(compress("aabcccccaaa"), "a2b1c5a3");
		Test.equals(compress("abbbbbcddddddaaaa"), "a1b5c1d6a4");
		Test.equals(compress("abc"), "abc");
		Test.equals(compress("abcc"), "abcc");
		Test.equals(compress("abbcccc"), "a1b2c4");
	}
}
