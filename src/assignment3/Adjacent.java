//Adjacent.java

package assignment3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

/**
 * Finds and stores words that differ from given word by one letter.
 * @author Aaron and Ka
 *
 */
public class Adjacent {
	private ArrayList<String> adjacentWords;
	
	Adjacent(String current, Set<String> dict) {
		adjacentWords = new ArrayList<String>(0);
		
		for (String word: dict) {
			int index = adjacentWord(current, word);
			
			if (index != -1) {
				adjacentWords.add(word);
			}
		}
	}
	
	private static int adjacentWord(String current, String dict) {
		int diffCount = 0;
		int diffIndex = 0;
		
		for (int i = 0; i < dict.length(); i++) {
			if (current.charAt(i) != dict.charAt(i)) {
				diffCount++;
				diffIndex = i;
			}
		}
		
		if (diffCount != 1) {
			return -1;
		}
		
		return diffIndex;
	}
	
	/**
	 * Finds word closest to given word.
	 * @param current given word
	 * @return closest adjacent word
	 */
	public String closestAdjacentWord(String current) {
		String closestWord = current.toUpperCase();
		int difference = current.length();
		
		for (String neighbor: adjacentWords) {
			int countDifference = 0;
			for (int i = 0; i < current.length(); i++) {
				if (neighbor.charAt(i) != current.toUpperCase().charAt(i)) {
					countDifference++;
				}
			}
			if (countDifference <= difference) {
				difference = countDifference;
				closestWord = neighbor;
			}
		}
		
		return closestWord;
	}
	
	public ArrayList<String> getAdjWords() {
		return adjacentWords;
	}
	
	public String get(int index) {
		return adjacentWords.get(index);
	}
	
	public void remove(String word) {
		adjacentWords.remove(word);
	}
	
	public int size() {
		return adjacentWords.size();
	}
}
	