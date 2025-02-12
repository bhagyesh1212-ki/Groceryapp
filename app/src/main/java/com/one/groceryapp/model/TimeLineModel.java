package com.one.groceryapp.model;

public class TimeLineModel {

    private String orderDeliveryStatus;
    private String date;
    private int imageRound;

    public TimeLineModel(String orderDeliveryStatus, String date, int imageRound) {
        this.orderDeliveryStatus = orderDeliveryStatus;
        this.date = date;
        this.imageRound = imageRound;
    }

    public int getImageRound() {
        return imageRound;
    }

    public void setImageRound(int imageRound) {
        this.imageRound = imageRound;
    }

    public String getOrderDeliveryStatus() {
        return orderDeliveryStatus;
    }

    public void setOrderDeliveryStatus(String orderDeliveryStatus) {
        this.orderDeliveryStatus = orderDeliveryStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
