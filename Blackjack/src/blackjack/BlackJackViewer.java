package blackjack;


import javax.swing.*;

/**
 * The BlackJackViewer displays the BlackJack frame
 *
 * @author Anqi Wu
 * @version 1.0
 * @since 2016-11-12
 */
public class BlackJackViewer {

    public static void main(String[] args) {

        JFrame frame = new BlackJackFrame();

        frame.setTitle("Blackjack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }


}
