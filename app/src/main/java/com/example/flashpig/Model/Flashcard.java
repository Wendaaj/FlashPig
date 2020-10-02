package com.example.flashpig.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * A flashcard game.
 *
 * @author wendy
 * @version 2020-09-17
 */
public class Flashcard extends GameLogic {

    private Deck deck;
    public List<Card> gameDeck = new ArrayList<>();

    public Flashcard(String title, Deck deck) {
        super(title, deck);
        this.deck = super.deck;
        gameDeck.addAll(deck.cards);
    }

    public Deck getDeck() {
        return deck;
    }

    public boolean roundIsOver() {
        if (gameDeck.isEmpty()) {
            gameDeck.addAll(deck.cards);
            return true;
        }else {
            return false;
        }
    }

    /**
     * Adds the picked card to easy.
     * @param card The current card.
     */
    public void addEasyCard(Card card) {
        card.setDifficulty(Difficulty.EASY);
        gameDeck.remove(card);
        //Timekeeper set to 10 hours
    }

    /**
     * Adds the picked card to medium.
     * @param card The current card.
     */
    public void addMediumCard(Card card) {
        card.setDifficulty(Difficulty.MEDIUM);
        gameDeck.remove(card);
    }
    //Timekeeper set to 10 min

    /**
     * Adds the picked card to hard.
     * @param card The current card.
     */
    public void addHardCard(Card card) {
        card.setDifficulty(Difficulty.HARD);
        gameDeck.remove(card);
        gameDeck.add(gameDeck.size(), card);
    }
    //Timekeeper er to 1 min

    /**
     * Flips the card and show the information of the cards backside.
     * @param card The current card.
     */
    public void turnOver(Card card) {
        card.setFrontside(false);
    }
}
