package com.example.flashpig.Model;


import com.example.flashpig.Difficulty;

import java.util.ArrayList;
import java.util.List;

/**
 * A flashcard game.
 *@author wendy
 * @responsibility Flashcard game logic
 * @version 2020-09-17
 */
public class Flashcard extends GameLogic {

    private Deck deck;
    public List<Card> gameDeck = new ArrayList<>();
    private int easyAmount, mediumAmount, hardAmount;

    public Flashcard(Deck deck) {
        super(deck);
        this.deck = super.deck;
        gameDeck.addAll(deck.cards);
        updateDifficultyAmount();
    }

    /**
     * Check if the round is over.
     * @return Returns true if round is over.
     */
    public boolean roundIsOver() {
        if (gameDeck.isEmpty()) {
            gameDeck.addAll(deck.cards);
            return true;
        }else {
            return false;
        }
    }

    /**
     * Sets the picked card to easy.
     * @param card The current card.
     */
    public void addEasyCard(Card card) {
        card.setDifficulty(Difficulty.EASY);
        updateDifficultyAmount();
        gameDeck.remove(card);
    }

    /**
     * Sets the picked card to medium.
     * @param card The current card.
     */
    public void addMediumCard(Card card) {
        card.setDifficulty(Difficulty.MEDIUM);
        updateDifficultyAmount();
        gameDeck.remove(card);
    }

    /**
     * Sets the picked card to hard.
     * @param card The current card.
     */
    public void addHardCard(Card card) {
        card.setDifficulty(Difficulty.HARD);
        updateDifficultyAmount();
        gameDeck.remove(card);
        gameDeck.add(gameDeck.size(), card);
    }

    /**
     * Add the card back to the game deck.
     * @param card The card to add back to the game deck.
     */
    public void addCardToMain(Card card) {
        gameDeck.add(gameDeck.size(),card);
    }

    /**
     * Updates the amount of cards with a specific difficulty.
     */
    private void updateDifficultyAmount(){
        easyAmount = deck.getAmountDifficultyCards(Difficulty.EASY);
        mediumAmount = deck.getAmountDifficultyCards(Difficulty.MEDIUM);
        hardAmount = deck.getAmountDifficultyCards(Difficulty.HARD);
    }

    public Card getCurrentCard() { return gameDeck.get(0); }

    public Deck getDeck() { return deck; }

    public int getEasyAmount() { return easyAmount; }

    public int getMediumAmount() { return mediumAmount; }

    public int getHardAmount() { return hardAmount; }
}
