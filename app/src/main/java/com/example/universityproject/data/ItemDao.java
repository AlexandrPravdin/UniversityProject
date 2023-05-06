package com.example.universityproject.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(StorageNameData item);

    @Query("DELETE FROM station_table")
    void deleteALL();

    @Query("SELECT * FROM station_table ORDER BY name")
    LiveData<List<StorageNameData>> getAllStations();
}
