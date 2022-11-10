package clueGame;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.*;

public class GameCardPanel extends JPanel {
	JPanel peoplePanel, roomPanel, weaponPanel;
	
	public GameCardPanel() {
		setLayout(new GridLayout(3,0)); //create grid for main panel
		setBorder(new TitledBorder (new EtchedBorder(), "Known Cards")); //create border and title for panel
		//create each type card panels
		peoplePanel = new JPanel();
		roomPanel = new JPanel();
		weaponPanel = new JPanel();
		

		
		//title, border and grid for panels
		peoplePanel.setBorder(new TitledBorder (new EtchedBorder(), "People")); 
		peoplePanel.setLayout(new GridLayout(0, 1)); //create grid for people panel
		
		roomPanel.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
		roomPanel.setLayout(new GridLayout(0, 1));
		
		weaponPanel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		weaponPanel.setLayout(new GridLayout(0, 1));
		
//		//if text field is empty, set it to none
//		if (handText.getText().isEmpty()) handText.setText("None");
//		if (seenText.getText().isEmpty()) seenText.setText("None");		  
	}
	
//	private void setPeoplePanel(ArrayList<Card> hand, Set<Card> seenCards) {
//
//		
//
//		
//		
//		//put each card of type person in text field
//		for (int i = 0; i < hand.size(); i++) {
//			if (hand.get(i).getCardType() == CardType.PERSON) {
//				handText.setText(hand.get(i).getCardName());
//				peoplePanel.add(handText); //text box colors??
//			}
//		}
//		
//		if (handText.getText().isEmpty()) handText.setText("None");
//		
//		
//
//		
//		
//		for (Card s: seenCards) {
//			if (s.getCardType() == CardType.PERSON) {
//				seenText.setText(s.getCardName());
//				peoplePanel.add(seenText);
//			}
//		}
//
//		if (seenText.getText().isEmpty()) seenText.setText("None");
//		
//	}
//	
//	private void setRoomsPanel(ArrayList<Card> hand, Set<Card> seenCards) {
//		
//		//put each card of type room in text field
//		for (int i = 0; i < hand.size(); i++) {
//			if (hand.get(i).getCardType() == CardType.ROOM) {
//				handText.setText(hand.get(i).getCardName());
//				roomPanel.add(handText);
//			}
//		}
//		
//		if (handText.getText().isEmpty()) handText.setText("None");
//		roomPanel.add(handText);
//		
//		
//		for (Card s: seenCards) {
//			if (s.getCardType() == CardType.ROOM) {
//				seenText.setText(s.getCardName());
//				roomPanel.add(seenText);
//			}
//		}
//		
//		if (seenText.getText().isEmpty()) seenText.setText("None");
//		roomPanel.add(seenText);
//	}
//	
//	private void setWeaponsPanel(ArrayList<Card> hand, Set<Card> seenCards) {
//		
//		//put each card of type weapon in text field
//		for (int i = 0; i < hand.size(); i++) {
//			if (hand.get(i).getCardType() == CardType.WEAPON) {
//				handText.setText(hand.get(i).getCardName());
//				weaponPanel.add(handText);
//			}
//		}
//		
//		if (handText.getText().isEmpty()) handText.setText("None");
//		weaponPanel.add(handText);
//		
//		for (Card s: seenCards) {
//			if (s.getCardType() == CardType.WEAPON) {
//				seenText.setText(s.getCardName());
//				weaponPanel.add(seenText);
//			}
//		}
//
//		if (seenText.getText().isEmpty()) seenText.setText("None");
//		weaponPanel.add(seenText);
//	}
	
	public void updatePanels() {
		updatePanel(peoplePanel, Card.CardType.PERSON);
		updatePanel(roomPanel, Card.CardType.ROOM);
		updatePanel(weaponPanel, Card.CardType.WEAPON);
	}
	
	private void updatePanel(JPanel panel, Card card) {
		panel.removeAll();
		// add the fields to go into the panel using the updated seen card data
		add(panel);   // causes swing to either add or readd the entire panel and recalculate it
		
		JLabel handLabel = new JLabel("In Hand:"); //create hand label
		JTextField handText = new JTextField(); //create hand text field
		handText.setText(card.getCardName());
		
//		JLabel seenLabel = new JLabel("Seen:"); //create seen label
//		JTextField seenText = new JTextField(); //create seen text field
		
		panel.add(handLabel);
		panel.add(handText);
		panel.add(seenLabel);
		panel.add(seenText);
}

	public static void main(String[] args) {
		GameCardPanel panel = new GameCardPanel();  // create the panel
		JFrame frame = new JFrame();  // create the frame 
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(200, 400);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		
		
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
//		panel.setPeoplePanel(handStart, seenCardsStart);
//		panel.setRoomsPanel(handStart, seenCardsStart);
//		panel.setWeaponsPanel(handStart, seenCardsStart);
		
		//check both windows
//		panel.setPeoplePanel(handDuring, seenCardsDuring);
//		panel.setRoomsPanel(handDuring, seenCardsDuring);
//		panel.setWeaponsPanel(handDuring, seenCardsDuring);
		
		panel.updatePanels();
		
		frame.setVisible(true); // make it visible
	}
}
