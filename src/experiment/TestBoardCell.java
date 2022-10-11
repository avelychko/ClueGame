package experiment;

import java.util.*;

public class TestBoardCell {
	private int row, col;
	private Boolean isRoom = false;
	private Boolean isOccupied = false;
	Set<TestBoardCell> adjList = new HashSet<TestBoardCell>();
	
	public TestBoardCell() { adjList = new HashSet<TestBoardCell>(); }
	
	//A constructor that has passed into it the row and column for that cell
	public TestBoardCell(int row, int col) {
		this.row = row;
		this.col = col;
	}

	//A setter to add a cell to this cells adjacency list, void addAdjacency( TestBoardCell cell )
	public void addAdjacency(TestBoardCell cell) {
		this.adjList.add(cell);
	}

	//Set<TestBoardCell> getAdjList() returns the adjacency list for the cell
	public Set<TestBoardCell> getAdjList() {
		return adjList;
	}

	//A setter (and perhaps a getter) for indicating a cell is part of a room 
	//(void setRoom(boolean) and perhaps boolean isRoom())
	public void setRoom(boolean bool) {
		this.isRoom = bool;
	}
	
	public boolean getRoom() {
		return isRoom;
	}

	//A setter and perhaps a getter for indicating a cell is occupied 
	//by another player (void setOccupied(boolean), boolean getOccupied()).
	public void setOccupied(boolean bool) {
		this.isOccupied = bool;
	}
	
	public boolean getOccupied() {
		return isOccupied;
	}
}