package challenge.deckcards.domain;

import java.util.List;

public class Deck {
	private String id;
	private List<Card> cardList;
	
	public List<Card> getCardList() {
		return cardList;
	}

	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
}
