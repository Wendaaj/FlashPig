package com.example.flashpig.Model;

import android.graphics.Bitmap;
import android.media.Image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.net.Uri;

/**
 * Class for card.
 * @author Jesper
 * @version 2020-09-14l
 */

public class Card {
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

    public Card(int id, String frontsideStr, String backsideStr,
                Bitmap frontImg, Bitmap backImg) {
        this.id = id;
        this.frontsideStr = frontsideStr;
        this.backsideStr = backsideStr;
        this.frontImg = frontImg;
        this.backImg = backImg;
        setDifficulty(Difficulty.NOTHING);
        setFrontside(true);
    }

    /**
     * Method to create frontside of card
     * @param str the tect or numbers to use
     * //@param img the picture to use
     *
     */
    public void EditFrontside(String str, Image img) {

    }


    public void setFrontsideStr(String frontsideStr) {
        this.frontsideStr = frontsideStr;
    }



    /**
     * Method to create backside of card
     * @param str the tect or numbers to use
     * //@param img the picture to use
     *
     */
    public void EditBackside(String str, Image img){
        if (!this.isFrontside && str != null && !str.equals("")){
            this.backsideStr = str;
        }

       // this.backImg = img;

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
