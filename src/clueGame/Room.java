package clueGame;

/**
 * Room Class
 * 
 * @author Anastasia Velyhko
 * @author Gordon Dina
 *
 */

public class Room {
	//variables
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	
	public String getName() { return name; } //returns room name
	public void setName(String name) { this.name = name; } //sets room name
	public BoardCell getLabelCell() { return labelCell; } //returns cell of label
	public BoardCell getCenterCell() { return centerCell; } //returns cell of center room
}
