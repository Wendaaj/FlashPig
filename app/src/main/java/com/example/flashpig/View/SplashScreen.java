package com.example.flashpig.View;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.flashpig.R;

/**
 * @author wendy
 * @responsibility Loads the splash screen
 * @version 22-10-20
 */

public class SplashScreen extends Fragment {
    AnimationDrawable anim;
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = view.findViewById(R.id.splashImg);
        imageView.setBackgroundResource(R.drawable.splash_animation);
        anim = (AnimationDrawable) imageView.getBackground();
        anim.start();

        //Show the animation 3 times before going to dashboard.
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 8s = 8000ms
                NavHostFragment.findNavController(SplashScreen.this)
                        .navigate(R.id.action_splashScreen_to_FirstFragment);
            }
        }, 4000);
    }
}