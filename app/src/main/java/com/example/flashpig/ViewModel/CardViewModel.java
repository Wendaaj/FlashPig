package com.example.flashpig.ViewModel;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashpig.DataBase.FakeDataBase;
import com.example.flashpig.DataBase.Repository;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.Difficulty;

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
    public boolean deckIsSet(){
        if(this.deck==null){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean cardIsSet(){
        if(this.card==null){
            return false;
        }
        else {
            return true;
        }
    }

    public void eraseCards(){
        this.deck.clearDeck(this.deck.getCards(Difficulty.NOTHING));
    }

    public void saveDeck() {
        repo.saveDeck(deck);
    }

    public void resetVievModel(){
        this.deck = null;
        this.card = null;
    }


    public String getDeckName(){String deckname = deck.getDeckName(); return deckname;}

    public void setDeckName(String deckName) {
        deck.setDeckName(deckName);
    }

    public Card getCard() {
        return card;
    }

    public int getCardPos(Card card){
        return deck.cards.indexOf(card) + 1;
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
