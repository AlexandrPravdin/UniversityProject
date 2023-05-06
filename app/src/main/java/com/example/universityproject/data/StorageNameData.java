package com.example.universityproject.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.universityproject.logic.models.RadioItem;

@Entity(tableName = "station_table")
public class StorageNameData {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "imageResource")
    private  int imageResource;

    public StorageNameData(String name) {
        this.name = name;
        this.imageResource = imageResource;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public RadioItem toRadioItem(){
        return new RadioItem(this.name, this.imageResource);
    }
}

