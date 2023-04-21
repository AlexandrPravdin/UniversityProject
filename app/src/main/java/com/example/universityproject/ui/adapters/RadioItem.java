package com.example.universityproject.ui.adapters;

public class RadioItem {
    private String stationName;
    private int pictureResource;

    public RadioItem(String stationName, int pictureResource) {
        this.stationName = stationName;
        this.pictureResource = pictureResource;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getPictureResource() {
        return pictureResource;
    }

    public void setPictureResource(int pictureResource) {
        this.pictureResource = pictureResource;
    }
}