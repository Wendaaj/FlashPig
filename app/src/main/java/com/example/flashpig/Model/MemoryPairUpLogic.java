package com.example.flashpig.Model;

/**
 *
 *
 * @author Madeleine
 * @responsibility Class for MemoryPairUpLogic, hold the common methods for Memory and Pair Up game.
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


