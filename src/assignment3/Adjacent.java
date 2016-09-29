/* WORD LADDER Adjacent.java
 * EE422C Project 3 submission by
 * Aaron Chang
 * AAC3434
 * 16475
 * Ka Tai Ho
 * KH33248
 * 16465
 * Slip days used: <0>
 * Git URL: https://github.com/aaronachang/Project3.git
 * Fall 2016
 */

package assignment3;

import java.util.ArrayList;
import java.util.Set;
/**
 * Finds and stores words that differ from given word by one letter.
 * @author Aaron and Ka Tai
 *
 */
public class Adjacent {
	private ArrayList<String> adjacentWords;
	
	/**
	 * The constructor for Adjacent
	 * finds all the adjacent words to current
	 * inside the dictionary dict and adds it to 
	 * an ArrayList
	 * @param current the word being evaluated
	 * @param dict the dictionary being searched
	 */
	Adjacent(String current, Set<String> dict) {
		adjacentWords = new ArrayList<String>(0);
		
		for (String word: dict) {
			int index = adjacentWord(current, word);
			
			if (index != -1) {
				adjacentWords.add(word);
			}
		}
	}
	
	/**
	 * checks whether or not a word is adjacent to the 
	 * current word
	 * @param current the word being evaluated
	 * @param dict the word in dictionary being evaluated
	 * @return diffIndex a number >= 0 indicates that it is 
	 * an adjacent word and returns -1 if it is not
	 */
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
	
	/**
	 * returns the ArrayList of adjacent Strings
	 * @return the adjacentWords Array
	 */
	public ArrayList<String> getAdjWords() {
		return adjacentWords;
	}
	
	/**
	 * returns the String at a certain index
	 * @param index of the String that you want
	 * @return the String at the index passed
	 */
	public String get(int index) {
		return adjacentWords.get(index);
	}
	
	/**
	 * removes a String from adjacentWords
	 * @param word the String you want to remove
	 */
	public void remove(String word) {
		adjacentWords.remove(word);
	}
	
	/**
	 * reutnr s the size of adjacentWords
	 * @return the size (number of adjacent words)
	 */
	public int size() {
		return adjacentWords.size();
	}
}
	