package clueGame;

/**
 * BoardCell Class
 * 
 * @author Anastasia Velyhko
 * @author Gordon Dina
 *
 */

import java.util.*;

public class BoardCell {
	private int row, col; //row and col var
	private char initial; //initial var
	private DoorDirection doorDirection; //doorDirection enum objects
	private boolean roomLabel; //roomLabel bool
	private boolean roomCenter; //roomCenter bool
	private char secretPassage; //secretPassage char
	Set<BoardCell> adjList = new HashSet<BoardCell>(); //initialized adjList set

	public BoardCell() { adjList = new HashSet<BoardCell>(); } //constructor
	
	//a constructor that has passed into it the row and column for that cell
	public BoardCell(int row, int col, char initial) {
		this.row = row;
		this.col = col;
		this.initial = initial;
	}

	public void addAdj(BoardCell adj) { this.adjList.add(adj); } //a setter to add a cell to this cells adjacency list
	public Set<BoardCell> getAdjList() { return adjList; } //returns the adjacency list for the cell
	public DoorDirection getDoorDirection() { return doorDirection; } //returns direction of the door
	
	//checks if cell is doorway
	public boolean isDoorway() { 
		if (getDoorDirection() == doorDirection.NONE) return false; 
		return true;
	} 
	
	public boolean isLabel() { return roomLabel; } //checks if cell is label
	public boolean isRoomCenter() { return roomCenter; } //checks if cell is room center
	public char getSecretPassage() { return secretPassage; } //returns secretPassage

	public void setSecretPassage(char secretPassage) {
		this.secretPassage = secretPassage;
	}

	public void setDoorDirection(DoorDirection doorDirection) {
		this.doorDirection = doorDirection;
	}
	
}
