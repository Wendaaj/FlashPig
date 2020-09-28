package com.example.flashpig.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * An abstract class that holds the common game logic.
 *
 * @author wendy
 * @version 2020-09-17
 */
public abstract class GameLogic implements Parcelable {
    String gameTitle;
    Deck deck;
    Card chosenCard1, chosenCard2;
    List<Card> frontsideCards, backsideCards;

    /**
     * Constructor for GameLogic.
     * @param title The games name.
     * @param deck The deck chosen to play the game.
     */
    public GameLogic(String title, Deck deck) {
        this.gameTitle = title;
        this.deck = deck;
    }

    public boolean gameWon(Deck deck) {
        return deck.getAmountCards() == 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
      //  dest.writeParcelable(deck, flags);
        dest.writeString(gameTitle);
    }

    protected GameLogic(Parcel in) {
        gameTitle = in.readString();
        deck = (Deck) in.readParcelable(Deck.class.getClassLoader());
    }



}