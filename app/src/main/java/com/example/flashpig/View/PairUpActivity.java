package com.example.flashpig.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.flashpig.R;
import com.example.flashpig.ViewModel.PairUpViewModel;

import android.os.Bundle;
import android.view.View;

/**
 * The activity that provides the Pair Up game screen.
 *
 * @author Madeleine
 * @version 2020-10-16
 */


public class PairUpActivity extends AppCompatActivity {

    Toolbar toolbar;

    /**
     * Initializes the Pair Up activity by defining the UI and retrieving the widgets in that UI
     *
     * @param savedInstanceState saved instance state
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pairup);

        toolbar = findViewById(R.id.toolbarMemory);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        PairUpViewModel model = new ViewModelProvider(this).get(PairUpViewModel.class);
        model.getUsers().observe(this, users -> {
            // update UI
        });

    }

}
