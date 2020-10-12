package com.example.flashpig.createcard;
import android.Manifest;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.os.Environment;
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
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CardFragment extends Fragment {
    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int IMAGE_PICK_CODE = 1000;
    public static final int PERMISSION_CODE = 1001;

    private TextView ccCardn;
    private TextView ccTextTop;
    private TextView ccCameraText;
    private TextView ccGalleryText;
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

    Random rand = new Random();
    private int currentCard = 1;
    private CardViewModel viewModel;
    public Card card;

    private String textFront;
    private String textBack;
    private String textOn; 

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
        card = new Card(rand.nextInt(), null, null, null, null);


        ccButtonfront.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                card.setFrontsideStr(ccTextinput.getEditText().getText().toString());
                textFront = ccTextinput.getEditText().getText().toString();
                ccTextinput.getEditText().getText().clear();
                enableFront(false);
                ccImageView.setImageURI(null);
                ccImageView.setImageBitmap(null);
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
                ccImageView.setImageURI(null);
                ccImageView.setImageBitmap(null);
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
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            ==PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else{
                        pickImageFromGallery(); 

                    }
                }
                else{
                    pickImageFromGallery();

                }


            }
        });




    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
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
            card.setFrontImg(image);

            hideButtons();

        }
        if(requestCode == IMAGE_PICK_CODE){

            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                ccImageView.setImageBitmap(bitmap);
                card.setFrontImg(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
            //ccImageView.setImageURI(data.getData());
            //card.EditFrontside(null,image);
            hideButtons();

        }
        /*if(requestCode == CAMERA_REQUEST_CODE && !card.isFrontside()){
            Bitmap image = (Bitmap) data.getExtras().get("data");
            ccImageView.setImageBitmap(image);

            hideButtons();

        }
        if(requestCode == IMAGE_PICK_CODE && !card.isFrontside()){
            ccImageView.setImageURI(data.getData());
            hideButtons();

        } */
    }









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
            ccButtonback2.setVisibility(View.INVISIBLE);
            ccButtonback1.setVisibility(View.INVISIBLE);

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

            ccButtonback2.setVisibility(View.VISIBLE);
            ccButtonback1.setVisibility(View.VISIBLE);
            ccCameraButton.setVisibility(View.VISIBLE);
            ccGalleryButton.setVisibility(View.VISIBLE);
            ccCameraText.setVisibility(View.VISIBLE);
            ccGalleryText.setVisibility(View.VISIBLE);


        }
    }
    private void hideButtons(){
        ccGalleryButton.setVisibility(View.INVISIBLE);
        ccCameraButton.setVisibility(View.INVISIBLE);
        ccCameraText.setVisibility(View.INVISIBLE);
        ccGalleryText.setVisibility(View.INVISIBLE);
    }
}