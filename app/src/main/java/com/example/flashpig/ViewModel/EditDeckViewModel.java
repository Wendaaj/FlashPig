package com.example.flashpig.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.flashpig.DataBase.FakeDataBase;
import com.example.flashpig.Model.Deck;

public class EditDeckViewModel extends ViewModel {
    private Deck deck;
    private FakeDataBase db = new FakeDataBase();

    public void initDeck() {
        deck = new Deck();
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    public void setDeckName(String deckName){
        deck.setDeckName(deckName);
    }

    public Deck getDeck() {
        return deck;
    }

    public FakeDataBase getDb() {
        return db;
    }
}
