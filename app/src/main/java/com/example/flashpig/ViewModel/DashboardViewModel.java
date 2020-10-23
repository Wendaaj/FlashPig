package com.example.flashpig.ViewModel;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashpig.DataBase.Repository;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;

import java.util.ArrayList;

/**
 * @author wendy
 * @responsibility ViewModel class for the DashboardFragment and EditDeckFragment
 * @version 22-10-20
 */

public class DashboardViewModel extends ViewModel {
    private Repository repo = Repository.getInstance();
    private MutableLiveData<ArrayList<Deck>> decks = new MutableLiveData<>();
    private MutableLiveData<Deck> chosenDeck = new MutableLiveData<>();
    private MutableLiveData<Integer> amountDecks = new MutableLiveData<>();
    private MutableLiveData<Card> card = new MutableLiveData<>();
    private MutableLiveData<Integer> amountCards = new MutableLiveData<>();
    private MutableLiveData<Boolean> isFrontside = new MutableLiveData<>();

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

    public void removeDeck(int pos) {
        repo.removeDeck(pos);
        amountDecks.setValue(decks.getValue().size());
    }

    public int getChosenDeckPos(){
        int pos = decks.getValue().indexOf(repo.getDeck(chosenDeck.getValue()));
        return pos;
    }

    public void removeCard(int cardPos, int deckPos){
        repo.removeCard(cardPos, deckPos);
        getAmountCards().setValue(chosenDeck.getValue().cards.size());
    }

    public MutableLiveData<Card> getCard() { return card; }

    public void setCardAtPos(int pos){ card.setValue(chosenDeck.getValue().cards.get(pos));}

    public String getCardFrontTxt(){ return card.getValue().getFrontsideStr();}

    public Bitmap getCardFrontImg(){ return card.getValue().getFrontImg();}

    public String getCardBackTxt(){ return card.getValue().getBacksideStr();}

    public Bitmap getCardBackImg(){ return card.getValue().getBackImg();}

    public int getAmountCardsAtPos(int pos){ return decks.getValue().get(pos).cards.size();}

    public LiveData<ArrayList<Deck>> getDecks() { return decks; }

    public LiveData<Integer> getAmountDecks() { return amountDecks; }

    public MutableLiveData<Deck> getChosenDeck() { return chosenDeck; }

    public Deck getDeck(){return chosenDeck.getValue();}

    public Deck getDeckAtPos(int pos){ return decks.getValue().get(pos);}

    public MutableLiveData<Boolean> getIsFrontside() { return isFrontside; }

    public void setDeckName(String deckName){ chosenDeck.getValue().setDeckName(deckName);}

    public String getDeckName(){ return chosenDeck.getValue().getDeckName();}

    public MutableLiveData<Integer> getAmountCards() { return amountCards; }

    public boolean decksEmpty(){
        return decks.getValue().isEmpty();
    }

}
