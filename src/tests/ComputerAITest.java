package tests;

import static org.junit.Assert.*;
import org.junit.jupiter.api.*;
import java.util.*;
import clueGame.*;

public class ComputerAITest {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
	private static ComputerPlayer player1, player2, player3;
	private static Solution solution;
	private static BoardCell cell;
	private static Card livingroom, bedroom, kitchen, garden, diningroom, atrium, bathroom, office, pool,
	alCapone, kenichiShinoda, pabloEscobar, eddieMcGrath, benjaminSiegel, matteoDenaro,
	pistol, poison, katana, throwingStars, wire, golfClub;
	private static ArrayList<Card> roomDeck; // array list stores room cards
	private static ArrayList<Card> personDeck; // array list stores person cards
	private static ArrayList<Card> weaponDeck; // array list stores weapon cards
		
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
		
		//player
		player1 = new ComputerPlayer("Kenichi Shinoda", "yellow", 2, 17);
		player2 = new ComputerPlayer("Benjamin Siegel", "Magenta", 2, 17);
		player3 = new ComputerPlayer("Matteo Denaro", "Red", 2, 17);
		
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
		
		//card decks
		roomDeck = new ArrayList<Card>();
		personDeck = new ArrayList<Card>();
		weaponDeck = new ArrayList<Card>();
		
		roomDeck.add(bedroom);
		roomDeck.add(office);
		roomDeck.add(pool);

		personDeck.add(alCapone);
		personDeck.add(pabloEscobar);
		personDeck.add(eddieMcGrath);
		
		weaponDeck.add(pistol);
		weaponDeck.add(katana);
		weaponDeck.add(wire);
		
		player1.updateHand(pabloEscobar);
		player1.updateHand(pool);
		player1.updateHand(wire);
		
		player1.updateSeen(bedroom);
		player1.updateSeen(eddieMcGrath);
		player1.updateSeen(office);
		player1.updateSeen(katana);
		player1.updateSeen(pistol);
		player1.updateSeen(alCapone);
		
		player2.updateHand(pabloEscobar);
		player2.updateHand(katana);
		player2.updateHand(wire);
		
		player2.updateSeen(bedroom);
		player2.updateSeen(eddieMcGrath);
		player2.updateSeen(office);
		player2.updateSeen(pistol);
		
		//cell
		cell = new BoardCell(23, 20);
	}
	
	@Test
	public void selectTargets() {
		//assertEquals(cell, player1.selectTarget());
	}
	
	@Test
	public void createSuggestion() {
		//check if player location is in room
		assertTrue(board.getCell(player1.getRow(), player1.getCol()).isRoomCenter());
		
		//check suggestion if only one not seen
		assertEquals(solution.getPerson(), player1.createSuggestion(pool, personDeck, weaponDeck).getPerson());
		assertEquals(solution.getWeapon(), player1.createSuggestion(pool, personDeck, weaponDeck).getWeapon());
		
		//check suggestion if multiple not seen
		//check that suggested cards were not seen
		assertFalse(player2.getSeenCards().contains(player2.createSuggestion(pool, personDeck, weaponDeck).getPerson()));
		assertFalse(player2.getSeenCards().contains(player2.createSuggestion(pool, personDeck, weaponDeck).getWeapon()));
		//check that one of the cards is in hand
		assertTrue(player2.getPlayerCards().contains(player2.createSuggestion(pool, personDeck, weaponDeck).getWeapon()));
	}
}
