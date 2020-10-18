package com.example.flashpig.DataBase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.flashpig.Model.Deck;

import java.util.ArrayList;

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

    public ArrayList<Deck> getDecks() {
        return db.getDeckList();
    }

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
