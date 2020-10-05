package com.example.flashpig.Model;

import android.graphics.Bitmap;
import android.media.Image;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for Memory Game
 *
 * @author Madeleine
 * @version 2020-09-16
 */

public class Memory extends GameLogic implements MemoryPairUpLogic {

    /**
     * Constructor for Memory
     */

    public Memory(String title, Deck deck) {
        super(title, deck);  //don't change OG deck
        this.deck = deck;

    public void divideCard(Deck deck){
        for (Card card: deck.cards) {
            Bitmap frontImg = card.getFrontImg();
            String frontStr = card.getFrontsideStr();
            Bitmap backImg = card.getBackImg();
            String backStr = card.getBacksideStr();

            saveElements(frontImg, frontStr, backImg, backStr);
        }
    }

    public void saveElements(Bitmap frontImg, String frontStr, Bitmap backImg, String backStr){
        List <Bitmap> imageList = new ArrayList<>();
        List <String> stringList = new ArrayList<>();

        imageList.add(frontImg);
        imageList.add(backImg);
        stringList.add(frontStr);
        stringList.add(backStr);

    }


    /**
     * Method to compare two cards from a deck
     */

    @Override
    public boolean isMatched(Card chosenCard1, Card chosenCard2, Deck deck) {
        int deckSize;
        if (chosenCard1.getId() == chosenCard2.getId()) {
            deckSize = deck.getAmountCards() - 1;
            isEndOfGame(deckSize);
            return true;
        } else {
            return false;
        }
    }

        /**
         * Method to check if it is end of game
         *
         */

        @Override
        public void isEndOfGame(int deckSize) {
            if (deckSize == 0) {
                gameWon(deck);
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



