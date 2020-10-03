package com.example.flashpig.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.Flashcard;
import com.example.flashpig.R;

import java.util.Objects;
import java.util.Random;

public class FlashcardActivity extends AppCompatActivity {
    Deck deck;
    Flashcard flashcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

        Intent intent = getIntent();
        deck = intent.getParcelableExtra("Deck");
        flashcard = new Flashcard(deck.getDeckName(), deck);

        Toolbar toolbar = findViewById(R.id.flashcardToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(deck.getDeckName());

    }
}