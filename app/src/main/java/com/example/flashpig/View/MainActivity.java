package com.example.flashpig.View;

import android.os.Bundle;

import com.example.flashpig.R;
import com.example.flashpig.ViewModel.DashboardViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.Menu;
import android.view.MenuItem;

import org.parceler.Parcels;

/**
 *
 * @responsibility Sets the initial screen
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras() != null) {
            DashboardViewModel viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
            viewModel.getChosenDeck().setValue(Parcels.unwrap(getIntent().getExtras().getParcelable("deck")));
        }
    }

    static final int REQUEST_TAKE_PHOTO = 1;
}