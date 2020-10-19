package com.example.flashpig.ViewModel;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.PairUp;

import java.util.List;

/**
 * The ViewModel for the Pair Up game.
 *
 * @author Madeleine
 * @version 2020-10-16
 */

public class PairUpViewModel extends ViewModel {

    private boolean hasFrontTxtAndImg;
    private boolean hasBackTxtAndImg;
    private MutableLiveData<String> backTxt = new MutableLiveData<>();
    private MutableLiveData<String> frontTxt = new MutableLiveData<>();
    private MutableLiveData<Bitmap> backImg = new MutableLiveData<>();
    private MutableLiveData<Bitmap> frontImg = new MutableLiveData<>();
    private MutableLiveData<Boolean> gameOver = new MutableLiveData<>();

    private PairUp pairUp;
    private String deckName;

    /**
     * Initialize the view model.
     */
    public void init(Deck deck){
        pairUp = new PairUp(deck.getDeckName(), deck);
        deckName = deck.getDeckName();
        gameOver.setValue(false);
        update();
    }

    public LiveData<List<Card>> getUsers() {
        if (pairUp.isEndOfGame() == null) {
            loadUsers();
        }
        return ;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
    }

    /**
     * Load the next cards values until game is over.
     */
    private void update(){

        if (!pairUp.isEndOfGame()){

        }
        else {
            gameOver.setValue(true);
        }
    }
}
