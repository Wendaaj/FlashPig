package com.example.flashpig.ViewModel;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashpig.DataBase.FakeDataBase;
import com.example.flashpig.DataBase.Repository;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.Difficulty;

public class CardViewModel extends ViewModel {
    private Repository repo = Repository.getInstance();
    private MutableLiveData<Card> card1 = new MutableLiveData<>();
    private MutableLiveData<Deck> deck1 = new MutableLiveData<>();

    public void initDeck() { deck1.setValue(new Deck()); }

    public void initCard(){
        card1.setValue(new Card());
        deck1.getValue().addCard(card1.getValue());
    }

    public LiveData<Card> getCard1() { return card1; }

    public void setCard1(Card card1) { this.card1.setValue(card1); }

    public LiveData<Deck> getDeck1() { return deck1; }

    public void setDeck1(Deck deck1) { this.deck1.setValue(deck1); }

    public void saveDeck() {
        repo.saveDeck(deck1.getValue());
    }

    public void resetViewModel(){
        deck1.setValue(null);
        card1.setValue(null);
    }

    public String getDeckName(){return deck1.getValue().getDeckName();}

    public void setDeckName(String deckName) {
        deck1.getValue().setDeckName(deckName);
    }

    public int getCardPos(Card card){
        return (deck1.getValue().cards.indexOf(card) + 1);
    }
}
