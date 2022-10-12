package clueGame;

import java.io.*;

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
	private int numColumns = 300; //column size
	private int numRows = 300; //row size
	private String layoutConfigFile; //board layout
	private String setupConfigFile; //board setup
	private Map<Character, Room> roomMap; //map for board rooms
	
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
    	 grid = new BoardCell[numRows][numColumns]; //set grid size
    	 roomMap = new HashMap<Character, Room>(); //initilizes room map
    	 
    	//for loop that creates grid at rows and cols
    	 for (int i = 0; i < numRows; i++) {
    		 for (int j = 0; j < numColumns; j++) {
    			 grid[i][j] = new BoardCell(i, j);
    		 }
    	 }
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
 		in.useDelimiter(", ");

 		while(in.hasNext()) {
 			if (in.next() == "Room") in.next();
 			System.out.println(in.next());
 		} in.close(); //close file after reading
     }
     
     //loads board layout 
     public void loadLayoutConfig() throws FileNotFoundException {
     	//read setup file
     	FileReader reader = new FileReader(layoutConfigFile); //reads file
  		Scanner in = new Scanner(reader);
  		in.useDelimiter(",");

  		while(in.hasNext()) {
  			in.next();
  		} in.close(); //close file after reading
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
