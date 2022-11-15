package clueGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * BoardCell Class
 * 
 * @author Anastasia Velyhko
 * @author Gordon Dina
 *
 */

import java.util.*;

public class BoardCell {
	private int row, col; 
	private char initial; 
	private DoorDirection doorDirection; 
	private boolean roomLabel; 
	private boolean roomCenter; 
	private char secretPassage = 0; 
	private boolean isRoom = true;
	private boolean isUnused = false;
	private boolean isWalkway = false;
	private Boolean isOccupied = false; 
	Set<BoardCell> adjList = new HashSet<BoardCell>(); 

	public BoardCell() { adjList = new HashSet<BoardCell>(); } 

	//a constructor that has passed into it the row and column for that cell
	public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public void drawCell(Graphics cell) {
		Graphics2D other = (Graphics2D) cell;
		Stroke stroke1 = new BasicStroke(3);
		
		cell.setColor(Color.blue);
		cell.fillRect(2, 1, 49, 49);
		other.setColor(Color.BLACK);
		other.setStroke(stroke1);

		other.drawRect(0, 1, 50, 50);
		
		
	
	}
	
	//adjList setter and getter
	public void addAdj(BoardCell adj) { this.adjList.add(adj); } 
	public Set<BoardCell> grabAdjList() { return adjList; } 

	//initial setter and getter
	public char getInitial() { return initial; } 
	public void setInitial(char initial) { this.initial = initial; } 
	
	//label setter and getter
	public boolean isLabel() { return roomLabel; } 
	public void setLabel() { roomLabel = true; } 
	
	//roomCenter setter and getter
	public boolean isRoomCenter() { return roomCenter; } 
	public void setRoomCenter() { roomCenter = true; } 
	
	//secretPassage setter and getter
	public char getSecretPassage() { return secretPassage; } 
	public void setSecretPassage(char secretPassage) { this.secretPassage = secretPassage; } 
	
	//doorDirection setter and getter
	public DoorDirection getDoorDirection() { return doorDirection; } 
	public void setDoorDirection(DoorDirection doorDirection) { this.doorDirection = doorDirection; } 
	//checks if cell is doorway
	public boolean isDoorway() { 
		if (getDoorDirection() == doorDirection.NONE) return false; 
		return true;
	} 
	
	//isOccupied setter and getter
	public void setOccupied(boolean bool) { this.isOccupied = bool; } 
	public boolean getOccupied() { return this.isOccupied; } 
	
	//isRoom setter and getter
	public boolean getRoom() { return this.isRoom; }
	public void setRoom(boolean isRoom) { this.isRoom = isRoom; }
	
	//isUnused setter and getter
	public boolean getUnused() { return this.isUnused; }
	public void setUnused(boolean isUnused) { this.isUnused = isUnused; }
	
	//isWalkway setter and getter
	public boolean getWalkway() { return this.isWalkway; }
	public void setWalkway(boolean isWalkway) { this.isWalkway = isWalkway; }
}
