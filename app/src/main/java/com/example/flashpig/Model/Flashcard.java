package com.example.flashpig.Model;

/**
 * A flashcard game.
 *
 * @author wendy
 * @version 2020-09-17
 */
public class Flashcard extends GameLogic {

    private Deck deck;

    public Flashcard(String title, Deck deck) {
        super(title, deck);
        this.deck = super.deck;
    }

    public Deck getDeck() {
        return deck;
    }

    /**
     * Method to add the picked card to easy.
     * @param card The current card.
     */
    public void addEasyCard(Card card) {
        card.setDifficulty(Difficulty.EASY);
        //Timekeeper set to 10 hours
    }

    /**
     * Method to add the picked card to medium.
     * @param card The current card.
     */
    public void addMediumCard(Card card) {
        card.setDifficulty(Difficulty.MEDIUM);
    }
    //Timekeeper set to 10 min

    /**
     * Method to add the picked card to hard.
     * @param card The current card.
     */
    public void addHardCard(Card card) {
        card.setDifficulty(Difficulty.HARD);
    }
    //Timekeeper er to 1 min


    /**
     * Method to flip the card and show the information of the cards backside.
     * @param card The current card.
     */
    public void turnOver(Card card) {
        card.setFrontside(false);
        System.out.println(card.getBacksideStr());
    }
}
