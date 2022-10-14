package clueGame;

import java.io.*;
import java.util.*;

/**
 * Board Class
 * 
 * @author Anastasia Velyhko
 * @author Gordon Dina
 *
 */

public class Board {
	private BoardCell[][] grid; //2D array of object TestBoardCell
	private int numColumns; //column size
	private int numRows; //row size
	private String layoutConfigFile; //board layout
	private String setupConfigFile; //board setup
	private Map<Character, Room> roomMap = new HashMap<Character, Room>(); //map for board rooms
	public List<String[]> layoutLines; //array list stores layout cells
	String[] setupLines; //array stores setup lines

	/*
	 * variable and methods used for singleton pattern
	 */
	private static Board theInstance = new Board();
	private Board() { super(); } // constructor is private to ensure only one can be created
	public static Board getInstance() { 
		if (theInstance == null) theInstance = new Board();
		return theInstance; 
	} // this method returns the only Board

	/*
	 * initialize the board (since we are using singleton pattern)
	 */
	public void initialize() { 
		roomMap = new HashMap<Character, Room>(); //initilizes room map  

		//load setup and layout
		try {
			loadSetupConfig();
			loadLayoutConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setAdj();
	}
	private void setAdj() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if ((i-1) >= 0) grid[i][j].addAdj(getCell(i-1, j)); //adjacency in x-1 direction
				if ((j-1) >= 0) grid[i][j].addAdj(getCell(i, j-1)); //adjacency in y-1 direction
				if ((i+1) < numRows) grid[i][j].addAdj(getCell(i+1, j)); //adjacency in x+1 direction
				if ((j+1) < numColumns) grid[i][j].addAdj(getCell(i, j+1)); //adjacency in y+1 direction
			}
		}
	}

	//sets board setup and layout
	public void setConfigFiles(String layout, String setup) {
		this.layoutConfigFile = layout;
		this.setupConfigFile = setup;
	}

	//loads board setup 
	public void loadSetupConfig() throws Exception {
		FileReader reader = null;
		Scanner in = null;

		try {
			reader = new FileReader(setupConfigFile); //reads file
			in = new Scanner(reader);

			while(in.hasNextLine()) {
				String[] setupLines = in.nextLine().split(", "); //adds split line to array

				//check if line is a comment
				if (!setupLines[0].contains("//")) {
					//If an entry in either file does not have the proper format.
					if (!(setupLines[0].equals("Room") || setupLines[0].equals("Space"))) 
						throw new BadConfigFormatException("Error: Setup file doesn't have proper format");
					Room room = new Room();
					room.setName(setupLines[1]); //sets room name
					char initial = setupLines[2].charAt(0);
					roomMap.put(initial, room); //adds room name and initial to map
				}
			} 
		} catch (FileNotFoundException e) {
			e.getLocalizedMessage();
		} in.close(); //close file
	}

	//loads board layout 
	public void loadLayoutConfig() throws Exception {
		FileReader reader = null;
		Scanner in = null;
		String[] nextCell = null; //stores layout lines

		try {
			reader = new FileReader(layoutConfigFile); //reads file
			in = new Scanner(reader);
			layoutLines = new ArrayList<String[]>(); //holds file lines

			//reads each line and adds to array
			while(in.hasNextLine()) {
				nextCell = in.nextLine().split(","); //adds split cell to array
				layoutLines.add(nextCell); //stores each cell in array list
			}
		} catch (FileNotFoundException e) {
			e.getLocalizedMessage();
		} in.close(); //close file
		setGridSize();
		setGridCell();
	}
	
	private void setGridCell() throws Exception {
		DoorDirection doorDirection;
		//identifies if cell is center, label, door (which way), or secret passage
		//adds each cell to grid
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {   			 
				grid[i][j] = new BoardCell(i,j); //sets board sells
				char ch = layoutLines.get(i)[j].charAt(0);
				grid[i][j].setInitial(ch); //set cell initial
				doorDirection = DoorDirection.NONE;
				grid[i][j].setDoorDirection(doorDirection); //set all cells to initial no door

				//If the board layout refers to a room that is not in your setup file.
				if(!roomMap.containsKey(ch)) 
					throw new BadConfigFormatException("Error: Layout refers to room that is not in setup file");

				//check if cell char contains a sign
				if(layoutLines.get(i)[j].length() == 2) {
					//if cell char contains *, set to room center
					char initial = grid[i][j].getInitial();
					char identifier = layoutLines.get(i)[j].charAt(1);
					if(identifier == '*') {
						grid[i][j].setRoomCenter(); //set cell to center
						roomMap.get(initial).setCenterCell(grid[i][j]); 
					}
					//if cell char contains #, set to label
					else if(identifier == '#') {
						grid[i][j].setLabel(); //set cell to label
						roomMap.get(initial).setLabelCell(grid[i][j]);
					}
					//if cell char contains ^, set door direction
					else if(identifier == '^') {
						doorDirection = DoorDirection.UP;
						grid[i][j].setDoorDirection(doorDirection);
						grid[i][j].isDoorway();
					}
					//if cell char contains >, set door direction
					else if(identifier == '>') {
						doorDirection = DoorDirection.RIGHT;
						grid[i][j].setDoorDirection(doorDirection);
						grid[i][j].isDoorway();
					}
					//if cell char contains <, set door direction
					else if(identifier == '<') {
						doorDirection = DoorDirection.LEFT;
						grid[i][j].setDoorDirection(doorDirection);
						grid[i][j].isDoorway();
					}
					//if cell char contains v, set door direction
					else if(identifier == 'v') {
						doorDirection = DoorDirection.DOWN;
						grid[i][j].setDoorDirection(doorDirection);
						grid[i][j].isDoorway();
					}
					//if cell char contains another char, set secret passage
					else {
						grid[i][j].setSecretPassage(identifier);
					}
				}
			}
		}
	}
	private void setGridSize() throws Exception {
		numRows = layoutLines.size(); //set row size
		numColumns = layoutLines.get(0).length; //set column size
		grid = new BoardCell[numRows][numColumns]; //set grid size

		//If the board layout file does not have the same number of columns in every row.
		for(int k=0; k < layoutLines.size(); k++) {
			if (layoutLines.get(k).length != numColumns) 
				throw new BadConfigFormatException("Error: Layout file does not have the same number of columns in every row");
		}
	}

	public BoardCell getCell(int row, int col) { return grid[row][col]; } //returns the cell from the board at row, col
	public int getNumRows() { return numRows; } //returns board row size
	public int getNumColumns() { return numColumns; } //returns board column size
	public Room getRoom(char room) { return roomMap.get(room); } //returns room at char
	public Room getRoom(BoardCell cell) { return roomMap.get(cell.getInitial()); } //return room at cell 
}
