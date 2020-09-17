package com.example.flashpig.Model;

import android.media.Image;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import javax.crypto.spec.DESedeKeySpec;

/**
 * An abstract class that holds the common game logic.
 *
 * @author wendy
 * @version 2020-09-17
 */
public abstract class GameLogic {
    String gameTitle;
    View vGame;
    Deck deck;
    Card chosenCard1, chosenCard2;
    List<Card> frontsideCards, backsideCards;
    Button buttonHome, buttonRestart;

    /**
     * Constructor for GameLogic.
     * @param title The games name.
     * @param viewGame The games view.
     * @param deck The deck chosen to play the game.
     */
    public GameLogic(String title, View viewGame, Deck deck) {
        this.gameTitle = title;
        this.vGame = viewGame;
        this.deck = deck;
    }

    abstract Image gameWon(Deck deck);

    abstract void restartGame(View v);

    abstract void returnHome(View v);
}