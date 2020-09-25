package com.example.flashpig.Model;

import android.media.Image;
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
    private Image frontImg;
    private Image backImg;
    private Enum difficulty;

    /**
     * Card constructor
     *
     */

    public Card(int id, String frontsideStr, String backsideStr,
                Image frontImg, Image backImg) {
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

        if (this.isFrontside && str != null && !str.equals("")){
            this.frontsideStr = str;
        }

        this.frontImg = img;




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

        this.backImg = img;

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

    public Image getFrontImg() {
        return frontImg;
    }

    public Image getBackImg() {
        return backImg;
    }

    public Enum getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Enum difficulty) {
        this.difficulty = difficulty;
    }

}
