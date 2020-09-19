package com.example.flashpig.Model;

import java.util.List;

/**
 * Class for MemoryPairUpLogic.
 *
 * @author Madeleine
 * @version 2020-09-16
 */

public interface MemoryPairUpLogic {

    /**
     * Method to compare two cards from a deck
     * @param Card the cards to be compared
     * @param Deck the deck to be used
     *
     */

        void isMatch(Card chosenCard1, Card chosenCard2, Deck deck);

    /**
     * Method to check if it is a match
     *
     */

        void ifMatch();
    }

   
