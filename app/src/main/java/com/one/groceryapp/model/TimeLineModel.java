package com.one.groceryapp.model;

public class TimeLineModel {

    private String orderDeliveryStatus;
    private String date;
    private int imageRound;

    private int bottomLine;
    private int topLine;


    public TimeLineModel(String orderDeliveryStatus, String date, int imageRound, int bottomLine, int topLine) {
        this.orderDeliveryStatus = orderDeliveryStatus;
        this.date = date;
        this.imageRound = imageRound;
        this.bottomLine = bottomLine;
        this.topLine = topLine;
    }

    public int getBottomLine() {
        return bottomLine;
    }

    public void setBottomLine(int bottomLine) {
        this.bottomLine = bottomLine;
    }

    public int getTopLine() {
        return topLine;
    }

    public void setTopLine(int topLine) {
        this.topLine = topLine;
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
