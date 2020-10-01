package com.example.flashpig.createcard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.flashpig.R;
import com.google.android.material.textfield.TextInputLayout;

public class CardFragment extends Fragment {
    private EditText ccCardn;
    private Toolbar ccToolbar;
    private ImageButton ccCameraButton;
    private ImageButton ccGalleryButton;
    private CardView ccPicCardview;
    private CardView ccTextCardview;
    private TextInputLayout ccTextinput;
    private Button ccButtonfront;
    private Button ccButtonback1;
    private Button ccButtonback2;
    private int currentCard = 1;
    private CardViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.cardcreatefront, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(CardViewModel.class);
        findViews(view);
        loadUI();

        ccButtonfront.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                enableFront(false);
                ccCardn.setText("Add backside nr" + currentCard);
            }
        });

        ccButtonback2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                enableFront(true);
                currentCard+=1;
            }
        });




    }

    private void findViews(View view){
        ccCardn = view.findViewById(R.id.ccText);
        ccToolbar = view.findViewById(R.id.toolbar6);
        ccCameraButton = view.findViewById(R.id.imageButtoncc1);
        ccGalleryButton = view.findViewById(R.id.imageButtoncc2);
        ccPicCardview = view.findViewById(R.id.cardViewccfront1);
        ccTextCardview = view.findViewById(R.id.cardView3);
        ccTextinput = view.findViewById(R.id.ccTextinputFront);
        ccButtonfront = view.findViewById(R.id.buttonccfront);
        ccButtonback1 = view.findViewById(R.id.buttonccback1);
        ccButtonback2 = view.findViewById(R.id.buttonccback2);

    }

    private  void loadUI(){
        ccCardn.setText("Add frontside nr" + currentCard);


    }

    private void loadCard(int i) {

    }

    private void enableFront(boolean bol) {
        if (bol) {
            //changes
            ccCardn.setVisibility(View.VISIBLE);
            ccButtonback2.setVisibility(View.VISIBLE);
            ccButtonback1.setVisibility(View.VISIBLE);
            ccButtonfront.setVisibility(View.INVISIBLE);
            //Stays
            ccToolbar.setVisibility(View.VISIBLE);
            ccCameraButton.setVisibility(View.VISIBLE);
            ccGalleryButton.setVisibility(View.VISIBLE);
            ccPicCardview.setVisibility(View.VISIBLE);
            ccTextCardview.setVisibility(View.VISIBLE);
            ccTextinput.setVisibility(View.VISIBLE);
        }
        else {
            ccButtonback2.setVisibility(View.INVISIBLE);
            ccButtonback1.setVisibility(View.INVISIBLE);


        }
    }
}