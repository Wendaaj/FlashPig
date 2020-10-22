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
            DashboardViewModel vm = new ViewModelProvider(this).get(DashboardViewModel.class);
            vm.setChosenDeck(Parcels.unwrap(getIntent().getExtras().getParcelable("deck")));
        }
    }

    static final int REQUEST_TAKE_PHOTO = 1;
}