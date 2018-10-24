package challenge.deckcards.business;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import challenge.deckcards.dao.IDeckDao;
import challenge.deckcards.domain.Card;
import challenge.deckcards.domain.DealDeck;
import challenge.deckcards.domain.Deck;

@Service
public class PokerDealDeckSvc implements IDealDeckSvc {
	@Autowired
	private IDeckDao deckDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see business.IDealDeckSvc#generateDealDeck(challenge.deckcards.domain.Deck)
	 */
	@Override
	public DealDeck generateAndSuffleDealDeck(Deck deck) {
		DealDeck dealDeck = deckDao.generateNewDealDeck(deck);
		shuffleDealDeck(dealDeck);
		return dealDeck;
	}

	private void shuffleDealDeck(DealDeck dealDeck) {
		List<Integer> dealPosList = dealDeck.getDealCardPositionList();
		
		// Get the total number of cards in the deck
		int sizeCardList = dealDeck.getDeck().getCardList().size();
		// Simply build a list of numbers from 0 to number of cards which will be the position of the card in the list
		dealPosList.addAll(IntStream.rangeClosed(0, sizeCardList-1).boxed().collect(Collectors.toList()));
				
		Random random = new Random();
		for (int i = 0; i < dealDeck.getDeck().getCardList().size(); i++) {
			// for each position, generate a random value past the current index.
			// we need to substract the total size of the list with the index to avoid out of bound exception. 
			int randomIndice = i + random.nextInt(sizeCardList - i);

			// Get the real position from the deal position
			int dealPosition = dealPosList.get(randomIndice);
			
			// We now able to permute the random position found after the current index with the current one.
			dealPosList.set(randomIndice, dealPosList.get(i)); 
			dealPosList.set(i, dealPosition);			
		}

		dealDeck.setCurrentDealCardPositionIndex(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see business.IDealDeckSvc#dealOneCard(challenge.deckcards.domain.DealDeck)
	 */
	@Override
	public Card dealOneCard(DealDeck dealDeck) {
		// here's the important synchronise in order to properly deal one card
		synchronized (dealDeck) {
			int currDealPosIndex = dealDeck.getCurrentDealCardPositionIndex();
			List<Integer> listDealPos = dealDeck.getDealCardPositionList();
			if (currDealPosIndex >= listDealPos.size()) {
				return null;
			}

			int currDealPos = listDealPos.get(currDealPosIndex);
			
			Card dealCard = dealDeck.getDeck().getCardList().get(currDealPos);
			dealDeck.setCurrentDealCardPositionIndex(currDealPosIndex + 1);

			return dealCard;			
		}
	}
}
