package com.example.universityproject.logic.repository;

import android.content.Context;
import android.util.Log;

import com.example.universityproject.data.DataStation;
import com.example.universityproject.data.StorageNameData;
import com.example.universityproject.logic.models.RadioItem;

import java.util.ArrayList;
import java.util.List;

public class StationRepository {
    StorageNameData storageNameData;

    public StationRepository(Context context) {
        storageNameData = new StorageNameData(context);
    }

    public ArrayList<RadioItem> getRandomData() {
        Log.i("AAA","In repository" + DataStation.createRandomList().get(0).getStationName());
        return DataStation.createRandomList();
    }

    public void saveName(String str){
        storageNameData.saveName(str);
    }
}
