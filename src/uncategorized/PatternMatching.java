package uncategorized;

import testing.Test;

//given an input string and a pattern of a's and b's, determine if the input matches the pattern
public class PatternMatching {

	private static boolean patternMatch(String value, String pattern) {
		if(value.length() < pattern.length())
			return false;
		
		//count number of as and bs
		char primaryPatternChar = pattern.charAt(0);
		int numPrimary = 0;
		int numSecondary = 0;
		
		for(int i = 0; i < pattern.length(); i++) {
			if(pattern.charAt(i) == primaryPatternChar) {
				numPrimary++;
			}
			else {
				numSecondary++;
			}
		}

		int firstSecondaryOffset = pattern.indexOf(primaryPatternChar == 'a' ? 'b' : 'a');
		
		for(int i = 1; i <= value.length(); i++) {
			
			//construct primary
			String primary = value.substring(0, i);
			
			//construct secondary if there are secondaries
			String secondary = "";
			
			if(numSecondary != 0) {
				int primaryLength = primary.length();
				int secondaryLength = (value.length() - (primaryLength * numPrimary)) / numSecondary; 
				
				//if there are secondaries but not enough characters left, must not be possible
				if(secondaryLength == 0)
					return false;
				
				//build a secondary
				int secondaryStart = firstSecondaryOffset * primaryLength;
				int secondaryEnd = secondaryStart + secondaryLength;
				
				secondary = value.substring(secondaryStart, secondaryEnd);
			}
			
			//build full string using pattern, primary, and secondary and check if it matches input
			StringBuilder built = new StringBuilder();
			for(int j = 0; j < pattern.length(); j++) {
				char p = pattern.charAt(j);
				if(p == primaryPatternChar)
					built.append(primary);
				else
					built.append(secondary);
			}
			
			if(built.toString().equals(value))
				return true;
		}
	
		//dead...
		return true;
	}
	
	public static void main(String[] args) {
		Test.header("PatternMatching");
	
		String[] inputs = {
				"catcatcatgogocat",
				"catcatcatgogodog",
				"catcatcatgogocat",
				"catcatcatgogocat",
				"catcatgogocatcat",
				"catcatgogocatcat",
				"catcatcatgogocat",
				"catcatcatgogocat",
				"dogcatdogcat",
				"dogcatdogcat",
				"aabb",
				"aabb"
		};
		
		String[] patterns = {
				"aaabba",
				"aaabba",
				"a",
				"b",
				"aba",
				"bab",
				"aaabbab",
				"bababa",
				"abab",
				"aabb",
				"aabb",
				"ab",
				"a"
		};
		
		boolean[] answers = {
				true,
				false,
				true,
				true,
				true,
				true,
				false,
				false,
				true,
				false,
				true,
				true,
				true
		};
		
		for(int i = 0; i < inputs.length; i++) {
			String input = inputs[i];
			String pattern = patterns[i];
			boolean expected = answers[i];
			Test.equals(patternMatch(input, pattern), expected);
		}
		
		Test.results();
	}
}
