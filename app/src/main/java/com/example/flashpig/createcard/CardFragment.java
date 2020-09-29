package com.example.flashpig.createcard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.flashpig.R;
import com.google.android.material.textfield.TextInputLayout;

public class CardFragment extends Fragment {
    private Toolbar ccToolbar;
    private TextView ccToolbartextview;
    private ImageButton ccCameraButton;
    private ImageButton ccGalleryButton;
    private CardView ccPicCardview;
    private CardView ccTextCardview;
    private TextInputLayout ccTextinput;
    private Button ccButtonfront;
    private Button ccButtonback1;
    private Button ccButtonback2;

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

        ccToolbar = view.findViewById(R.id.toolbar6);
        ccToolbartextview = view.findViewById(R.id.toolbarcctext);
        ccCameraButton = view.findViewById(R.id.imageButtoncc1);
        ccGalleryButton = view.findViewById(R.id.imageButtoncc2);
        ccPicCardview = view.findViewById(R.id.cardViewccfront1);
        ccTextCardview = view.findViewById(R.id.cardView3);
        ccTextinput = view.findViewById(R.id.ccTextinputFront);
        ccButtonfront = view.findViewById(R.id.buttonccfront);
        ccButtonback1 = view.findViewById(R.id.buttonccback1);
        ccButtonback2 = view.findViewById(R.id.buttonccback2);
    }
}