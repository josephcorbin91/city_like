package com.android.jco.citylike_android.models;

import java.io.Serializable;


import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by jco on 8/3/2017.
 */



public class SeattleBuildingPermit extends DataSupport implements Serializable {


    @Column(nullable = false)
    private Integer permitNumber;

    @Column(nullable = false)
    private String permitType;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String description;
    @Column(nullable = true)
    private String category;

    @Column(nullable = false)
    private String actionType;

    @Column(nullable = false)
    private String workType;

    @Column(nullable = true)
    private String applicationDate;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private Integer latitude;

    @Column(nullable = false)
    private Integer longitude;


    public SeattleBuildingPermit(Integer permitNumber,String permitType, String address, String actionType, String workType, String applicationDate, String url, Integer latitude,Integer longitude){
        this.permitNumber = permitNumber;
        this.permitType = permitType;
        this.address =address;
        this.actionType=actionType;
        this.applicationDate=applicationDate;
        this.url=url;
        this.latitude=latitude;
        this.longitude=longitude;

        }

    public Integer getPermitNumber() {
        return permitNumber;
    }

    public void setPermitNumber(Integer permitNumber) {
        this.permitNumber = permitNumber;
    }

    public String getPermitType() {
        return permitType;
    }

    public void setPermitType(String permitType) {
        this.permitType = permitType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }
}
