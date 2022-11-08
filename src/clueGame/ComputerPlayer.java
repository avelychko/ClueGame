package clueGame;

import java.awt.Color;
import java.util.*;

public class ComputerPlayer extends Player {
	private Board board = Board.getInstance();

	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
	}

	//Given a room, the computer player will create a suggestion composed of that room, 
	//a weapon and player from those cards the computer player has not seen.
	public Solution createSuggestion(ArrayList<Card> roomDeck, ArrayList<Card> personDeck, ArrayList<Card> weaponDeck) { 
		Card room = null, person = null, weapon = null;
		BoardCell cell = board.getCell(super.getRow(), super.getCol());
		//BoardCell cell = new BoardCell(super.getRow(), super.getCol());

		if (cell.isRoomCenter()) {
			while (getSeenCards().contains(room) || room == null) {
				Random randRoom = new Random();
				int randomIndexRoom = randRoom.nextInt(roomDeck.size());
				room = roomDeck.get(randomIndexRoom);
			} 

			while (getSeenCards().contains(person) || person == null) {
				Random randPerson = new Random();
				int randomIndexPerson = randPerson.nextInt(personDeck.size());
				person = personDeck.get(randomIndexPerson);
			}
			while (getSeenCards().contains(weapon) || weapon == null) {
				Random randPerson = new Random();
				int randomIndexPerson = randPerson.nextInt(personDeck.size());
				weapon = personDeck.get(randomIndexPerson);
			}
			return new Solution(room, person, weapon); 
		}

		return null;
	}


	// This is another simplified (i.e. read brain dead) AI, in which the computer 
	//player selects that location he or she wishes to move to from the target list. 
	public BoardCell selectTarget() { 

		return null; 
	}
}
