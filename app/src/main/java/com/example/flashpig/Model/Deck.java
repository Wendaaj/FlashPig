package com.example.flashpig.Model;

import java.util.List;

/**
 * Class for Deck.
 *
 * @author Salvija Zelvyte.
 * @version 2020-09-17
 */
public class Deck {
    public List<Card> deck;
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
        deck.add(card);
    }

    /**
     * Method for deleting a card from your deck.
     * @param card
     */
    public void deleteCard(Card card){
        if(deck.contains(card)) {
            deck.remove(card);
        }else {
            System.out.println("Card does't exist in deck.");
        }
    }

    /**
     * Method for deleting all cards in a deck.
     * @param deck
     */
    public void clearDeck(List<Card> deck){
        if(!deck.isEmpty()){
            deck.clear();
        }else{
            System.out.println("Deck is already empty.");
        }

    }

    /**
     * Method returns the amount of cards in your deck.
     * @return
     */
    public int getAmountCards(){
       return deck.size();
        }

    public String getDeckName() {
        return deckName;
    }
}
