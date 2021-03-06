package com.example.flashpig.View;
import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashpig.R;
import com.example.flashpig.ViewModel.CardViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.parceler.Parcels;

import java.io.IOException;

/**
 * The controller that connects the card model with the views.
 *
 * @author Jesper
 * @version 2020-10-12
 */

public class CardFragment extends Fragment {
    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int IMAGE_PICK_CODE = 1000;
    public static final int PERMISSION_CODE = 1001;

    private TextView ccCardn, ccTextTop, ccCameraText, ccGalleryText;
    private Toolbar ccToolbar;
    private ImageButton ccCameraButton, ccGalleryButton;
    private CardView ccPicCardview, ccTextCardview;
    private TextInputLayout ccTextinput;
    private TextInputEditText ccTextEditTxt;
    private Button ccButtonfront, ccButtonback1, ccButtonback2, ccSaveBtn;
    private ImageView ccImageView;

    private CardViewModel viewModel;
    private boolean isFront = true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cardcreatefront, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);

        viewModel = new ViewModelProvider(getActivity()).get(CardViewModel.class);
        if (getArguments() != null){
            viewModel.getDeck().setValue(Parcels.unwrap(getArguments().getParcelable("deck")));
            if (getArguments().containsKey("card")) {
                viewModel.getCard().setValue(Parcels.unwrap(getArguments().getParcelable("card")));
                ccButtonback1.setEnabled(false);
                updateViews();
            }else {
                viewModel.initCard();
                ccButtonback1.setEnabled(false);
            }
        }
        else {
            viewModel.initCard();
        }

        loadUI();

        ((AppCompatActivity) getActivity()).setSupportActionBar(ccToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ccToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackPressed();
            }
        });

        ccButtonfront.setOnClickListener(new View.OnClickListener(){ //Go to backside
            @Override
            public void onClick(View v){
                String inputText = ccTextinput.getEditText().getText().toString();
                if (!inputText.isEmpty()||ccImageView.getDrawable()!=null) { //If something is set
                    viewModel.setFrontStr(inputText);
                    ccTextinput.getEditText().getText().clear(); //Clear for the backside
                    isFront = false; //Set is backside
                    enableFront();
                    ccImageView.setImageDrawable(null); //Clear image for backside
                    ccCardn.setText("Add backside nr: " + viewModel.getCardPos());
                    if (viewModel.getBackImg() != null || viewModel.getBackStr() != null){
                        updateViews();
                    }
                }
                else{
                    Toast.makeText(getActivity(), "Please fill that front with something! OINK! OINK!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        ccButtonback2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String inputText = ccTextinput.getEditText().getText().toString();
                if (!inputText.isEmpty()||ccImageView.getDrawable()!=null){
                    viewModel.setBackStr(inputText);
                    ccTextinput.getEditText().getText().clear();
                    viewModel.initCard();
                    isFront = true;
                    enableFront();
                    ccImageView.setImageDrawable(null);
                    ccCardn.setText("Add frontside nr: " + viewModel.getCardPos());
                }else{
                    Toast.makeText(getActivity(), "Please fill that ham with something! OINK! OINK!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        ccButtonback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = ccTextinput.getEditText().getText().toString();
                if (!inputText.isEmpty()||ccImageView.getDrawable()!=null){
                    viewModel.setBackStr(inputText);
                    ccTextinput.getEditText().getText().clear();
                    ccImageView.setImageDrawable(null);
                    ccCardn.setText("Add backside nr: " + viewModel.getCardPos());
                    viewModel.saveDeck();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("deck", Parcels.wrap(viewModel.getCurrentDeck()));
                    Toast.makeText(getContext(), viewModel.getDeckName() +
                            " is saved! OINK! OINK!", Toast.LENGTH_SHORT).show();
                    viewModel.resetViewModel();
                    NavHostFragment.findNavController(CardFragment.this)
                            .navigate(R.id.action_cardFragment_to_mainActivity3, bundle);
                }
                else{
                    Toast.makeText(getActivity(), "Please fill that ham with something! OINK! OINK!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        ccSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = ccTextinput.getEditText().getText().toString();
                if (!inputText.isEmpty()||ccImageView.getDrawable()!=null){
                    viewModel.setBackStr(inputText);
                    ccTextinput.getEditText().getText().clear();
                    ccImageView.setImageDrawable(null);
                    ccCardn.setText("Add backside nr: " + viewModel.getCardPos());
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("deck", Parcels.wrap(viewModel.getCurrentDeck()));
                    Toast.makeText(getContext(), viewModel.getDeckName() +
                            " is saved! OINK! OINK!", Toast.LENGTH_SHORT).show();
                    viewModel.resetViewModel();
                    NavHostFragment.findNavController(CardFragment.this)
                            .navigate(R.id.action_cardFragment_to_editDeckFragment, bundle);
                }else{
                    Toast.makeText(getActivity(), "Please fill that ham with something! OINK! OINK!",
                            Toast.LENGTH_SHORT).show();
                }
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
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            ==PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    }else{ pickImageFromGallery(); }
                }else{ pickImageFromGallery(); }
            }
        });
    }

    private void setBackPressed(){
        if (getArguments() != null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("deck", Parcels.wrap(viewModel.getCurrentDeck()));
            NavHostFragment.findNavController(CardFragment.this)
                    .navigate(R.id.action_cardFragment_to_editDeckFragment, bundle);
        } else {
            NavHostFragment.findNavController(CardFragment.this)
                    .navigate(R.id.action_cardFragment_to_createDeckFragment);
        }
    }

    private void updateViews(){
        if (isFront) {
            ccTextEditTxt.setText(viewModel.getFrontStr());
            ccImageView.setImageBitmap(viewModel.getFrontImg());
        }
        else {
            ccTextEditTxt.setText(viewModel.getBackStr());
            ccImageView.setImageBitmap(viewModel.getBackImg());
        }
    }

    /**
     * Sends indent to choose image from gallery.
     */
    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    /**
     * Ask permission to use camera if permission is not granted message about it will show.
     */
    private void askCameraPermission() {
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA},  CAMERA_PERM_CODE);

        }else { openCamera(); }
    }

    /**
     * Opens camera if permission is granted to do so.
     */
    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);
    }

    /**
     * Check which button is clicked and sets the card to the specific difficulty.
     *
     * @param requestCode
     * @param resultCode are matched to either convert picture from camera or gallery.
     *
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CAMERA_REQUEST_CODE){
            Bitmap image = (Bitmap) data.getExtras().get("data");
            ccImageView.setImageBitmap(image);
            hideButtons();
            if(isFront){
                viewModel.setFrontImg(image);
            }
            else{
                viewModel.setBackImg(image);
            }

        }
        if(requestCode == IMAGE_PICK_CODE){

            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                ccImageView.setImageBitmap(bitmap);
                if(isFront){
                    viewModel.setFrontImg(bitmap);
                }
                else{
                    viewModel.setBackImg(bitmap);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            hideButtons();
        }

    }

    /**
     * Check which button is clicked and sets the card to the specific difficulty.
     *
     * @param requestCode is checked to either give access to camera or gallery
     * or show toast that permission failed.
     *
     *
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CAMERA_PERM_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera();
            }else{
                Toast.makeText(getActivity(), "Camera Permission is Required to Use Camera",
                        Toast.LENGTH_SHORT).show();
            }
        }
        if(requestCode == PERMISSION_CODE){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                pickImageFromGallery();
            }
            else{
                Toast.makeText(getActivity(), "Permission is Required to Use Gallery",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }

    /**
     * Connects the fragments attributes with the views components.
     *
     * @param view The respective view.
     */
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
        ccCameraText = view.findViewById(R.id.ccCameraText);
        ccGalleryText = view.findViewById(R.id.ccGalleryText);
        ccTextEditTxt = view.findViewById(R.id.textinputccfront);
        ccSaveBtn = view.findViewById(R.id.saveBtn);
    }

    private  void loadUI(){
        enableFront();
        ccCardn.setText("Add frontside nr: " + viewModel.getCardPos());
        ccTextTop.setText(viewModel.getDeckName());
    }

    /**
     * Setting visibility of components depending on side of card.
     */
    private void enableFront() {
        if (isFront) {
            ccButtonback2.setVisibility(View.INVISIBLE);
            ccButtonback1.setVisibility(View.INVISIBLE);
            ccSaveBtn.setVisibility(View.INVISIBLE);

            ccCardn.setVisibility(View.VISIBLE);
            ccButtonfront.setVisibility(View.VISIBLE);
            ccToolbar.setVisibility(View.VISIBLE);
            ccCameraButton.setVisibility(View.VISIBLE);
            ccGalleryButton.setVisibility(View.VISIBLE);
            ccPicCardview.setVisibility(View.VISIBLE);
            ccTextCardview.setVisibility(View.VISIBLE);
            ccTextinput.setVisibility(View.VISIBLE);
            ccCameraText.setVisibility(View.VISIBLE);
            ccGalleryText.setVisibility(View.VISIBLE);
        }
        else {
            ccButtonfront.setVisibility(View.INVISIBLE);

            ccSaveBtn.setVisibility(View.VISIBLE);
            ccButtonback2.setVisibility(View.VISIBLE);
            ccButtonback1.setVisibility(View.VISIBLE);
            ccCameraButton.setVisibility(View.VISIBLE);
            ccGalleryButton.setVisibility(View.VISIBLE);
            ccCameraText.setVisibility(View.VISIBLE);
            ccGalleryText.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Setting visibility of components depending on picture imported or not.
     */
    private void hideButtons(){
        ccGalleryButton.setVisibility(View.INVISIBLE);
        ccCameraButton.setVisibility(View.INVISIBLE);
        ccCameraText.setVisibility(View.INVISIBLE);
        ccGalleryText.setVisibility(View.INVISIBLE);
    }
}