package tests;

import static org.junit.Assert.*;
import org.junit.jupiter.api.*;
import java.util.*;
import clueGame.*;

public class GameSolutionTest {
	private static Board board;
	private static Solution solution;
	private static Player player;

	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
		
		solution = new Solution(new Card("Pablo Escobar", CardType.PERSON), new Card("Wire", CardType.WEAPON), new Card("Pool", CardType.ROOM));
		player = new HumanPlayer("Al Capone", "cyan", 23, 10);
		player.updateHand(new Card("Pistol", CardType.WEAPON));
		player.updateHand(new Card("Bathroom", CardType.ROOM));
		player.updateHand(new Card("Wire", CardType.WEAPON));
	}
	
	@Test
	public void checkAccusation() {
		assertTrue(board.checkAccusation(new Card("Pablo Escobar", CardType.PERSON), new Card("Wire", CardType.WEAPON), new Card("Pool", CardType.ROOM)));
	}
	
	@Test
	public void disproveSuggestion() {
		assertEquals(solution.getPerson(), player.disproveSuggestion(new Card("Pablo Escobar", CardType.PERSON), new Card("Wire", CardType.WEAPON), new Card("Pool", CardType.ROOM)));
		assertEquals(solution.getRoom(), player.disproveSuggestion(new Card("Pablo Escobar", CardType.PERSON), new Card("Wire", CardType.WEAPON), new Card("Pool", CardType.ROOM)));
		assertEquals(solution.getWeapon(), player.disproveSuggestion(new Card("Pablo Escobar", CardType.PERSON), new Card("Wire", CardType.WEAPON), new Card("Pool", CardType.ROOM)));
	}
	
	@Test
	public void handleSuggestion() {
		assertEquals(solution.getPerson(), board.handleSuggestion(new Card("Pablo Escobar", CardType.PERSON), new Card("Wire", CardType.WEAPON), new Card("Pool", CardType.ROOM)));
		assertEquals(solution.getRoom(), board.handleSuggestion(new Card("Pablo Escobar", CardType.PERSON), new Card("Wire", CardType.WEAPON), new Card("Pool", CardType.ROOM)));
		assertEquals(solution.getWeapon(), board.handleSuggestion(new Card("Pablo Escobar", CardType.PERSON), new Card("Wire", CardType.WEAPON), new Card("Pool", CardType.ROOM)));
	}
}
