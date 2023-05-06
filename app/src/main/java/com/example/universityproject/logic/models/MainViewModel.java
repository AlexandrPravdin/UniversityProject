package com.example.universityproject.logic.models;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.universityproject.logic.repository.StationRepository;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    public MutableLiveData<ArrayList<RadioItem>> list = new MutableLiveData<ArrayList<RadioItem>>();
    public MutableLiveData<String> station = new MutableLiveData<>("0.1");
    public MutableLiveData<String> name = new MutableLiveData<>("Sasha");
    StationRepository stationRepository;


    public MainViewModel(Context context) {
        stationRepository = new StationRepository(context.getApplicationContext());
        Log.i("AAA", "In modelview" + stationRepository.getRandomData().get(0).getStationName());
        ArrayList<RadioItem> itemArrayList = stationRepository.getRandomData();
        list.setValue(stationRepository.getRandomData());
    }

    public void saveName(String str) {
        stationRepository.saveName(str);
    }

}

