package assignment3;

import java.util.ArrayList;

public class Ladder {
	private ArrayList<String> rungs;

	Ladder(String s) {
		rungs = new ArrayList<String>(0);
		rungs.add(s);
	}
	
	Ladder(ArrayList<String> o) {
		rungs = new ArrayList<String>(0);
		for (int i = 0; i < o.size(); i++) {
			rungs.add(o.get(i));
		}
	}
	
	public String getLast() {
		return rungs.get(rungs.size() - 1);
	}
	
	public int size() {
		return rungs.size();
	}
	
	public ArrayList<String> toArrayList() {
		return rungs;
	}
	
	public void add(String s) {
		rungs.add(s);
	}
	
}
