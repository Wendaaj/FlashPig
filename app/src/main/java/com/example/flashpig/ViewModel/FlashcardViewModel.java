package com.example.flashpig.ViewModel;


import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashpig.DataBase.Repository;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.Difficulty;
import com.example.flashpig.Model.Flashcard;


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
        flashcard.setValue(new Flashcard(deck1));
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
            case EASY:
                flashcard.getValue().addEasyCard(currentCard.getValue());
                startTimer(currentCard.getValue(), 3600000);
                break;
            case MEDIUM:
                flashcard.getValue().addMediumCard(currentCard.getValue());
                startTimer(currentCard.getValue(), 600000);
                break;
            case HARD: flashcard.getValue().addHardCard(currentCard.getValue()); break;
        }
        update();
    }

    /**
     * Set a countdown timer on the card and when the time is up, add the card back to the game deck.
     * @param card The card to set the timer on.
     * @param i How many millisec to delay.
     */
    private void startTimer(Card card, long i){
        CountDownTimer countDownTimer = new CountDownTimer(i, 1000) {
            @Override
            public void onTick(long l) {}

            @Override
            public void onFinish() {
                flashcard.getValue().addCardToMain(card);
            }
        }.start();
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