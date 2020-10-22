package com.example.flashpig.DataBase;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;

import java.util.ArrayList;

/**
 * @author Wendy
 * @responsibility Holds list of Decks and Deck methods
 * * @version 2020-10-28
 */
public interface DataBase {
    ArrayList<Deck> deckList = new ArrayList<>();

    ArrayList<Deck> getDeckList();
    void addDeck(Deck deck);
    void removeDeck(int pos);
    void removeCard(int cardPos, int deckPos );
}
