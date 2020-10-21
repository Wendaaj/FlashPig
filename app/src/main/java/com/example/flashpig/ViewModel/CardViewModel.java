package com.example.flashpig.ViewModel;

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

    public void initDeck() { deck.setValue(new Deck()); }

    public void initCard(){
        card.setValue(new Card());
        deck.getValue().addCard(card.getValue());
    }

    public void setDeck(Deck deck) { this.deck.setValue(deck); }

    public void saveDeck() {
        repo.saveDeck(deck.getValue());
    }

    public void resetViewModel(){
        deck.setValue(null);
        card.setValue(null);
    }

    public LiveData<Card> getCard() { return card; }

    public void setCard(Card card) { this.card.setValue(card); }

    public LiveData<Deck> getDeck() { return deck; }

    public void setDeckName(String deckName) { deck.getValue().setDeckName(deckName); }

    public int getCardPos(Card card){
        return (deck.getValue().cards.indexOf(card) + 1);
    }
}
