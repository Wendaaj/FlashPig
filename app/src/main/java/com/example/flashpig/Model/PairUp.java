package com.example.flashpig.Model;

/**
 * Class for Pair Up Game
 *
 * @author Madeleine
 * @version 2020-10-16
 */

public class PairUp extends GameLogic implements MemoryPairUpLogic {

    public PairUp(String title, Deck deck) {
        super(title, deck);
    }


    /**
     *  Compare two cards from a deck
     *  @param chosenCard1 The card first selected
     *  @param chosenCard2 The card second selected
     *  @return Returns true if it the chosen card are a pair
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
     * Check if it is end of game
     * @param deckSize Amount of cards in a deck
     * @return Returns true if a game is done
     */

    @Override
    public boolean isEndOfGame(int deckSize) {
        if (deckSize == 0) {
            return true;
        }
        return false;
    }
}
