package com.example.flashpig.Model;

/** Class for Memory Game
 *
 * @author Madeleine
 * @responsibility hold the logic for Memory Game
 * @version 2020-09-16
 */


public class Memory extends GameLogic implements MemoryPairUpLogic {

    /** Constructor for Memory
     */

    public Memory(Deck deck) {
        super(deck);  //don't change OG deck
        this.deck = deck;
    }

    /**
     * Check if it is end of game
     * @return Returns true if a game is done
     */
    @Override
    public boolean gameIsOver() { return deck.cards.size() == 0; }

    /**
     *  Compare two cards from a deck
     *  @param chosenCard1 The card first selected
     *  @param chosenCard2 The card second selected
     *  @return Returns true if it the chosen card are a pair
     */
    @Override
    public boolean isMatched(Card chosenCard1, Card chosenCard2, Deck deck) {
        int deckSize;
        if (chosenCard1.getId() == chosenCard2.getId()) {
            deckSize = (deck.getAmountCards()/2) - 1;
            gameIsOver();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to turn a card
     * @param selectedCard the card to turn over
     *
     */
    public void flipCard (Card selectedCard){ //onFingerPressed
        if (selectedCard.isFrontside()) {
            selectedCard.setFrontside(false);
        } else {
            selectedCard.setFrontside(true);
        }
    }
}


