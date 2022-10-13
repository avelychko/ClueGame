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
	private Map<Character, Room> roomMap; //map for board rooms
	public BoardCell boardCell; //board cell
	List<String[]> layoutLines; //array list stores layout cells
	String[] setupLines; //array stores setup lines

	//grabs/makes the board from the csv file

	/*
	 * variable and methods used for singleton pattern
	 */
	private static Board theInstance = new Board();
	private Board() { super(); } // constructor is private to ensure only one can be created
	public static Board getInstance() { return theInstance; } // this method returns the only Board

	/*
	 * initialize the board (since we are using singleton pattern)
	 */
	public void initialize() { 
		roomMap = new HashMap<Character, Room>(); //initilizes room map  
		boardCell = new BoardCell();

		//load setup and layout
		try {
			loadSetupConfig();
			loadLayoutConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setAdj(); //calls adjacencies
	}

	//calculates cell adjacencies
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
				setupLines = in.nextLine().split(", "); //adds split line to array

				//check if line is a comment
				if (!setupLines[0].contains("//")) {
					//If an entry in either file does not have the proper format.
					if (!(setupLines[0].equals("Room") || setupLines[0].equals("Space"))) throw new BadConfigFormatException();
					Room room = new Room();
					room.setName(setupLines[1]); //sets room name
					roomMap.put(setupLines[2].charAt(0), room); //adds room name and initial to map
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
			String[] nextCell; //stores layout lines
			//read once to see how many rows and cols 
			//and then read 2nd time to store cell information in grid[][]
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
			setGridSize(); //sets grid size
		}

		private void setGridSize() {
			numRows = layoutLines.size(); //set row size
			numColumns = layoutLines.get(0).length; //set column size
			grid = new BoardCell[numRows][numColumns]; //set grid size
			setBoardCells(); //sets board cells
		}

		private void setBoardCells() {
			//local variables
			char initial, roomCenter, label, secretPassage;
			DoorDirection doorDirection;
			//identifies if cell is center, label, door (which way), or secret passage
			//adds each cell to grid
			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numColumns; j++) {   			 
					grid[i][j] = new BoardCell(i,j); //sets board sells
					initial = layoutLines.get(i)[j].charAt(0); //gets char from cell
					grid[i][j].setInitial(initial); //set cell initial
					grid[i][j].setDoorDirection(DoorDirection.NONE); //set all cells to initial no door

					//If the board layout refers to a room that is not in your setup file.
					//If the board layout file does not have the same number of columns in every row.
					//if(layoutLines.get(i)[j].charAt(0) == 0) throw new BadConfigFormatException();
					//if(!roomMap.containsKey(layoutLines.get(i)[j].charAt(0))) throw new BadConfigFormatException();

					if(layoutLines.get(i)[j].length() == 2) {
						if(layoutLines.get(i)[j].charAt(1) == '*') {
							grid[i][j].setRoomCenter();
							roomCenter = grid[i][j].getInitial();
							roomMap.get(roomCenter).setCenterCell(grid[i][j]);
						}
						else if(layoutLines.get(i)[j].charAt(1) == '#') {
							grid[i][j].setLabel();
							label = grid[i][j].getInitial();
							roomMap.get(label).setLabelCell(grid[i][j]);
						}
						else if(layoutLines.get(i)[j].charAt(1) == '^') {
							doorDirection = DoorDirection.UP;
							grid[i][j].setDoorDirection(doorDirection);
							grid[i][j].isDoorway();
						}
						else if(layoutLines.get(i)[j].charAt(1) == '>') {
							doorDirection = DoorDirection.RIGHT;
							grid[i][j].setDoorDirection(doorDirection);
							grid[i][j].isDoorway();
						}
						else if(layoutLines.get(i)[j].charAt(1) == '<') {
							doorDirection = DoorDirection.LEFT;
							grid[i][j].setDoorDirection(doorDirection);
							grid[i][j].isDoorway();

						}
						else if(layoutLines.get(i)[j].charAt(1) == 'v') {
							doorDirection = DoorDirection.DOWN;
							grid[i][j].setDoorDirection(doorDirection);
							grid[i][j].isDoorway();
						}
						else {
							secretPassage = layoutLines.get(i)[j].charAt(1);
							grid[i][j].setSecretPassage(secretPassage);
						}
					}
				}
			}
		}

		public BoardCell getCell(int row, int col) { return grid[row][col]; } //returns the cell from the board at row, col
		public int getNumRows() { return numRows; } //returns board row size
		public int getNumColumns() { return numColumns; } //returns board column size
		public Room getRoom(char room) { return roomMap.get(room); } //returns room at char
		public Room getRoom(BoardCell cell) { return roomMap.get(cell.getInitial()); } //return room at cell 
	}
