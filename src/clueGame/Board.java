package clueGame;

import java.util.*;

//import experiment.TestBoardCell;

public class Board {
	private BoardCell[][] grid; //2D array of object TestBoardCell
//	private Set<BoardCell> targets; //set of target cells
//	private Set<BoardCell> visited; //set of visited cells
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
     public void initialize()
     {
 		grid = new BoardCell[numRows][numColumns]; //set grid at size ROWS and COLS
// 		targets = new HashSet<BoardCell>(); //initialize targets
// 		visited = new HashSet<BoardCell>(); //initialize visited

 		//for loop that creates grid at rows and cols
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

//	//calculates legal targets for a move from startCell of length pathlength
//	public void calcTargets(BoardCell startCell, int pathlength) {
//		visited.add(startCell); //adds visited cell to visited set
//		//for loop for each adjacency cell
//		for (BoardCell adjCell: startCell.adjList) {
//			//checks if adjacency cell has already been visited	
//			if (visited.contains(adjCell) == false) {
//				//checks if cell is occupied
//				if (adjCell.getOccupied() == false) {
//					if (pathlength == 1) targets.add(adjCell); //checks if length is 1 then add adj cell to targets set
//					if (adjCell.getRoom() == true) targets.add(adjCell); //if is room add adj cell to targets
//					else calcTargets(adjCell, pathlength - 1);  //else call adj cell recursively
//					visited.remove(adjCell); //remove visited adj cell
//				}
//			}
//		}
//	}

	//returns the cell from the board at row, col
	public BoardCell getCell(int row, int col) {
		return grid[row][col];
	}

	//gets the targets last created by calcTargets()
	public Set<BoardCell> getTargets() {
		return targets;
	}

}
