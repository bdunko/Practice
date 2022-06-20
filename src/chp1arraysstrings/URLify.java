package chp1arraysstrings;

import testing.Test;

public class URLify {
	
	//O(N) where N = length
	public static char[] urlify(char[] url, int length) {
		int copytoptr = url.length - 1;
		int copyfromptr = length - 1;
		
		while(copyfromptr >= 0) {
			char c = url[copyfromptr];
			if(c == ' ') {
				url[copytoptr-2] = '%';
				url[copytoptr-1] = '2';
				url[copytoptr] = '0';
				copytoptr -= 3;
			} else {
				url[copytoptr] = url[copyfromptr];
				copytoptr -= 1;
			}
			copyfromptr--;
		}
		
		return url;
	}
	
	public static void main(String[] args) {
		Test.equals(String.valueOf(urlify("Hello World  ".toCharArray(), 11)), "Hello%20World");
		Test.equals(String.valueOf(urlify("   ".toCharArray(), 1)), "%20");
		Test.equals(String.valueOf(urlify(" WORDS  ".toCharArray(), 6)), "%20WORDS");
		Test.equals(String.valueOf(urlify("WORDS   ".toCharArray(), 6)), "WORDS%20");
		Test.equals(String.valueOf(urlify(" ONE TWO       ".toCharArray(), 9)), "%20ONE%20TWO%20");
		Test.equals(String.valueOf(urlify("NOTHING".toCharArray(), 7)), "NOTHING");
		Test.results();
	}
}
