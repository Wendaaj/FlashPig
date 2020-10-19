package com.example.flashpig.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashpig.DataBase.FakeDataBase;
import com.example.flashpig.DataBase.Repository;
import com.example.flashpig.Model.Deck;

import java.util.ArrayList;

public class DashboardViewModel extends ViewModel {
    private Repository repo = Repository.getInstance();
    private MutableLiveData<ArrayList<Deck>> decks = new MutableLiveData<>();
    private MutableLiveData<Deck> chosenDeck = new MutableLiveData<>();

    public DashboardViewModel() {
        decks.setValue(repo.getDecks());
    }

    public void saveDeck(Deck deck) {
        repo.saveDeck(deck);
    }

    public void removeDeck(Deck deck) {
        repo.removeDeck(deck);
    }

    public LiveData<ArrayList<Deck>> getDecks() { return decks; }

    public int getChosenDeckPos(){
        int pos = decks.getValue().indexOf(repo.getDeck(chosenDeck.getValue()));
        return pos;
    }

    public LiveData<Deck> getChosenDeck() {
        return chosenDeck;
    }

    public void setChosenDeck(Deck chosenDeck) {
        this.chosenDeck.setValue(repo.getDeck(chosenDeck));
    }
}