package blackjack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The BlackJackFrame displays the objects on the user interface of the game.
 *
 * @author Anqi Wu
 * @version 1.0
 * @since 2016-11-12
 */
public class BlackJackFrame extends JFrame {

    /**The width of the frame*/
    private static final int FRAME_WIDTH = 2000;

    /**The height of the frame*/
    private static final int FRAME_HEIGHT = 2200;

    /**The path to the image, add absolute path to the project */
    private String path = "Blackjack/src/blackjack/resources/";

    /**userLabel for displaying the value*/
    private JLabel userLabel;

    /**dealer label for displaying the value*/
    private JLabel dealerLabel;

    /**result label for displaying the result */
    private JLabel resultLabel;

    /**label for displaying the amount of money user has*/
    private JLabel moneyLabel;

    /**label for displaying the amount of bet*/
    private JLabel betLabel;


    /**label for displaying user's first card*/
    private JLabel userCard1;

    /**label for displaying user's second card*/
    private JLabel userCard2;

    /**label for displaying the card the user hits*/
    private JLabel userHit;

    /**label for displaying the backcard of the dealer's hand*/
    private JLabel backCard;

    /**label for displaying the first card of the dealer's hand*/
    private JLabel dealerCard1;

    /**label for displaying the second card of the dealer's hand*/
    private JLabel dealerCard2;

    /**label for displaying the card the dealer hits*/
    private JLabel dealerHit;


    /**button for deal*/
    private JButton deal;

    /**button for hit*/
    private JButton hit;

    /**button for stand*/
    private JButton stand;

    /**button for clear*/
    private JButton clear;

    /**button panel for the buttons*/
    private JPanel buttonPanel;

    /**panel for the user card*/
    private JPanel userCardPanel;

    /**panel for the dealer card*/
    private JPanel dealerCardPanel;


    /**the amount of money*/
    private int userMoney;

    /**the amount of bet*/
    private int bet;

    /**Initialize a blackjack game*/
    Hand user = new Hand();
    Hand dealer = new Hand();
    BlackJack game=new BlackJack(user, dealer);

    /**
     * Constructs a BlackjackFrame
     */

    public BlackJackFrame () {

        /**The game begin with $1000*/
        this.userMoney = 1000;

        /**Assume each time the bet is $100*/
        this.bet = 100;

        createComponents();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

    }

    /**
     * Create components on the frame
     */
    public void createComponents() {

        createButtons();
        createLabels();
        createPanels();

        /**Set layout of the panel on the frame*/
        setLayout(new BorderLayout());
        add(this.buttonPanel,BorderLayout.NORTH);
        add(this.userCardPanel,BorderLayout.CENTER);
        add(this.dealerCardPanel,BorderLayout.SOUTH);

    }

    /**
     * Create buttons and add action listeners to them
     */
    public void createButtons(){

        this.deal = new JButton("Deal");
        this.deal.addActionListener(new dealListener());
        this.deal.setEnabled(true);
        this.hit = new JButton("Hit");
        this.hit.addActionListener(new hitListener());
        this.hit.setEnabled(false);
        this.stand = new JButton("Stand");
        this.stand.addActionListener(new standListener());
        this.stand.setEnabled(false);
        this.clear = new JButton("Clear");
        this.clear.addActionListener(new clearListener());
        this.clear.setEnabled(false);

    }

    /**
     * Create labels which will be added to the button panel
     */

    public void createLabels(){

        this.resultLabel = new JLabel();
        this.moneyLabel = new JLabel("Money: $1000");
        this.betLabel = new JLabel("Bet: $100");

    }

    /**
     * Create panels
     */
    public void createPanels(){

        /**Create button panel and add buttons and labels to it*/
        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new FlowLayout());
        this.buttonPanel.add(this.moneyLabel);
        this.buttonPanel.add(this.betLabel);
        this.buttonPanel.add(this.deal);
        this.buttonPanel.add(this.hit);
        this.buttonPanel.add(this.stand);
        this.buttonPanel.add(this.clear);
        this.buttonPanel.add(this.resultLabel);

