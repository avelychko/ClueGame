package clueGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Suggestion extends JDialog{
	JComboBox<String> personBox, weaponBox;
	JTextField roomBox;
	JLabel roomLabel, personLabel, weaponLabel;
	JButton submitButton, cancelButton;
	Board board;
	GameControlPanel gameControlPanel;
	
	public Suggestion() {
		setTitle("Make a Suggestion");
		setSize(260, 150);
		setLayout(new GridLayout(4, 2));
		board = Board.getInstance();
		gameControlPanel = new GameControlPanel();
		
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
		
		cancelButton.addActionListener(e -> setVisible(false));
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				gameControlPanel.setGuess(roomBox.getText() + ", " + personBox.getSelectedItem().toString() + ", " + weaponBox.getSelectedItem().toString());
				setVisible(false);
			}
		});
		
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
		roomBox.setText(board.getRoomCard().getCardName());
	}
}
