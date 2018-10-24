package challenge.deckcards.business;

import challenge.deckcards.domain.Card;
import challenge.deckcards.domain.DealDeck;
import challenge.deckcards.domain.Deck;

public interface IDealDeckSvc {

	DealDeck generateAndSuffleDealDeck(Deck deck);

	Card dealOneCard(DealDeck dealDeck);

}