package com.one.groceryapp.model;

public class DeliveryModel {
    private String deliveryType;
    private String deliveryDesc;
    private int deliveryPrice;

    public DeliveryModel(String deliveryType, String deliveryDesc, int deliveryPrice) {
        this.deliveryType = deliveryType;
        this.deliveryDesc = deliveryDesc;
        this.deliveryPrice = deliveryPrice;
    }

    public String getDeliveryDesc() {
        return deliveryDesc;
    }

    public void setDeliveryDesc(String deliveryDesc) {
        this.deliveryDesc = deliveryDesc;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }
}
