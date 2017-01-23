package blackjack;

/**
 * The BlackJack provides methods for rendering and updating the BlackJack Game Object.
 *
 * @author Anqi Wu
 * @version 1.0
 * @since 2016-11-12
 */
public class BlackJack {

    /**User's hand*/
    private Hand user;

    /**Dealer's hand*/
    private Hand dealer;

    /**6-deck shoe*/
    private Shoe newShoe;

    /**
     * Constructs a BlackJack game
     * @param user user's hand
     * @param dealer dealer's hand
     */
    public BlackJack(Hand user,Hand dealer){
        this.user = user;
        this.dealer = dealer;
        this.newShoe = new Shoe();
        /**Shuffle the shoe*/
        this.newShoe.shuffle();
    }

    /**
     * Deal two cards to dealer and user alternately
     */
    public void dealCards(){
        user.addCard(this.newShoe.getCardDealt());
        dealer.addCard(this.newShoe.getCardDealt());
        user.addCard(this.newShoe.getCardDealt());
        dealer.addCard(this.newShoe.getCardDealt());
    }

    /**
     * Get the card which user hits
     * @return the card which user hits
     */
    public Card hit(){
        return this.newShoe.getCardDealt();
    }

    /**
     * Whether the hand busts
     * @param hand either user or dealer's hand
     * @return whether the hand busts
     */

    public boolean bust(Hand hand) {
        boolean bust = false;

        if(hand.getHandValue() > 21) {
            bust = true;
        }
        return bust;
    }

    /**
     * Let the dealer hit the card according to certain rule
     * @return the hand after the dealer obeys the rule and hits the card
     */
    public Hand dealerHit() {
        /**The dealer has to hit the card until the value reaches 17*/
        while(this.dealer.getDealerHandValue() <= 16)
        {
            this.dealer.addCard(newShoe.getCardDealt());
        }

        /**When the dealer has to hit on soft 17*/
        if(this.dealer.getDealerHandValue()== 17 && this.dealer.hasAce()){
            this.dealer.addCard(newShoe.getCardDealt());
        }
        return this.dealer;

    }


    /**
     * Announce the result of the game
     * @return the result of the game
     */
    public String announce() {

        if((user.getHandValue() < dealer.getHandValue()) && dealer.getHandValue() <= 21 ){
            return "Lose";

        }
        else if ((user.getHandValue() == dealer.getHandValue()) && dealer.getHandValue() <= 21 ) {
            return "Push";
        }
        else {
            return "Win";
        }

    }


}

