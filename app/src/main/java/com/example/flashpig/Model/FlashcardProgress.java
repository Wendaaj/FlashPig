package com.example.flashpig.Model;

import java.util.EnumSet;

/**
 * Shows cards after difficulty.
 *
 * @author Wendy
 * @version 2020-09-14
 */
public class FlashcardProgress {
    private Deck deck;
    private EnumSet<Difficulties> enumSetEasy = EnumSet.of(Difficulties.EASY);
    private EnumSet<Difficulties> enumSetMedium = EnumSet.of(Difficulties.MEDIUM);
    private EnumSet<Difficulties> enumSetHard = EnumSet.of(Difficulties.HARD);

    /**
     * FlashcardProgress constructor
     * @param deck The deck to use
     */
    public FlashcardProgress(Deck deck) {
        this.deck = deck;
    }

    /**
     * Show the cards after which difficulty is choosen. The cards are picked out from the deck and shown in the view.
     * @param difficulties The choosen difficulty to show.
     */
    private void showCards(Difficulties difficulties) {
        switch (difficulties) {
            case EASY:
            case MEDIUM:
            case HARD:
            default:
        }
    }

    /**
     * Moves the card to another difficulty.
     * @param card The card to be moved.
     * @param difficulty The difficulty the card is to change to.
     */
    private void moveCard(Card card, Difficulties difficulty) {
        if (!card.getDifficulty().equals(difficulty)) {
            card.setDifficulty(difficulty);
        }
    }

    /**
     * Basically resets the card so that it is neither easy, medium or hard.
     * @param card The card to be reset.
     */
    private void deleteCard(Card card) {
        card.setDifficulty(Difficulties.NOTHING);
    }

}
