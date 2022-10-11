package clueGame;

import java.util.*;

public class BoardCell {
	private int row, col; //row and col variables
	private char initial;
	private DoorDirection doorDirection;
	private boolean roomLabel;
	private boolean roomCenter;
	private char SecretPassage;
	Set<BoardCell> adjList = new HashSet<BoardCell>(); //initialized adjList set
	
	//constructor
	public BoardCell() { adjList = new HashSet<BoardCell>(); }
	
	//a constructor that has passed into it the row and column for that cell
	public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
	}

	//a setter to add a cell to this cells adjacency list
	public void addAdj(BoardCell adj) {
		this.adjList.add(adj);
	}

	//returns the adjacency list for the cell
	public Set<BoardCell> getAdjList() {
		return adjList;
	}
}
