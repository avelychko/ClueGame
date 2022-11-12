package clueGame;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.*;

public class GameCardPanel extends JPanel {
	JPanel peoplePanel, roomPanel, weaponPanel;
	JTextField generalText;
	private Color[] otherPlayers = {new Color(255, 108, 108), Color.white, new Color(255, 255, 63), new Color(116, 255, 101), new Color(255, 165, 86)};
	//red, white, yellow, green, orange 
	
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
	}

	
	public void updatePanels(Player human) {
		//creates the people panel
		peoplePanel(human);
		//creates the room panel
		roomPanel(human);
		//creates the weapon panel
		weaponPanel(human);
	}
	
	/*
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
		add(peoplePanel);   // causes swing to either add or read the entire panel and recalculate it
		
		JLabel handLabel = new JLabel("In Hand:"); //create hand label
		
		JLabel seenLabel = new JLabel("Seen:"); //create seen label
		
		peoplePanel.add(handLabel);
		
		boolean contains = false; // used to check if hand/seen contains the type, if not "none" will be used
		
	
		for (int i = 0; i < human.getPlayerCards().size(); i++) {
			JTextField handText = new JTextField(); //create hand text field
			//adds hand person cards to panel
			if (human.getPlayerCards().get(i).getCardType() == CardType.PERSON) {
				handText.setText(human.getPlayerCards().get(i).getCardName());
				handText.setBackground(human.getcolor()); //text box colors by player set color
				peoplePanel.add(handText); 
				contains = true;
			}	
		}
			
		//only works if there is no person card type in players hand
		if (!contains) {
			JTextField none = new JTextField();
			none.setText("None");
			peoplePanel.add(none);
		}
		
		
		peoplePanel.add(seenLabel);
		
		contains = false;
		
		/*Random randColor = new Random();
		int randomIndexColor = randColor.nextInt(otherPlayers.length);
		Color randomPlayerColor = otherPlayers[randomIndexColor];*/
		for (Card s: human.getSeenCards()) {
			JTextField seenText = new JTextField(); //create seen text field
			//adds seen person cards to panel
			if (s.getCardType() == CardType.PERSON) {
				seenText.setText(s.getCardName());
				Random randColor = new Random();
				int randomIndexColor = randColor.nextInt(otherPlayers.length);
				Color randomPlayerColor = otherPlayers[randomIndexColor];
				seenText.setBackground(randomPlayerColor);
				peoplePanel.add(seenText);
				contains = true;
			}
			
		}
		
		//only works if there is no person card type in players seen cards
		if (!contains) {
			JTextField seenText = new JTextField();
			seenText.setText("None");
			peoplePanel.add(seenText);
		}
		
	}
	
	private void roomPanel(Player human) {
		
		roomPanel.removeAll();
		// add the fields to go into the panel using the updated seen card data
		add(roomPanel);   // causes swing to either add or read the entire panel and recalculate it
		
		JLabel handLabel = new JLabel("In Hand:"); //create hand label
		
		JLabel seenLabel = new JLabel("Seen:"); //create seen label
		
		
		roomPanel.add(handLabel);
		
		boolean contains = false; // used to check if hand/seen contains the type, if not "none" will be used
		
		
		for (int i = 0; i < human.getPlayerCards().size(); i++) {
			JTextField handText = new JTextField(); //create hand text field
			//adds hand room cards to panel
			if (human.getPlayerCards().get(i).getCardType() == CardType.ROOM) {
				handText.setText(human.getPlayerCards().get(i).getCardName());
				handText.setBackground(human.getcolor()); //text box colors by player set color
				roomPanel.add(handText); 
				contains = true;
			}	
		}
			
		//only works if there is no room card type in players hand
		if (!contains) {
			JTextField none = new JTextField();
			none.setText("None");
			roomPanel.add(none);
		}
		
		roomPanel.add(seenLabel);
		
		contains = false;
		
		//green
		for (Card s: human.getSeenCards()) {
			JTextField seenText = new JTextField(); //create seen text field
			//adds seen room cards to panel
			if (s.getCardType() == CardType.ROOM) {
				seenText.setText(s.getCardName());
				Random randColor = new Random();
				int randomIndexColor = randColor.nextInt(otherPlayers.length);
				Color randomPlayerColor = otherPlayers[randomIndexColor];
				seenText.setBackground(randomPlayerColor);
				roomPanel.add(seenText);
				contains = true;
			}
			
		}
		
		//only works if there is no room card type in players seen cards
		if (!contains) {
			JTextField seenText = new JTextField();
			seenText.setText("None");
			roomPanel.add(seenText);
		}
	}

	private void weaponPanel(Player human) {
	
	weaponPanel.removeAll();
	// add the fields to go into the panel using the updated seen card data
	add(weaponPanel);   // causes swing to either add or read the entire panel and recalculate it
	
	JLabel handLabel = new JLabel("In Hand:"); //create hand label
	
	JLabel seenLabel = new JLabel("Seen:"); //create seen label
	
	
	weaponPanel.add(handLabel);
	
	boolean contains = false; // used to check if hand/seen contains the type, if not "none" will be used
	
	
	for (int i = 0; i < human.getPlayerCards().size(); i++) {
		JTextField handText = new JTextField(); //create hand text field
		//adds hand weapon cards to panel
		if (human.getPlayerCards().get(i).getCardType() == CardType.WEAPON) {
			handText.setText(human.getPlayerCards().get(i).getCardName());
			handText.setBackground(human.getcolor()); //text box colors by player set color
			weaponPanel.add(handText); 
			contains = true;
		}	
	}
		
	//only works if there is no weapon card type in players hand
	if (!contains) {
		JTextField none = new JTextField();
		none.setText("None");
		weaponPanel.add(none);
	}
	
	weaponPanel.add(seenLabel);
	
	contains = false;
	
	//green, yellow, orange
	for (Card s: human.getSeenCards()) {
		JTextField seenText = new JTextField(); //create seen text field
		//adds seen room cards to panel
		if (s.getCardType() == CardType.WEAPON) {
			seenText.setText(s.getCardName());
			Random randColor = new Random();
			int randomIndexColor = randColor.nextInt(otherPlayers.length);
			Color randomPlayerColor = otherPlayers[randomIndexColor];
			seenText.setBackground(randomPlayerColor);
			weaponPanel.add(seenText);
			contains = true;
		}
		
	}

	//only works if there is no weapon card type in players seen cards
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
		frame.setSize(200, 600);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		
		
		ArrayList<Card> handDuring = new ArrayList<Card>();
		Set<Card> seenCardsDuring = new HashSet<Card>();
		
		handDuring.add(new Card("Al Capone", CardType.PERSON));
		handDuring.add(new Card("Bedroom", CardType.ROOM));
		handDuring.add(new Card("Garden", CardType.ROOM));
		//handDuring.add(new Card("Wire", CardType.WEAPON));
		
		//red, yellow, green, white, and orange
		seenCardsDuring.add(new Card("Benjamin Siegel", CardType.PERSON));
		seenCardsDuring.add(new Card("Eddie McGrath", CardType.PERSON));
		seenCardsDuring.add(new Card("Katana", CardType.WEAPON));
		seenCardsDuring.add(new Card("Pistol", CardType.WEAPON));
		seenCardsDuring.add(new Card("Pool", CardType.ROOM));
		seenCardsDuring.add(new Card("Wire", CardType.WEAPON));
		
		Player player1 = new HumanPlayer("Al Capone", "Blue", 0, 0);
		
		player1.setHand(handDuring);
		player1.setSeenCards(seenCardsDuring);
		
		
		panel.updatePanels(player1);

		
		frame.setVisible(true); // make it visible
	}
}
