package com.example.flashpig.DataBase;

import com.example.flashpig.Model.Deck;

import java.util.ArrayList;

/**
 * A repository that handles the database so that it's easier for other classes to make use of the database.
 *
 * @author wendy
 * @responsibility Handles the data fronm the FakeDataBase and works as a wrapper.
 * The wrapped data will be sent to whoever calls on it.
 * @version 2020-10-18
 */
public class Repository {
    private static Repository instance;
    private FakeDataBase db = new FakeDataBase();

    private Repository() {
    }

    public static Repository getInstance() {
        if (instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public void removeCard(int cardPos, int deckPos){ db.removeCard(cardPos, deckPos);}

    public void saveDeck(Deck deck){
        db.addDeck(deck);
    }

    public void removeDeck(int pos) {db.removeDeck(pos);}

    /**
     * Gets the databases' list of decks.
     * @return
     */
    public ArrayList<Deck> getDecks() {
        return db.getDeckList();
    }

    /**
     * Get a deck by its id.
     * @param chosenDeck The deck to get from the database.
     * @return The corresponding deck.
     */
    public Deck getDeck(Deck chosenDeck){
        int pos = 0;
        for (Deck deck : db.getDeckList()) {
            if (deck.getId() == chosenDeck.getId()){
                pos = db.getDeckList().indexOf(deck);
            }
        }
        return db.getDeckList().get(pos);
    }
}
