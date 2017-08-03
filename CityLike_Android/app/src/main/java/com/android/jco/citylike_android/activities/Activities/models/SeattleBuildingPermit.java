package com.android.jco.citylike_android.activities.Activities.models;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.location.Location;


import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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


}
