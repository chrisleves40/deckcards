package challenge.deckcards.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import challenge.deckcards.dao.IDeckDao;
import challenge.deckcards.domain.Card;
import challenge.deckcards.domain.DealDeck;
import challenge.deckcards.domain.Deck;

@RunWith(MockitoJUnitRunner.class)
public class PokerDealDeckSvcTest {
   @Mock
   IDeckDao deckDao;
	
   @InjectMocks
   PokerDealDeckSvc pokerDealDeckSvc;

   @Test
   public void testNeverTheSameCardDealed() {
	   Deck deck = CreateTestObjectUtils.createDeck();
	   DealDeck dealDeck = CreateTestObjectUtils.createPokerDealDeck(deck);

 	   doReturn(dealDeck).when(deckDao).generateNewDealDeck(deck); 	   
 	   DealDeck dealDeckReturn = pokerDealDeckSvc.generateAndSuffleDealDeck(deck);
	   
 	   List<Card> dealedCardList = new ArrayList<>();
 	   for (int i = 0; i < deck.getCardList().size() + 3; i++) {
 		   Card dealCard = pokerDealDeckSvc.dealOneCard(dealDeckReturn);
 		   
 		   if (dealCard != null) {
 			   assertFalse(dealedCardList.contains(dealCard));
 			   
 			   dealedCardList.add(dealCard);
 		   }
       } 	   
   }

   @Test
   public void testCountDealedCardsUntilIsNull() {
	   Deck deck = CreateTestObjectUtils.createDeck();
	   DealDeck dealDeck = CreateTestObjectUtils.createPokerDealDeck(deck);

 	   doReturn(dealDeck).when(deckDao).generateNewDealDeck(deck); 	   
 	   DealDeck dealDeckReturn = pokerDealDeckSvc.generateAndSuffleDealDeck(deck);
	   
 	   int ctrDealedCard = 0;
 	   for (int i = 0; i < deck.getCardList().size() + 3; i++) {
 		   Card dealCard = pokerDealDeckSvc.dealOneCard(dealDeckReturn);
 		   
 		   if (dealCard != null) {
 			   ctrDealedCard++;
 			
 			   // ensure the dealed card was actually the one cibled by the dealCardPositionList/currentDealCardPositionIndex
 	 		   int currDealPos = dealDeckReturn.getDealCardPositionList().get(i);
 	 		   int indexDealCardFound = deck.getCardList().indexOf(dealCard);
 	 		   
 	 		   assertEquals(currDealPos, indexDealCardFound); 			   
 		   }
       }
 	   
 	   assertEquals(deck.getCardList().size(), ctrDealedCard);
   }
   
   @Test
   public void testGenerateAndSuffleDealDeck() {
	   Deck deck = CreateTestObjectUtils.createDeck();
	   DealDeck dealDeck = CreateTestObjectUtils.createPokerDealDeck(deck);
	   
 	   doReturn(dealDeck).when(deckDao).generateNewDealDeck(deck);
 	   
 	   DealDeck dealDeckReturn = pokerDealDeckSvc.generateAndSuffleDealDeck(deck);
 	   
 	   assertEquals(dealDeck, dealDeckReturn);
 	   assertEquals(dealDeck.getDeck(), dealDeckReturn.getDeck());
 	   assertEquals(dealDeck.getDeck().getCardList().size(), dealDeckReturn.getDealCardPositionList().size());

  	   int ctrEquals = 0;
	   for (int i = 0; i < deck.getCardList().size(); i++) {
		   if (i == dealDeck.getDealCardPositionList().get(i).intValue()) {
			   ctrEquals++;
		   }
	   }			
	   if (ctrEquals > 1) {
		   fail("More than one equals");
	   }
	   
 	   Collections.sort(dealDeck.getDealCardPositionList()); 	   
 	   for (int i = 0; i < deck.getCardList().size(); i++) {
 		   assertEquals(i, dealDeck.getDealCardPositionList().get(i).intValue());
 	   }			
   }
}
