package com.example.flashpig.Model;

/** Class for Memory Game
        *
        * @author Madeleine
        * @version 2020-09-16
        */

public class Memory extends GameLogic implements MemoryPairUpLogic {

    /** Constructor for Memory
     */

    public Memory(String title, Deck deck) {
        super(title, deck);  //don't change OG deck
        this.deck = deck;
    }

    /** Method to compare two cards from a deck
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

        /** Method to check if it is end of game
         *
         * @return
         */

        @Override
        public boolean isEndOfGame(int deckSize) {
            if (deckSize == 0) {
                return true;
            }
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
