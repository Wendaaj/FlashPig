package com.example.flashpig.ViewModel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.flashpig.DataBase.Repository;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class PairUpViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    PairUpViewModel pairUpViewModel = new PairUpViewModel();
    Card card = new Card();
    Card card2 = new Card();
    Card card3 = new Card();
    Deck deck = new Deck();

    @Before
    public void setup() {
        Repository repo = Repository.getInstance();
        deck.addCard(card);
        deck.addCard(card2);
        deck.addCard(card3);
        repo.saveDeck(deck);
        pairUpViewModel.init(deck);

    }

    @Test
    public void isPairForDeckSizeAndShowingCardsLarge() {
        pairUpViewModel.setCard1(card);
        pairUpViewModel.setCard2(card);
        pairUpViewModel.isPair();
        assertTrue(pairUpViewModel.getIsMatch().getValue());
    }

    @Test
    public void isPairForDeckSizeOne() {
        pairUpViewModel.setCard1(card);
        pairUpViewModel.setCard2(card);
        pairUpViewModel.setDeckSize(1);
        pairUpViewModel.isPair();
        assertTrue(pairUpViewModel.isEndOfGame().getValue());
    }

    @Test
    public void isPairForShowingCardsTwo() {
        pairUpViewModel.setCard1(card);
        pairUpViewModel.setCard2(card);
        pairUpViewModel.setShowingCards(2);
        pairUpViewModel.isPair();
        assertTrue(pairUpViewModel.getLoadNewCards().getValue());
    }


    @Test
    public void loadNewCards() {
        int before = pairUpViewModel.getChosenDeck().getValue().getAmountCards();
        pairUpViewModel.loadNewCards();
        int after = pairUpViewModel.getChosenDeck().getValue().getAmountCards();
        assertTrue(after == before - 3);
    }
}
