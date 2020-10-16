package com.example.flashpig.Model;

/**
 * Class for MemoryPairUpLogic.
 *
 * @author Madeleine
 * @version 2020-09-16
 */

    //TA BORT?

public interface MemoryPairUpLogic {

    /**
     * Method to compare two cards from a deck
     *
     */

    boolean isMatched(Card chosenCard1, Card chosenCard2, Deck deck);

    /**
     * Method to check if it is a match
     *
     * @return
     */

    boolean isEndOfGame(int deckSize);
    }


