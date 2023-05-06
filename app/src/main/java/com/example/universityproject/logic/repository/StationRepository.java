package com.example.universityproject.logic.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.universityproject.data.ItemDao;
import com.example.universityproject.data.ItemDatabase;
import com.example.universityproject.data.StorageNameData;
import com.example.universityproject.logic.models.RadioItem;

import java.util.List;
import java.util.stream.Collectors;

public class StationRepository {
    private final ItemDao dao;
    private final LiveData<List<RadioItem>> allItems;
    private final Context context;
    StorageNameData storageNameData;
    private ItemDatabase database;

    public StationRepository(Application application) {
        context = application.getApplicationContext();
        database = ItemDatabase.getDatabase(context);
        dao = ItemDatabase.getDatabase(context).itemDao();
        allItems = Transformations.map(dao.getAllStations(),
                entities -> entities.stream()
                        .map(StorageNameData::toRadioItem)
                        .collect(Collectors.toList()));
        // storageNameData = new StorageNameData("Sasha");
    }

    public LiveData<List<RadioItem>> getAllItems() {
        return allItems;
    }

    public void insert(StorageNameData storageNameData, int pictureResource) {
        ItemDatabase.databaseWriteExecutor.execute(() -> {
            dao.insert(storageNameData);
        });
    }
}
