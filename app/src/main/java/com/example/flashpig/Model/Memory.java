package com.example.flashpig.Model;

/** Class for Memory Game
        *
        * @author Madeleine
        * @version 2020-09-16
        */

//TA BORT??


public class Memory extends GameLogic implements MemoryPairUpLogic {

    /** Constructor for Memory
     */

    public Memory(String title, Deck deck) {
        super(title, deck);  //don't change OG deck
        this.deck = deck;
    }

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
            isEndOfGame(deckSize);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if it is end of game
     * @param deckSize Amount of cards in a deck
     * @return Returns true if a game is done
     */

        @Override
        public boolean isEndOfGame(int deckSize) {
            if (deckSize == 0) {
                return true;
            }
            return false;
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


