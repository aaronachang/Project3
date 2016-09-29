/* WORD LADDER LadderTests.java
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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import org.junit.Test;

public class LadderTests {
	
	@Test(timeout = 30000)
	public void oneAwayTest() {
		ArrayList<String> res = Main.getWordLadderBFS("alone", "aline");
		assertEquals(0, res.size() - 2);
		HashSet<String> set = new HashSet<String>(res);
		assertEquals(set.size(), res.size());
		assertFalse(res == null || res.size() == 0);
	}
	
	@Test(timeout = 30000)
	public void oneAwayTest1() {
		ArrayList<String> res = Main.getWordLadderBFS("HEARD", "HEARS");
		assertEquals(0, res.size() - 2);
		HashSet<String> set = new HashSet<String>(res);
		assertEquals(set.size(), res.size());
		assertFalse(res == null || res.size() == 0);
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
	public void testBFS2() {
		ArrayList<String> res = Main.getWordLadderBFS("", "leady");
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
	
	@Test(timeout = 30000)
	public void testDFS2() {
		ArrayList<String> res = Main.getWordLadderDFS("", "geese");
		HashSet<String> set = new HashSet<String>(res);
		assertEquals(set.size(), res.size());
		assertTrue(res == null || res.size() == 0);
	}
	
	@Test(timeout = 30000)
	public void emptyArray() {
		assertEquals(new ArrayList<>(0), Main.getWordLadderBFS("jazzy","leady"));
	}
}
