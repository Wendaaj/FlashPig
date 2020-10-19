package com.example.flashpig.ViewModel;

import android.graphics.Bitmap;

import androidx.lifecycle.ViewModel;

import com.example.flashpig.DataBase.FakeDataBase;
import com.example.flashpig.DataBase.Repository;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;

public class CardViewModel extends ViewModel {
    private Repository repo = Repository.getInstance();
    private Deck deck;
    private Card card;

    public void initDeck() {
        deck = new Deck();
    }

    public void initCard(){
        card = new Card();
        deck.addCard(card);
    }

    public void saveDeck() {
        repo.saveDeck(deck);
    }

    public String getDeckName(){String deckname = deck.getDeckName(); return deckname;}

    public void setDeckName(String deckName) {
        deck.setDeckName(deckName);
    }

    public void setFrontStr(String frontStr) {
        card.setFrontsideStr(frontStr);
    }

    public void setBackStr(String backStr) {
        card.setBacksideStr(backStr);
    }
    public void setFrontImg(Bitmap frontImg) {
        card.setFrontImg(frontImg);
    }
    public void setBackImg(Bitmap backImg) {
        card.setBackImg(backImg);
    }
}
