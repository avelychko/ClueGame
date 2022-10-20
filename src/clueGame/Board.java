package clueGame;

import java.io.*;
import java.util.*;

import experiment.TestBoardCell;

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
	private Set<BoardCell> targets; //set of target cells
	private Set<BoardCell> visited; //set of visited cells

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
		roomMap = new HashMap<Character, Room>(); 
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();

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

				char initial;
				char secretPassage = grid[i][j].getSecretPassage();

				if(secretPassage != 0) {
					initial = grid[i][j].getInitial();
					BoardCell currentRoom = roomMap.get(initial).getCenterCell();
					currentRoom.addAdj(roomMap.get(secretPassage).getCenterCell());
				}
				if(grid[i][j].isDoorway()) {
					BoardCell roomCell;
					if(grid[i][j].getDoorDirection() == DoorDirection.UP) {
						initial = grid[i-1][j].getInitial();
						roomCell = roomMap.get(initial).getCenterCell();
						roomCell.addAdj(grid[i][j]);
						grid[i][j].addAdj(roomCell);
					}
					if(grid[i][j].getDoorDirection() == DoorDirection.DOWN) {
						initial = grid[i+1][j].getInitial();
						roomCell = roomMap.get(initial).getCenterCell();
						roomCell.addAdj(grid[i][j]);
						grid[i][j].addAdj(roomCell);
					}
					if(grid[i][j].getDoorDirection() == DoorDirection.LEFT) {
						initial = grid[i][j-1].getInitial();
						roomCell = roomMap.get(initial).getCenterCell();
						roomCell.addAdj(grid[i][j]);
						grid[i][j].addAdj(roomCell);
					}
					if(grid[i][j].getDoorDirection() == DoorDirection.RIGHT) {
						initial = grid[i][j+1].getInitial();
						roomCell = roomMap.get(initial).getCenterCell();
						roomCell.addAdj(grid[i][j]);
						grid[i][j].addAdj(roomCell);
					}
				}

				//adjacency in x-1 direction
				if ((i-1) >= 0) {
					initial = grid[i-1][j].getInitial();
					if(initial == 'W') {
						BoardCell cell = getCell(i-1, j);
						grid[i][j].addAdj(cell);
					}
				} 

				//adjacency in y-1 direction
				if ((j-1) >= 0) {
					initial = grid[i][j-1].getInitial();
					if(initial == 'W') {
						BoardCell cell = getCell(i, j-1);
						grid[i][j].addAdj(cell);
					}
				} 

				//adjacency in x+1 direction
				if ((i+1) < numRows) {
					initial = grid[i+1][j].getInitial();
					if(initial == 'W') {
						BoardCell cell = getCell(i+1, j);
						grid[i][j].addAdj(cell);
					}
				} 

				//adjacency in y+1 direction
				if ((j+1) < numColumns) {
					initial = grid[i][j+1].getInitial();
					if(initial == 'W') {
						BoardCell cell = getCell(i, j+1);
						grid[i][j].addAdj(cell);
					}
				} 
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


				if((grid[i][j].getInitial() != 'W') || (grid[i][j].getInitial() !='X')) {
					grid[i][j].setRoom(true);
				}
				

				if(grid[i][j].getInitial() == 'W') grid[i][j].setWalkway(true);
				if (grid[i][j].getInitial() =='X') grid[i][j].setUnused(true);

				if(grid[i][j].getInitial() == 'W') grid[i][j].setWalkway(true); //check if walkway
				if (grid[i][j].getInitial() =='X') grid[i][j].setUnused(true); //check if unused


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

	//calculates legal targets for a move from startCell of length pathlength
	public void calcTargets(BoardCell startCell, int pathlength) {
		visited.clear(); //empty visited set
		targets.clear(); //empty targets set
		visited.add(startCell); //add startCell to visited
		findAllTargets(startCell, pathlength); 
	}

	private void findAllTargets(BoardCell thisCell, int numSteps) {
		for (BoardCell adjCell: thisCell.adjList) {
			//checks if adjacency cell has already been visited	
			if (visited.contains(adjCell) == false) {
				visited.add(adjCell); //adds visited cell to visited set
				if (!(adjCell.getWalkway() || adjCell.getUnused())) targets.add(adjCell); //if is room add adj cell to targets
				//checks if cell is occupied
				else {
					//check if cell is occupied
					if (adjCell.getOccupied() == false) {
						if (numSteps == 1) targets.add(adjCell); //checks if length is 1 then add adj cell to targets set
						else findAllTargets(adjCell, numSteps - 1);  //else call adj cell recursively
						visited.remove(adjCell); //remove visited adj cell
					}
				}
			}
		}
	}

	public Set<BoardCell> getAdjList(int row, int col) { return grid[row][col].grabAdjList(); } 
	public Set<BoardCell> getTargets() { return targets; } //returns the targets last created by calcTargets()

	public BoardCell getCell(int row, int col) { return grid[row][col]; } //returns the cell from the board at row, col

	public int getNumRows() { return numRows; } //returns board row size
	public int getNumColumns() { return numColumns; } //returns board column size

	public Room getRoom(char room) { return roomMap.get(room); } //returns room at char
	public Room getRoom(BoardCell cell) { return roomMap.get(cell.getInitial()); } //return room at cell 
}
