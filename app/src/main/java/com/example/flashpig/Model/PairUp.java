package com.example.flashpig.Model;

/**
 * Class for Pair Up Game
 *
 * @author Madeleine
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

    public boolean isEndOfGame() {
        return deck.cards.size() == 0;
    }
}
