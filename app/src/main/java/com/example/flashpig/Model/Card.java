package com.example.flashpig.Model;

import android.media.Image;
/**
 * Class for card.
 *
 * @author Jesper
 * @version 2020-09-14
 */

public class Card {
    private int id;
    private boolean isFrontside;
    private String frontsideStr;
    private String backsideStr;
    private Image frontImg;
    private Image backImg;
    private Enum difficulty;

    /**
     * Card constructor
     *
     */

    public Card(int id, boolean isFrontside, String frontsideStr, String backsideStr,
                Image frontImg, Image backImg, Enum difficulty) {
        this.id = id;
        this.isFrontside = isFrontside;
        this.frontsideStr = frontsideStr;
        this.backsideStr = backsideStr;
        this.frontImg = frontImg;
        this.backImg = backImg;
        this.difficulty = difficulty;
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

    public String getFrontsideStr() {
        return frontsideStr;
    }

    public void setFrontsideStr(String frontsideStr) {
        this.frontsideStr = frontsideStr;
    }

    public String getBacksideStr() {
        return backsideStr;
    }

    public void setBacksideStr(String backsideStr) {
        this.backsideStr = backsideStr;
    }

    public Image getFrontImg() {
        return frontImg;
    }

    public void setFrontImg(Image frontImg) {
        this.frontImg = frontImg;
    }

    public Image getBackImg() {
        return backImg;
    }

    public void setBackImg(Image backImg) {
        this.backImg = backImg;
    }

    public Enum getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Enum difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Method to create frontside of card
     * @param str the tect or numbers to use
     * @param img the picture to use
     *
     */
    void  CreateFrontside(String str, Image img){
        if (str.equals(null)){

        }
}

    /**
     * Method to create backside of card
     * @param str the tect or numbers to use
     * @param img the picture to use
     *
     */
    void CreateBackside(String str, Image img){
    if (str.equals(null)){

    }
}

    

}
