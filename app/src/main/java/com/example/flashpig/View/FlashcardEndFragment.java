package com.example.flashpig.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flashpig.Model.Deck;
import com.example.flashpig.R;
import com.example.flashpig.ViewModel.FlashcardViewModel;

import org.parceler.Parcels;

/**
 * Checks if the player wants to return home or restart the game.
 *
 * @author wendy
 * @version 2020-10-04
 * */
public class FlashcardEndFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_end, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlashcardViewModel viewModel = new ViewModelProvider(getActivity()).get(FlashcardViewModel.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("deck", Parcels.wrap(viewModel.getFlashcard().getValue().getDeck()));
                NavHostFragment.findNavController(FlashcardEndFragment.this)
                        .navigate(R.id.action_flashcardEndFragment_to_mainActivity2, bundle);
            }
        });

        view.findViewById(R.id.btn_play_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavHostFragment.findNavController(FlashcardEndFragment.this)
                        .navigate(R.id.action_flashcardEndFragment_to_flashcardStartFragment);
            }
        });
    }
}