package com.example.flashpig.Model;


import java.util.ArrayList;
import java.util.List;

/**
 * A flashcard game.
 *@author wendy
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
        //Timekeeper set to 10 hours
    }

    /**
     * Updates the amount of cards with a specific difficulty.
     */
    private void updateDifficultyAmount(){
        easyAmount = deck.getAmountDifficultyCards(Difficulty.EASY);
        mediumAmount = deck.getAmountDifficultyCards(Difficulty.MEDIUM);
        hardAmount = deck.getAmountDifficultyCards(Difficulty.HARD);
    }


    /**
     * Flips the card and show the information of the cards backside.
     * @param card The current card.
     */
    public void turnOver(Card card) {
        card.setFrontside(false);
    }

    public Card getCurrentCard() {
        Card currentCard;
        return currentCard = gameDeck.get(0);
    }

    public Deck getDeck() { return deck; }

    public int getEasyAmount() { return easyAmount; }

    public int getMediumAmount() { return mediumAmount; }

    public int getHardAmount() { return hardAmount; }
}
