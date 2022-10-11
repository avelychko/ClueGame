package clueGame;

import java.util.*;

import experiment.TestBoardCell;

public class Board {
	private BoardCell[][] grid; //2D array of object TestBoardCell
	private int numColumns; //column size
	private int numRows; //row size
	private String layoutConfigFile; 
	private String setupConfigFile;
	private Map<Character, Room> roomMap;
	
	 /*
     * variable and methods used for singleton pattern
     */
     private static Board theInstance = new Board();
     // constructor is private to ensure only one can be created
     private Board() {
            super() ;
     }
     // this method returns the only Board
     public static Board getInstance() {
            return theInstance;
     }
     
     /*
      * initialize the board (since we are using singleton pattern)
      */
     public void initialize() { 
    	 grid = new BoardCell[numRows][numColumns]; //set grid size
    	 
    	 for (int i = 0; i < numRows; i++) {
    		 for (int j = 0; j < numColumns; j++) {
    			 grid[i][j] = new BoardCell(i, j);
    		 }
    	 }
    	 //adds 4 adjacencies of rows and cols
    	 for (int i = 0; i < numRows; i++) {
    		 for (int j = 0; j < numColumns; j++) {
    			 if ((i-1) >= 0) grid[i][j].addAdj(getCell(i-1, j)); //adjacency in x-1 direction
    			 if ((j-1) >= 0) grid[i][j].addAdj(getCell(i, j-1)); //adjacency in y-1 direction
    			 if ((i+1) < numRows) grid[i][j].addAdj(getCell(i+1, j)); //adjacency in x+1 direction
    			 if ((j+1) < numColumns) grid[i][j].addAdj(getCell(i, j+1)); //adjacency in y+1 direction
    		 }
    	 }
     } 
     
     public void loadSetupConfig() {
    	 
     }
     
     public void loadLayoutConfig() {
    	 
     }
     
 	public void setConfigFiles(String layout, String setup) {
		this.layoutConfigFile = layout;
		this.setupConfigFile = setup;
	}

	//returns the cell from the board at row, col
	public BoardCell getCell(int row, int col) { return grid[row][col]; }
	
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	
	public Room getRoom(char room) {
		return getRoom(room);
	}

	public Room getRoom(BoardCell cell) {
		return getRoom(cell);
	}
}
