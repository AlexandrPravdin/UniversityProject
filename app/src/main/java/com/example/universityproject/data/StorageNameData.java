package com.example.universityproject.data;

import android.content.Context;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StorageNameData {
    private final File file;

    public StorageNameData(Context context) {
        File directoryFile = context.getFilesDir();
        file = new File(directoryFile, "name.txt");
    }

    public void saveName(String name) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(name);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

