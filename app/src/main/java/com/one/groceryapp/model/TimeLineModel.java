package com.one.groceryapp.model;

public class TimeLineModel {

    private String orderDeliveryStatus;
    private String date;

    public TimeLineModel(String orderDeliveryStatus, String date) {
        this.orderDeliveryStatus = orderDeliveryStatus;
        this.date = date;
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
