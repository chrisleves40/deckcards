package challenge.deckcards.init;

import java.util.ArrayList;
import java.util.List;

import challenge.deckcards.domain.Card;
import challenge.deckcards.domain.DealDeck;
import challenge.deckcards.domain.Deck;
import challenge.deckcards.domain.ECardSuit;

public class CreateTestObjectUtils {
	public static DealDeck createPokerDealDeck(Deck deck) {
		DealDeck dealDeck = new DealDeck();
		dealDeck.setDeck(deck);
		return dealDeck;
	}
	
	public static Deck createDeck() {   
		Deck deck = new Deck();
		deck.setId("pokerId");   // could be something from a database
		
		List<Card> allCards = new ArrayList<>();
		
		generateSuit(ECardSuit.CLUBS, allCards);
		generateSuit(ECardSuit.DIAMONDS, allCards);
		generateSuit(ECardSuit.HEARTS, allCards);
		generateSuit(ECardSuit.SPADES, allCards);
		
		deck.setCardList(allCards);
		
		return deck;
	}

	private static void generateSuit(ECardSuit suit, List<Card> allCard) {
		for (int i = 1; i <= 13; i++) {
			Card card = new Card();
			card.setSuit(suit);
			card.setFaceValue(i);
			allCard.add(card);			
		}
	}
}
