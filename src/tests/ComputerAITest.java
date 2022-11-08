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
		player = new ComputerPlayer("Kenichi Shinoda", "yellow", 2, 17);
		
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
		roomDeck.add(livingroom);
		roomDeck.add(bedroom);
		roomDeck.add(kitchen);
		roomDeck.add(garden);
		roomDeck.add(diningroom);
		roomDeck.add(atrium);
		roomDeck.add(bathroom);
		roomDeck.add(office);
		roomDeck.add(pool);

		personDeck.add(alCapone);
		personDeck.add(kenichiShinoda);
		personDeck.add(pabloEscobar);
		personDeck.add(eddieMcGrath);
		personDeck.add(benjaminSiegel);
		personDeck.add(matteoDenaro);
		
		weaponDeck.add(pistol);
		weaponDeck.add(poison);
		weaponDeck.add(katana);
		weaponDeck.add(throwingStars);
		weaponDeck.add(wire);
		weaponDeck.add(golfClub);
		
		player.updateSeen(bedroom);
		player.updateSeen(eddieMcGrath);
		player.updateSeen(office);
		player.updateSeen(katana);
		player.updateSeen(pistol);
		player.updateSeen(alCapone);
		
		//cell
		cell = new BoardCell(23, 20);
	}
	
	@Test
	public void selectTargets() {
		assertEquals(cell, player.selectTarget());
	}
	
	@Test
	public void createSuggestion() {
		assertEquals(solution, player.createSuggestion(roomDeck, personDeck, weaponDeck));
	}
}
