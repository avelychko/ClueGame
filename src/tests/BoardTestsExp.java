package tests;

import java.util.Set;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import experiment.*;

public class BoardTestsExp {
	TestBoard board;

	//@BeforeEach method to set up your BoardExp.
	public void setUp() {
		board = new TestBoard();	}
	
	@Test
	public void testAdjacency() {
		TestBoardCell topLeftCorner  = board.getCell(0,0);
		Set<TestBoardCell> testTLC = topLeftCorner.getAdjList();
		
		assertTrue(testTLC.contains(board.getCell(1, 0)));
		assertTrue(testTLC.contains(board.getCell(0, 1)));
		assertEquals(2, testTLC.size());
		
		TestBoardCell bottomRightCorner = board.getCell(3,3);
		Set<TestBoardCell> testBRC = bottomRightCorner.getAdjList();
		
		assertTrue(testBRC.contains(board.getCell(2, 3)));
		assertTrue(testBRC.contains(board.getCell(3, 2)));
		assertEquals(2, testBRC.size());
		
		TestBoardCell rightEdge = board.getCell(1,3);
		Set<TestBoardCell> testRE = rightEdge.getAdjList();
		
		assertTrue(testRE.contains(board.getCell(0, 3)));
		assertTrue(testRE.contains(board.getCell(1, 2)));
		assertTrue(testRE.contains(board.getCell(2, 3)));
		assertEquals(3, testRE.size());
		
		TestBoardCell leftEdge = board.getCell(2,0);
		Set<TestBoardCell> testLE = leftEdge.getAdjList();
		
		assertTrue(testLE.contains(board.getCell(1, 0)));
		assertTrue(testLE.contains(board.getCell(3, 0)));
		assertTrue(testLE.contains(board.getCell(2, 1)));
		assertEquals(3, testLE.size());
		
		TestBoardCell middleGrid = board.getCell(2,2);
		Set<TestBoardCell> testMG = middleGrid.getAdjList();
		
		assertTrue(testMG.contains(board.getCell(1, 2)));
		assertTrue(testMG.contains(board.getCell(2, 1)));
		assertTrue(testMG.contains(board.getCell(2, 3)));
		assertTrue(testMG.contains(board.getCell(3, 2)));
		assertEquals(4, testMG.size());
	}
	
	/*
	@Test
	public void
	TestBoardCell cell = board.getCell(0,0);
		board.calcTargets(cell, 3);
		
		
		Set<TestBoardCell> target = board.getTargets();
		
		assertEquals(6, target.size());
		assertTrue(target.contains(board.getCell(3, 0)));
		*/

}
