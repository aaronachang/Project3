/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Aaron Chang
 * AAC3434
 * 16475
 * Ka Tai Ho
 * KH33248
 * 16465
 * Slip days used: <0>
 * Git URL: https://github.com/aaronachang/EE-422C.git
 * Fall 2016
 */

package assignment3;

/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Fall 2016
 */

import java.util.*;
import java.io.*;

public class Main {
	
	// static variables and constants only here.
	
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
		
		System.out.println("searching for" + input.get(0) + " and " + input.get(1) );
		
		ArrayList<String> temp = getWordLadderBFS(input.get(0), input.get(1));
		
		System.out.println("printing the ladder: ");
		printLadder(temp);
		
		
		// TODO methods to read in words, output ladder
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		// TO DO
		ArrayList<String> result = new ArrayList<String>(0);
		String start = keyboard.nextLine();
		start = start.toUpperCase();
		if (!start.equals("/quit")) {
			result.add(start);
			result.add(keyboard.nextLine().toUpperCase());
		}
		return result;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code
		
		return null; // replace this line later with real return
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
    	
    	Set<String> dict = makeDictionary();
    	
    	start = start.toUpperCase();
    	end = end.toUpperCase();
    	
    	Queue<Ladder> myQueue = new LinkedList<Ladder>();
    	myQueue.add(new Ladder(start));
    	
    	while (!myQueue.isEmpty()) {
    	
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
    			
    			System.out.println("adj words" + wordsToCheck.getAdjWords().toString());
    			
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
		
		for (String s: ladder) {
			System.out.println(s);
		}
	}
	// TODO
	// Other private static methods here
	
	private static String formatString(String word, int index) {
		StringBuilder temp = new StringBuilder(word);
		temp.setCharAt(index, Character.toUpperCase(word.charAt(index)));
		
		return temp.toString();
	}
}
