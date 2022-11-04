package clueGame;

import java.awt.Color;
import java.util.ArrayList;

//Name, Color, Human/Computer, Starting Location
public abstract class Player {
	private String name;
	private String color;
	private int row, col;
	private static final int CARD_AMOUNT = 3;
	private ArrayList<Card> hand = new ArrayList<Card>(CARD_AMOUNT);
	
	public Player(String name, String color, int row, int col) {
		super();
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public void setPlayerCards(Card card) {
		hand.add(card);
	}
	
	public ArrayList<Card> getPlayerCards() {
		return this.hand;
	}
}
