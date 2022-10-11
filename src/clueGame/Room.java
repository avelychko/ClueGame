package clueGame;

public class Room {
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BoardCell getLabelCell() {
		return labelCell;
	}
	public BoardCell getCenterCell() {
		return centerCell;
	}
}