        /**Create userCardPanel and dealerCardPanel*/
        this.userCardPanel = new JPanel();
        this.dealerCardPanel = new JPanel();

    }

    /**
     * Inner class for dealListener
     */
    class dealListener implements ActionListener {

        /**
         * Action upon clicking the deal button
         * @param e event
         */
        public void actionPerformed(ActionEvent e) {
            /**set the frame to be invisible*/
            setVisible(false);

            /**If user's money is equal to the bet, it does not allow the game to continue*/
            if (userMoney <= 100){
                throw new IllegalArgumentException("You have to add money");
            }

            /**Game starts dealing cards to the user and dealer*/
            game.dealCards();

            /**Display user's hand value*/
            userLabel = new JLabel("  User:  "+user.getHandValue());
            dealerLabel = new JLabel("  Dealer:  "+dealer.getHand().get(1).getDealerValue());

            /**Add label to the panel*/
            userCardPanel.add(userLabel);
            dealerCardPanel.add(dealerLabel);

            /**Cards the user get*/
            ArrayList<Card> userHandList = user.getHand();
            userCard1 = new JLabel(new ImageIcon(path+ userHandList.get(0).toString() + ".png"));
            userCard2 = new JLabel(new ImageIcon(path + userHandList.get(1).toString() + ".png"));

            /**Add cards to the user card panel*/
            userCardPanel.add(userCard1);
            userCardPanel.add(userCard2);

            /**Cards the card get*/
            ArrayList<Card> dealerHandList = dealer.getHand();
            dealerCard1 = new JLabel(new ImageIcon(path + dealerHandList.get(0).toString() + ".png"));
            dealerCard2 = new JLabel(new ImageIcon(path + dealerHandList.get(1).toString() + ".png"));

            /**back card*/
            backCard = new JLabel(new ImageIcon(path + "back.png"));

            /**Add cards to the dealer card panel*/
            dealerCardPanel.add(backCard);
            dealerCardPanel.add(dealerCard2);

            /**
            add(userCardPanel,BorderLayout.CENTER);
            add(dealerCardPanel,BorderLayout.SOUTH);
            */

            /**If the user has a blackjack, disable all other buttons except clear*/
            if (user.getHandValue() == 21) {
                hit.setEnabled(false);
                stand.setEnabled(false);
                deal.setEnabled(false);
                clear.setEnabled(true);
                resultLabel.setText("BlackJack! Win! ");
                userMoney += bet * 1.5;
                moneyLabel.setText("Money: " + userMoney);

            }
            /**If the dealer has a blackjack, disable all other buttons except clear*/
            else if (dealer.getDealerHandValue() == 21) {
                resultLabel.setText("Dealer BlackJack. Lose!");
                hit.setEnabled(false);
                stand.setEnabled(false);
                deal.setEnabled(false);
                clear.setEnabled(true);
            }
            /**o/w disable deal button*/
            else {
                hit.setEnabled(true);
                stand.setEnabled(true);
                deal.setEnabled(false);
            }

            /**Refreshes the frame and display the change*/
            setVisible(true);


        }
    }

    /**
     * Inner class for hitlistener
     */
    class hitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            /**The card the user hit*/
            Card hitcard = game.hit();
            user.addCard(hitcard);

            /**Displays the card on the label*/
            userHit = new JLabel(new ImageIcon(path + hitcard.toString()+".png"));

            /**Add the label to the panel*/
            userCardPanel.add(userHit);
            userCardPanel.repaint();

            /**If the hand bust, disable all other buttons except clear*/
            if(game.bust(user)) {
                resultLabel.setText("Bust");
                hit.setEnabled(false);
                deal.setEnabled(false);
                stand.setEnabled(false);
                clear.setEnabled(true);
                userMoney -= bet;
                moneyLabel.setText("Money: " + userMoney);
            }

                /**Display user's hand value*/
            userLabel.setText("  User:   " + user.getHandValue());

            }
        }


    /**
     * Inner class for standListener
     */
    class standListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            /**Remove all elements from dealerCardPanel*/

            dealerCardPanel.removeAll();


            /**Display dealer's two initial cards*/

            dealerCardPanel.add(dealerCard1);
            dealerCardPanel.add(dealerCard2);
            dealerCardPanel.repaint();

            /**Add to panel*/
            dealerCardPanel.add(dealerLabel);
            dealerLabel.setText(" " + dealerLabel.getText());

            /**Let dealer hit*/
            dealer = game.dealerHit();

            /**What the dealer get after hit or not*/
            ArrayList<Card> dealerHand = dealer.getHand();
            for (int i = 2; i < dealerHand.size(); i++) {
                dealerHit = new JLabel(new ImageIcon(path + dealerHand.get(i).toString() + ".png"));
                dealerCardPanel.add(dealerHit);
            }

            /**Display the user's hand value and dealer's hand value*/
            userLabel.setText("User: " + user.getHandValue());
            dealerLabel.setText("Dealer: " + dealer.getDealerHandValue());

            /**Display the game result and award or penalty*/
            resultLabel.setText(game.announce());
            if (game.announce().equals("Win")) {
                userMoney += bet;
                moneyLabel.setText("Money: " + userMoney);
            }
            if (game.announce().equals("Lose")) {
                userMoney -= bet;
                moneyLabel.setText("Money: " + userMoney);
            }

            hit.setEnabled(false);
            stand.setEnabled(false);
            deal.setEnabled(false);
            clear.setEnabled(true);


        }

    }

    /**
     * Inner class for clearListener
     */
    class clearListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            /**Clear the hand*/
            dealer.clear();
            user.clear();

            /**Remove the elements on card panel, and reset the game result*/
            resultLabel.setText(" ");
            dealerCardPanel.removeAll();
            userCardPanel.removeAll();

            /**Restart the game*/
            hit.setEnabled(false);
            stand.setEnabled(false);
            clear.setEnabled(false);
            deal.setEnabled(true);

            userCardPanel.repaint();
            dealerCardPanel.repaint();



        }


    }


}
