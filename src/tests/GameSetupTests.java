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
	private static Card livingroom, bedroom, kitchen, garden, diningroom, atrium, bathroom, office, pool,
		alCapone, kenichiShinoda, pabloEscobar, eddieMcGrath, benjaminSiegel, matteoDenaro,
		pistol, poison, katana, throwingStars, wire, golfClub;
	private static Player player1, player2, player3, player4, player5, player6;
	
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
		
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
		
		//player
		player1 = new HumanPlayer("Al Capone", Color.getColor("Cyan"), 23, 10);
		player2 = new ComputerPlayer("Kenichi Shinoda", Color.getColor("Yellow"), 24, 4);
		player3 = new ComputerPlayer("Pablo Escobar", Color.getColor("Blue"), 23, 20);
		player4 = new ComputerPlayer("Eddie McGrath", Color.getColor("Green"), 0, 10);
		player5 = new ComputerPlayer("Benjamin Siegel", Color.getColor("Magenta"), 23, 29);
		player6 = new ComputerPlayer("Matteo Denaro", Color.getColor("Red"), 8, 30);
	}
	
	@Test
	public void testCards() {
		assertEquals("Living Room", livingroom.getCardName());
		assertEquals(CardType.ROOM, atrium.getCardType());
		assertEquals("Pablo Escobar", pabloEscobar.getCardName());
		assertEquals(CardType.PERSON, alCapone.getCardType());
		assertEquals("Katana", katana.getCardName());
		assertEquals(CardType.WEAPON, wire.getCardType());
	}
	
	public void testPlayerColor() {
		
	}
	
	public void testPlayerLocation() {
		
	}

	public void testPlayerName() {
		
	}
	
	public void testDeckAndCards() {
		
	}
}
