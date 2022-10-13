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
	public void setLabelCell(BoardCell labelCell) { this.labelCell = labelCell; } //set label cell
	public BoardCell getCenterCell() { return centerCell; } //returns cell of center room
	public void setCenterCell(BoardCell centerCell) { this.centerCell = centerCell; } //set center cell
}
