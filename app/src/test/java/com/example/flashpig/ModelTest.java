package com.example.flashpig;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.Difficulty;
import com.example.flashpig.Model.Flashcard;
import com.example.flashpig.Model.FlashcardProgress;
import com.example.flashpig.Model.Memory;
import com.example.flashpig.Model.PairUp;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModelTest {
    Random rand = new Random();
    Deck deck = new Deck("Madematik", rand.nextInt());
    Card card0 = new Card(rand.nextInt(),"Vad betyder bae på danska?",
            "Madde",null,null);
    Card card1 = new Card(rand.nextInt(), "Efter vem uppkom namnet Madematik?",
            "SMÄQ",null,null);
    Card card2 = new Card(rand.nextInt(), "Lever Smäq upp till sitt namn Madematik?",
            "Man kan aldrig vara för smart.",null, null);
    Card card3 = new Card(rand.nextInt(), "Kommer Smäq slakta tentorna?",
            "OM hon kommer", null,null);
    Card card4 = new Card(rand.nextInt(), null, null, null,
            null);
    Card card5 = new Card(rand.nextInt(), "hej", "hej", null,
            null);
    //Tests for Flashcard
    @Test
    public void canChooseDifficulty(){
        Flashcard flashcard = new Flashcard("Madematik", deck);
        flashcard.addEasyCard(card0);
        Assert.assertEquals(card0.getDifficulty(), Difficulty.EASY);

        flashcard.addMediumCard(card0);
        Assert.assertEquals(card0.getDifficulty(), Difficulty.MEDIUM);

        flashcard.addHardCard(card0);
        Assert.assertEquals(card0.getDifficulty(), Difficulty.HARD);
    }

    @Test
    public void canTurnOverCard() {
        Flashcard flashcard = new Flashcard("Madematik", deck);
        flashcard.turnOver(card3);
        Assert.assertEquals("OM hon kommer", card3.getBacksideStr());
    }

    //Tests for FlashcardProgess
    @Test
    public void canShowCardsAfterDifficulty() {
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        card1.setDifficulty(Difficulty.EASY);
        card2.setDifficulty(Difficulty.EASY);
        card3.setDifficulty(Difficulty.NOTHING);
        List<Card> actualList = new ArrayList<>();
        actualList.add(card1);
        actualList.add(card2);
        FlashcardProgress flashcardProgress = new FlashcardProgress(deck);
        List<Card> expectedList = flashcardProgress.showCards(Difficulty.EASY);
        Assert.assertEquals(expectedList,actualList);
    }

    @Test
    public void canChangeCardsDifficulty() {
        FlashcardProgress flashcardProgress = new FlashcardProgress(deck);
        flashcardProgress.resetCard(card1);
        flashcardProgress.moveCard(card2, Difficulty.MEDIUM);
        Assert.assertEquals(card1.getDifficulty(), Difficulty.NOTHING);
        Assert.assertEquals(card2.getDifficulty(), Difficulty.MEDIUM);
    }

    @Test
    public void memoryIsMatchPositive() {
        Card chosenCard1 = new Card(2, "Vem är Zorri?",
                "Kungen", null, null);

        Card chosenCard2 = new Card(2, "Vem är Zorri?",
                "Kungen", null, null);

        Deck deck = new Deck("Legender", 2);

        Memory memory = new Memory("GameOfTheGame", deck, deck, 1);
        int deckSize = deck.getAmountCards();

        memory.isMatch(chosenCard1, deck);

        Assert.assertEquals(deckSize, 0);
    }

    @Test
    public void memoryIsMatchNegative() { //FAAAAIL
        Card chosenCard1 = new Card(2, "Vem är Zorri?",
                "Kungen", null, null);

        Card chosenCard2 = new Card(3, "Vem är han?",
                "Han e han", null, null);


        Deck deck = new Deck("Legender", 2);

        Memory memory = new Memory("GameOfTheGame", deck, deck, 1);
        int deckSize = deck.getAmountCards();

        memory.isMatch(chosenCard1, deck);

        Assert.assertEquals(deckSize, 1);
    }

    @Test
    public void pairUpIsMatchPositive() {
        Card chosenCard1 = new Card(2, "Vem är Zorri?",
                "Kungen", null, null);

        Card chosenCard2 = new Card(2, "Vem är Zorri?",
                "Kungen", null, null);

        Deck deck = new Deck("Legender", 2);

        PairUp pairUp = new PairUp("FunGame", deck);
        int deckSize = deck.getAmountCards();

        pairUp.isMatch(chosenCard1, deck);

        Assert.assertEquals(deckSize, 0);
    }

    @Test
    public void canCreateCardFront(){
        card4.EditFrontside(null, null);

        card5.EditFrontside("hej", null);

        Assert.assertEquals(card4.getFrontsideStr(),null);

        Assert.assertEquals(card5.getFrontsideStr(),"hej");
    }

    @Test
    public void canCreateCardBack(){
        card4.setFrontside(false);
        card5.setFrontside(false);

        card4.EditBackside(null, null);

        card5.EditBackside("Hej", null);

        Assert.assertEquals(card4.getBacksideStr(),null);

        Assert.assertEquals(card5.getBacksideStr(),"Hej");
    }


    /*@Test
    public void pairUpIsMatchNegative() { //FAAAIL
        Card chosenCard1 = new Card(2, "Vem är Zorri?",
                "Kungen", null, null);

        Card chosenCard1 = new Card(3, "Vem är Zorri?",
                "Kungen", null, null);

        Deck deck = new Deck("Legender", 2);

        PairUp pairUp = new PairUp("FunGame", deck);
        int deckSize = deck.getAmountCards();

        pairUp.isMatch(chosenCard1, deck);

        Assert.assertEquals(deckSize, 1);
    }*/

    @Test
    public void testFlipCardBacksideDown() {

        Card chosenCard1 = new Card(2, "Vem är Zorri?",
                "Kungen", null, null);

        Memory memory = new Memory("GameOfTheGame", deck, deck, 1);

        chosenCard1.setFrontside(false);
        memory.flipCard(chosenCard1);

        Assert.assertTrue(chosenCard1.isFrontside());

    }

    @Test
    public void testFlipCardFrontsideUp() {

        Card chosenCard1 = new Card(2, "Vem är Zorri?",
                "Kungen", null, null);

        Memory memory = new Memory("GameOfTheGame", deck, deck, 1);

        chosenCard1.setFrontside(true);
        memory.flipCard(chosenCard1);

        Assert.assertEquals(chosenCard1.isFrontside(), false);

    }
   /* @Test
    void ifMatchPositive() {

        //NOT DONE

        Deck deck = new Deck("Legender", 2);

        int deckSize = deck.getAmountCards(); //deckSize = 0
        Memory memory = new Memory("GameOfTheGame", deck, deck, 1);
        memory.ifMatch();

        //Assert.assertTrue(Image pops out);

    }

    @Test
    void ifMatchNegative() {

        // NOT DONE
        Card chosenCard1 = new Card(2,true, "Vem är Zorri?",
                "Kungen", null, null, Difficulties.EASY);

        Card chosenCard2 = new Card(2,true, "Vem är Zorri?",
                "Kungen", null, null, Difficulties.EASY);
        Deck deck = new Deck("Legender", 2);

        int deckSize = deck.getAmountCards();  //deckSize = 2
        Memory memory = new Memory("GameOfTheGame", deck, deck, 1);
        memory.ifMatch();

        //Assert.assertEquals(Nothing happens);

    }

    @Test
    void gameWon() {
        Deck deck = new Deck("Legender", 2);

        //NOT DONE
    }



    @Test
    void restartGame() {

        //NOT DONE
    }



    @Test
    void returnHome() {

        //NOT DONE
    }
*/
//Test Deck
    @Test
    public void testAddCard() {
        deck.addCard(card0);
        Assert.assertEquals(deck.getAmountCards(), 1);
    }

    @Test
    public void testDeleteCardNegative() {
        deck.addCard(card0);
        deck.addCard(card1);
        deck.deleteCard(card0);
        Assert.assertEquals(deck.deckContainsCard(card0), false);
        Assert.assertEquals(deck.getAmountCards(), 1);

    }

    @Test
    public void testDeleteCardPositive() {
        deck.addCard(card0);
        deck.addCard(card1);
        deck.deleteCard(card0);
        Assert.assertTrue(deck.deckContainsCard(card1));
        Assert.assertEquals(deck.getAmountCards(), 1);
    }

    @Test
    public void testClearDeckPositive() {
        deck.addCard(card0);
        deck.addCard(card1);
        deck.clearDeck(deck.cards);
        Assert.assertEquals(deck.getAmountCards(), 0);
    }
    @Test
    public void testClearDeckNegative() {
        deck.addCard(card0);
        deck.addCard(card1);
        deck.clearDeck(deck.cards);
        deck.addCard(card2);
        Assert.assertEquals(deck.getAmountCards(), 1);
    }

    @Test
    public void testGetAmountCards() {
        deck.addCard(card0);
        deck.addCard(card1);
        Assert.assertEquals(deck.getAmountCards(), 2);
    }

    @Test
    public void testDeckContainsCardPositive() {
        deck.addCard(card0);
        deck.addCard(card1);
        Assert.assertTrue(deck.deckContainsCard(card0));
    }
    @Test
    public void testDeckContainsCardNegative() {
        deck.addCard(card0);
        deck.addCard(card1);
        Assert.assertFalse(deck.deckContainsCard(card2));
    }

}
