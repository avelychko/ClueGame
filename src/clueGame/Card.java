package clueGame;
public class Card {
	private String cardName; 
	private CardType cardType;

	public boolean equals(Card target) {
		return false;
	}
	
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	
	public void setName(String cardName) {
		this.cardName = cardName;
	}
}
