package com.example.flashpig.Model;

/**
 * Class for PairUp
 *
 * @author Madeleine
 * @version 2020-09-16
 */

public class PairUp extends GameLogic implements MemoryPairUpLogic {

    public PairUp(String title, Deck deck) {
        super(title, deck);
    }


    /**
     * Method to compare two cards from a deck
     */

    @Override
    public boolean isMatched(Card chosenCard1, Card chosenCard2, Deck deck) {
        int deckSize;
        if (chosenCard1.getId() == chosenCard2.getId()) {
            deckSize = deck.getAmountCards() - 1;
            isEndOfGame(deckSize);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to check if it is end of game
     *
     */

    @Override
    public void isEndOfGame(int deckSize) {
        if (deckSize == 0) {
            gameWon(deck);
        }
    }
}


