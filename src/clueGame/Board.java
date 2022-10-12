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
    	 try {
			loadLayoutConfig();
			loadSetupConfig();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 //set grid size
    	 roomMap = new HashMap<Character, Room>(); //initilizes room map
    	 
    	//for loop that creates grid at rows and cols
    	 
    	 
    	 //adds 4 adjacencies of rows and cols
    	 for (int i = 0; i < numRows; i++) {
    		 for (int j = 0; j < numColumns; j++) {
    			 if ((i-1) >= 0) grid[i][j].addAdj(getCell(i-1, j)); //adjacency in y-1 direction
    			 if ((j-1) >= 0) grid[i][j].addAdj(getCell(i, j-1)); //adjacency in x-1 direction
    			 if ((i+1) < numRows) grid[i][j].addAdj(getCell(i+1, j)); //adjacency in y+1 direction
    			 if ((j+1) < numColumns) grid[i][j].addAdj(getCell(i, j+1)); //adjacency in x+1 direction
    		 }
    	 }
     } 
     
     //loads board setup 
     public void loadSetupConfig() throws FileNotFoundException {
    	//read setup file
    	FileReader reader = new FileReader(setupConfigFile); //reads file
 		Scanner in = new Scanner(reader);
 		
 		
 		while(in.hasNextLine()) {
 			
 			String[] lines = in.nextLine().split(","); //grabs a line from txt, its already spilt
 			//next grab all the data, then put that data inside the map, make sure not to grab the comments 
 			if (lines[0] == "Room") {}
 			
 		} in.close(); //close file after reading
     }
     
     //loads board layout 
     public void loadLayoutConfig() throws FileNotFoundException {
    	 
    	FileReader reader = new FileReader(layoutConfigFile); //reads file
 		Scanner in = new Scanner(reader);
 	
 		in.useDelimiter(",");
 	 
 		List<String[]> lines = new ArrayList<String[]>();
 		
 		while(in.hasNextLine()) {
 			lines.add(in.nextLine().split(","));
 			}
 		
 		in.close();
 		numRows = lines.size();
		numColumns = lines.get(0).length;
		
		 grid = new BoardCell[numRows][numColumns];
		
		for (int i = 0; i < numRows; i++) {
   		 for (int j = 0; j < numColumns; j++) {   			 
   			 grid[i][j] = new BoardCell(i,j,lines.get(i)[j].charAt(0));
   			 if(lines.get(i)[j].charAt(0) == 2) {
   				 if(lines.get(i)[j].charAt(1) == '*') {
   					 grid[i][j].isRoomCenter();
   				 }
   				 else if(lines.get(i)[j].charAt(1) == '#') {
   					 grid[i][j].isLabel();
   				 }
   				else if(lines.get(i)[j].charAt(1) == '^') {
  					 grid[i][j].setDoorDirection(DoorDirection.UP);
  				 }
   				else if(lines.get(i)[j].charAt(1) == '>') {
 					 grid[i][j].setDoorDirection(DoorDirection.RIGHT);
   				}
   				else if(lines.get(i)[j].charAt(1) == '<') {
 					 grid[i][j].setDoorDirection(DoorDirection.LEFT);
   				}
   				else if(lines.get(i)[j].charAt(1) == 'v') {
					 grid[i][j].setDoorDirection(DoorDirection.DOWN);
  				}
   				 else {
   					grid[i][j].setSecretPassage(lines.get(i)[j].charAt(1));
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

	public BoardCell getCell(int row, int col) { return grid[row][col]; } //returns the cell from the board at row, col
	public int getNumRows() { return numRows; } //returns board row size
	public int getNumColumns() { return numColumns; } //returns board column size
	public Room getRoom(char room) { return roomMap.get(room); } //returns room at char
	public Room getRoom(BoardCell cell) { return new Room(); } //return room at cell
}
