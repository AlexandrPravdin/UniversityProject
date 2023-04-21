package com.example.universityproject.logic.models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> name, station;


/*    public MainViewModel() {
        name = new MutableLiveData<String>("Sasha");
        station = new MutableLiveData<String>("0.0");
    }*/

    public MutableLiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<String>("Sasha");
        }
        return name;
    }

    public MutableLiveData<String> getStation(){
        if (station == null) {
            station = new MutableLiveData<String>("0.0");
        }
        return station;
    }
}