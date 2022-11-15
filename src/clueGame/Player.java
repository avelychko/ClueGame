package clueGame;

import java.awt.*;
import java.util.*;

//Name, Color, Human/Computer, Starting Location
public abstract class Player {
	private String name;
	private String color;
	private int row, col;
	private static final int CARD_AMOUNT = 3;
	private ArrayList<Card> hand = new ArrayList<Card>(CARD_AMOUNT);
	private Set<Card> seenCards = new HashSet<Card>();
	private ArrayList<Card> disproveCards;
	private Map<String, Color> colorSelection  = new HashMap<>();

	public Player(String name, String color, int row, int col) {
		super();
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
		colorSelection.put("Blue", new Color(108, 133, 255));
		colorSelection.put("Cyan", new Color(119, 255, 255));
		colorSelection.put("Gray", Color.gray);
		colorSelection.put("LightGray", Color.lightGray);
		colorSelection.put("Green",  new Color(116, 255, 101));
		colorSelection.put("Magenta", new Color(218, 112, 255));
		colorSelection.put("Orange", new Color(255, 165, 86));
		colorSelection.put("Yellow",  new Color(255, 255, 63));
		colorSelection.put("Red", new Color(255, 108, 108));
		colorSelection.put("White", Color.white);
		colorSelection.put("Pink", new Color(255, 112, 189));
	}

	public String getName() { return this.name; }
	public Color getColor() { return colorSelection.get(color); }
	public int getRow() { return this.row; }
	public int getCol() { return this.col; }

	public void updateHand(Card card) { this.hand.add(card); }
	public ArrayList<Card> getPlayerCards() { return this.hand; }
	public void setHand(ArrayList<Card> hand) {this.hand = hand;}

	public void updateSeen(Card seenCard) { this.seenCards.add(seenCard); }
	public Set<Card> getSeenCards() { return this.seenCards; }
	public void setSeenCards(Set<Card> seenCards) { this.seenCards = seenCards; }

	//checks if the player has the card and will return it in the end, 
	//if they have multiple then one will be chosen at random
	public Card disproveSuggestion(Card room, Card person, Card weapon) { 
		disproveCards = new ArrayList<Card>();

		if (hand.contains(room)) disproveCards.add(room);
		if (hand.contains(person)) disproveCards.add(person);	
		if (hand.contains(weapon)) disproveCards.add(weapon);

		if (disproveCards.size() == 0) return null;

		if (disproveCards.size() > 1) {
			Random rand = new Random();
			int randomIndex = rand.nextInt(disproveCards.size());
			return disproveCards.get(randomIndex);
		}
		return disproveCards.get(0); 
	}

	public void drawPlayer(Graphics g, int width, int height) {
		g.setColor(Color.black);
		g.drawOval(this.col * width, this.row * height, width, height);
		g.setColor(getColor());
		g.fillOval(this.col * width, this.row * height, width, height);
	}
}
