package blackjack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The Shoe class provides methods for rendering and updating the Shoe Object. Here a shoe has 6 decks.
 *
 * @author Anqi Wu
 * @version 1.0
 * @since 2016-11-12
 */
public class Shoe {

    /**A list of cards*/
    private ArrayList<Card> shoe;

    /**number of card used*/
    private int used;

    /**
     * Constructs a 6-deck shoe
     */
    public Shoe() {
        this.shoe  = new ArrayList<Card>();
        /**j = rank, k = suit*/
        for (int i = 0; i < 6; i++) {
            for (int j = 1; j <= 13; j++) {
                for (int k = 1; k <= 4; k++) {
                    this.shoe.add(new Card(j, k));
                }
            }
        }
        this.used = 0;
    }

    /**
     * Shuffle the shoe
     */
    public void shuffle() {
        Collections.shuffle(this.shoe);
        this.used = 0;
    }

    /**
     * Get card from the shoe
     * @return the card from the shoe
     */
    public Card getCardDealt(){
        /**Reshuffle the shoe when half of the shoe has been used*/
        if (this.used == 52 * 3){
            shuffle();
        }
        this.used += 1;
        return this.shoe.get(this.used-1);
    }
}
