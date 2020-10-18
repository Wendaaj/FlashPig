package com.example.flashpig.DataBase;

import com.example.flashpig.Model.Deck;

import java.util.ArrayList;

/**
 * @author wendy
 * @version 2020-10-28
 */
public interface DataBase {
    ArrayList<Deck> deckList = new ArrayList<>();

    ArrayList<Deck> getDeckList();
    void addDeck(Deck deck);
    void removeDeck(Deck deck);
}
