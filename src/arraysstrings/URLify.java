package arraysstrings;

import testing.Test;

//Replace all spaces in a string with %20.
//String is given in a char buffer which is guaranteed to be large enough for the end string
public class URLify {
	
	//Performs the operation by tracking two indices in the string
	//First starts at end of string, other at end of character buffer
	//One at a time, copy characters from copyfromptr to copytoptr
	//When a space is copied, instead copy over %20.
	//Time: O(N)	Space: O(1)		 where N = length
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
		Test.header("URLify");
		Test.equals(String.valueOf(urlify("Hello World  ".toCharArray(), 11)), "Hello%20World");
		Test.equals(String.valueOf(urlify("   ".toCharArray(), 1)), "%20");
		Test.equals(String.valueOf(urlify(" WORDS  ".toCharArray(), 6)), "%20WORDS");
		Test.equals(String.valueOf(urlify("WORDS   ".toCharArray(), 6)), "WORDS%20");
		Test.equals(String.valueOf(urlify(" ONE TWO       ".toCharArray(), 9)), "%20ONE%20TWO%20");
		Test.equals(String.valueOf(urlify("NOTHING".toCharArray(), 7)), "NOTHING");
		Test.results();
	}
}
