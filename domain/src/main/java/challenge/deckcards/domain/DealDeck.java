package challenge.deckcards.domain;

import java.util.ArrayList;
import java.util.List;

public class DealDeck {
	private String id;
	private Deck deck;
	private List<Integer> dealCardPositionList;
	private int currentDealCardPositionIndex;

	public int getCurrentDealCardPositionIndex() {
		return currentDealCardPositionIndex;
	}

	public void setCurrentDealCardPositionIndex(int currentDealCardPositionIndex) {
		this.currentDealCardPositionIndex = currentDealCardPositionIndex;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public List<Integer> getDealCardPositionList() {
		if (dealCardPositionList == null) {
			dealCardPositionList = new ArrayList<>();
		}
		
		return dealCardPositionList;
	}
}
