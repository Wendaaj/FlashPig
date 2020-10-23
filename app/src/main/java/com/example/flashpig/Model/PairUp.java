package com.example.flashpig.Model;

/**
 *
 *
 * @author Madeleine
 * @responsibility Class for Pair Up Game, hold the logic for Pair Up game
 * @version 2020-10-16
 */

public class PairUp extends GameLogic {

    public PairUp(Deck deck) {
        super(deck);
    }

    /**
     *  Compare two cards from a deck
     *  @param chosenCard1 The card first selected
     *  @param chosenCard2 The card second selected
     *  @return Returns true if it the chosen card are a pair
     */

    public boolean isMatched(Card chosenCard1, Card chosenCard2) {
        return chosenCard1.getId() == chosenCard2.getId();
    }

    /**
     * Check if it is end of game
     * @return Returns true if a game is done
     */
    @Override
    public boolean gameIsOver() { return deck.cards.size() == 1; }
}
