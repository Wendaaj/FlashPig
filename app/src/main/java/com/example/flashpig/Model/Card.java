package com.example.flashpig.Model;

import android.media.Image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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
     * copy constructor for deep copy
     * @param copiedCard
     * @return
     */

    /*public Card Card (Card copiedCard){
        //create new instance
        Card newCard = new Card(0, "hej", "då", null,
                null);

        //copy instance values
        newCard.id = copiedCard.id;
        newCard.frontsideStr = copiedCard.frontsideStr;
        newCard.backsideStr = copiedCard.backsideStr;
        newCard.frontImg = copiedCard.frontImg;
        newCard.backImg = copiedCard.backImg;

        //return copy
        return newCard;
    }

    /*public Card makeClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeObject(this);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        ObjectInputStream in = new ObjectInputStream(inputStream);
        Card copied = (Card) in.readObject();
        return copied;
    }*/


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
