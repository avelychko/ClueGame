package tests;

/*
 * This program tests that config files are loaded properly.
 */

// Doing a static import allows me to write assertEquals rather than
// Assert.assertEquals
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;
import clueGame.Room;

public class FileInitTests {
	// Constants that I will use to test whether the file was loaded correctly
	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 25;
	public static final int NUM_COLUMNS = 31;

	// NOTE: I made Board static because I only want to set it up one
	// time (using @BeforeAll), no need to do setup before each test.
	private static Board board;

	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		// Initialize will load BOTH config files
		board.initialize();
	}

	@Test
	public void testRoomLabels() {
		// To ensure data is correctly loaded, test retrieving for every rooms
		// from the hash, including the first and last in the file and a few others
		assertEquals("Living Room", board.getRoom('L').getName() );
		assertEquals("Bedroom", board.getRoom('B').getName() );
		assertEquals("Kitchen", board.getRoom('K').getName() );
		assertEquals("Garden", board.getRoom('G').getName() );
		assertEquals("Dining Room", board.getRoom('D').getName() );
		assertEquals("Atrium", board.getRoom('A').getName() );
		assertEquals("Bathroom", board.getRoom('T').getName() );
		assertEquals("Office", board.getRoom('O').getName() );
		assertEquals("Pool", board.getRoom('P').getName() );
	}

	@Test
	public void testBoardDimensions() {
		// Ensure we have the proper number of rows and columns
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}

	// Test a doorway in each direction (RIGHT/LEFT/UP/DOWN), plus
	// three cells that are not a doorway, 2 are walkways and 1 is unused.
	// These cells are dark blue on the planning spreadsheet
	@Test
	public void FourDoorDirections() {
		// door to pool, left and up
		BoardCell pool = board.getCell(3, 23); 
		assertTrue(pool.isDoorway());
		assertEquals(DoorDirection.LEFT, pool.getDoorDirection());
		pool = board.getCell(4, 13); // door to pool
		assertTrue(pool.isDoorway());
		assertEquals(DoorDirection.UP, pool.getDoorDirection());
		
		// doors to Garden, up right left 
		BoardCell garden = board.getCell(3, 23);
		garden = board.getCell(15, 12);
		assertTrue(garden.isDoorway());
		assertEquals(DoorDirection.UP, garden.getDoorDirection());
		garden = board.getCell(15, 21);
		assertTrue(garden.isDoorway());
		assertEquals(DoorDirection.UP, garden.getDoorDirection());
		garden = board.getCell(7, 11);
		assertTrue(garden.isDoorway());
		assertEquals(DoorDirection.RIGHT, garden.getDoorDirection());
		garden = board.getCell(2, 22);
		assertTrue(garden.isDoorway());
		assertEquals(DoorDirection.LEFT, garden.getDoorDirection());
		
		// single door to Kitchen Room, right
		BoardCell kitchen = board.getCell(5, 27);
		assertTrue(kitchen.isDoorway());
		assertEquals(DoorDirection.RIGHT, kitchen.getDoorDirection());
		
		//doors to Atrium, has many doors directions down, left and right
		BoardCell atrium = board.getCell(16, 15);
		assertTrue(atrium.isDoorway());
		assertEquals(DoorDirection.DOWN, atrium.getDoorDirection());
		atrium = board.getCell(16, 16); 
		assertTrue(atrium.isDoorway());
		assertEquals(DoorDirection.DOWN, atrium.getDoorDirection());
		atrium = board.getCell(20, 20);
		assertTrue(atrium.isDoorway());
		assertEquals(DoorDirection.LEFT, atrium.getDoorDirection());
		atrium = board.getCell(21, 20); 
		assertTrue(atrium.isDoorway());
		assertEquals(DoorDirection.LEFT, atrium.getDoorDirection());
		atrium = board.getCell(20, 11);
		assertTrue(atrium.isDoorway());
		assertEquals(DoorDirection.RIGHT, atrium.getDoorDirection());
		atrium = board.getCell(21, 11); 
		assertTrue(atrium.isDoorway());
		assertEquals(DoorDirection.RIGHT, atrium.getDoorDirection());
		
		// Test that walkways are not doors
		BoardCell nonDoors = board.getCell(11, 10); // only next to other walkways
		assertFalse(nonDoors.isDoorway());
		nonDoors = board.getCell(23, 29);//next to unused and room
		assertFalse(nonDoors.isDoorway());
		nonDoors = board.getCell(6, 7);// right next to a door
		assertFalse(nonDoors.isDoorway());
	}

	

	// Test that we have the correct number of doors
	@Test
	public void testNumberOfDoorways() {
		int numDoors = 0;
		for (int row = 0; row < board.getNumRows(); row++)
			for (int col = 0; col < board.getNumColumns(); col++) {
				BoardCell cell = board.getCell(row, col);
				if (cell.isDoorway())
					numDoors++;
			}
		Assert.assertEquals(20, numDoors);
	}

	// Test a few room cells to ensure the room initial is correct.
	@Test
	public void testRooms() {
		// just test a standard room location
		BoardCell cell = board.getCell( 23, 15);
		Room room = board.getRoom( cell ) ;
		assertTrue( room != null );
		assertEquals( room.getName(), "Atrium" ) ;
		assertFalse( cell.isLabel() );
		assertFalse( cell.isRoomCenter() ) ;
		assertFalse( cell.isDoorway()) ;

		// this is a label cell to test
		cell = board.getCell(10, 14);
		room = board.getRoom( cell ) ;
		assertTrue( room != null );
		assertEquals( room.getName(), "Garden" ) ;
		assertTrue( cell.isLabel() );
		assertTrue( room.getLabelCell() == cell );
		
		// this is a room center cell to test
		cell = board.getCell(16, 27);
		room = board.getRoom( cell ) ;
		assertTrue( room != null );
		assertEquals( room.getName(), "Dinning Room" ) ;
		assertTrue( cell.isRoomCenter() );
		assertTrue( room.getCenterCell() == cell );
		
		// this is a secret passage test
		cell = board.getCell(22, 0);
		room = board.getRoom( cell ) ;
		assertTrue( room != null );
		assertEquals( room.getName(), "Office" ) ;
		assertTrue( cell.getSecretPassage() == 'K' );
		
		// test a walkway
		cell = board.getCell(18, 20);
		room = board.getRoom( cell ) ;
		// Note for our purposes, walkways and closets are rooms
		assertTrue( room != null );
		assertEquals( room.getName(), "Walkway" ) ;
		assertFalse( cell.isRoomCenter() );
		assertFalse( cell.isLabel() );
		
		// test a closet
		cell = board.getCell(17, 9);
		room = board.getRoom( cell ) ;
		assertTrue( room != null );
		assertEquals( room.getName(), "Unused" ) ;
		assertFalse( cell.isRoomCenter() );
		assertFalse( cell.isLabel() );
		
	}

}


