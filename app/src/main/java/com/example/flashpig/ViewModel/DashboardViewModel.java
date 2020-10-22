package com.example.flashpig.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashpig.DataBase.FakeDataBase;
import com.example.flashpig.DataBase.Repository;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;

import java.util.ArrayList;

public class DashboardViewModel extends ViewModel {
    private Repository repo = Repository.getInstance();
    private MutableLiveData<ArrayList<Deck>> decks = new MutableLiveData<>();
    private MutableLiveData<Deck> chosenDeck = new MutableLiveData<>();
    private MutableLiveData<Integer> amountDecks = new MutableLiveData<>();
    private MutableLiveData<Card> card = new MutableLiveData<>();
    private MutableLiveData<Integer> amountCards = new MutableLiveData<>();

    public DashboardViewModel() {
        decks.setValue(repo.getDecks());
        amountDecks.setValue(repo.getDecks().size());
    }

    /**
     * Checks if card has both front text and front image.
     */
    public boolean checkHasFrontTxtAndImg() {
        return (card.getValue().getFrontImg() != null && !card.getValue().getFrontsideStr().isEmpty());
    }

    /**
     * Checks if card has only front text.
     */
    public boolean checkHasFrontTxtOnly() {
        return (card.getValue().getFrontImg() == null && !card.getValue().getFrontsideStr().isEmpty());
    }

    /**
     * Checks if card has both front text and front image.
     */
    public boolean checkHasBackTxtAndImg() {
        return (card.getValue().getBackImg() != null && !card.getValue().getBacksideStr().isEmpty());
    }

    /**
     * Checks if card has only back text.
     */
    public boolean checkHasBackTxtOnly() {
        return (card.getValue().getBackImg() == null && !card.getValue().getBacksideStr().isEmpty());
    }

    public void removeDeck(Deck deck) {
        repo.removeDeck(deck);
        amountDecks.setValue(decks.getValue().size());
    }

    public int getChosenDeckPos(){
        int pos = decks.getValue().indexOf(repo.getDeck(chosenDeck.getValue()));
        return pos;
    }

    public void setChosenDeck(Deck chosenDeck) {
        if (chosenDeck != null) {
            this.chosenDeck.setValue(repo.getDeck(chosenDeck));
        }else {
            this.chosenDeck.setValue(chosenDeck);
        }
        amountCards.setValue(chosenDeck.cards.size());
    }

    public MutableLiveData<Card> getCard() { return card; }

    public void setCardAtPos(int pos){ card.setValue(chosenDeck.getValue().cards.get(pos));}

    public void removeCard(int pos, Deck deck){
        repo.removeCard(pos, deck);
        getAmountCards().setValue(deck.cards.size());
    }

    public void setCard(Card card) { this.card.setValue(card); }

    public LiveData<ArrayList<Deck>> getDecks() { return decks; }

    public LiveData<Integer> getAmountDecks() { return amountDecks; }

    public LiveData<Deck> getChosenDeck() { return chosenDeck; }

    public void setDeckName(String deckName){ chosenDeck.getValue().setDeckName(deckName);}

    public String getDeckName(){ return chosenDeck.getValue().getDeckName();}

    public MutableLiveData<Integer> getAmountCards() { return amountCards; }
}
