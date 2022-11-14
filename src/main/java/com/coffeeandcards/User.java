package com.coffeeandcards;

import java.util.ArrayList;
import java.util.List;

public class User implements IPlayer {
    private List<Card> userHand = new ArrayList<>();
    private boolean turnCompleted;
    private int intValueOfUserHand = 0;

    public void drawCardFromDeck() {
        int intValueOfUserHand = getIntValueOfUserHand();
        Card drawnCard = BlackJack.
                getBlackJackInstance().
                getDeckUtility().
                drawCardFromDeck();
        intValueOfUserHand += drawnCard.
                getCardRank().
                getValue();
        userHand.add(drawnCard);
        setUserHand(userHand);
        setIntValueOfUserHand(intValueOfUserHand);
        changeValueOfAce();
    }

    @Override
    public void endTurn() {
        setTurnCompleted(true);
        System.out.println("User turn is over. Let's see what the dealer has!");

    }

    @Override
    public void changeValueOfAce() {
        for (Card card : userHand) {
            if (currentValueOfUserHand() > BlackJack.MAX_VALUE_ALLOWED_IN_HAND &&
                    card.getCardRank().equals(CardRank.ACE)) {
                card.getCardRank().setValue(1);
            }
        }
    }

    public int currentValueOfUserHand() {
        int currentValueOfUserHand = 0;
        for (Card card : userHand) {
            currentValueOfUserHand += card.
                    getCardRank().
                    getValue();
        }
        setIntValueOfUserHand(currentValueOfUserHand);
        return currentValueOfUserHand;
    }

    public void displayUserHand() {
        StringBuilder display = new StringBuilder();
        System.out.print("Your current hand: ");
        for (Card card : userHand) {
            display.append(card.getCardRank()).append(" of ").append(card.getCardSuit().getIcon());
        }
        System.out.println(display.toString());
    }

    public boolean isTurnCompleted() {
        return turnCompleted;
    }

    public void setTurnCompleted(boolean turnCompleted) {
        this.turnCompleted = turnCompleted;
    }

    public List<Card> getUserHand() {
        return userHand;
    }

    public void setUserHand(List<Card> userHand) {
        this.userHand = userHand;
    }

    public void setIntValueOfUserHand(int intValueOfUserHand) {
        this.intValueOfUserHand = intValueOfUserHand;
    }

    public int getIntValueOfUserHand() {
        return intValueOfUserHand;
    }
}



