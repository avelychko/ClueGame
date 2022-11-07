package tests;

import static org.junit.Assert.*;
import org.junit.jupiter.api.*;
import java.util.*;
import clueGame.*;

public class GameSolutionTest {
	private static Board board;
	private static Solution solution;
	private static Player player;
	private static Card livingroom, bedroom, kitchen, garden, diningroom, atrium, bathroom, office, pool,
	alCapone, kenichiShinoda, pabloEscobar, eddieMcGrath, benjaminSiegel, matteoDenaro,
	pistol, poison, katana, throwingStars, wire, golfClub;

	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
		
		//player
		player = new HumanPlayer("Al Capone", "cyan", 23, 10);
		
		//room cards
		livingroom = new Card("Living Room", CardType.ROOM);
		bedroom = new Card("Bedroom", CardType.ROOM);
		kitchen = new Card("Kitchen", CardType.ROOM);
		garden = new Card("Garden", CardType.ROOM);
		diningroom = new Card("Dining Room", CardType.ROOM);
		atrium = new Card("Atrium", CardType.ROOM);
		bathroom = new Card("Bathroom", CardType.ROOM);
		office = new Card("Office", CardType.ROOM);
		pool = new Card("Pool", CardType.ROOM);
		
		//person cards
		alCapone = new Card("Al Capone", CardType.PERSON);
		kenichiShinoda = new Card("Kenichi Shinoda", CardType.PERSON);
		pabloEscobar = new Card("Pablo Escobar", CardType.PERSON);
		eddieMcGrath = new Card("Eddie McGrath", CardType.PERSON);
		benjaminSiegel = new Card("Benjamin Siegel", CardType.PERSON);
		matteoDenaro = new Card("Matteo Denaro", CardType.PERSON);
		
		//weapon cards
		pistol = new Card("Pistol", CardType.WEAPON);
		poison = new Card("Poison", CardType.WEAPON);
		katana = new Card("Katana", CardType.WEAPON);
		throwingStars = new Card("Throwing Stars", CardType.WEAPON);
		wire = new Card("Wire", CardType.WEAPON);
		golfClub = new Card("Golf Club", CardType.WEAPON);
		
		//solution
		solution = new Solution(pabloEscobar, wire, pool);
	}
	
	@Test
	public void checkAccusation() {
		assertTrue(board.checkAccusation(pabloEscobar, wire, pool));
	}
	
	@Test
	public void disproveSuggestion() {
		assertEquals(solution.getPerson(), player.disproveSuggestion(pabloEscobar, wire, pool));
		assertEquals(solution.getRoom(), player.disproveSuggestion(pabloEscobar, wire, pool));
		assertEquals(solution.getWeapon(), player.disproveSuggestion(pabloEscobar, wire, pool));
	}
	
	@Test
	public void handleSuggestion() {
		assertEquals(solution.getPerson(), board.handleSuggestion(pabloEscobar, wire, pool));
		assertEquals(solution.getRoom(), board.handleSuggestion(pabloEscobar, wire, pool));
		assertEquals(solution.getWeapon(), board.handleSuggestion(pabloEscobar, wire, pool));
	}
}
