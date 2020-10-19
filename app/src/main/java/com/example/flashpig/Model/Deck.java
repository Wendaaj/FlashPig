package com.example.flashpig.Model;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.Iterator;
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
    int deckId;
    public List<Card> cards = new ArrayList<>();
    String deckName;
    int amountcards;
    boolean deckNotEmpty;
    public boolean isFrontside = true;
    Random rand = new Random();

    /**
     * Class constructor
     */
    public Deck() {
        this.deckId = rand.nextInt(); //Check if id already exist.
    }

    public Deck(String deckName, int deckId) {
        this.deckName = deckName;
        this.deckId = deckId;

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
    }

    /**
     * Deletes a card from a deck.
     *
     * @param card the specific card you want to delete.
     */
    public void deleteCard(Card card) {
        if (deckContainsCard(card)) {
            cards.remove(card);
        }
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
     * Get a list of cards with a specific difficulty.
     * @param difficulty The wanted difficulty.
     * @return Returns a list with cards with a specific difficulty.
     */
    public List<Card> getCards(Difficulty difficulty) {
        ArrayList<Card> result = new ArrayList();
        Iterator var3 = cards.iterator();

        while(var3.hasNext()) {
            Card c = (Card) var3.next();
            if (c.getDifficulty().equals(difficulty)) {
                result.add(c);
            }
        }
        return result;
    }

    /**
     * Gets the amount of cards with a specific difficulty in a deck.
     * @param difficulty The wanted difficulty.
     * @return Returns the amount of cards with a specific difficulty.
     */
    public int getAmountDifficultyCards(Difficulty difficulty){
        List<Card> cards = getCards(difficulty);
        return cards.size();
    }

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

    public int getId() { return deckId; }
}
