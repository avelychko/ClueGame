package experiment;

import java.util.*;

public class TestBoard {
	private TestBoardCell[][] grid;
	private Set<TestBoardCell> targets;
	private Set<TestBoardCell> visited;
	final static int COLS = 4;
	final static int ROWS = 4;

	//A constructor probably empty.
	public TestBoard() {
		grid = new TestBoardCell[COLS][ROWS];
		targets = new HashSet<TestBoardCell>();
		visited = new HashSet<TestBoardCell>();
	};

	//void calcTargets(TestBoardCell startCell, int pathlength) 
	//calculates legal targets for a move from startCell of length pathlength.
	public void calcTargets(TestBoardCell startCell, int pathlength) {
		//targets.add(startCell);
	}

	//TestBoardCell getCell( int row, int col ) returns the cell from the board at row, col.
	public TestBoardCell getCell(int row, int col) {
		return new TestBoardCell();
		//return grid[row][col];
	}

	//Set<TestBoardCell> getTargets() gets the targets last created by calcTargets()
	public Set<TestBoardCell> getTargets() {
		return targets;
	}
}