package com.example.flashpig.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flashpig.R;

/**
 * Represents the end screen of a Pair Up game
 *
 * @author Madeleine
 * @version 2020-10-16
 */

public class PairUpFragmentEnd extends Fragment {

    /**
     * Does initial creations of the fragment
     *
     * @param savedInstanceState
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * Creates and returns the fragment's view hierarchy
     *
     * @param inflater Used to inflate views in the fragment
     * @param container Generates the LayoutParams of the view
     * @param savedInstanceState Save instance state
     * @return The view which represents the end of a Pair Up game
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.pairup_fragment_end, container, false);
    }

    /**
     *  Subclasses initialize themselves after their view hierarchy has been created
     *
     * @param view The view returned by onCreateView(LayoutInflater, ViewGroup, bundle)
     * @param savedInstanceState Saved instance state
     */

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(PairUpFragmentEnd.this)
                        .navigate(R.id.action_pairUpFragmentEnd_to_mainActivity);
            }
        });

        view.findViewById(R.id.btn_play_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(PairUpFragmentEnd.this)
                        .navigate(R.id.action_pairUpFragmentEnd_to_pairUpFragmentStart);
            }
        });

    }
}
