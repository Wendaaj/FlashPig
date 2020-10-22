package com.example.flashpig.ViewModel;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashpig.DataBase.Repository;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;

public class CardViewModel extends ViewModel {
    private Repository repo = Repository.getInstance();
    private MutableLiveData<Card> card = new MutableLiveData<>();
    private MutableLiveData<Deck> deck = new MutableLiveData<>();
    private MutableLiveData<String> frontTxt = new MutableLiveData<>();

    public void initDeck() { deck.setValue(new Deck()); }

    public void initCard(){
        card.setValue(new Card());
        deck.getValue().addCard(card.getValue());
    }

    public void saveDeck() {
        repo.saveDeck(deck.getValue());
    }

    public void resetViewModel(){
        deck.setValue(null);
        card.setValue(null);
    }

    public MutableLiveData<Card> getCard() { return card; }

    //public void setCard(Card card) { this.card.setValue(card); }
    public void  setFrontStr(String frontStr){ card.getValue().setFrontsideStr(frontStr);}
    public void  setBackStr(String backStr){ card.getValue().setBacksideStr(backStr);}
    public void setFrontImg(Bitmap img){card.getValue().setFrontImg(img);}
    public void setBackImg(Bitmap img){card.getValue().setBackImg(img);}

    public String getDeckName(){return deck.getValue().getDeckName();}
    public String getFrontStr(){return card.getValue().getFrontsideStr();}
    public String getBackStr(){return card.getValue().getBacksideStr();}
    public Bitmap getFrontImg(){return card.getValue().getFrontImg();}
    public Bitmap getBackImg(){return card.getValue().getBackImg();}

    public Deck getCurrentDeck(){return deck.getValue();}
    public Card getCurrentCard(){return card.getValue();}


    public MutableLiveData<Deck> getDeck() { return deck; }

    public void setDeckName(String deckName) { deck.getValue().setDeckName(deckName); }

    public int getCardPos(Card card){
        return (deck.getValue().cards.indexOf(card) + 1);
    }

    public MutableLiveData<String> getFrontTxt() { return frontTxt; }
}
