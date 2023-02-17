package com.example.universityproject;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),"On Create",Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Created");


    }

    @Override
    protected void onStop() {
        Toast.makeText(getApplicationContext(),"On stop Toast",Toast.LENGTH_SHORT).show();
        Log.w(TAG,"ВНИМАНИЕ!");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(getApplicationContext(),"On Destroy",Toast.LENGTH_SHORT).show();
        Log.v(TAG,"Destroy is the off of programm....");
        super.onDestroy();
        finish();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.wtf(TAG, "Lol, i dont know what happened");
        Toast.makeText(getApplicationContext(),"On Pause",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.wtf(TAG,"wtf");
        Log.d(TAG, "Debug msg");
        Toast.makeText(getApplicationContext(),"On Resume",Toast.LENGTH_SHORT).show();
    }
//maked
}