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
	JTextField generalText;
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	public GameCardPanel() {

		setLayout(new GridLayout(3,1)); //create grid for main panel
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
	
	/*public void updatePanels() {
		updatePanel(peoplePanel, CardType.PERSON);
		updatePanel(roomPanel, CardType.ROOM);
		updatePanel(weaponPanel, CardType.WEAPON);
		//updatePanel(peoplePanel);
		//updatePanel(roomPanel);
		//updatePanel(weaponPanel);
	}
	//Card card
	private void updatePanel(JPanel panel, CardType card) {
		panel.removeAll();
		// add the fields to go into the panel using the updated seen card data
		add(panel);   // causes swing to either add or readd the entire panel and recalculate it
		
		JLabel handLabel = new JLabel("In Hand:"); //create hand label
		JTextField handText = new JTextField(); //create hand text field
		//handText.setText(card.getCardName());
		
		JLabel seenLabel = new JLabel("Seen:"); //create seen label
		JTextField seenText = new JTextField(); //create seen text field
		
		panel.add(handLabel);
		
		panel.add(handText);
		
		
		panel.add(seenLabel);
		panel.add(seenText);
}*/
	
	private void peoplePanel(Player human) {
		
		peoplePanel.removeAll();
		// add the fields to go into the panel using the updated seen card data
		add(peoplePanel);   // causes swing to either add or readd the entire panel and recalculate it
		
		JLabel handLabel = new JLabel("In Hand:"); //create hand label
		//JTextField handText = new JTextField(); //create hand text field
		
		JLabel seenLabel = new JLabel("Seen:"); //create seen label
		//JTextField seenText = new JTextField(); //create seen text field
		
		peoplePanel.add(handLabel);
		
		boolean contains = false;
		for (int i = 0; i < human.getPlayerCards().size(); i++) {
			JTextField handText = new JTextField();
			if (human.getPlayerCards().get(i).getCardType() == CardType.PERSON) {
				handText.setText(human.getPlayerCards().get(i).getCardName());
				peoplePanel.add(handText); //text box colors??
				contains = true;
			}	
		}
			
		
		if (!contains) {
			JTextField none = new JTextField();
			none.setText("None");
			peoplePanel.add(none);
		}
		
		peoplePanel.add(seenLabel);
		
		contains = false;
		
		for (Card s: human.getSeenCards()) {
			JTextField seenText = new JTextField();
			if (s.getCardType() == CardType.PERSON) {
				seenText.setText(s.getCardName());
				peoplePanel.add(seenText);
				contains = true;
			}
			
		}

		if (!contains) {
			JTextField seenText = new JTextField();
			seenText.setText("None");
			peoplePanel.add(seenText);
		}
		
	}
	
	private void roomPanel(Player human) {
		
		roomPanel.removeAll();
		// add the fields to go into the panel using the updated seen card data
		add(roomPanel);   // causes swing to either add or readd the entire panel and recalculate it
		
		JLabel handLabel = new JLabel("In Hand:"); //create hand label
		//JTextField handText = new JTextField(); //create hand text field
		//handText.setText(card.getCardName());
		
		JLabel seenLabel = new JLabel("Seen:"); //create seen label
		// JTextField seenText = new JTextField(); //create seen text field
		
		
		roomPanel.add(handLabel);
		
		boolean contains = false;
		
		for (int i = 0; i < human.getPlayerCards().size(); i++) {
			JTextField handText = new JTextField();
			if (human.getPlayerCards().get(i).getCardType() == CardType.ROOM) {
				handText.setText(human.getPlayerCards().get(i).getCardName());
				roomPanel.add(handText); //text box colors??
				contains = true;
			}	
		}
			
		
		if (!contains) {
			JTextField none = new JTextField();
			none.setText("None");
			roomPanel.add(none);
		}
		
		roomPanel.add(seenLabel);
		
		contains = false;
		
		for (Card s: human.getSeenCards()) {
			JTextField seenText = new JTextField();
			if (s.getCardType() == CardType.ROOM) {
				seenText.setText(s.getCardName());
				roomPanel.add(seenText);
				contains = true;
			}
			
		}

		if (!contains) {
			JTextField seenText = new JTextField();
			seenText.setText("None");
			roomPanel.add(seenText);
		}
	}

private void weaponPanel(Player human) {
	
	weaponPanel.removeAll();
	// add the fields to go into the panel using the updated seen card data
	add(weaponPanel);   // causes swing to either add or readd the entire panel and recalculate it
	
	JLabel handLabel = new JLabel("In Hand:"); //create hand label
	//JTextField handText = new JTextField(); //create hand text field
	
	
	JLabel seenLabel = new JLabel("Seen:"); //create seen label
	//JTextField seenText = new JTextField(); //create seen text field
	
	weaponPanel.add(handLabel);
	
	boolean contains = false;
	for (int i = 0; i < human.getPlayerCards().size(); i++) {
		JTextField handText = new JTextField();
		if (human.getPlayerCards().get(i).getCardType() == CardType.WEAPON) {
			handText.setText(human.getPlayerCards().get(i).getCardName());
			weaponPanel.add(handText); //text box colors??
			contains = true;
		}	
	}
		
	
	if (!contains) {
		JTextField none = new JTextField();
		none.setText("None");
		weaponPanel.add(none);
	}
	
	weaponPanel.add(seenLabel);
	
	contains = false;
	
	for (Card s: human.getSeenCards()) {
		JTextField seenText = new JTextField();
		if (s.getCardType() == CardType.WEAPON) {
			seenText.setText(s.getCardName());
			weaponPanel.add(seenText);
			contains = true;
		}
		
	}

	if (!contains) {
		JTextField seenText = new JTextField();
		seenText.setText("None");
		weaponPanel.add(seenText);
	}
	
}

	public static void main(String[] args) {
		GameCardPanel panel = new GameCardPanel();  // create the panel
		JFrame frame = new JFrame();  // create the frame 
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(200, 500);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		
		
		/*ArrayList<Card> handStart = new ArrayList<Card>();
		Set<Card> seenCardsStart = new HashSet<Card>();
		
		
		handStart.add(new Card("Benjamin Siegel", CardType.PERSON));
		handStart.add(new Card("Eddie McGrath", CardType.PERSON));
		handStart.add(new Card("Katana", CardType.WEAPON));*/
		
		ArrayList<Card> handDuring = new ArrayList<Card>();
		Set<Card> seenCardsDuring = new HashSet<Card>();
		
		handDuring.add(new Card("Al Capone", CardType.PERSON));
		handDuring.add(new Card("Bedroom", CardType.ROOM));
		handDuring.add(new Card("Garden", CardType.ROOM));
		//handDuring.add(new Card("Wire", CardType.WEAPON));
		
		seenCardsDuring.add(new Card("Benjamin Siegel", CardType.PERSON));
		seenCardsDuring.add(new Card("Eddie McGrath", CardType.PERSON));
		seenCardsDuring.add(new Card("Katana", CardType.WEAPON));
		seenCardsDuring.add(new Card("Pistol", CardType.WEAPON));
		seenCardsDuring.add(new Card("Pool", CardType.ROOM));
		seenCardsDuring.add(new Card("Wire", CardType.WEAPON));
		
		Player player1 = new HumanPlayer("Al Capone", "Blue", 0, 0);
		
		player1.setHand(handDuring);
		player1.setSeenCards(seenCardsDuring);
		
		
		// test filling in the data
//		panel.setPeoplePanel(handStart, seenCardsStart);
//		panel.setRoomsPanel(handStart, seenCardsStart);
//		panel.setWeaponsPanel(handStart, seenCardsStart);
		
		//check both windows
//		panel.setPeoplePanel(handDuring, seenCardsDuring);
//		panel.setRoomsPanel(handDuring, seenCardsDuring);
//		panel.setWeaponsPanel(handDuring, seenCardsDuring);
		
		//panel.updatePanels();
		panel.peoplePanel(player1);
		panel.roomPanel(player1);
		panel.weaponPanel(player1);
		
		frame.setVisible(true); // make it visible
	}
}
