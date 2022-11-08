package clueGame;

import java.awt.Color;
import java.util.*;


public class ComputerPlayer extends Player {

	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
	}

	//Given a room, the computer player will create a suggestion composed of that room, 
	//a weapon and player from those cards the computer player has not seen.
	public Solution createSuggestion(ArrayList<Card> roomDeck, ArrayList<Card> personDeck, ArrayList<Card> weaponDeck) { 
		
		Card roomSuggestion = null, personSuggestion = null, weaponSuggestion = null;
		
//		for (int i = 0; i < getSeenCards().size(); i++) {
//			for (int j = 0; j < roomDeck.size())
//			if (!getSeenCards().contains(room)) {
//				roomSuggestion = room;
//			}
//			if (!getSeenCards().contains(person)) {
//				personSuggestion = person;
//			}
//			if (!getSeenCards().contains(weapon)) {
//				weaponSuggestion = weapon;
//			}
//		}
		return new Solution(roomSuggestion, personSuggestion, weaponSuggestion); 
	}
	

	//returns soln
	// need current location (if room then make suggestion) (grid[row][col]getRoom)
	//needs the seen cards
	//needs the other cards and uses seen cards to make a decision
	
	
	// This is another simplified (i.e. read brain dead) AI, in which the computer 
	//player selects that location he or she wishes to move to from the target list. 
	public BoardCell selectTarget() { 
		
		return null; 
	}

}
