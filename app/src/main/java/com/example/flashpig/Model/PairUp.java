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

    public PairUp(String title, View viewGame, Deck deck, boolean match) {
        super(title, viewGame, deck);
    }

    /**
     * Method to compare two cards from a deck
     * @param Card the cards to be compared
     * @param Deck the deck to be used
     *
     */

    @Override
    public void isMatch(Card chosenCard1, Card chosenCard2, Deck deck) {
        if (chosenCard1.getId() == chosenCard2.getId()) {
            deckSize = deckSize - 1; //getter from Deck class
            ifMatch();
        }
    }

    /**
     * Method to check if it is a match
     *
     */

    @Override
    public void ifMatch() {
        if (deckSize == 0) { //getter from Deck class
            gameWon(deck);
        }
    }

    /**
     * Method to show that game is won
     * @param Deck that has been used to win the game
     * @return img
     *
     */

    @Override
    Image gameWon(Deck deck) {
        return null;
    }

    /**
     * Method to restart the game
     * @param View that will be shown
     *
     */

    @Override
    void restartGame(View v) {
    }

    /**
     * Method to return to startpage
     * @param View that will be shown
     *
     */

    @Override
    void returnHome(View v) {
    }

}


