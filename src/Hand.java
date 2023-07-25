import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand = new ArrayList<Card>();

    public ArrayList<Card> TakeFromHand(ArrayList<Card> cardsToTake) {
        for (Card cardToRemove : cardsToTake) {
            hand.remove(cardToRemove);
        }

        return cardsToTake;
    }// end of takeFromHand

    public void setHand(ArrayList<Card> hand) {
        this.hand = sortHand(hand);
    }// end of setHand

    public ArrayList<Card> getHand() {
        return this.hand;
    }// end of getHand

    public void addToHand(ArrayList<Card> cardsToAdd) {
        for (Card card : cardsToAdd) {
            hand.add(card);
        }
        this.hand = sortHand(hand);
    }// end of addToHand

    public int size() {
        return this.hand.size();
    }

    private ArrayList<Card> sortHand(ArrayList<Card> cardsToSort) {
        for (int i = 0; i < cardsToSort.size(); i++) {
            try {
                // System.out.println("hey; "+cardsToSort.get(i).getValueInt());
                // System.out.println(cardsToSort.get(i + 1).getValueInt());
                // System.out.println(cardsToSort.get(i).getValueInt() > cardsToSort.get(i + 1).getValueInt());
                if (cardsToSort.get(i).getValueInt() > cardsToSort.get(i + 1).getValueInt()) {
                    Card tempCard = cardsToSort.get(i);
                    cardsToSort.remove(i);
                    cardsToSort.add(i + 1, tempCard);
                    i = -1;
                }
            } catch (Exception e) {
                // TODO: handle exception
            }

        }

        return cardsToSort;

    }

    public Hand(ArrayList<Card> hand) {
        this.hand = sortHand(hand);
    }

    public Hand() {

    }
}
