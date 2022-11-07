package clueGame;

import java.util.*;

//Name, Color, Human/Computer, Starting Location
public abstract class Player {
	private String name;
	private String color;
	private int row, col;
	private static final int CARD_AMOUNT = 3;
	public ArrayList<Card> hand = new ArrayList<Card>(CARD_AMOUNT);
	private Set<Card> seenCards = new HashSet<Card>();
	private ArrayList<Card> disproveCards;
	
	public Player(String name, String color, int row, int col) {
		super();
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
	}
	
	public String getName() { return this.name; }
	public String getColor() { return this.color; }
	public int getRow() { return this.row; }
	public int getCol() { return this.col; }
	
	public void updateHand(Card card) { this.hand.add(card); }
	public ArrayList<Card> getPlayerCards() { return this.hand; }
	public void updateSeen(Card seenCard) { this.seenCards.add(seenCard); }
	
	//checks if the player has the card and will return it in the end, if they have multiple then one will be chosen at random
	public Card disproveSuggestion(Card room, Card person, Card weapon) { 
		disproveCards = new ArrayList<>();
		
		if (hand.contains(room)) disproveCards.add(room);
		
		if (hand.contains(person)) disproveCards.add(person);
			
		if (hand.contains(weapon)) disproveCards.add(weapon);
		
		
		if (disproveCards.size() == 0) return null;
		
		
		if (disproveCards.size() > 1) {
			Random rand = new Random();
			int randomIndex = rand.nextInt(disproveCards.size());
			return disproveCards.get(randomIndex);
		}
		
		
		return disproveCards.get(0); }
}
