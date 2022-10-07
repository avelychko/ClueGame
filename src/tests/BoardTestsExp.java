package tests;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import experiment.*;

public class BoardTestsExp {
	TestBoard board;
	
<<<<<<< HEAD
	public void testBoard() {
		
	}
	
	public void testBoardCell() {
		
	}

=======
	//@BeforeEach method to set up your BoardExp.
	public void setUp() {
		board = new TestBoard();
	}
	
	@Test
	public void testAdjacency() {
		TestBoardCell cell = board.getCell(0,0);
	}
>>>>>>> 5d116b56782c9fde01737d86d1d04aed8259dc86
}
