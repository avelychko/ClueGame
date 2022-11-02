package clueGame;

//Name, Color, Human/Computer, Starting Location
public abstract class Player {
	private String name;
	private String color;
	private int row, col;
	
	public Player(String name, String color, int row, int col) {
		super();
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
	}

	public void updateHand(Card card) {
		
	}

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
