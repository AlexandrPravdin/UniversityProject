package com.example.universityproject.logic.models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<String> name;
    private final MutableLiveData<String> station;


    public MainViewModel() {
        name = new MutableLiveData<String>("Sasha");
        station = new MutableLiveData<String>("0.0");
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getStation() {
        return station;
    }
}