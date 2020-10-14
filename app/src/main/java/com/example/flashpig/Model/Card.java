package com.example.flashpig.Model;

import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;

import com.example.flashpig.R;

import java.util.Random;

/**
 * Class for card.
 * @author Jesper
 * @version 2020-09-14l
 */

public class Card {
    private Random rand = new Random();
    private int id;
    private boolean isFrontside;
    private String frontsideStr;
    private String backsideStr;
    private Bitmap frontImg;
    private Bitmap backImg;
    private Enum difficulty;


    /**
     * Card constructor
     *
     */

    public Card() {
        this.id = rand.nextInt(); //check if already exist
        /*
        this.frontsideStr = frontsideStr;
        this.backsideStr = backsideStr;
        this.frontImg = frontImg;
        this.backImg = backImg;
         */
        setDifficulty(Difficulty.NOTHING);
        setFrontside(true);
    }





    public void setFrontsideStr(String frontsideStr) {
        this.frontsideStr = frontsideStr;
    }

    public void setBacksideStr(String backsideStr) {
        this.backsideStr = backsideStr;
    }

    public void setFrontImg(Bitmap frontImg) {
        this.frontImg = frontImg;
    }

    public void setBackImg(Bitmap backImg) {
        this.backImg = backImg;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public boolean isFrontside() {
        return isFrontside;
    }

    public void setFrontside(boolean frontside) {
        isFrontside = frontside;
    }

    public String getFrontsideStr() { return frontsideStr; }

    public String getBacksideStr() {
        return backsideStr;
    }

    public Bitmap getFrontImg() {
        return frontImg;
    }

    public Bitmap getBackImg() {
        return backImg;
    }

    public Enum getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Enum difficulty) {
        this.difficulty = difficulty;
    }



}
