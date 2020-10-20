package com.example.flashpig.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashpig.DataBase.Repository;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.PairUp;

import java.util.Collections;
import java.util.List;

/**
 * The ViewModel for the Pair Up game.
 *
 * @author Madeleine
 * @version 2020-10-19
 */

public class PairUpViewModel extends ViewModel {

    private Repository repo = Repository.getInstance();

    private MutableLiveData<Card> card1 = new MutableLiveData<>();
    private MutableLiveData<Card> card2 = new MutableLiveData<>();
    private MutableLiveData<Deck> chosenDeck = new MutableLiveData<>();
    private MutableLiveData<PairUp> pairUp = new MutableLiveData<>();

    private MutableLiveData<Boolean> isMatch = new MutableLiveData<>();
    private MutableLiveData<Boolean> ifLastPair = new MutableLiveData<>();
    private MutableLiveData<Boolean> isEndOfGame = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadNewCards = new MutableLiveData<>();

    private MutableLiveData<Boolean> setFirstViews = new MutableLiveData<>();

    int showingCards = 6;
    int deckSize;

    /**
     * Initialize the view model.
     */
    public void init(Deck deck){
        Deck chosenDeck = repo.getDeck(deck);
        deckSize = chosenDeck.getAmountCards();
        pairUp.setValue(new PairUp(chosenDeck));
        this.chosenDeck.setValue(deck);
        card1.setValue(null);
        isEndOfGame.setValue(false);
        ifLastPair.setValue(false);
    }

    public void isPair(){
        if (pairUp.getValue().isMatched(card1.getValue(), card2.getValue()) && showingCards != 2){
            isMatch.setValue(true);
            updateAmountCards();
        } else if (showingCards == 2 ){ //Is the last pair
            ifLastPair.setValue(true);
            if (pairUp.getValue().isEndOfGame()){
                isEndOfGame.setValue(true);
            } else {
                //delay;
                loadNewCards.setValue(true);
                loadNewCards();
            }
        } else {
            isMatch.setValue(false);
        }
        card1.setValue(null);
    }

    /**
     * Updates the game board with new cards
     */
    private void loadNewCards() {
        int i = 3;
        while (i != 0) {
            chosenDeck.getValue().cards.remove(0);
            i--;
        }
        showingCards = 6;
    }

    /**
     * Updates the amount of cards showing on the game board and the amount of car in the deck
     */
    private void updateAmountCards(){
        showingCards -= 2;
        deckSize--;
    }

    public LiveData<Boolean> isEndOfGame(){ return isEndOfGame; }

    public LiveData<Boolean> getLoadNewCards() { return loadNewCards; }

    public MutableLiveData<Boolean> getIfLastPair() { return ifLastPair; }

    public LiveData<Card> getCard1() { return card1; }

    public LiveData<Card> getCard2() {
        return card2;
    }

    public LiveData<Deck> getChosenDeck() {
        return chosenDeck;
    }

    public LiveData<Boolean> getIsMatch() { return isMatch; }

    public void setCard1(Card card1) {
        this.card1.setValue(card1);
    }

    public void setCard2(Card card2) { this.card2.setValue(card2); }

    public void setIsMatch(boolean isMatch) { this.isMatch.setValue(isMatch);}

    public int getDeckSize() {
        return deckSize;
    }

    public MutableLiveData<Boolean> getSetFirstViews() { return setFirstViews; }


}
