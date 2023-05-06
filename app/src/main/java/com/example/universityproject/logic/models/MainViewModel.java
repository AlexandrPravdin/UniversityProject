package com.example.universityproject.logic.models;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.universityproject.logic.repository.StationRepository;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    public MutableLiveData<ArrayList<RadioItem>> list = new MutableLiveData<ArrayList<RadioItem>>();
    public MutableLiveData<String> station = new MutableLiveData<>("0.1");
    public MutableLiveData<String> name = new MutableLiveData<>("Sasha");


    public MainViewModel() {
        StationRepository stationRepository = new StationRepository();
        Log.i("AAA", "In modelview" + stationRepository.getRandomData().get(0).getStationName());
        ArrayList<RadioItem> itemArrayList = stationRepository.getRandomData();
        list.setValue(stationRepository.getRandomData());
    }

}

