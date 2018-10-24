package challenge.deckcards.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import challenge.deckcards.business.IDealDeckSvc;
import challenge.deckcards.cache.CacheDealDeck;
import challenge.deckcards.domain.Card;
import challenge.deckcards.domain.DealDeck;
import challenge.deckcards.domain.Deck;
import challenge.deckcards.exception.BadRequestException;

@RestController
@ControllerAdvice
public class RestDeckcardsController {
	private static final Logger logger = LoggerFactory.getLogger(RestDeckcardsController.class);
	
	@Autowired CacheDealDeck cacheDealDeck;
	@Autowired IDealDeckSvc pokerDealDeckSvc;
		
	@RequestMapping("/shuffle")
    public String shuffleDeck() {
		logger.debug("Entering rest call /shuffle");
		
		// get the deck and cache it for future use 
		Deck deck = cacheDealDeck.getDeck();       
		
		// generate and suffle the deck for dealing
		DealDeck dealDeck = pokerDealDeckSvc.generateAndSuffleDealDeck(deck);
		cacheDealDeck.addDealDeck(dealDeck);

		logger.debug("Quitting rest call /shuffle with returning id of " + dealDeck.getId());
		
		// return the deal deck id to use with dealOneCard rest
		return dealDeck.getId();
    }
	
	@RequestMapping("/dealOneCard")
    public ResponseEntity<Card> dealOneCard(@RequestParam(value="dealDeckId", required = true) String dealDeckId) throws BadRequestException {		
		logger.debug("Entering rest call /dealOneCard");
		
		DealDeck dealDeck = cacheDealDeck.getDealDeck(dealDeckId);
        
		if (dealDeck == null) {
			throw new BadRequestException("dealDeck not found with dealDeckId = " + dealDeckId);
		}

		Card dealCard = pokerDealDeckSvc.dealOneCard(dealDeck);
		
		logger.debug("Quitting rest call /dealOneCard with dealCard = " + dealCard);
		
		return new ResponseEntity<Card>(dealCard, HttpStatus.OK);		
    }	

	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)	
	public String handleOtherException(BadRequestException ex) {	    
	    return ex.getMessage();
	}
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)	
	public String handleBadRequestException(BadRequestException ex) {	    
	    return ex.getMessage();
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)	
	public String handleMissingParams(MissingServletRequestParameterException ex) {	    
	    return ex.getParameterName() + " parameter is missing";
	}
}
