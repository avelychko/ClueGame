package experiment;

import java.util.*;

public class TestBoardCell {
	private int row;
	private int col;
	private Set<TestBoardCell> getAdjlist = new HashSet<TestBoardCell>();
	
	//A constructor that has passed into it the row and column for that cell
	public TestBoardCell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	//A setter to add a cell to this cells adjacency list, void addAdjacency( TestBoardCell cell )
	public void addAdjacecy(TestBoard cell) {
		getAdjlist.add(cell);
	}
	
	//Set<TestBoardCell> getAdjList() – returns the adjacency list for the cell
	public Set<TestBoardCell> getAdjList() {
		for (TestBoardCell cell : getAdjlist) return cell;
	}
	
	//A setter (and perhaps a getter) for indicating a cell is part of a room (void setRoom(boolean) and perhaps boolean isRoom())
	public void setRoom(boolean bool) {
		getAdjlist.contains(bool);
	}
	public boolean isRoom() {
		return true;
	}
	
	//A setter and perhaps a getter for indicating a cell is occupied by another player (void setOccupied(boolean), boolean getOccupied()).
	public void setOccupied(boolean bool) {
		
	}
	
	public boolean getOccupied() {
		return false;
	}
}
