package com.example.flashpig.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashpig.FakeDataBase;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;

import org.parceler.Parcels;

import java.util.List;

public class EditDeckViewModel extends ViewModel {
    private Deck deck;
    private FakeDataBase db = FakeDataBase.getInstance();

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
