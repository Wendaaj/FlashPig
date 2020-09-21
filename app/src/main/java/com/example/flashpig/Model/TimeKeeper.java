package com.example.flashpig.Model;

import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

//https://www.youtube.com/watch?v=RLnb4vVkftc

public class TimeKeeper {
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    private CountDownTimer countDownTimer;

    private void startChronometer(View v) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    /**
     * Inte nödvändig, men om den skulle komma till användning
     * @param v - Chronometers view
     */
    private void pauseChronometer(View v) {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    private void resetChronometer(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime()); //Sets chronometer to 0
        pauseOffset = 0;
    }


    private void CountdownEasy(){
        new CountDownTimer(60000,1000){

            @Override
            public void onTick(long l) {
                // Card removed from view

            }

            @Override
            public void onFinish() {
                // Card back in view

            }
        };

    }
    private void CountdownMedium(){
        new CountDownTimer(120000,1000){

            @Override
            public void onTick(long l) {
                // Card removed from view

            }

            @Override
            public void onFinish() {
                // Card back in view

            }
        };

    }
    private void CountdownHard(){
        new CountDownTimer(1000000,1000){

            @Override
            public void onTick(long l) {
                //Card removed from view
            }

            @Override
            public void onFinish() {
                // Card back in view

            }
        };

    }
}
