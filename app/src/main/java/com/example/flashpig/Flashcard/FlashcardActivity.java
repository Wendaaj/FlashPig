package com.example.flashpig.Flashcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.flashpig.R;

import java.util.Objects;

public class FlashcardActivity extends AppCompatActivity {
    private FlashcardViewModel viewModel;
    private ConstraintLayout quitView;
    private Toolbar toolbar;
    private TextView title;
    private CheckBox checkBox;
    private Button yesBtn, noBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        findViews();
        loadPreferences();
        viewModel = new ViewModelProvider(this).get(FlashcardViewModel.class);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText(viewModel.flashcard.getDeck().getDeckName());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBox.isChecked()) {
                    quit();
                } else {
                    onBackPressed();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        savePreferences();
        super.onBackPressed();
    }

    private void savePreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("state", checkBox.isChecked());
        editor.apply();
    }

    private void loadPreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        boolean state = sharedPreferences.getBoolean("state", false);
        checkBox.setChecked(state);
    }

    private void findViews() {
        quitView = findViewById(R.id.quit_view);
        checkBox = findViewById(R.id.checkBox);
        toolbar = findViewById(R.id.flashcardToolbar);
        title = findViewById(R.id.txt_title);
        yesBtn = findViewById(R.id.btn_yes);
        noBtn = findViewById(R.id.btn_no);
    }

    private void quit() {
        quitView.setVisibility(View.VISIBLE);
        findViewById(R.id.fragment_container).setVisibility(View.INVISIBLE);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quitView.setVisibility(View.INVISIBLE);
                findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
            }
        });
    }
}