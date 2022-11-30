package clueGame;

import java.awt.*;
import javax.swing.*;

public class Suggestion extends JDialog{
	JComboBox<String> personBox, weaponBox;
	JTextField roomBox;
	JLabel roomLabel, personLabel, weaponLabel;
	JButton submitButton, cancelButton;
	Board board;
	
	public Suggestion() {
		setTitle("Make a Suggestion");
		setSize(260, 150);
		setLayout(new GridLayout(4, 2));
		board = Board.getInstance();
		
		roomLabel = new JLabel("Room");
		personLabel = new JLabel("Person");
		weaponLabel = new JLabel("Weapon");
		
		roomBox = new JTextField();
		personBox = new JComboBox<String>();
		weaponBox = new JComboBox<String>();
		
		submitButton = new JButton("Submit");
		cancelButton = new JButton("Cancel");
		
		roomBox.setEditable(false);
		
		addRoomCard();
		addPersonCard();
		addWeaponCard();
		
		add(roomLabel);
		add(roomBox);
		add(personLabel);
		add(personBox);
		add(weaponLabel);
		add(weaponBox);
		
		submitButton.addActionListener(e -> setVisible(false));
		cancelButton.addActionListener(e -> setVisible(false));
		
		add(submitButton);
		add(cancelButton);
	}

	private void addWeaponCard() {
		for (int i = 0; i < board.getWeaponDeck().size(); i++) {
			weaponBox.addItem(board.getWeaponDeck().get(i).getCardName());
		}
	}

	private void addPersonCard() {
		for (int i = 0; i < board.getPersonDeck().size(); i++) {
			personBox.addItem(board.getPersonDeck().get(i).getCardName());
		}
	}

	private void addRoomCard() {
		for (int i = 0; i < board.getPlayer().size(); i++) {
			int row = board.getPlayer().get(i).getRow(), col = board.getPlayer().get(i).getCol();
			BoardCell cell = board.getCell(row, col);
			boolean isRoom = !(cell.getWalkway() || cell.getUnused());
			if (isRoom) {
				roomBox.setText(board.getCard(board.getRoom(cell).getName(), CardType.ROOM).getCardName());
			}
		}
	}
}
