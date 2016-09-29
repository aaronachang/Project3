/* WORD LADDER Main.java
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

import java.util.*;
import java.io.*;

/**
 * Contains static methods to obtain a word ladder between two words.
 * @author Aaron and Ka Tai
 *
 */
public class Main {
	private static String start;
	private static String end;
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			
			System.setOut(ps);			// redirect output to ps
		} 
		else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		
		initialize();
		ArrayList<String> input = parse(kb);
		
		if (!input.isEmpty()) {			
			ArrayList<String> bfs = getWordLadderBFS(start, end);
			printLadder(bfs);
			ArrayList<String> dfs = getWordLadderDFS(start, end);
			printLadder(dfs);
		}
	}
	
	/**
	 * Initializes static variables.
	 */
	public static void initialize() {
		start = "";
		end = "";
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList.
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> result = new ArrayList<String>(0);
		start = keyboard.nextLine().trim();
		
		if (!start.equalsIgnoreCase("/quit")) {
			result.add(start.toUpperCase());
			end = keyboard.nextLine().trim();
			
			if (end.equalsIgnoreCase("/quit")) {
				System.exit(0);
			}
			
			result.add(end.toUpperCase());
		}
		else {
			System.exit(0);
		}
		
		return result;
	}
	
	/**
	 * Obtains word ladder between start and end using DFS.
	 * @param start first word in word ladder
	 * @param end last word in word ladder
	 * @return
	 */
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		ArrayList<String> ladder = new ArrayList<String>();
		Set<String> dict = makeDictionary();
		
    	Main.start = start;
    	Main.end = end;
    	
    	start = start.toUpperCase();
    	end = end.toUpperCase();
    	
		if (!(dict.contains(start) && dict.contains(end))) {
			return ladder; 
		}

    	if (recursiveDFS(start, ladder, dict)) { // returns true if ladder is found
    		ladder.add(start);
    		
			return reverseStringArray(ladder); // ladder must be reversed since words were added in reverse
		} 
    	else {
			return new ArrayList<String>(0);
		}
	}
	
	/**
	 * Recursive helper method to make word ladder using DFS.
	 * @param current word currently being evaluated
	 * @param ladder word ladder
	 * @param dict dictionary of words
	 * @return
	 */
    private static Boolean recursiveDFS(String current, ArrayList<String> ladder, Set<String> dict) {
    	dict.remove(current);
    	
    	if (current.equals(end.toUpperCase())) {
    		return true; // base case; found end of word ladder
    	}
    	
    	Adjacent neighbors = new Adjacent(current, dict);
    	
        for (int i = 0; i < neighbors.size(); i++) {
        	String closestWord = neighbors.closestAdjacentWord(end.toUpperCase()); // finds closest neighbor to last word in ladder
        	
        	if (recursiveDFS(closestWord, ladder, dict)){
        		ladder.add(closestWord); // reached base case; add words in ladder
        		
        		return true;
        	} 
        	else {
        		neighbors.remove(closestWord); // no neighbors; remove from list of neighbors
        	}
        }
        
        return false; // no neighbors
    }
    
    /**
     * Reverses strings in an ArrayList.
     * @param words to be reversed
     * @return words in reverse order
     */
	private static ArrayList<String> reverseStringArray(ArrayList<String> words) {
		ArrayList<String> result = new ArrayList<String>();
		
		for (int i = words.size() - 1; i >= 0; i--) {
			result.add(words.get(i));
		}
		
		return result; 
	}
	
	/**
	 * Obtains word ladder between start and end using BFS.
	 * @param start first word in word ladder
	 * @param end last word in word ladder
	 * @return word ladder
	 */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
    	Set<String> dict = makeDictionary();
    	
    	Main.start = start;
    	Main.end = end;
    	
    	start = start.toUpperCase();
    	end = end.toUpperCase();
    	
    	Queue<Ladder> myQueue = new LinkedList<Ladder>();
    	myQueue.add(new Ladder(start));
    	
    	while (!myQueue.isEmpty() && !start.equals(end)) {
    		if (myQueue.element().getLast().equals(end)) {
    			return myQueue.remove().toArrayList();
    		}
    		else if(!dict.contains(myQueue.element().getLast())) {
    			myQueue.remove();
    		}
    		else {
    			dict.remove(myQueue.element().getLast());
    			Ladder currentLadder = myQueue.remove();
    			
    			Adjacent wordsToCheck = new Adjacent(currentLadder.getLast(), dict);
    			
    			for (int i = 0; i < wordsToCheck.size(); i++) {
					Ladder copyLadder = new Ladder(currentLadder.toArrayList());
					copyLadder.add(wordsToCheck.get(i));
					myQueue.add(copyLadder);
				}
    		}
    	}
    	
    	return new ArrayList<String>(0);
	}
    
    /**
     * Makes dictionary from file.
     * @return dictionary
     */
	public static Set<String> makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} 
		catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		
		return words;
	}
	
	/**
	 * Prints word ladder.
	 * @param ladder empty if no ladder is found, contains word ladder if found
	 */
	public static void printLadder(ArrayList<String> ladder) {
		if (ladder.isEmpty()) {
			System.out.println("no word ladder can be found between " + start + " and " + end + ".");
		} 
		else {
			int rungs = ladder.size() - 2;
			System.out.println("a " + rungs + "-rung word ladder exists between " + start + " and " + end + ".");
			
			for (String words: ladder) {
				System.out.println(words.toLowerCase());
			}
		}
	}
}
