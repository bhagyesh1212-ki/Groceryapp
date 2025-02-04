package com.one.groceryapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transaction_table")
public class TransactionModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "price")
    private int price;
    @ColumnInfo(name = "date")
    private String date;

    public TransactionModel(int price, String date) {
        this.price = price;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
