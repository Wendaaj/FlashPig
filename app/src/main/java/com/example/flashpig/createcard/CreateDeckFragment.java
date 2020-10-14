package com.example.flashpig.createcard;
import com.example.flashpig.Model.Deck;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.flashpig.FirstFragment;
import com.example.flashpig.R;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Random;

public class CreateDeckFragment extends Fragment {

    private TextView dcTextView;
    private TextInputLayout dcTextInputLayout;
    private Button dcButton;
    private CardViewModel viewModel;
    Random rand = new Random();
    //public Deck deck;

    private String inputText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.deckcreate, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(CardViewModel.class);
        findViews(view);
        viewModel.initDeck();
        loadUI();
        //deck = new Deck(null, rand.nextInt() );

        dcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText = dcTextInputLayout.getEditText().getText().toString();
                if (!inputText.isEmpty()) {
                    viewModel.setDeckName(dcTextInputLayout.getEditText().getText().toString());
                    //deck.setDeckName(dcTextInputLayout.getEditText().getText().toString());
                    dcTextInputLayout.getEditText().getText().clear();
                    NavHostFragment.findNavController(CreateDeckFragment.this)
                            .navigate(R.id.action_createDeckFragment_to_cardFragment);
                } else{
                    Toast.makeText(getActivity(), "Please input deck name! OINK! OINK!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private  void loadUI() {
    }
    private void findViews(View view){
        dcTextView = view.findViewById(R.id.dcTextView);
        dcTextInputLayout = view.findViewById(R.id.dcTextInputLayout);
        dcButton = view.findViewById(R.id.dcButton);
    }



}
