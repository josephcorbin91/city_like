package com.android.jco.citylike_android.models;

import java.io.Serializable;


import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by jco on 8/3/2017.
 */



public class SeattleBuildingPermit extends DataSupport implements Serializable {


    @Column
    private Integer permitNumber;

    @Column
    private String imagePath;
    @Column
    private String category;

    @Column
    private String description;

    @Column
    private String date;

    @Column
    private String address;

    @Column
    private Integer value;



    @Column
    private Double latitude;

    @Column
    private Double longitude;

    public SeattleBuildingPermit(Integer permitNumber, String category, String description, String date, String address, Integer value, Double latitude, Double longitude) {
        this.permitNumber = permitNumber;
        this.category = category;
        this.description = description;
        this.date = date;
        this.address = address;
        this.imagePath = "http://www.bridge-chailley.fr/wp-content/uploads/2013/04/D%C3%A9fense-quartier.jpg";
        this.value = value;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Permit Number : " + permitNumber + " , Address : " + address + " , Description : " + description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getPermitNumber() {
        return permitNumber;
    }

    public void setPermitNumber(Integer permitNumber) {
        this.permitNumber = permitNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
