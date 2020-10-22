package com.example.flashpig.ViewModel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.example.flashpig.DataBase.Repository;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.View.FlashcardStartFragment;
import com.example.flashpig.ViewModel.FlashcardViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

public class FlashcardViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void setup(){
        Repository repo = Repository.getInstance();
        Deck deck = new Deck();
        Card card = new Card();
        card.setFrontsideStr("Test");
        card.setBacksideStr("Test");
        deck.addCard(card);
        repo.saveDeck(deck);
        FlashcardViewModel flashcardviewmodel = new FlashcardViewModel();
        flashcardviewmodel.init(deck);
    }

    @Test
    public void setCardsDifficulty() {
    }

    @Test
    public void hasFrontTxtAndImg() {
    }

    @Test
    public void hasBackTxtAndImg() {
    }
}

