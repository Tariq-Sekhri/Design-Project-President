import java.util.ArrayList;

public class Player {
	// Player holds information about the Player
	// also hold methods for the Player
	private String name;
	private Hand hand = new Hand();

	Player(String name , ArrayList<Card> hand){
		this.name = name;
		this.hand.setHand(hand);
	}// end of player

	
	public Hand getHandObject() {
		return this.hand;
	}
	

	public String getName() {
		return name;
	}// end of getName

	public void setName(String name) {
		this.name = name;
	}// end of setName




	public void calculateBooks() {
		ArrayList<Card> whatToAddToBooks = new ArrayList<Card>();
		for (int card1Index = 0; card1Index < hand.size(); card1Index++) {
			boolean skip = false;
			for (Card waitToBeAddedToBooks : whatToAddToBooks) {
				if (hand.getHand().get(card1Index).getValueInt() == waitToBeAddedToBooks.getValueInt()) {
					skip = true;
				}
			}
			if (skip) {
				continue;
			}
			for (int card2Index = card1Index + 1; card2Index < hand.size(); card2Index++) {
				if (hand.getHand().get(card2Index).getValueInt() == hand.getHand().get(card1Index).getValueInt()) {
					for (int card3Index = card2Index + 1; card3Index < hand.size(); card3Index++) {
						if (hand.getHand().get(card3Index).getValueInt() == hand.getHand().get(card1Index).getValueInt()) {
							for (int card4Index = card3Index + 1; card4Index < hand.size(); card3Index++) {
								if (hand.getHand().get(card4Index).getValueInt() == hand.getHand().get(card1Index).getValueInt()) {
									whatToAddToBooks.add(hand.getHand().get(card1Index));
									whatToAddToBooks.add(hand.getHand().get(card2Index));
									whatToAddToBooks.add(hand.getHand().get(card3Index));
									whatToAddToBooks.add(hand.getHand().get(card4Index));
								}

							}
						}
					}
				}
			}
		}
		if (!whatToAddToBooks.isEmpty()) {
			hand.TakeFromHand(whatToAddToBooks);
			
		}
	}// end of calculateBooks



	public String[] handToString() {
		String[] output = new String[hand.size()];
		for (int i = 0; i < hand.size(); i++) {
			output[i] = (i + 1) + ". " + hand.getHand().get(i).getValue()+" of "+hand.getHand().get(i).getSuit();; 
		}
		return output;
	}
}