package com.example.flashpig.Model;

import android.media.Image;
import android.view.View;

import java.util.List;

/**
 * Class for PairUp
 *
 * @author Madeleine
 * @version 2020-09-16
 */

public class PairUp extends GameLogic implements MemoryPairUpLogic {
    int deckSize = deck.getAmountCards();

    public PairUp(String title, Deck deck) {
        super(title, deck);
    }


    /**
     * Method to compare two cards from a deck
     * @param chosenCard1 the cards to be compared
     * @param deck the deck to be used
     *
     */

    @Override
    public void isMatch(Card chosenCard1, Deck deck) {
        if (chosenCard1.getId() == chosenCard2.getId()) {
            deckSize = deckSize - 1;
            ifMatch();
        }
    }

    /**
     * Method to check if it is a match
     *
     */

    @Override
    public void ifMatch() {
        if (deckSize == 0) {
            gameWon(deck);
        }
    }

}


