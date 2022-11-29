package clueGame;

import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 * BoardCell Class
 * 
 * @author Anastasia Velyhko
 * @author Gordon Dina
 *
 */

public class BoardCell {
	private int row, col; 
	private int x, y;
	private int width, height;
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

	//draws cells 
	public void drawCell(Graphics cell) {
		if(getWalkway()) {
			//draw walkway cell
			cell.setColor(Color.lightGray); 
			cell.fillRect(x, y, width, height);
			
			//set cell outlines
			Graphics2D other = (Graphics2D) cell;
			Stroke stroke = new BasicStroke(1);
			cell.setColor(Color.black);
			other.setStroke(stroke);
			
			cell.drawRect(x, y, width, height);
			
			//draw doors
			if(isDoorway()) {
				switch (getDoorDirection()) {
				
				case UP: //draw up door
					Graphics2D lineUp = (Graphics2D) cell;
					Stroke strokeUp = new BasicStroke(4);
					lineUp.setColor(Color.black);
					lineUp.setStroke(strokeUp);
					lineUp.drawLine(x+2, y, x+width-2, y);
					break;
				case DOWN: //draw down door
					Graphics2D lineDown = (Graphics2D) cell;
					Stroke strokeDown = new BasicStroke(4);
					lineDown.setColor(Color.black);
					lineDown.setStroke(strokeDown);
					lineDown.drawLine(x+2, y+height-3, x+width-1, y+height-3);
					break;
				case LEFT: //draw left door
					Graphics2D lineLeft = (Graphics2D) cell;
					Stroke strokLeft = new BasicStroke(4);
					lineLeft.setColor(Color.black);
					lineLeft.setStroke(strokLeft);
					lineLeft.drawLine(x, y+1, x, y+height);
					break;
				case RIGHT: //draw right door
					Graphics2D lineRight = (Graphics2D) cell;
					Stroke strokeRight = new BasicStroke(4);
					lineRight.setColor(Color.black);
					lineRight.setStroke(strokeRight);
					lineRight.drawLine(x+width-2, y+1, x+width-2, y+height);
					break;
				default:
					//don't do anything
				}
			}
		}
		
		//draw unused cell
		if(getUnused()) {
			cell.setColor(new Color(204, 17, 0));
			cell.fillRect(x, y, width, height);
		}
		
		//draw rooms
		if (!(getWalkway() || getUnused())) {
			cell.setColor(new Color(118, 96, 138));
			cell.fillRect(x, y, width, height);
		}
	}

	//draws room at row and col
	public void drawName(Graphics g, int width, int height) {
		Font font = new Font("Century Gothic", Font.BOLD, 15);
		g.setFont(font);
		g.setColor(new Color(204, 17, 0));
		g.drawString(Board.getInstance().getRoom(initial).getName(), this.col * width, this.row * height + height);
	}
	
	public boolean containsClick(int mouseX, int mouseY) {
		Rectangle rect = new Rectangle(x, y, width, height);
		if (rect.contains(new Point(mouseX, mouseY))) 
			return true;
		return false;
	}
	
	//cell location on board
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public int getX() { return x; }
	public int getY() { return y; }
	
	public void setWidth(int width) { this.width = width; }
	public void setHeight(int height) { this.height = height; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public int getRow() { return row; }
	public int getCol() { return col; }

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
