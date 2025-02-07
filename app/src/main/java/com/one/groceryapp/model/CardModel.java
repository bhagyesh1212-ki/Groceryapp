package com.one.groceryapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cards_table")
public class CardModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "cardholder")
    private String cardholder;
    @ColumnInfo(name = "cardnumber")
    private String cardnumber;
    @ColumnInfo(name = "carddate")
    private String carddate;
    @ColumnInfo(name = "cardcvv")
    private String cardcvv;
    @ColumnInfo(name = "isSwitched")
    private boolean isSwitched;

    public CardModel(String cardholder, String cardnumber, String carddate, String cardcvv, boolean isSwitched) {
        this.cardholder = cardholder;
        this.cardnumber = cardnumber;
        this.carddate = carddate;
        this.cardcvv = cardcvv;
        this.isSwitched = isSwitched;
    }

    public boolean isSwitched() {
        return isSwitched;
    }

    public void setSwitched(boolean switched) {
        isSwitched = switched;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCarddate() {
        return carddate;
    }

    public void setCarddate(String carddate) {
        this.carddate = carddate;
    }

    public String getCardcvv() {
        return cardcvv;
    }

    public void setCardcvv(String cardcvv) {
        this.cardcvv = cardcvv;
    }
}
