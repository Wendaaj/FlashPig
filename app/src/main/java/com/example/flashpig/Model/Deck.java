package com.example.flashpig.Model;

import java.util.List;

public class Deck {
    public List<Card> deck;
    public int deckId;
    private String deckName;
    private int amountcards;

    public Deck(String deckName, int deckId) {
        this.deckName = deckName;
        this.deckId = deckId;

    }
    private void addCard(Card card, int deckId){
        deck.add(card);

    }
    private void deleteCard(Card card){
        if(deck.contains(card)) {
            deck.remove(card);
        }

    }
    private void editCard(Card card){

    }
    private void clearDeck(List<Card> deck){
        if(deck.isEmpty()){
            //error
        }else{
            deck.clear();
        }

    }
}
