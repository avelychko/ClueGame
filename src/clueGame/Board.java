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

import java.util.*;

public class Board {
	private BoardCell[][] grid; //2D array of object TestBoardCell
	private int numColumns; //column size
	private int numRows; //row size
	private String layoutConfigFile; //board layout
	private String setupConfigFile; //board setup
	private Map<Character, Room> roomMap; //map for board rooms
	public BoardCell boardCell = new BoardCell();
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
		try {
			loadSetupConfig();
			loadLayoutConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
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
	public void loadSetupConfig() throws FileNotFoundException, BadConfigFormatException {
		FileReader reader = new FileReader(setupConfigFile); //reads file
		Scanner in = new Scanner(reader);

		while(in.hasNextLine()) {
			String[] lines = in.nextLine().split(", "); //grabs a line from txt, its already spilt

			//next grab all the data, then put that data inside the map, make sure not to grab the comments 
			if (!lines[0].contains("//")) {
				if (lines[0] != "Room" || lines[0] != "Space") {//throw new BadConfigFormatException();
					if (lines[0].equals("Room")) {
						Room room = new Room();
						room.setName(lines[1]);
						roomMap.put(lines[2].charAt(0), room);
				}
			}
			}
		} in.close(); //close file after reading
	}

	//loads board layout 
	public void loadLayoutConfig() throws FileNotFoundException{
		//read once to see how many rows and cols 
		//and then read 2nd time to store cell information in grid[][]

		FileReader reader = new FileReader(layoutConfigFile); //reads file
		Scanner in = new Scanner(reader);
		List<String[]> lines = new ArrayList<String[]>(); //holds file lines

		in.useDelimiter(","); //REMOVE?

		//reads each line and adds to array
		while(in.hasNextLine()) {
			lines.add(in.nextLine().split(","));
		}
		in.close(); //close file

		numRows = lines.size(); //set row size
		numColumns = lines.get(0).length; //set column size
		grid = new BoardCell[numRows][numColumns]; //set grid size

		//identifies if cell is center, label, door (which way), or secret passage
		//adds each cell to grid
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {   			 
				grid[i][j] = new BoardCell(i,j);
				boardCell.setInitial(lines.get(i)[j].charAt(0));

				grid[i][j].setDoorDirection(DoorDirection.NONE); //set all cells to initial no door

				//if(lines.get(i)[j].charAt(0) == 0) throw new BadConfigFormatException();
				//if(!roomMap.containsKey(lines.get(i)[j].charAt(0))) throw new BadConfigFormatException();

				if(lines.get(i)[j].length() == 2) {
					if(lines.get(i)[j].charAt(1) == '*') {
						grid[i][j].isRoomCenter();
					}
					else if(lines.get(i)[j].charAt(1) == '#') {
						grid[i][j].isLabel();
					}
					else if(lines.get(i)[j].charAt(1) == '^') {
						grid[i][j].isDoorway();
						grid[i][j].setDoorDirection(DoorDirection.UP);
					}
					else if(lines.get(i)[j].charAt(1) == '>') {
						grid[i][j].isDoorway();
						grid[i][j].setDoorDirection(DoorDirection.RIGHT);
					}
					else if(lines.get(i)[j].charAt(1) == '<') {
						grid[i][j].isDoorway();
						grid[i][j].setDoorDirection(DoorDirection.LEFT);
					}
					else if(lines.get(i)[j].charAt(1) == 'v') {
						grid[i][j].isDoorway();
						grid[i][j].setDoorDirection(DoorDirection.DOWN);
					}
					else {
						grid[i][j].setSecretPassage(lines.get(i)[j].charAt(1));
					}
				}
			}
		}
	}

	public BoardCell getCell(int row, int col) { return grid[row][col]; } //returns the cell from the board at row, col
	public int getNumRows() { return numRows; } //returns board row size
	public int getNumColumns() { return numColumns; } //returns board column size
	//CORRECT?
	public Room getRoom(char room) { return roomMap.get(room); } //returns room at char
	//CORRECT? 
	public Room getRoom(BoardCell cell) { return roomMap.get(cell.getInitial()); } //return room at cell 
}
