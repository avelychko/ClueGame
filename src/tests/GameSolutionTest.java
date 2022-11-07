package tests;

import static org.junit.Assert.*;
import org.junit.jupiter.api.*;
import java.util.*;
import clueGame.*;

import clueGame.Board;

public class GameSolutionTest {
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
	public void checkAccusation() {
		
	}
	
	@Test
	public void disproveSuggestion() {
		
	}
	
	@Test
	public void handleSuggestion() {
		
	}
}
