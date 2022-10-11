package clueGame;

import java.util.HashSet;
import java.util.Set;

import experiment.TestBoardCell;

public class BoardCell {
	private int row, col; //row and col variables
	private Boolean isRoom = false; //isRoom boolean initialized to false
	private Boolean isOccupied = false; //isOccupied initialized to false
	Set<BoardCell> adjList = new HashSet<BoardCell>(); //initialized adjList set
	
	//constructor
	public BoardCell() { adjList = new HashSet<BoardCell>(); }
	
	//a constructor that has passed into it the row and column for that cell
	public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
	}

	//a setter to add a cell to this cells adjacency list
	public void addAdjacency(BoardCell cell) {
		this.adjList.add(cell);
	}

	//returns the adjacency list for the cell
	public Set<BoardCell> getAdjList() {
		return adjList;
	}

	//a setter for indicating a cell is part of a room 
	public void setRoom(boolean bool) {
		this.isRoom = bool;
	}
	
	//a getter for indicating a cell is part of a room 
	public boolean getRoom() {
		return this.isRoom;
	}

	//a setter for indicating a cell is occupied by another player
	public void setOccupied(boolean bool) {
		this.isOccupied = bool;
	}
	
	//a getter for indicating a cell is occupied by another player
	public boolean getOccupied() {
		return this.isOccupied;
	}

}
