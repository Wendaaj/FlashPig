package com.example.flashpig.Model;

import android.media.Image;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import javax.crypto.spec.DESedeKeySpec;

public abstract class GameLogic {
    String gameTitle;
    View vGame;
    Deck deck;
    Cards chosenCard1, chosenCard2;
    List<Cards> frontsideCards, backsideCards;
    Button buttonHome, buttonRestart;

    public GameLogic(String title, View viewGame, Deck deck) {
        this.gameTitle = title;
        this.vGame = viewGame;
        this.deck = deck;
    }

    abstract Image gameWon(Deck deck);

    abstract void restartGame(View v);

    abstract void returnHome(View v);

    abstract void magicAlgoritm();
}

