package experiment;

import java.util.*;

public class TestBoard {
	private TestBoardCell[][] grid;
	private Set<TestBoardCell> targets;
	private Set<TestBoardCell> visited;
	final static int COLS = 4;
	final static int ROWS = 4;
	TestBoardCell cell = new TestBoardCell(); 

	//A constructor probably empty.
	public TestBoard() {
		grid = new TestBoardCell[ROWS][COLS];
		targets = new HashSet<TestBoardCell>();
		visited = new HashSet<TestBoardCell>();
		cell = new TestBoardCell();
		
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				grid[i][j] = new TestBoardCell(i, j);
			}
		}
		

		cell.addAdjacency(getCell(0, 1));
		cell.addAdjacency(getCell(1, 0));
		
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {

				if ((i-1) >= 0) cell.addAdjacency(getCell(i-1, j));
				if ((j-1) >= 0) cell.addAdjacency(getCell(i, j-1));
				if ((i+1) <= ROWS) cell.addAdjacency(getCell(i+1, j));
				if ((j+1) <= COLS) cell.addAdjacency(getCell(i, j+1));
			}
		}
				if ((i-1) >= 0)
				    cell.addAdjacency(getCell(i+1, j));
					cell.addAdjacency(getCell(i, j+1));
			}
		// Make all the adjacencies for the cells
		// for i in rows, 
		//	 for j in cols
		//		find the 1-4 adjacencies for each cell
	};

	//void calcTargets(TestBoardCell startCell, int pathlength) 
	//calculates legal targets for a move from startCell of length pathlength.
	public void calcTargets(TestBoardCell startCell, int pathlength) {
		visited.add(startCell);
		if (pathlength == 1) targets.add(startCell);
		else calcTargets(startCell, pathlength - 1);
	}

	//TestBoardCell getCell( int row, int col ) returns the cell from the board at row, col.
	public TestBoardCell getCell(int row, int col) {
		//return grid[row][col];
		return new TestBoardCell(row, col);
	}

	//Set<TestBoardCell> getTargets() gets the targets last created by calcTargets()
	public Set<TestBoardCell> getTargets() {
		return targets;
	}
}