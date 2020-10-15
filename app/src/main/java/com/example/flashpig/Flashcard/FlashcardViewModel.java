package com.example.flashpig.Flashcard;


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
    private Random rand = new Random();
    private Deck deck;
    private Flashcard flashcard;
    private String deckName;
    private MutableLiveData<String> backTxt = new MutableLiveData<>();
    private MutableLiveData<String> frontTxt = new MutableLiveData<>();
    private MutableLiveData<String> easyAmount = new MutableLiveData<>();
    private MutableLiveData<String> mediumAmount = new MutableLiveData<>();
    private MutableLiveData<String> hardAmount = new MutableLiveData<>();
    private MutableLiveData<Boolean> gameOver = new MutableLiveData<>();

    /**
     * Initialize the viewmodel.
     */
    public void init(){
        deck = fillDeck();
        flashcard = new Flashcard(deck.getDeckName(), deck);
        deckName = deck.getDeckName();
        gameOver.setValue(false);
        update();
    }

    /**
     * Load the next cards values until game is over.
     */
    private void update(){
        if (!flashcard.roundIsOver()){
            backTxt.setValue(flashcard.getCurrentCard().getBacksideStr());
            frontTxt.setValue(flashcard.getCurrentCard().getFrontsideStr());
            easyAmount.setValue(Integer.toString(flashcard.getAmountCards(Difficulty.EASY)));
            mediumAmount.setValue(Integer.toString(flashcard.getAmountCards(Difficulty.MEDIUM)));
            hardAmount.setValue(Integer.toString(flashcard.getAmountCards(Difficulty.HARD)));
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

    private Deck fillDeck() {
        Deck deck = new Deck("Madematik", rand.nextInt());
        deck.addCard(new Card(rand.nextInt(),"Vad betyder bae på danska?",
                "Madde",null,null));
        deck.addCard(new Card(rand.nextInt(), "Efter vem uppkom namnet Madematik?",
                "SMÄQ",null,null));
        deck.addCard(new Card(rand.nextInt(), "Lever Smäq upp till sitt namn Madematik?",
                "Man kan aldrig vara för smart.",null, null));
        deck.addCard(new Card(rand.nextInt(), "Kommer Smäq slakta tentorna?",
                "OM hon kommer", null,null));
        return deck;
    }

    public LiveData<Boolean> getGameOver() { return gameOver; }

    public LiveData<String> getBackTxt() { return backTxt; }

    public LiveData<String> getFrontTxt() { return frontTxt; }

    public LiveData<String> getEasyAmount() { return easyAmount; }

    public LiveData<String> getMediumAmount() { return mediumAmount; }

    public LiveData<String> getHardAmount() { return hardAmount; }

    public String getDeckName() { return deckName; }
}