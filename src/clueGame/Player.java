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
	
	public String getName() {
		return this.name;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}

	public void updateHand(Card card) {
		
	}
<<<<<<< HEAD

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}
=======
}
>>>>>>> 0e8c28a2ac8cbf010629316d63cf5f35cba31a57
