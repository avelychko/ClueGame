package tests;

import static org.junit.Assert.*;
import org.junit.jupiter.api.*;
import java.util.*;
import clueGame.*;

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
		ComputerPlayer player = new ComputerPlayer("Kenichi Shinoda", "yellow", 24, 4);
		BoardCell cell = new BoardCell(23, 20);
		assertEquals(cell, player.selectTarget());
	}
	
	@Test
	public void createSuggestion() {
		ComputerPlayer player = new ComputerPlayer("Kenichi Shinoda", "yellow", 24, 4);
		Solution solution = new Solution(new Card("Pablo Escobar", CardType.PERSON), new Card("Wire", CardType.WEAPON), new Card("Pool", CardType.ROOM));
		assertEquals(solution, player.createSuggestion());
	}

}
