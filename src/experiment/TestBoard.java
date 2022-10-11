package experiment;

import java.util.*;

public class TestBoard {
	private TestBoardCell[][] grid;
	private Set<TestBoardCell> targets;
	private Set<TestBoardCell> visited;
	final static int COLS = 4;
	final static int ROWS = 4;
	TestBoardCell cell;

	//A constructor probably empty.
	public TestBoard() {
		grid = new TestBoardCell[ROWS][COLS];
		targets = new HashSet<TestBoardCell>();
		visited = new HashSet<TestBoardCell>();
		//cell = new TestBoardCell();
		
		//makes our grid
	
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				grid[i][j] = new TestBoardCell(i, j);
			}
		}
		
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if ((i-1) >= 0) grid[i][j].addAdjacency(getCell(i-1, j));
				if ((j-1) >= 0) grid[i][j].addAdjacency(getCell(i, j-1));
				if ((i+1) < ROWS) grid[i][j].addAdjacency(getCell(i+1, j));
				if ((j+1) < COLS) grid[i][j].addAdjacency(getCell(i, j+1));
			}
		}
	}

	//void calcTargets(TestBoardCell startCell, int pathlength) 
	//calculates legal targets for a move from startCell of length pathlength.
	public void calcTargets(TestBoardCell startCell, int pathlength) {
		visited.add(startCell);
		for (TestBoardCell adjCell: cell.adjList) {
			if (visited.contains(adjCell) == false) {
				if (pathlength == 1) targets.add(adjCell);
				else calcTargets(adjCell, pathlength - 1);
				visited.remove(adjCell);
			}
		}
	}

	//TestBoardCell getCell( int row, int col ) returns the cell from the board at row, col.
	public TestBoardCell getCell(int row, int col) {
		return grid[row][col];
	}

	//Set<TestBoardCell> getTargets() gets the targets last created by calcTargets()
	public Set<TestBoardCell> getTargets() {
		return targets;
	}
}