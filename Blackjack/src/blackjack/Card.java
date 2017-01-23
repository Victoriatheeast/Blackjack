package blackjack;


/**
 * The Card class provides methods for rendering and updating the Coin Object.
 *
 * @author Anqi Wu
 * @version 1.0
 * @since 2016-11-12
 */
public class Card {

    /**The rank of the card in integer*/
    private int nRank;

    /**The suit of the card in integer*/
    private int nSuit;

    /**
     * Constructs a card
     * @param rank the rank of the card
     * @param suit the suit of the card
     */
    public Card(int rank, int suit){
        if (rank < 0 || rank > 13) {
            throw new IllegalArgumentException("The rank is not valid");
        }
        if (suit < 1 || suit > 4){
            throw new IllegalArgumentException("The suit is not valid");
        }
        this.nRank = rank;
        this.nSuit = suit;
    }


    /**
     * Get the rank of the card
     * @return the rank of the card
     */
    public int getRank(){
        return this.nRank;
    }

    /**
     * Get the value of the dealer's card which is displayed the first time
     * @return the value of the dealer's card
     */
    public int getDealerValue(){
        switch(this.nRank) {
            case 1:   return 11;
            case 2:   return 2;
            case 3:   return 3;
            case 4:   return 4;
            case 5:   return 5;
            case 6:   return 6;
            case 7:   return 7;
            case 8:   return 8;
            case 9:   return 9;
            case 10:  return 10;
            case 11:  return 10;
            case 12:  return 10;
            case 13:  return 10;
            default:  return 0;
        }
    }

    /**
     * Get the string representation of the rank of hte card
     * @return the rank of the card in String
     */

    public String rankInString() {

        switch ( nRank ) {
            case 1:   return "ace";
            case 2:   return "2";
            case 3:   return "3";
            case 4:   return "4";
            case 5:   return "5";
            case 6:   return "6";
            case 7:   return "7";
            case 8:   return "8";
            case 9:   return "9";
            case 10:  return "10";
            case 11:  return "jack";
            case 12:  return "queen";
            case 13:  return "king";

            /**default rank value is "Not Applicable.*/
            default:  return "NA";
        }
    }

    /**
     * Get the string representation of card's suit
     * @return the string representation of cards' suit
     */

    public String suitInString() {

        switch ( nSuit ) {
            case 1:   return "clubs";
            case 2:   return "diamonds";
            case 3:    return "hearts";
            case 4:    return "spades" ;

            /**default rank value is "Not Applicable.*/
            default:       return "NA";
        }
    }


    /**
     * Get the string representation of a card
     * @return the string representation of a card
     */
    public String toString() {
        return  rankInString() + "_of_" + suitInString();
    }

}
