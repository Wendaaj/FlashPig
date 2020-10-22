package com.example.flashpig.View;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.flashpig.R;
import com.example.flashpig.ViewModel.CardViewModel;
import com.google.android.material.textfield.TextInputLayout;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author Jepser
 * @responsibility Fragment for Create deck
 * @version 22-10-20
 */

public class CreateDeckFragment extends Fragment {
    private TextView dcTextView;
    private TextInputLayout dcTextInputLayout;
    private Button dcButton;
    private Button ccYesBtn;
    private Button ccNoBtn;
    private CardViewModel viewModel;
    private String inputText;
    private Toolbar toolbar;
    private CheckBox cccheckBox;
    private ConstraintLayout goBack;
    private ImageView pig;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.deckcreate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(CardViewModel.class);
        findViews(view);
        setView();
        loadPreferences();

        if (viewModel.getDeck().getValue() != null){
            dcTextInputLayout.getEditText().setText(viewModel.getDeck().getValue().getDeckName());
        }

        dcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText = dcTextInputLayout.getEditText().getText().toString();
                if (!inputText.isEmpty()) {
                    if(viewModel.getDeck().getValue() != null){
                        viewModel.setDeckName(dcTextInputLayout.getEditText().getText().toString());
                    }else{
                        viewModel.initDeck();
                        viewModel.setDeckName(dcTextInputLayout.getEditText().getText().toString());
                    }
                    dcTextInputLayout.getEditText().getText().clear();
                    NavHostFragment.findNavController(CreateDeckFragment.this)
                            .navigate(R.id.action_createDeckFragment_to_cardFragment);
                } else{
                    Toast.makeText(getActivity(), "Please input deck name! OINK! OINK!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackCheckboxHandler();
                dcTextInputLayout.getEditText().getText().clear();
                viewModel.resetViewModel();
            }
        });
    }

    private void findViews(View view){
        dcTextView = view.findViewById(R.id.dcTextView);
        dcTextInputLayout = view.findViewById(R.id.dcTextInputLayout);
        dcButton = view.findViewById(R.id.dcButton);
        toolbar = view.findViewById(R.id.toolbar2);
        cccheckBox = view.findViewById(R.id.checkBox3);
        ccYesBtn = view.findViewById(R.id.yesBtn1);
        ccNoBtn = view.findViewById(R.id.noBtn1);
        goBack = view.findViewById(R.id.goBack);
        pig = view.findViewById(R.id.imageView3);
    }

    private void goBackCheckboxHandler(){
        if (!cccheckBox.isChecked()) {
            setCheckboxView();
            goBack.setVisibility(View.VISIBLE);
            setYesNoDeckBtn();
        } else {
            goBack.setVisibility(View.INVISIBLE);
            savePreferences();
            dcTextInputLayout.getEditText().getText().clear();
            viewModel.resetViewModel();
            NavHostFragment.findNavController(CreateDeckFragment.this)
                    .navigate(R.id.action_createDeckFragment_to_FirstFragment);

        }
    }
    private void setCheckboxView(){
        dcTextView.setVisibility(View.INVISIBLE);
        dcTextInputLayout.setVisibility(View.INVISIBLE);
        dcButton.setVisibility(View.INVISIBLE);
        pig.setVisibility(View.INVISIBLE);
        cccheckBox.setVisibility(View.VISIBLE);
        ccYesBtn.setVisibility(View.VISIBLE);
        ccNoBtn.setVisibility(View.VISIBLE);
        goBack.setVisibility(View.VISIBLE);
    }
    private void setView(){
        dcTextView.setVisibility(View.VISIBLE);
        dcTextInputLayout.setVisibility(View.VISIBLE);
        dcButton.setVisibility(View.VISIBLE);
        pig.setVisibility(View.VISIBLE);
        cccheckBox.setVisibility(View.INVISIBLE);
        ccYesBtn.setVisibility(View.INVISIBLE);
        ccNoBtn.setVisibility(View.INVISIBLE);
        goBack.setVisibility(View.INVISIBLE);
    }

    private void setYesNoDeckBtn() {
        ccYesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreferences();
                dcTextInputLayout.getEditText().getText().clear();
                viewModel.resetViewModel();
                goBack.setVisibility(View.INVISIBLE);
                NavHostFragment.findNavController(CreateDeckFragment.this)
                        .navigate(R.id.action_createDeckFragment_to_FirstFragment);
            }
        });
        ccNoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack.setVisibility(View.INVISIBLE);
                setView();

            }
        });
    }

    public void loadPreferences(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        boolean state = sharedPreferences.getBoolean("state", false);
        cccheckBox.setChecked(state);
    }

    public void savePreferences(){
        SharedPreferences sharedPreferences =  getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("state", cccheckBox.isChecked());
        editor.apply();
    }
}
