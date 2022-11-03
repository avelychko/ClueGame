package tests;

import static org.junit.Assert.*;
import org.junit.jupiter.api.*;
import java.util.*;
import clueGame.*;
import java.awt.Color;

public class GameSetupTests {
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
	
	public void testPlayerColor() {
		//assertEquals( , );
		//assertTrue();
	}
	
	public void testPlayerLocation() {
		
	}

	public void testPlayerName() {
		
	}
	
	public void testDeckandCards() {
		
	}
}
