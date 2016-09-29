package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class LadderTests {
	
	Set<String> dict = Main.makeDictionary();
	Adjacent adjTest = new Adjacent("Hello", dict);
	
	
	@Test
	public void ladderLength() {
		assertEquals(0, Main.getWordLadderBFS("alone", "aline").size() - 2);
		assertEquals(0, Main.getWordLadderBFS("HEARD", "HEARS").size() - 2);
	}
	
	@Test(timeout = 30000)
	public void testBFS() {
		ArrayList<String> res = Main.getWordLadderBFS("hello", "cells");
		HashSet<String> set = new HashSet<String>(res);
		assertEquals(set.size(), res.size());
		assertFalse(res == null || res.size() == 0);
		assertTrue(res.size() < 4);
	}
	
	@Test(timeout = 30000)
	public void testBFS1() {
		ArrayList<String> res = Main.getWordLadderBFS("jazzy", "leady");
		HashSet<String> set = new HashSet<String>(res);
		assertEquals(set.size(), res.size());
		assertTrue(res == null || res.size() == 0);
	 
	}
	
	@Test(timeout = 30000)
	public void testDFS() {
		ArrayList<String> res = Main.getWordLadderDFS("hello", "cells");
		HashSet<String> set = new HashSet<String>(res);
		assertEquals(set.size(), res.size());
		assertFalse(res == null || res.size() == 0);
	}
	
	@Test(timeout = 30000)
	public void testDFS1() {
		ArrayList<String> res = Main.getWordLadderDFS("wites", "geese");
		HashSet<String> set = new HashSet<String>(res);
		assertEquals(set.size(), res.size());
		assertFalse(res == null || res.size() == 0);
	}
	
	@Test
	public void emptyArray() {
		assertEquals(new ArrayList<>(0), Main.getWordLadderBFS("jazzy","leady"));
	
	}

}
