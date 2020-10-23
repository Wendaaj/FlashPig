package com.example.flashpig.ViewModel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.flashpig.DataBase.Repository;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    CardViewModel cardViewModel = new CardViewModel();
    @Before
    public void setup(){
        Card card = new Card();
        Deck deck = new Deck();
        cardViewModel.getDeck().setValue(deck);
        cardViewModel.getCard().setValue(card);
        cardViewModel.initCard();
        cardViewModel.initDeck();
    }


    @Test
    public void resetViewModelWorksForCard() {
        cardViewModel.resetViewModel();
        assertNull(cardViewModel.getCard().getValue());

    }
    @Test
    public void resetViewModelWorksForDeck() {
        cardViewModel.resetViewModel();
        assertNull(cardViewModel.getDeck().getValue());
    }
}