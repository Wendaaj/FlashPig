package com.example.flashpig.Model;

import android.media.Image;
import android.view.View;

import java.util.List;

/**
 * Class for MemoryPairUpLogic.
 *
 * @author Madeleine
 * @version 2020-09-16
 */

public class Memory extends GameLogic implements MemoryPairUpLogic {
    Deck deck;

    /**
     * Constructor for Memory
     *
     */

    public Memory(String title, View viewGame, Deck deck, Deck deck1) {
        super(title, viewGame, deck);
        this.deck = deck1;
    }

    /**
     * Method to compare two cards from a deck
     * @param Card the cards to be compared
     * @param Deck the deck to be used
     *
     */

    @Override
    public void isMatch(Card chosenCard1, Card chosenCard2, Deck deck) {
        flipCard(chosenCard1);
        flipCard(chosenCard2);
         if (chosenCard1.getId() == chosenCard2.getId()) {
             deck.getAmountCards() - 1;
             ifMatch();
       }
    }

    /**
     * Method to check if it is a match
     *
     */

    @Override
    public void ifMatch() {
        if (deck.getAmountCards == 0) {
            gameWon(deck);
        }
    }

    /**
     * Method to turn a card
     * @param Card the card to turn over
     *
     */

    private void flipCard(Card chosenCard1){
        if(chosenCard1.isFrontside()){
            //chosenCard1.isFrontside()= !chosenCard1.isFrontside();
        }
    }

    /**
     * Method to show that game is won
     * @param Deck that has been used to win the game
     * @return img
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
