package com.one.groceryapp.model;

public class AddtocartModel {

    private int image;
    private String productname;
    private int price;
    private int quantity;
    private String dozen;
    private Boolean delete;

    public AddtocartModel(int image, String productname, int price, int quantity, String dozen) {
        this.image = image;
        this.productname = productname;
        this.price = price;
        this.quantity = quantity;
        this.dozen = dozen;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDozen() {
        return dozen;
    }

    public void setDozen(String dozen) {
        this.dozen = dozen;
    }
}
