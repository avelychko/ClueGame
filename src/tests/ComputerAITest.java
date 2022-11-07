package tests;

import static org.junit.Assert.*;
import org.junit.jupiter.api.*;
import java.util.*;
import clueGame.*;

public class ComputerAITest {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
	private static ComputerPlayer player;
	private static Solution solution;
	private static BoardCell cell;
	
		
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
		
		solution = new Solution(new Card("Pablo Escobar", CardType.PERSON), new Card("Wire", CardType.WEAPON), new Card("Pool", CardType.ROOM));
		player = new ComputerPlayer("Kenichi Shinoda", "yellow", 24, 4);
		cell = new BoardCell(23, 20);
	}
	
	@Test
	public void selectTargets() {
		assertEquals(cell, player.selectTarget());
	}
	
	@Test
	public void createSuggestion() {
		assertEquals(solution, player.createSuggestion());
	}
}
