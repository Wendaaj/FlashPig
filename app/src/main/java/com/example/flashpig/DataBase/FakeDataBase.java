package com.example.flashpig.DataBase;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;

import java.util.ArrayList;
import java.util.List;
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
        Deck deck0 = new Deck("Pig quiz", random.nextInt());
        deck0.addCard(new Card(random.nextInt(),"What are juvenile pigs known as?",
                "Piglets",null,null));
        deck0.addCard(new Card(random.nextInt(), "Pigs, like all suids, are native to where?",
                "The Eurasian and African continents.",null,null));
        deck0.addCard(new Card(random.nextInt(), "Because of the similarities between" +
                " pigs and humans, pigs are used for what?",
                "Human medical research.",null, null));
        deck0.addCard(new Card(random.nextInt(), "Domesticated pigs, are called what?",
                "Swine", null,null));
        deck0.addCard(new Card(random.nextInt(), "Pork is one of the most popular forms of meat for what?",
                "Human consumption.", null,null));
        deck0.addCard(new Card(random.nextInt(), "How much water per day do older pigs consume?",
                "Three to five gallons of water per day.", null,null));

        Deck deck1 = new Deck("Pig puns", random.nextInt());
        deck1.cards.add(new Card(random.nextInt(), "What do you call a Spanish pig?",
                "Porque.", null, null));
        deck1.cards.add(new Card(random.nextInt(), "How do pigs write top secret " +
                "messages?", "With invisible oink!", null, null));
        deck1.cards.add(new Card(random.nextInt(), "What’s it called when a bunch of" +
                " pigs compete in athletic games?", "The Olympigs", null, null));
        deck1.cards.add(new Card(random.nextInt(), "What do you call a pig thief?",
                "A hamburglar.", null, null));
        deck1.cards.add(new Card(random.nextInt(), "What do you call a pig that does " +
                "a lot of charity work?", "Philanthropig", null, null));
        deck1.cards.add(new Card(random.nextInt(), "what do you call a pigs karate punch",
                "Pork chop lol", null, null));
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

    @Override
    public void removeCard(int pos, Deck deck) { deckList.get(deckList.indexOf(deck)).deleteCard(deck.getCard(pos)); }
}
