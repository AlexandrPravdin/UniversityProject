package com.example.universityproject.data;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageNameData {
    Context context;
    private final SharedPreferences file;

    public StorageNameData(Context context) {
        file = context.getSharedPreferences("name", Context.MODE_PRIVATE);
    }

    public void saveName(String name) {
        file.edit().putString("name", name).apply();
    }

}

