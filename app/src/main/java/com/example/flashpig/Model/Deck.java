package com.example.flashpig.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for Deck.
 *
 * @author Salvija Zelvyte.
 * @version 2020-09-17
 */
public class Deck {
    public List<Card> cards = new ArrayList<>();
    public int deckId;
    private String deckName;
    private int amountcards;
    private boolean deckNotEmpty;


    /**
     * Class constructor
     *
     * @param deckName name of deck
     * @param deckId a unique id (in case some decks have the same name)
     */
    public Deck(String deckName, int deckId) {
        this.deckName = deckName;
        this.deckId = deckId;

    }

    /**
     * Method for adding a new card to a deck.
     *
     * @param card the card you want to add
     */
    public void addCard(Card card) {
        if(!deckContainsCard(card)){
            cards.add(card);
        } //fix error message
    }

    /**
     * Method for deleting a card from a deck.
     *
     * @param card the specific card you want to delete.
     */
    public void deleteCard(Card card) {
        if (deckContainsCard(card)) {
            cards.remove(card);
        }  //fix error message
    }

    /**
     * Method for deleting all cards in a deck.
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
     * This method checks if a specific card exists in a deck.
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
     * Method returns the amount of cards in a deck.
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

}
