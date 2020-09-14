package com.example.flashpig.Model;

import android.media.Image;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public abstract class GameLogic extends AppCompatActivity {
    String gameTitle;
    View vGame;
    Deck deck;
    Cards chosenCard1, chosenCard2;
    List<Cards> frontsideCards, backsideCards;
    Button buttonHome, buttonRestart;

    public GameLogic(String title, View viewGame, Deck deck, List<Cards> frontsideCards, List<Cards> backsideCards) {
        this.gameTitle = title;
        this.vGame = viewGame;
        this.deck = deck;

        for (Cards card : deck.deck) {
            //frontsideCards.add(card.getFrontside());
            //backsideCards.add(card.getFrontside());
        }
    }

    private Image gameWon(int deckSize) {}

    private void restartGame(View v) {}

    private void returnHome(View v) {}

    private void magicAlgoritm() {}
}

