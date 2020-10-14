package com.example.flashpig.createcard;

import android.graphics.Bitmap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.Flashcard;
import java.util.Random;

public class CardViewModel extends ViewModel {

    private Deck deck;
    private Card card;

    public void initDeck() {
        deck = new Deck();
    }

    public void initCard(){
        card = new Card();
    }

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
