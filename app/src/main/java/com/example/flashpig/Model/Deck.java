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

    /**
     * Class constructor
     * @param deckName
     * @param deckId
     */
    public Deck(String deckName, int deckId) {
        this.deckName = deckName;
        this.deckId = deckId;

    }

    /**
     * Method for adding a new card to your deck.
     * @param card
     * @param deckId
     */
    public void addCard(Card card, int deckId){
        cards.add(card);
    }

    /**
     * Method for deleting a card from your deck.
     * @param card
     */
    public void deleteCard(Card card){
        boolean cardExists;
        if(cards.contains(card)) {
            cards.remove(card);
            cardExists = true;
        }else {
            cardExists = false;
        }
    }

    /**
     * Method for deleting all cards in a deck.
     * @param cards
     */
    public void clearDeck(List<Card> cards){
        boolean deckNotEmpty;
        if(!cards.isEmpty()){
            cards.clear();
            deckNotEmpty = true;
        }else{
            deckNotEmpty = false;
        }

    }

    /**
     * Method returns the amount of cards in your deck.
     * @return
     */
    public int getAmountCards(){
       return cards.size();
        }

    public String getDeckName() {
        return deckName;
    }
}
