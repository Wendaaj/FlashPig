package com.example.flashpig.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashpig.DataBase.Repository;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.PairUp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 *
 * @author Madeleine
 * @responsibility The ViewModel for the Pair Up game.
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

    private int showingCards = 6;
    private int deckSize;
    private List<Card> gameDeck = new ArrayList<>();

    /**
     * Initialize the view model.
     */
    public void init(){
        gameDeck.addAll(chosenDeck.getValue().cards);
        deckSize = chosenDeck.getValue().getAmountCards();
        pairUp.setValue(new PairUp(chosenDeck.getValue()));
    }

    public void isPair(){
        if (deckSize!=1){
            if (pairUp.getValue().isMatched(card1.getValue(), card2.getValue()) && showingCards != 2){
                isMatch.setValue(true);
                updateAmountCards();
            } else if (showingCards == 2 ){
                ifLastPair.setValue(true);
                updateAmountCards();
                loadNewCards();
                loadNewCards.setValue(true);
            }
            else {
                isMatch.setValue(false);
            }
            card1.setValue(null);
        }
        else{
            isEndOfGame.setValue(true);
        }
    }

    /**
     * Updates the game board with new cards
     */
    void loadNewCards() {
        if (deckSize >= 3) {
            int i = 3;
            while (i != 0) {
                //chosenDeck.getValue().cards.remove(0);
                gameDeck.remove(0);
                i--;
            }
            showingCards = 6;

        }
        if(deckSize==2){
            int i = 2;
            while (i != 0) {
                //chosenDeck.getValue().cards.remove(0);
                gameDeck.remove(0);
                i--;
            }
            showingCards = 4;
        }
        if(deckSize==1){
            int i = 1;
            while (i != 0) {
                //chosenDeck.getValue().cards.remove(0);
                gameDeck.remove(0);
                i--;
            }
            showingCards = 2;

        }
    }

    /**
     * Updates the amount of cards showing on the game board and the amount of car in the deck
     */
    private void updateAmountCards(){
        showingCards -= 2;
        deckSize--;
    }

    public boolean cardOneNull() {
        if (card1.getValue() == null) {return true;
        }else {return false;}
    }

    public void setIsEndOfGame(boolean isEndOfGame) {
        this.isEndOfGame.setValue(isEndOfGame);
    }

    public void reloadDeck(){
        getChosenDeck().getValue().cards.addAll(gameDeck);
    }

    public LiveData<Boolean> isEndOfGame(){ return isEndOfGame; }

    public LiveData<Boolean> getLoadNewCards() { return loadNewCards; }

    public MutableLiveData<Boolean> getIfLastPair() { return ifLastPair; }

    public MutableLiveData<Deck> getChosenDeck() {
        return chosenDeck;
    }

    public LiveData<Boolean> getIsMatch() { return isMatch; }

    public Deck getDeck(){return chosenDeck.getValue();}

    public List<Card> getCards(){return chosenDeck.getValue().cards;}

    public Card getItem(int position) { return getCards().get(position); }

    public void setFrontPosTrue(int position){
        getItem(position).setFrontside(true);
    }

    public void setFrontPosFalse(int position){
        getItem(position).setFrontside(true);
    }

    public void setCard1(Card card) {
        card1.setValue(card);
    }

    public void setCard2(Card card) { card2.setValue(card); }

    public MutableLiveData<Boolean> getSetFirstViews() { return setFirstViews; }

    public String getFrontTxtAtPos(int pos){ return gameDeck.get(pos).getFrontsideStr();}

    public String getBackTxtAtPos(int pos){ return gameDeck.get(pos).getBacksideStr();}

    public boolean getIsFrontsideAtPos(int pos){ return gameDeck.get(pos).isFrontside();}

    public void setDeckSize(int i){this.deckSize=i;}

    public void setShowingCards(int i){this.showingCards =i;}

}
