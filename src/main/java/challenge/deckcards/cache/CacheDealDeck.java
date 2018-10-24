package challenge.deckcards.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import challenge.deckcards.dao.IDeckDao;
import challenge.deckcards.domain.DealDeck;
import challenge.deckcards.domain.Deck;

@Configuration
@EnableCaching
public class CacheDealDeck {
    @Autowired private IDeckDao deckDao;
		
	@Cacheable(cacheNames = "deck")
	public Deck getDeck() {
        return deckDao.getDeck();		
	}

	@Cacheable(cacheNames = "dealDeck")
	public DealDeck getDealDeck(String dealDeckId) {
        return null;		
	}
			
	@CachePut(cacheNames = "dealDeck", key="#dealDeck.id")
	public DealDeck addDealDeck(DealDeck dealDeck) {
		return dealDeck;
	}
	
}
