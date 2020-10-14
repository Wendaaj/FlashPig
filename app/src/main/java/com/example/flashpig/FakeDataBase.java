package com.example.flashpig;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FakeDataBase {
    private Random random = new Random();
    private ArrayList<Deck> deckList = new ArrayList<>();

    public FakeDataBase() {
        Deck deck = new Deck("Madematik", random.nextInt());
        deck.addCard(new Card(random.nextInt(),"Vad betyder bae på danska?",
                "Madde",null,null));
        deck.addCard(new Card(random.nextInt(), "Efter vem uppkom namnet Madematik?",
                "SMÄQ",null,null));
        deck.addCard(new Card(random.nextInt(), "Lever Smäq upp till sitt namn Madematik?",
                "Man kan aldrig vara för smart.",null, null));
        deck.addCard(new Card(random.nextInt(), "Kommer Smäq slakta tentorna?",
                "OM hon kommer", null,null));

        Deck deck1 = new Deck("matte", random.nextInt());
        Deck deck2 = new Deck("svenska", random.nextInt());
        Deck deck3 = new Deck("engelska", random.nextInt());
        Deck deck4 = new Deck("fysik", random.nextInt());
        Deck deck5 = new Deck("OOP", random.nextInt());
        Deck deck6 = new Deck("spanska", random.nextInt());
        deck1.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck1.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck1.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck2.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck2.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck2.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck2.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck3.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck3.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck4.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck5.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck6.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deckList.add(deck);
        deckList.add(deck1);
        deckList.add(deck2);
        deckList.add(deck3);
        deckList.add(deck4);
        deckList.add(deck5);
        deckList.add(deck6);
    }

    public ArrayList<Deck> getDeckList() {
        return deckList;
    }

    public void addDeck(Deck deck) {
        deckList.add(deck);
    }

    public void removeDeck(Deck deck) {
        deckList.remove(deck);
    }

}
