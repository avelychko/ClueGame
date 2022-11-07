package tests;

import static org.junit.Assert.*;
import org.junit.jupiter.api.*;
import java.util.*;
import clueGame.*;

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
		assertTrue(board.checkAccusation());
	}
	
	@Test
	public void disproveSuggestion() {
		Player player = new HumanPlayer("Al Capone", "cyan", 23, 10);
		Solution solution = new Solution(new Card("Pablo Escobar", CardType.PERSON), new Card("Wire", CardType.WEAPON), new Card("Pool", CardType.ROOM));
		player.updateHand(new Card("Pistol", CardType.WEAPON));
		player.updateHand(new Card("Bathroom", CardType.ROOM));
		player.updateHand(new Card("Wire", CardType.WEAPON));
		
		assertEquals(solution.getPerson(), player.disproveSuggestion());
		assertEquals(solution.getRoom(), player.disproveSuggestion());
		assertEquals(solution.getWeapon(), player.disproveSuggestion());
	}
	
	@Test
	public void handleSuggestion() {
		Solution solution = new Solution(new Card("Pablo Escobar", CardType.PERSON), new Card("Wire", CardType.WEAPON), new Card("Pool", CardType.ROOM));
		assertEquals(solution.getPerson(), board.handleSuggestion());
		assertEquals(solution.getRoom(), board.handleSuggestion());
		assertEquals(solution.getWeapon(), board.handleSuggestion());
	}
}
