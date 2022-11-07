package tests;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import clueGame.Board;

public class ComputerAITest {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
		
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
	}
	
	@Test
	public void selectTargets() {
		
	}
	
	@Test
	public void createSuggestion() {
		
	}

}
