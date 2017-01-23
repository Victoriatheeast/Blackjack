package blackjack;

import java.util.ArrayList;

/**
 * The Hand class provides methods for rendering and updating the Hand Object.
 *
 * @author Anqi Wu
 * @version 1.0
 * @since 2016-11-12
 */
public class Hand {

    /**A list of card*/
    private ArrayList<Card> hand;

    /**The value of hand*/
    private int handValue;

    /**
     * Constructs a hand
     */
    public Hand(){
        this.hand = new ArrayList<Card>();
        this.handValue = 0;
    }

    /**
     * Get the details of the hand
     * @return the details of the hand
     */
    public ArrayList<Card> getHand() {
        return this.hand;
    }

    /**
     * Clear the hand
     */

    public void clear(){
        this.hand.clear();
        this.handValue = 0;
    }

    /**
     * Add card to the hand
     * @param newC new Card
     */
    public void addCard(Card newC){
        if(newC != null){
            this.hand.add(newC);
        }
    }

    /**
     * Determine whether the hand has an Ace
     * @return boolean whether the hand has an Ace
     */
    public boolean hasAce(){
        boolean ace = false;
        for(int i = 0; i < this.hand.size(); i++){
            if(this.hand.get(i).getRank() == 1){
                ace = true;
            }
        }
        return ace;
    }

    /**
     * Get the handValue for user
     * @return the user's handValue
     */
    public int getHandValue(){
        this.handValue = 0;

        for (int i = 0; i < this.hand.size(); i++) {
            int cardValue = this.hand.get(i).getRank();

            if (cardValue > 10) {
                cardValue = 10;
            }
            this.handValue = this.handValue + cardValue;
        }

        /**Count ace as 11 if added, the hand value is not greater than 21*/
        if(this.hasAce() == true && this.handValue + 10 <= 21){
                this.handValue = this.handValue + 10;
        }
        return this.handValue;

    }

    /**
     * Get the dealer's hand value, different rule for the dealer
     * @return the dealer's hand value
     */

    public int getDealerHandValue() {
        this.handValue = 0;

        for (int i = 0; i < this.hand.size(); i++) {
            int cardValue = this.hand.get(i).getRank();

            if (cardValue > 10) {
                cardValue = 10;
            }
            /**The ace is always considered as 11 for dealer*/
            else if (cardValue == 1) {
                cardValue = 11;
            }
            this.handValue = this.handValue + cardValue;


        }
        return this.handValue;
    }



}
