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
	private Boolean isRoom = false; //isRoom boolean initialized to false
	private Boolean isOccupied = false; //isOccupied initialized to false

	public BoardCell() { adjList = new HashSet<BoardCell>(); } //constructor

	//a constructor that has passed into it the row and column for that cell
	public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public void addAdj(BoardCell adj) { this.adjList.add(adj); } //a setter to add a cell to this cells adjacency list
	public Set<BoardCell> grabAdjList() { return adjList; } //returns the adjacency list for the cell
	public DoorDirection getDoorDirection() { return doorDirection; } //returns direction of the door

	public char getInitial() { return initial; } //return initial
	public void setInitial(char initial) { this.initial = initial; } //set initial
	public boolean isLabel() { return roomLabel; } //checks if cell is label
	public void setLabel() { roomLabel = true; } //set room label
	public boolean isRoomCenter() { return roomCenter; } //checks if cell is room center
	public void setRoomCenter() { roomCenter = true; } //set room center
	public char getSecretPassage() { return secretPassage; } //returns secretPassage
	public void setSecretPassage(char secretPassage) { this.secretPassage = secretPassage; } //set secret passage
	//checks if cell is doorway
	public boolean isDoorway() { 
		if (getDoorDirection() == doorDirection.NONE) return false; 
		return true;
	} 
	public void setDoorDirection(DoorDirection doorDirection) { this.doorDirection = doorDirection; } //set door direction 
	public void setRoom(boolean bool) { this.isRoom = bool; } //a setter for indicating a cell is part of a room
	public boolean getRoom() { return this.isRoom; } //a getter for indicating a cell is part of a room
	public void setOccupied(boolean bool) { this.isOccupied = bool; } //a setter for indicating a cell is occupied by another player
	public boolean getOccupied() { return this.isOccupied; } //a getter for indicating a cell is occupied by another player
}
