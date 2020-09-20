package com.example.flashpig.Model;

import android.media.Image;
import android.view.View;

import java.util.List;

/**
 * A flashcard game.
 *
 * @author wendy
 * @version 2020-09-17
 */
public class Flashcard extends GameLogic {

    public Flashcard(String title, View viewGame, Deck deck) {
        super(title, viewGame, deck);
    }

    /**
     * Method to add the picked card to easy.
     * @param card The current card.
     */
    public void addEasyCard(Card card) {
        card.setDifficulty(Difficulties.EASY);
        //Timekeeper set to 10 hours
    }

    /**
     * Method to add the picked card to medium.
     * @param card The current card.
     */
    public void addMediumCard(Card card) {
        card.setDifficulty(Difficulties.MEDIUM);
    }
    //Timekeeper set to 10 min

    /**
     * Method to add the picked card to hard.
     * @param card The current card.
     */
    public void addHardCard(Card card) {
        card.setDifficulty(Difficulties.HARD);
    }
    //Timekeeper er to 1 min


    /**
     * Method to flip the card and show the information of the cards backside.
     * @param card The current card.
     */
    public void flipCard(Card card) { card.isFrontside(); }

    @Override
    Image gameWon(Deck deck) {
        if (deck.getAmountCards() == 0) {
            return null;
        } else {
            return null;
        }
    }

    @Override
    void restartGame() {

    }

    @Override
    void returnHome() {

    }
}
