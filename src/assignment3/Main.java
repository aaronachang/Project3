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
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		
		ArrayList<String> input = parse(kb);
		
		if (!input.isEmpty()) {
			//System.out.println("searching for " + start + " and " + end);
			
			ArrayList<String> temp = getWordLadderBFS(start, end);
			
			printLadder(temp);
		}
		
		// TODO methods to read in words, output ladder
	}
	
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
		// TO DO
		ArrayList<String> result = new ArrayList<String>(0);
		start = keyboard.nextLine();
		if (!start.equalsIgnoreCase("/quit")) {
			result.add(start.toUpperCase());
			end = keyboard.nextLine();
			result.add(end.toUpperCase());
		}
		return result;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code
    	Main.start = start;
    	Main.end = end;
    	start = start.toUpperCase();
    	end = end.toUpperCase();
		
		return null; // replace this line later with real return
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
    	Set<String> dict = makeDictionary();
    	Main.start = start;
    	Main.end = end;
    	start = start.toUpperCase();
    	end = end.toUpperCase();
    	
    	Queue<Ladder> myQueue = new LinkedList<Ladder>();
    	myQueue.add(new Ladder(start));
    	
    	while (!myQueue.isEmpty() && start.equals(end)) {
    	
    		if (myQueue.element().getLast().equals(end)) {
  
    			return myQueue.remove().toArrayList();
    		}
    		else if(!dict.contains(myQueue.element().getLast())) {
    			myQueue.remove();
    		}
    		else {
    			dict.remove(myQueue.element().getLast());
    			Ladder currentLadder = myQueue.remove();
    			
    			Adjacent wordsToCheck = new Adjacent(currentLadder.getLast(), dict, end);
    			
    			//System.out.println("adj words" + wordsToCheck.getAdjWords().toString());
    			
    			for (int i = 0; i < wordsToCheck.size(); i++) {
    				
					Ladder copyLadder = new Ladder(currentLadder.toArrayList());
					
					if (dict.contains(wordsToCheck.getAdjWords().get(i))) { 
						copyLadder.add(wordsToCheck.get(i));
						myQueue.add(copyLadder);
					}
				}
    		}
    	}
    	
    	return new ArrayList<String>(0);
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	public static void printLadder(ArrayList<String> ladder) {
		if (ladder.isEmpty()) {
			System.out.println("no word ladder can be found between " + start + " and " + end + ".");
		} else {
			int rungs = ladder.size() - 2;
			System.out.println("a " + rungs + "-rung word ladder exists between " + start + " and " + end + ".");
			for (String s: ladder) {
				System.out.println(s.toLowerCase());
			}
		}
	}
	// TODO
	// Other private static methods here
}
