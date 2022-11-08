package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
	}
	
	//returns soln
	// need current location (if room then make suggestion) (grid[row][col]getRoom)
	//needs the seen cards
	//needs the other cards and uses seen cards to make a decision
	public Solution createSuggestion() {
		if (grid[row][col].getRoom() == false) {
			return null;
		}
		Solution suggestion = new Solution();
		
		return null; 
		}
	public BoardCell selectTarget() { return null; }
}
