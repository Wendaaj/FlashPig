package com.example.flashpig.ViewModel;

import android.graphics.Bitmap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashpig.FakeDataBase;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.Flashcard;
import java.util.Random;

public class CardViewModel extends ViewModel {

    private FakeDataBase db = FakeDataBase.getInstance();
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
        db.addDeck(deck);
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
        card.setFrontImg(backImg);
    }
}
