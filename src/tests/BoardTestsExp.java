package tests;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import experiment.*;

public class BoardTestsExp {
	TestBoard board;

	//@BeforeEach method to set up your BoardExp.
	public void setUp() {
		board = new TestBoard();
	}
	
	@Test
	public void testAdjacency() {
		TestBoardCell cell = board.getCell(0,0);
	}
}
