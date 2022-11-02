package clueGame;

import java.awt.Color;

//Name, Color, Human/Computer, Starting Location
public abstract class Player {
	private String name;
	private Color color;
	private int row, col;
	
	public Player(String name, Color color, int row, int col) {
		super();
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
	}

	public void updateHand(Card card) {
		
	}
}