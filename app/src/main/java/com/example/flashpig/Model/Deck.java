package com.example.flashpig.Model;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class for Deck.
 *
 * @author Salvija Zelvyte.
 * @version 2020-09-17
 */
@Parcel
public class Deck {
    public List<Card> cards = new ArrayList<>();
    public int deckId;
    private String deckName;
    private int amountcards;
    private boolean deckNotEmpty;
    public boolean isFrontside = true;
    private Random rand = new Random();

    /**
     * Class constructor
     */
    public Deck() {
        this.deckId = rand.nextInt(); //Check if id already exist.
    }

    public void setFrontside() {
        if (isFrontside) {
            for (Card card : cards) {
                card.setFrontside(false);
            }
        } else {
                    for (Card card : cards) {
                        card.setFrontside(true);
                    }
                }
    }

    /**
     * Adds a new card to a deck.
     *
     * @param card the card you want to add
     */
    public void addCard(Card card) {
        if(!deckContainsCard(card)){
            cards.add(card);

        }
       //fix error message
    }

    /**
     * Deletes a card from a deck.
     *
     * @param card the specific card you want to delete.
     */
    public void deleteCard(Card card) {
        if (deckContainsCard(card)) {
            cards.remove(card);
        }  //fix error message
    }

    /**
     * Deletes all cards in a deck.
     *
     * @param cards the list of cards in the deck.
     */
    public void clearDeck(List<Card> cards) {
        if (!cards.isEmpty()) {
            cards.clear();
            deckNotEmpty = true;
        } else {
            deckNotEmpty = false;
        }
    }

    /**
     * Checks if a specific card exists in a deck.
     * @param card the specific card.
     * @return true if the card exists and false if not.
     */
    public boolean deckContainsCard(Card card){
        if(cards.contains(card)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Returns the amount of cards in a deck.
     *
     * @return the card amount.
     */
    public int getAmountCards() {
        return cards.size();
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public void setIsFrontside(boolean frontside) {
        setFrontside();
        isFrontside = frontside;
    }
}
