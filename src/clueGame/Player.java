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
		return "";
	}
	
	public String getColor() {
		return "";
	}
	
	public int getRow() {
		return 0;
	}
	
	public int getCol() {
		return 0;
	}
	
	public void setPlayerCards(Card card) {
	
	}
	
	public ArrayList<Card> getPlayerCards() {
		return this.hand;
	}
}
