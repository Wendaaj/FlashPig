package com.example.flashpig.Model;

import android.graphics.Bitmap;
import android.media.Image;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for Memory
 *
 * @author Madeleine
 * @version 2020-09-16
 */

public class Memory extends GameLogic implements MemoryPairUpLogic {
    Deck deck;
    int deckSize;

    /**
     * Constructor for Memory
     *
     */

    public Memory(String title, Deck deck, Deck deck1, int deckSize) {
        super(title, deck);
        this.deck = deck1;
        this.deckSize = deckSize;
    }

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
     * @param chosenCard1 the cards to be compared
     * @param deck the deck to be used
     *
     */

    @Override
    public void isMatch(Card chosenCard1, Deck deck) {
        flipCard(chosenCard1);
        flipCard(chosenCard2);
         if (chosenCard1.getId() == chosenCard2.getId()) {
             deckSize = deckSize - 1;
             ifMatch();
       }
    }

    /**
     * Method to check if it is a match
     *
     */

    @Override
    public void ifMatch() {
        if (deckSize == 0) {
            gameWon(deck);
        }
    }

    /**
     * Method to turn a card
     * @param chosenCard1 the card to turn over
     *
     */

    public void flipCard(Card chosenCard1){ //onFingerPressed
        if(chosenCard1.isFrontside()){
            chosenCard1.setFrontside(false);
        } else {
            chosenCard1.setFrontside(true);
        }
    }

}
