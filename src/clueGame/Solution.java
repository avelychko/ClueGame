package clueGame;

import java.util.ArrayList;

//who did it
public class Solution {
	private Card room;
	private Card person;
	private Card weapon;
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	public Solution(Card room, Card person, Card weapon) {
		super();
		this.room = room;
		this.person = person;
		this.weapon = weapon;
	}
	
	public Card getRoom() {
		return room;
	}
	
	public Card getPerson() {
		return person;
	}
	
	public Card getWeapon() {
		return weapon;
	}
	
	public void setSolutionCards() {
		this.hand.add(this.room);
		this.hand.add(this.person);
		this.hand.add(this.weapon);
	}
	
	public ArrayList<Card> getSolutionCards() {
		return this.hand;
	}
}
