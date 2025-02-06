package com.one.groceryapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Address_table")
public class AddressModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "mobile_number")
    private String mobile_number;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "zip")
    private String zip;
    @ColumnInfo(name = "city")
    private String city;
    @ColumnInfo(name = "country")
    private String country;
    @ColumnInfo(name = "isswitched")
    private Boolean isswitched;

    public AddressModel(String name, String email, String mobile_number, String address, String zip, String city, String country) {
        this.name = name;
        this.email = email;
        this.mobile_number = mobile_number;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.country = country;
    }

    public Boolean getIsswitched() {
        return isswitched;
    }

    public void setIsswitched(Boolean isswitched) {
        this.isswitched = isswitched;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
