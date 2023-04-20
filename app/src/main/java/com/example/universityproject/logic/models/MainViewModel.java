package com.example.universityproject.logic.models;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private String name, station;

    public MainViewModel() {
        Log.i("AAA","VM created");
        name = "Sasha";
        station = "0.0";
    }

    public MainViewModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Override
    protected void onCleared() {
        Log.e("AAA","VM cleared");
        super.onCleared();

    }
}