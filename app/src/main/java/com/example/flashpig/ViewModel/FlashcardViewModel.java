package com.example.flashpig.ViewModel;


import android.graphics.Bitmap;
import android.telephony.CellSignalStrength;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashpig.DataBase.FakeDataBase;
import com.example.flashpig.DataBase.Repository;
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
    private Repository repo = Repository.getInstance();

    private MutableLiveData<Flashcard> flashcard = new MutableLiveData<>();
    private MutableLiveData<Card> currentCard = new MutableLiveData<>();
    private MutableLiveData<Boolean> gameOver = new MutableLiveData<>();

    private boolean hasFrontTxtAndImg;
    private boolean hasBackTxtAndImg;

    /**
     * Initialize the view model.
     */
    public void init(Deck deck){
        Deck deck1 = repo.getDeck(deck);
        flashcard.setValue(new Flashcard(deck1.getDeckName(), deck1));
        currentCard.setValue(flashcard.getValue().getCurrentCard());
        gameOver.setValue(false);
        update();
    }

    /**
     * Load the next cards values until game is over.
     */
    private void update(){
        flashcard.setValue(flashcard.getValue());
        if (!flashcard.getValue().roundIsOver()){
            currentCard.setValue(flashcard.getValue().getCurrentCard());
            checkHasFrontTxtAndImg();
            checkHasBackTxtAndImg();
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
            case EASY: flashcard.getValue().addEasyCard(currentCard.getValue()); break;
            case MEDIUM: flashcard.getValue().addMediumCard(currentCard.getValue()); break;
            case HARD: flashcard.getValue().addHardCard(currentCard.getValue()); break;
        }
        update();
    }

    /**
     * Checks if card has both front text and front image.
     */
    private void checkHasFrontTxtAndImg() {
        hasFrontTxtAndImg = currentCard.getValue().getFrontImg() != null && !currentCard.getValue().getFrontsideStr().isEmpty();
    }

    /**
     * Checks if card has both front text and front image.
     */
    private void checkHasBackTxtAndImg() {
        hasBackTxtAndImg = currentCard.getValue().getBackImg() != null && !currentCard.getValue().getBacksideStr().isEmpty();
    }

    public boolean hasFrontTxtAndImg() { return hasFrontTxtAndImg; }

    public boolean hasBackTxtAndImg() { return hasBackTxtAndImg; }

    public MutableLiveData<Boolean> getGameOver() { return gameOver; }

    public LiveData<Flashcard> getFlashcard() { return flashcard; }

    public LiveData<Card> getCurrentCard() { return currentCard; }
}