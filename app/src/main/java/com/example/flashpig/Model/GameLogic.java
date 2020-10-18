package com.example.flashpig.Model;



/**
 * An abstract class that holds the common game logic.
 *
 * @author wendy
 * @version 2020-09-17
 */
public abstract class GameLogic {
    Deck deck;

    /**
     * Constructor for GameLogic.
     * @param deck The deck chosen to play the game.
     */
    public GameLogic(Deck deck) {
        this.deck = deck;
    }

    public boolean gameWon(Deck deck) {
        return deck.getAmountCards() == 0;
    }
}