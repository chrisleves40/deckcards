package challenge.deckcards.dao;

import challenge.deckcards.domain.DealDeck;
import challenge.deckcards.domain.Deck;

public interface IDeckDao {
   DealDeck generateNewDealDeck(Deck deck);
   
   Deck getDeck();   
}
