package com.example.flashpig.ViewModel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.flashpig.DataBase.Repository;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class DashboardViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    DashboardViewModel dashboardViewModel;
    Deck deck;
    Card card;

    @Before
    public void setup() {
        //Bitmap bmp = BitmapFactory.decodeFile("R.drawable.partypig");
        Repository repo = Repository.getInstance();
        deck = new Deck();
        card = new Card();
        repo.saveDeck(deck);
        dashboardViewModel = new DashboardViewModel();
        //dashboardViewModel.init(repo);
        card.setFrontsideStr("Test");
        card.setBacksideStr("Test");
        //card.setBackImg(bmp);
        //card.setFrontImg(bmp);
        deck.addCard(card);
        dashboardViewModel.setChosenDeck(deck);
        dashboardViewModel.setCard(card);
    }

    @Test
    public void checkHasFrontTxtAndImg() {
      /* card.setBacksideStr(null);
       card.setBackImg(null);
       dashboardViewModel.setCard(card);
       assertTrue(dashboardViewModel.checkHasFrontTxtAndImg());
        */
    }

    @Test
    public void checkHasFrontTxtOnly() {
        card.setBacksideStr(null);
        card.setBackImg(null);
        card.setFrontImg(null);
        dashboardViewModel.setCard(card);
        assertTrue(dashboardViewModel.checkHasFrontTxtOnly());
    }

    @Test
    public void checkHasBackTxtAndImg() {
         /* card.setFrontsideStr(null);
       card.setFrontImg(null);
       dashboardViewModel.setCard(card);
       assertTrue(dashboardViewModel.checkHasBackTxtAndImg());
        */
    }

    @Test
    public void checkHasBackTxtOnly() {
        card.setFrontsideStr(null);
        card.setBackImg(null);
        card.setFrontImg(null);
        dashboardViewModel.setCard(card);
        assertTrue(dashboardViewModel.checkHasBackTxtOnly());
    }

    @Test
    public void removeDeck() {
        int before = dashboardViewModel.getAmountDecks().getValue();
        dashboardViewModel.removeDeck(deck);
        int after = dashboardViewModel.getAmountDecks().getValue();
        after = after + 1;
        assertEquals(before, after);
    }

    @Test
    public void removeCard() {
        int amountCardsBefore = dashboardViewModel.getChosenDeck().getValue().getAmountCards();
        dashboardViewModel.removeCard(0, deck);
        int amountCardsAfter = dashboardViewModel.getChosenDeck().getValue().getAmountCards();
        assertEquals(amountCardsBefore, amountCardsAfter + 1);
    }
}
