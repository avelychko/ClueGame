package clueGame;

public class Card {
	private String cardName; 
	private CardType cardType;

	public Card(String cardName, CardType cardType) {
		super();
		this.cardName = cardName;
		this.cardType = cardType;
	}
	
	public String getCardName() {
		return "";
	}
	
	public CardType getCardType() {
		return cardType;
	}

	public boolean equals(Card target) {
		return false;
	}
}

