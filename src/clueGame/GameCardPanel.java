package clueGame;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.*;

public class GameCardPanel extends JPanel {
	JPanel cardPanel, peoplePanel, roomsPanel, weaponsPanel;
	
	public GameCardPanel() {
		cardPanel = new JPanel(); //create card panel
		cardPanel.setLayout(new GridLayout(3,1)); //create grid for main panel
		//set Card panel size
		cardPanel.setSize(new Dimension(160, 700));
		cardPanel.setBorder(new TitledBorder (new EtchedBorder(), "Known Cards")); //create border and title for panel
		add(cardPanel); //add card panel to main panel
		//create each type card panels
		peoplePanel = new JPanel();
		roomsPanel = new JPanel();
		weaponsPanel = new JPanel();
		//add each card type panels to card panel
		cardPanel.add(peoplePanel);
		cardPanel.add(roomsPanel);
		cardPanel.add(weaponsPanel);
	}
	
	private void setPeoplePanel(ArrayList<Card> hand, Set<Card> seenCards) {
		peoplePanel.setBorder(new TitledBorder (new EtchedBorder(), "People")); //create title and border for people panel
		peoplePanel.setLayout(new GridLayout(4,1)); //create grid for people panel
		
		JLabel handLabel = new JLabel("In Hand:"); //create label
		JTextField handText = new JTextField();
		peoplePanel.add(handLabel);
		
		//put each card of type person in text field
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getCardType() == CardType.PERSON) {
				handText.setText(hand.get(i).getCardName());
				peoplePanel.add(handText); //text box colors??
			}
		}
		
		if (handText.getText().isEmpty()) handText.setText("None");
		peoplePanel.add(handText);
		
		JLabel seenLabel = new JLabel("Seen:");
		JTextField seenText = new JTextField();
		peoplePanel.add(seenLabel);
		
		for (Card s: seenCards) {
			if (s.getCardType() == CardType.PERSON) {
				seenText.setText(s.getCardName());
				peoplePanel.add(seenText);
			}
		}

		if (seenText.getText().isEmpty()) seenText.setText("None");
		peoplePanel.add(seenText);
	}
	
	private void setRoomsPanel(ArrayList<Card> hand, Set<Card> seenCards) {
		roomsPanel.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
		roomsPanel.setLayout(new GridLayout(4,1));
		
		JLabel handLabel = new JLabel("In Hand:");
		JTextField handText = new JTextField();
		roomsPanel.add(handLabel);
		
		//put each card of type room in text field
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getCardType() == CardType.ROOM) {
				handText.setText(hand.get(i).getCardName());
				roomsPanel.add(handText);
			}
		}
		
		if (handText.getText().isEmpty()) handText.setText("None");
		roomsPanel.add(handText);
		
		JLabel seenLabel = new JLabel("Seen:");
		JTextField seenText = new JTextField();
		roomsPanel.add(seenLabel);
		
		for (Card s: seenCards) {
			if (s.getCardType() == CardType.ROOM) {
				seenText.setText(s.getCardName());
				roomsPanel.add(seenText);
			}
		}
		
		if (seenText.getText().isEmpty()) seenText.setText("None");
		roomsPanel.add(seenText);
	}
	
	private void setWeaponsPanel(ArrayList<Card> hand, Set<Card> seenCards) {
		weaponsPanel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		weaponsPanel.setLayout(new GridLayout(4,1));
		
		JLabel handLabel = new JLabel("In Hand:");
		JTextField handText = new JTextField();
		weaponsPanel.add(handLabel);
		
		//put each card of type weapon in text field
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getCardType() == CardType.WEAPON) {
				handText.setText(hand.get(i).getCardName());
				weaponsPanel.add(handText);
			}
		}
		
		if (handText.getText().isEmpty()) handText.setText("None");
		weaponsPanel.add(handText);
		
		JLabel seenLabel = new JLabel("Seen:");
		JTextField seenText = new JTextField();
		weaponsPanel.add(seenLabel);
		
		for (Card s: seenCards) {
			if (s.getCardType() == CardType.WEAPON) {
				seenText.setText(s.getCardName());
				weaponsPanel.add(seenText);
			}
		}

		if (seenText.getText().isEmpty()) seenText.setText("None");
		weaponsPanel.add(seenText);
	}
	
	public static void main(String[] args) {
		GameCardPanel panel = new GameCardPanel();  // create the panel
		JFrame frame = new JFrame();  // create the frame 
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(180, 750);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
		
		ArrayList<Card> handStart = new ArrayList<Card>();
		Set<Card> seenCardsStart = new HashSet<Card>();
		
		handStart.add(new Card("Benjamin Siegel", CardType.PERSON));
		handStart.add(new Card("Eddie McGrath", CardType.PERSON));
		handStart.add(new Card("Katana", CardType.WEAPON));
		
		ArrayList<Card> handDuring = new ArrayList<Card>();
		Set<Card> seenCardsDuring = new HashSet<Card>();
		
		handDuring.add(new Card("Al Capone", CardType.PERSON));
		handDuring.add(new Card("Bedroom", CardType.ROOM));
		handDuring.add(new Card("Garden", CardType.ROOM));
		
		seenCardsDuring.add(new Card("Benjamin Siegel", CardType.PERSON));
		seenCardsDuring.add(new Card("Eddie McGrath", CardType.PERSON));
		seenCardsDuring.add(new Card("Katana", CardType.WEAPON));
		seenCardsDuring.add(new Card("Pistol", CardType.WEAPON));
		seenCardsDuring.add(new Card("Pool", CardType.ROOM));
		seenCardsDuring.add(new Card("Wire", CardType.WEAPON));
		
		// test filling in the data
		panel.setPeoplePanel(handStart, seenCardsStart);
		panel.setRoomsPanel(handStart, seenCardsStart);
		panel.setWeaponsPanel(handStart, seenCardsStart);
		
		//check both windows
//		panel.setPeoplePanel(handDuring, seenCardsDuring);
//		panel.setRoomsPanel(handDuring, seenCardsDuring);
//		panel.setWeaponsPanel(handDuring, seenCardsDuring);
	}
}
