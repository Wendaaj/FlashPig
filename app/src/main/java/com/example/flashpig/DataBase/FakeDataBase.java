package com.example.flashpig.DataBase;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;

import java.util.ArrayList;
import java.util.Random;

/**
 * A fake database that is replaceable when ready.
 *
 * @author wendy
 * @version 2020-10-18
 */
public class FakeDataBase implements DataBase{

    public FakeDataBase() {
        Random random = new Random();
        Deck deck0 = new Deck("Madematik", random.nextInt());
        deck0.addCard(new Card(random.nextInt(),"1",
                "1",null,null));
        deck0.addCard(new Card(random.nextInt(), "2",
                "2",null,null));
        deck0.addCard(new Card(random.nextInt(), "3",
                "3",null, null));
        deck0.addCard(new Card(random.nextInt(), "4",
                "4", null,null));
        deck0.addCard(new Card(random.nextInt(), "5",
                "5", null,null));
        deck0.addCard(new Card(random.nextInt(), "6",
                "6", null,null));

        Deck deck1 = new Deck("matte", random.nextInt());
        deck1.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck1.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck1.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));

        Deck deck2 = new Deck("engelska", random.nextInt());
        deck2.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck2.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));

        Deck deck3 = new Deck("fysik", random.nextInt());
        deck3.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));

        deckList.add(deck0);
        deckList.add(deck1);
        deckList.add(deck2);
        deckList.add(deck3);
    }

    @Override
    public ArrayList<Deck> getDeckList() { return deckList; }

    @Override
    public void addDeck(Deck deck) { deckList.add(deck); }

    @Override
    public void removeDeck(Deck deck) { deckList.remove(deck); }
}
