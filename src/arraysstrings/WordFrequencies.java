package arraysstrings;

import java.util.HashMap;

import testing.Test;

public class WordFrequencies {

	public static HashMap<String, Integer> wordFrequencies(String book) {
		HashMap<String, Integer> freqMap = new HashMap<String, Integer>();
		String[] words = book.replace(",", "").replace(".",  "").replace("!",  "").replace("?", "").split(" ");
		
		for(String word : words) {
			freqMap.put(word, freqMap.getOrDefault(word, 0)+1);
		}
		
		return freqMap;
	}

	
	public static void main(String[] args) {
		Test.header("WordFrequencies");
		
		HashMap<String, Integer> freqMap = wordFrequencies("I felt happy because I saw the others were happy and because I knew I should feel happy, but I wasn't really happy.");
		
		Test.equals(freqMap.get("felt"), 1);
		Test.equals(freqMap.get("happy"), 4);
		Test.equals(freqMap.get("I"), 5);
		Test.equals(freqMap.get("wasn't"), 1);
		Test.equals(freqMap.get("because"), 2);
		
		Test.results();
	}
}
