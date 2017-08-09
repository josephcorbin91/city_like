package com.android.jco.citylike_android.models;

/**
 * Created by jco on 8/4/2017.
 */

public class Data {

    private String description;
    private String imagePath;

    public Data(String description, String imagePath) {
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
