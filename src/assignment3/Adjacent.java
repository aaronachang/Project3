package assignment3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

public class Adjacent {
	private ArrayList<String> adjacentWords = new ArrayList<String>();
	private int size = 0;
	
	Adjacent(String current, Set<String> dict, String end) {
		ArrayList<String> temp = new ArrayList<String>();
		
		for (String s: dict) {
			int index = adjacentWord(current, s, end);
			
			if (index != -1) {
				temp.add(s);
			}
		}
		
		for (int i = 0; i < temp.size(); i++) {
			adjacentWords.add(temp.remove(i));
		}
		
		size = adjacentWords.size();
	
	}
	
	
	private static int adjacentWord(String current, String dict, String end) {
		int diffCount = 0;
		int diffIndex = 0;
		
		for (int i = 0; i < dict.length(); i++) {
			if (current.charAt(i) != dict.charAt(i)) {
				diffCount++;
				diffIndex = i;
			}
		}
		
		//System.out.println(diffIndex);
		
		if (diffCount != 1) {
			return -1;
		}
		

		return diffIndex;
	}
	
	public ArrayList<String> getAdjWords() {
		return adjacentWords;
	}
	
	public String get(int index) {
		return adjacentWords.get(index);
	}
	public int size() {
		return size;
	}
}
	