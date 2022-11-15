package com.coffeeandcards;

import java.util.ArrayList;
import java.util.List;

public class Dealer implements IPlayer {
    public static final int MINIMUM_DEALER_HAND_VALUE = 17;
    public static final int STARTING_NUMBER_OF_CARDS = 2;
    private int intValueOfDealerHand = 0;
    private List<Card> dealerHand = new ArrayList<>();

    /**
     * The dealer has to have a hand value of at least 17, getCard() is run until that minimum is met.
     * getCard() is also run if the dealer wishes to 'Hit' and get a new card.
     */
    @Override
    public void drawCardFromDeck() {
        //TODO: Draw initial card from the deck
        int intValueOfDealerHand = getIntValueOfDealerHand();
        Card drawnCard = BlackJack.
                getBlackJackInstance().
                getDeckUtility().
                drawCardFromDeck();
        intValueOfDealerHand += drawnCard.
                getCardRank().
                getValue();
        dealerHand.add(drawnCard);
        setDealerHand(dealerHand);
        setIntValueOfDealerHand(intValueOfDealerHand);
        changeValueOfAce();
    }

    @Override
    public void changeValueOfAce() {
        for (Card card : dealerHand) {
            if (currentValueOfDealerHand() > BlackJack.MAX_VALUE_ALLOWED_IN_HAND &&
                    card.getCardRank().equals(CardRank.ACE)) {
                card.getCardRank().setValue(1);
            }
        }
    }

    public void displayDealerHand() {
        List<Card> listToDisplay = new ArrayList<>();
        boolean userTurnCompleted = BlackJack.getBlackJackInstance().getUser().isTurnCompleted();
        if (!userTurnCompleted) {
            System.out.println("Dealer's face-up card");
            listToDisplay.add(dealerHand.get(0));
            displayDealerCards(listToDisplay);
        } else {
            System.out.println("Dealer's final hand");
            displayDealerCards(dealerHand);
        }
    }

    private void displayDealerCards(List<Card> listOfCards) {
        StringBuilder display = new StringBuilder();
        for (Card card : listOfCards) {
            if (card.getCardRank().equals(CardRank.ACE) ||
                    card.getCardRank().equals(CardRank.KING) ||
                    card.getCardRank().equals(CardRank.QUEEN) ||
                    card.getCardRank().equals(CardRank.JACK)) {
                char firstCharInCardRank = card.getCardRank().name().charAt(0);
                display.append(firstCharInCardRank).append("  ")
                        .append(firstCharInCardRank).append("\n");
                display.append(" ").append(card.getCardSuit()
                        .getIcon()).append("\n");
                display.append(firstCharInCardRank).append("  ")
                        .append(firstCharInCardRank).append("\n");
            } else {
                display.append(card.getCardRank().getValue())
                        .append("  ").append(card.getCardRank()
                                .getValue()).append("\n");
                display.append(" ").append(card.getCardSuit()
                        .getIcon()).append("\n");
                display.append(card.getCardRank().getValue())
                        .append("  ").append(card.getCardRank()
                                .getValue()).append("\n");
            }
        }
        System.out.println(display);
    }

    @Override
    public void endTurn() {
        System.out.println("The dealer has ended their turn." +
                "\nDealer has: " + getIntValueOfDealerHand());
    }

    /**
     * Because the dealer is dealt a face-down and a face-up card,displayCards will show one card
     * to the user at the start. When the user ends their turn, the dealer will reveal the rest of their cards.
     *
     * @return int currentValueOfHand
     */

    public int showDealerCardValues() {
        int currentVisibleValueOfDealerHand = 0;
        boolean isUserTurnCompleted = BlackJack.
                getBlackJackInstance().
                getUser().
                isTurnCompleted();

        if (isUserTurnCompleted) {
            currentVisibleValueOfDealerHand = currentValueOfDealerHand();
        } else {
            currentVisibleValueOfDealerHand = dealerHand.get(0).
                    getCardRank().
                    getValue();
        }
        return currentVisibleValueOfDealerHand;
    }

    public int currentValueOfDealerHand() {
        int totalValueOfDealerHand = 0;
        for (Card card : dealerHand) {
            totalValueOfDealerHand += card.
                    getCardRank().
                    getValue();
        }
        setIntValueOfDealerHand(totalValueOfDealerHand);
        return totalValueOfDealerHand;
    }

    public void dealCards() {
        //TODO: The dealer needs to shuffle the deck of cards
        // and deal 2 cards to every player and themself.
        int numberOfCardsDealtToEachPlayer = 0;
        while (numberOfCardsDealtToEachPlayer < STARTING_NUMBER_OF_CARDS) {
            //add cards to players hands
            drawCardFromDeck(); // dealer implementation of draw/hit/getCard
            BlackJack.
                    getBlackJackInstance().
                    getUser().
                    drawCardFromDeck(); // user implementation of draw/hit/getCard
            numberOfCardsDealtToEachPlayer++;
        }
    }

    public void checkForDealerMinimumHandValue() {
        while (intValueOfDealerHand < MINIMUM_DEALER_HAND_VALUE) {
            drawCardFromDeck();
        }
    }

    public List<Card> getDealerHand() {
        return dealerHand;
    }

    public void setDealerHand(List<Card> dealerHand) {
        this.dealerHand = dealerHand;
    }

    public int getIntValueOfDealerHand() {
        return intValueOfDealerHand;
    }

    public void setIntValueOfDealerHand(int intValueOfDealerHand) {
        this.intValueOfDealerHand = intValueOfDealerHand;
    }
}
