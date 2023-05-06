package com.example.universityproject.data;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StorageNameData {
    Context context;
    private File file;

    public StorageNameData(Context context) {
        this.context = context;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            file = new File(Environment.getExternalStorageDirectory(), "name.txt");
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]
                    {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    public void saveName(String name) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(name);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]
                    {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

}

