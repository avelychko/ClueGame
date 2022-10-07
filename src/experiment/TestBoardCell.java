package experiment;

import java.util.Set;

public class TestBoardCell {
	private int row;
	private int column;
	
	//A constructor that has passed into it the row and column for that cell
	public TestBoardCell(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	//A setter to add a cell to this cells adjacency list, void addAdjacency( TestBoardCell cell )
	public void addAdjacecy(TestBoard cell) {
		
	}
	
	//Set<TestBoardCell> getAdjList() – returns the adjacency list for the cell
	Set<TestBoardCell> getAdjList() {
		return null;
	}
	
	//A setter (and perhaps a getter) for indicating a cell is part of a room (void setRoom(boolean) and perhaps boolean isRoom())
	void setRoom(boolean bool) {
		
	}
	
	boolean isRoom() {
		return false;
		
	}
	
	//A setter and perhaps a getter for indicating a cell is occupied by another player (void setOccupied(boolean), boolean getOccupied()).
	void setOccupied(boolean bool) {
		
	}
	
	boolean getOccupied() {
		return false;
		
	}
}
