package com.example.universityproject.logic.models;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.universityproject.data.StorageNameData;
import com.example.universityproject.logic.repository.StationRepository;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private final LiveData<List<RadioItem>> allItems;
    StationRepository stationRepository;


    public MainViewModel(Application application) {
        stationRepository = new StationRepository(application);
        allItems = stationRepository.getAllItems();
    }

    public LiveData<List<RadioItem>> getAllItems() {
        return allItems;
    }

    public void insert(RadioItem radioItem){
        stationRepository.insert(new StorageNameData(radioItem.getStationName()),radioItem.getPictureResource());
    }
}

