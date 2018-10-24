package challenge.deckcards.domain;

public class Card {
	private ECardSuit suit;
	private int faceValue;	
		
	public ECardSuit getSuit() {
		return suit;
	}

	public void setSuit(ECardSuit suit) {
		this.suit = suit;
	}

	public int getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}
	
	@Override
	public String toString() {
		return super.toString()+ "[name=" +suit.name()+ ", faceValue=" +faceValue+ "]";
	}

}
