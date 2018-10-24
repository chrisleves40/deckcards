package challenge.deckcards.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import challenge.deckcards.domain.Card;
import challenge.deckcards.domain.DealDeck;
import challenge.deckcards.domain.Deck;
import challenge.deckcards.domain.ECardSuit;

@Repository
public class MemoryDeckDao implements IDeckDao {

	@Override
	public DealDeck generateNewDealDeck(Deck deck) {
		DealDeck dealDeck = new DealDeck();
		dealDeck.setId(UUID.randomUUID().toString());
		dealDeck.setDeck(deck);
		
		return dealDeck;
	}
	
	@Override
	public Deck getDeck() {   
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

	private void generateSuit(ECardSuit suit, List<Card> allCard) {
		for (int i = 1; i <= 13; i++) {
			Card card = new Card();
			card.setSuit(suit);
			card.setFaceValue(i);
			allCard.add(card);			
		}
	}

}
