package com.example.flashpig.ViewModel;


import android.graphics.Bitmap;
import android.telephony.CellSignalStrength;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.Difficulty;
import com.example.flashpig.Model.Flashcard;

import java.util.Random;

/**
 * The viewmodel for the Flashcard game.
 *
 * @author wendy
 * @version 2020-10-12
 */
public class FlashcardViewModel extends ViewModel {
    private Flashcard flashcard;
    private String deckName;
    private boolean hasFrontTxtAndImg;
    private boolean hasBackTxtAndImg;
    private MutableLiveData<String> backTxt = new MutableLiveData<>();
    private MutableLiveData<String> frontTxt = new MutableLiveData<>();
    private MutableLiveData<Bitmap> backImg = new MutableLiveData<>();
    private MutableLiveData<Bitmap> frontImg = new MutableLiveData<>();
    private MutableLiveData<String> easyAmount = new MutableLiveData<>();
    private MutableLiveData<String> mediumAmount = new MutableLiveData<>();
    private MutableLiveData<String> hardAmount = new MutableLiveData<>();
    private MutableLiveData<Boolean> gameOver = new MutableLiveData<>();

    /**
     * Initialize the view model.
     */
    public void init(Deck deck){
        flashcard = new Flashcard(deck.getDeckName(), deck);
        deckName = deck.getDeckName();
        gameOver.setValue(false);
        update();
    }

    /**
     * Load the next cards values until game is over.
     */
    private void update(){
        easyAmount.setValue(Integer.toString(flashcard.getAmountCards(Difficulty.EASY)));
        mediumAmount.setValue(Integer.toString(flashcard.getAmountCards(Difficulty.MEDIUM)));
        hardAmount.setValue(Integer.toString(flashcard.getAmountCards(Difficulty.HARD)));

        if (!flashcard.roundIsOver()){
            checkHasFrontTxtAndImg();
            checkHasBackTxtAndImg();
            backTxt.setValue(flashcard.getCurrentCard().getBacksideStr());
            frontTxt.setValue(flashcard.getCurrentCard().getFrontsideStr());
            backImg.setValue(flashcard.getCurrentCard().getBackImg());
            frontImg.setValue(flashcard.getCurrentCard().getFrontImg());
        }
        else {
            gameOver.setValue(true);
        }
    }

    /**
     * Sets the cards difficulty and updates the game.
     * @param difficulty The difficulty to set.
     */
    public void setCardsDifficulty(Difficulty difficulty){
        switch (difficulty) {
            case EASY: flashcard.addEasyCard(flashcard.getCurrentCard()); break;
            case MEDIUM: flashcard.addMediumCard(flashcard.getCurrentCard()); break;
            case HARD: flashcard.addHardCard(flashcard.getCurrentCard()); break;
        }
        update();
    }

    /**
     * Checks if card has both front text and front image.
     */
    private void checkHasFrontTxtAndImg() {
        hasFrontTxtAndImg = flashcard.getCurrentCard().getFrontImg() != null && !flashcard.getCurrentCard().getFrontsideStr().isEmpty();
    }

    /**
     * Checks if card has both front text and front image.
     */
    private void checkHasBackTxtAndImg() {
        hasBackTxtAndImg = flashcard.getCurrentCard().getBackImg() != null && !flashcard.getCurrentCard().getBacksideStr().isEmpty();
    }

    public boolean hasFrontTxtAndImg() { return hasFrontTxtAndImg; }

    public boolean hasBackTxtAndImg() { return hasBackTxtAndImg; }

    public MutableLiveData<Boolean> getGameOver() { return gameOver; }

    public LiveData<String> getBackTxt() { return backTxt; }

    public LiveData<String> getFrontTxt() { return frontTxt; }

    public LiveData<Bitmap> getBackImg() { return backImg; }

    public LiveData<Bitmap> getFrontImg() { return frontImg; }

    public LiveData<String> getEasyAmount() { return easyAmount; }

    public LiveData<String> getMediumAmount() { return mediumAmount; }

    public LiveData<String> getHardAmount() { return hardAmount; }

    public String getDeckName() { return deckName; }

    public Deck getDeck() {return flashcard.getDeck();}
}