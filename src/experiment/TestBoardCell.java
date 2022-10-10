package experiment;

import java.util.*;

public class TestBoardCell {
	private int row, col;
	private Boolean isRoom, isOccupied;
	Set<TestBoardCell> adjList;

	
	public TestBoardCell() { adjList = new HashSet<TestBoardCell>(); }
	
	//A constructor that has passed into it the row and column for that cell
	public TestBoardCell(int row, int col) {
		this.row = row;
		this.col = col;
	}

	//A setter to add a cell to this cells adjacency list, void addAdjacency( TestBoardCell cell )
	public void addAdjacency(TestBoardCell cell) {
<<<<<<< HEAD
		adjList.add(cell);
=======
		adjList.add(cell);	
>>>>>>> 744466d5b8bb572940ea0fa8d423ab1a473222b6
	}

	//Set<TestBoardCell> getAdjList() returns the adjacency list for the cell
	public Set<TestBoardCell> getAdjList() {
		return adjList;
	}

	//A setter (and perhaps a getter) for indicating a cell is part of a room 
	//(void setRoom(boolean) and perhaps boolean isRoom())
	public void setRoom(boolean bool) {
		isRoom = bool;
	}

	//A setter and perhaps a getter for indicating a cell is occupied 
	//by another player (void setOccupied(boolean), boolean getOccupied()).
	public void setOccupied(boolean bool) {
		isOccupied = bool;
	}
}