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
	public List<String[]> layoutFile; //array list stores layout cells 
	String[] setupFile; //array stores setup lines
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
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (BadConfigFormatException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setAdj();
	}

	private void setAdj() {
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numColumns; col++) {

				char initial;
				char secretPassage = grid[row][col].getSecretPassage();

				if(secretPassage != 0) {
					initial = grid[row][col].getInitial();
					BoardCell currentRoom = roomMap.get(initial).getCenterCell();
					currentRoom.addAdj(roomMap.get(secretPassage).getCenterCell());
				}

				//sets adj for both the doorway and the center of the room its pointing to 
				boolean doorway = grid[row][col].isDoorway();
				if(doorway) {
					BoardCell roomCell;
					switch(grid[row][col].getDoorDirection()) {
					case UP:
						initial = grid[row-1][col].getInitial();
						roomCell = roomMap.get(initial).getCenterCell();
						roomCell.addAdj(grid[row][col]);
						grid[row][col].addAdj(roomCell);
						break;
					case DOWN:
						initial = grid[row+1][col].getInitial();
						roomCell = roomMap.get(initial).getCenterCell();
						roomCell.addAdj(grid[row][col]);
						grid[row][col].addAdj(roomCell);
						break;
					case LEFT:
						initial = grid[row][col-1].getInitial();
						roomCell = roomMap.get(initial).getCenterCell();
						roomCell.addAdj(grid[row][col]);
						grid[row][col].addAdj(roomCell);
						break;
					case RIGHT: 
						initial = grid[row][col+1].getInitial();
						roomCell = roomMap.get(initial).getCenterCell();
						roomCell.addAdj(grid[row][col]);
						grid[row][col].addAdj(roomCell);
						break;
					default:
						break;
					}
				}


				//adjacency in x-1 direction
				if ((row-1) >= 0) {
					initial = grid[row-1][col].getInitial();
					if(initial == 'W') {
						BoardCell cell = getCell(row-1, col);
						grid[row][col].addAdj(cell);
					}
				} 

				//adjacency in y-1 direction
				if ((col-1) >= 0) {
					initial = grid[row][col-1].getInitial();
					if(initial == 'W') {
						BoardCell cell = getCell(row, col-1);
						grid[row][col].addAdj(cell);
					}
				} 

				//adjacency in x+1 direction
				if ((row+1) < numRows) {
					initial = grid[row+1][col].getInitial();
					if(initial == 'W') {
						BoardCell cell = getCell(row+1, col);
						grid[row][col].addAdj(cell);
					}
				} 

				//adjacency in y+1 direction
				if ((col+1) < numColumns) {
					initial = grid[row][col+1].getInitial();
					if(initial == 'W') {
						BoardCell cell = getCell(row, col+1);
						grid[row][col].addAdj(cell);
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
		FileReader reader = new FileReader(setupConfigFile); //reads file
		Scanner in = new Scanner(reader);

		while(in.hasNextLine()) {
			readSetupConfig(in);
		}
		in.close(); //close file
	}
	private void readSetupConfig(Scanner in) throws BadConfigFormatException, Exception {
		String[] setupLines = in.nextLine().split(", "); //adds split line to array

		//check if line is a comment or line in empty
		if (!(setupLines[0].contains("//") || setupLines[0].isEmpty())) {
			//If an entry in either file does not have the proper format.
			if (!(setupLines[0].equals("Room") || setupLines[0].equals("Space"))) 
				throw new BadConfigFormatException("Error: Setup file doesn't have proper format");
			Room room = new Room();
			room.setName(setupLines[1]); //sets room name
			char initial = setupLines[2].charAt(0);
			roomMap.put(initial, room); //adds room name and initial to map
		}
	}

	//loads board layout 
	public void loadLayoutConfig() throws Exception {
		FileReader reader = new FileReader(layoutConfigFile);
		Scanner in = new Scanner(reader);
		String[] nextCell = null; //stores layout lines
		layoutFile = new ArrayList<String[]>(); //holds file lines

		//reads each line and adds to array
		while(in.hasNextLine()) {
			nextCell = in.nextLine().split(","); //adds split cell to array
			layoutFile.add(nextCell); //stores each cell in array list
		}
		in.close(); //close file
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
				char ch = layoutFile.get(i)[j].charAt(0);
				int numOfChar = layoutFile.get(i)[j].length();
				grid[i][j].setInitial(ch); //set cell initial

				if((grid[i][j].getInitial() != 'W') || (grid[i][j].getInitial() !='X')) grid[i][j].setRoom(true);
				if(grid[i][j].getInitial() == 'W') grid[i][j].setWalkway(true); //check if walkway
				if(grid[i][j].getInitial() =='X') grid[i][j].setUnused(true); //check if unused

				doorDirection = DoorDirection.NONE;
				grid[i][j].setDoorDirection(doorDirection); //set all cells to initial no door

				//If the board layout refers to a room that is not in your setup file.
				if(!roomMap.containsKey(ch)) 
					throw new BadConfigFormatException("Error: Layout refers to room that is not in setup file");

				//check if cell char contains a sign
				if(numOfChar == 2) {
					//if cell char contains *, set to room center
					char initial = grid[i][j].getInitial();
					char identifier = layoutFile.get(i)[j].charAt(1);

					switch(identifier) {
					case '*': //if cell char contains *, set to center
						grid[i][j].setRoomCenter(); //set cell to center
						roomMap.get(initial).setCenterCell(grid[i][j]); 
						break;
					case '#': //if cell char contains #, set to label
						grid[i][j].setLabel(); //set cell to label
						roomMap.get(initial).setLabelCell(grid[i][j]);
						break;
					case '^': //if cell char contains ^, set door direction
						doorDirection = DoorDirection.UP;
						grid[i][j].setDoorDirection(doorDirection);
						grid[i][j].isDoorway();
						break;
					case '>': //if cell char contains >, set door direction
						doorDirection = DoorDirection.RIGHT;
						grid[i][j].setDoorDirection(doorDirection);
						grid[i][j].isDoorway();
						break;
					case '<': //if cell char contains <, set door direction
						doorDirection = DoorDirection.LEFT;
						grid[i][j].setDoorDirection(doorDirection);
						grid[i][j].isDoorway();
						break;
					case 'v': //if cell char contains v, set door direction
						doorDirection = DoorDirection.DOWN;
						grid[i][j].setDoorDirection(doorDirection);
						grid[i][j].isDoorway();
						break;
					default: //if cell char contains another char, set secret passage
						grid[i][j].setSecretPassage(identifier);
						break;
					}
				}
			}
		}
	}

	private void setGridSize() throws Exception {
		numRows = layoutFile.size(); //set row size
		numColumns = layoutFile.get(0).length; //set column size
		grid = new BoardCell[numRows][numColumns]; //set grid size

		//If the board layout file does not have the same number of columns in every row.
		for(int k=0; k < layoutFile.size(); k++) {
			if (layoutFile.get(k).length != numColumns) 
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
