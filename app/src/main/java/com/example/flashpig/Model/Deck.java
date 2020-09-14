package com.example.flashpig.Model;

import java.util.List;

public class Deck {
    public List<Cards> deck;
    public String deckName;
    private int amountcards;

    public Deck(List<Cards> deck, String deckName, int amountcards) {
        this.deck = deck;
        this.deckName = deckName;
        this.amountcards = amountcards;
    }
    private void addCard(Cards card){
        deck.add(card);
    }
    private void deleteCard(Cards card){
        if(deck.contains(card)) {
            deck.remove(card);
        }

    }
    private void editCard(Cards card){

    }
    private void clearDeck(List<Cards> deck){
        if(deck.isEmpty()){
            //error
        }else{
            deck.clear();
        }

    }
}
