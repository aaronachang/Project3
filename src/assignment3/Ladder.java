//Ladder.java

package assignment3;

import java.util.ArrayList;

/**
 * Provides methods to store and access rungs in ladder.
 * @author Aaron and Ka Tai
 *
 */
public class Ladder {
	private ArrayList<String> rungs;
	
	/**
	 * The String constructor for Ladder
	 * @param s first string element in the ArrayList
	 */
	Ladder(String s) {
		rungs = new ArrayList<String>(0);
		rungs.add(s);
	}
	
	/**
	 * The ArrayList constructor for Ladder
	 * @param o The ArrayList that you want a copy of
	 */
	Ladder(ArrayList<String> o) {
		rungs = new ArrayList<String>(0);
		for (int i = 0; i < o.size(); i++) {
			rungs.add(o.get(i));
		}
	}
	
	/**
	 * Gets the last element in the Ladder
	 * @return the last element in the ladder
	 */
	public String getLast() {
		return rungs.get(rungs.size() - 1);
	}
	
	/**
	 * returns the size of the ladder
	 * @return the size of the ladder
	 */
	public int size() {
		return rungs.size();
	}
	
	/**
	 * returns the ladder as a ArrayList
	 * @return the rungs ArrayList in LAdder
	 */
	public ArrayList<String> toArrayList() {
		return rungs;
	}
	
	/**
	 * adds an element to the end of the ladder
	 * @param s the string that you want to add
	 */
	public void add(String s) {
		rungs.add(s);
	}
}
