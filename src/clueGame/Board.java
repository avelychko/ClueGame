package clueGame;

import java.util.*;

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
     public void initialize() { grid = new BoardCell[numRows][numColumns]; } //set grid size
     
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
}
