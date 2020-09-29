package com.example.flashpig.Flashcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.flashpig.R;

import java.util.Objects;

public class FlashcardActivity extends AppCompatActivity {
    FlashcardViewModel viewModel;
    Toolbar toolbar;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        viewModel = new ViewModelProvider(this).get(FlashcardViewModel.class);

        toolbar = findViewById(R.id.flashcardToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title = findViewById(R.id.txt_title);
        title.setText(viewModel.flashcard.getDeck().getDeckName());
    }
}