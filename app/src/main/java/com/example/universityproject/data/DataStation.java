package com.example.universityproject.data;

import android.util.Log;

import com.example.universityproject.R;
import com.example.universityproject.logic.models.RadioItem;

import java.util.ArrayList;
import java.util.List;

public class DataStation {
    public static ArrayList<RadioItem> createRandomList() {
        ArrayList<RadioItem> stations = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            stations.add(new RadioItem(i * 2 + "." + i * 3, R.drawable.radio_fill0_wght400_grad0_opsz48));

        }
        Log.i("AAA","In data station");
        return stations;

    }
}