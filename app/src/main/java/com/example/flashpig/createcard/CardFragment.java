package com.example.flashpig.createcard;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashpig.MainActivity;
import com.example.flashpig.R;
import com.google.android.material.textfield.TextInputLayout;

public class CardFragment extends Fragment {
    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    private TextView ccCardn;
    private TextView ccTextTop;
    private Toolbar ccToolbar;
    private ImageButton ccCameraButton;
    private ImageButton ccGalleryButton;
    private CardView ccPicCardview;
    private CardView ccTextCardview;
    private TextInputLayout ccTextinput;
    private Button ccButtonfront;
    private Button ccButtonback1;
    private Button ccButtonback2;
    private ImageView ccImageView;

    private int currentCard = 1;
    private CardViewModel viewModel;

    private String textFront;
    private String textBack;

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
                textFront = ccTextinput.getEditText().getText().toString();
                ccTextinput.getEditText().getText().clear();
                enableFront(false);
                ccCardn.setText("Add backside nr" + currentCard);
            }
        });

        ccButtonback2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                textBack = ccTextinput.getEditText().getText().toString();
                ccTextinput.getEditText().getText().clear();
                enableFront(true);
                currentCard+=1;
                ccCardn.setText("Add frontside nr" + currentCard);
            }
        });

        ccCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                askCameraPermission();

            }
        });

        ccGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




    }

    private void askCameraPermission() {
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA},  CAMERA_PERM_CODE);

        }else {
            openCamera();
        }
    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CAMERA_REQUEST_CODE){
            Bitmap image = (Bitmap) data.getExtras().get("data");
            ccImageView.setImageBitmap(image);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CAMERA_PERM_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera();
            }else{
                Toast.makeText(getActivity(), "Camera Permission is Required to Use Camera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void findViews(View view){
        ccCardn = view.findViewById(R.id.cardNumber);
        ccTextTop = view.findViewById(R.id.textViewccfront2);
        ccToolbar = view.findViewById(R.id.toolbar6);
        ccCameraButton = view.findViewById(R.id.imageButtoncc1);
        ccGalleryButton = view.findViewById(R.id.imageButtoncc2);
        ccPicCardview = view.findViewById(R.id.cardViewccfront1);
        ccTextCardview = view.findViewById(R.id.cardView3);
        ccTextinput = view.findViewById(R.id.ccTextinputFront);
        ccButtonfront = view.findViewById(R.id.buttonccfront);
        ccButtonback1 = view.findViewById(R.id.buttonccback1);
        ccButtonback2 = view.findViewById(R.id.buttonccback2);
        ccImageView = view.findViewById(R.id.ccImageView);

    }

    private  void loadUI(){
        enableFront(true);
        ccCardn.setText("Add frontside nr" + currentCard);
        ccTextTop.setText("Deck name");



    }

    private void loadCard(int i) {

    }

    private void enableFront(boolean bol) {
        if (bol) {

            ccCardn.setVisibility(View.VISIBLE);
            ccButtonback2.setVisibility(View.INVISIBLE);
            ccButtonback1.setVisibility(View.INVISIBLE);
            ccButtonfront.setVisibility(View.VISIBLE);

            ccToolbar.setVisibility(View.VISIBLE);
            ccCameraButton.setVisibility(View.VISIBLE);
            ccGalleryButton.setVisibility(View.VISIBLE);
            ccPicCardview.setVisibility(View.VISIBLE);
            ccTextCardview.setVisibility(View.VISIBLE);
            ccTextinput.setVisibility(View.VISIBLE);
        }
        else {
            ccButtonfront.setVisibility(View.INVISIBLE);
            ccButtonback2.setVisibility(View.VISIBLE);
            ccButtonback1.setVisibility(View.VISIBLE);


        }
    }
}