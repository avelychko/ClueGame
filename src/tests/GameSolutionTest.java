package tests;

import static org.junit.Assert.*;
import org.junit.jupiter.api.*;
import java.util.*;
import clueGame.*;

public class GameSolutionTest {
	private static Board board;
	private static Solution solution;
	private static Card livingroom, bedroom, kitchen, garden, diningroom, atrium, bathroom, office, pool,
	alCapone, kenichiShinoda, pabloEscobar, eddieMcGrath, benjaminSiegel, matteoDenaro,
	pistol, poison, katana, throwingStars, wire, golfClub;
	private static Player player1, player2, player3, player4, player5, player6;
	private static ArrayList<Player> players;
	
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();

		//players
		players = new ArrayList<Player>();
		player1 = new HumanPlayer("Al Capone", "Cyan", 23, 10);
		player2 = new ComputerPlayer("Kenichi Shinoda", "Yellow", 24, 4);
		player3 = new ComputerPlayer("Pablo Escobar", "Blue", 23, 20);
		player4 = new ComputerPlayer("Eddie McGrath", "Green", 0, 10);
		player5 = new ComputerPlayer("Benjamin Siegel", "Magenta", 23, 29);
		player6 = new ComputerPlayer("Matteo Denaro", "Red", 8, 30);
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		
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
		solution = new Solution(pool, pabloEscobar, wire);
		board.setAnswer(solution);
		board.setPlayer(players);
		
		//person card
		player1.updateHand(garden);
		player1.updateHand(pabloEscobar);
		player1.updateHand(katana);
		//room card
		player2.updateHand(garden);
		player2.updateHand(pool);
		player2.updateHand(katana);
		//weapon card
		player3.updateHand(garden);
		player3.updateHand(wire);
		player3.updateHand(pool);
		//no card
		player4.updateHand(garden);
		player4.updateHand(alCapone);
		player4.updateHand(katana);
		//all cards
		player5.updateHand(wire);
		player5.updateHand(pabloEscobar);
		player5.updateHand(matteoDenaro);
		//no cards
		player6.updateHand(poison);
		player6.updateHand(eddieMcGrath);
		player6.updateHand(office);
		

	}
	
	@Test
	public void checkAccusation() {
		assertTrue(board.checkAccusation(pool, pabloEscobar, wire));
	}
	
	@Test
	public void disproveSuggestion() {
		//tests person suggestion
		assertEquals(solution.getPerson(), player1.disproveSuggestion(pool, pabloEscobar, wire));
		
		//tests room suggestion
		assertEquals(solution.getRoom(), player2.disproveSuggestion(pool, pabloEscobar, wire));
		
		//tests weapon suggestion
		assertEquals(solution.getWeapon(), player3.disproveSuggestion(pool, pabloEscobar, wire));
		
		//test null
		assertEquals(null, player4.disproveSuggestion(pool, pabloEscobar, wire));
		
		//test multiple suggestions
		assertTrue(player5.getPlayerCards().contains(player5.disproveSuggestion(pool, pabloEscobar, wire)));
	}
	
	@Test
	public void handleSuggestion() {
		
		//tests person suggestion
		assertEquals(solution.getPerson(), board.handleSuggestion(player1, atrium, pabloEscobar, throwingStars));
	
		//tests room suggestion
		assertEquals(solution.getRoom(), board.handleSuggestion(player2, pool, kenichiShinoda, throwingStars));
		
		//tests weapon suggestion
		assertEquals(solution.getWeapon(), board.handleSuggestion(player3, atrium, kenichiShinoda, wire));
		
		//tests null suggestion
		assertEquals(null, board.handleSuggestion(player4, bedroom, benjaminSiegel, golfClub));
		
		assertEquals(null, board.handleSuggestion(player6, office, eddieMcGrath, poison));
	}
}
