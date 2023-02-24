package com.example.universityproject;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyApp";
    Button playBtn;
    TextView txt;
    ImageButton imgBtn;


    //Жизненные циклы приложения
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.txtView1);
        imgBtn = (ImageButton) findViewById(R.id.settingsButton);
        playBtn = (Button) findViewById(R.id.playButton);

        Toast.makeText(getApplicationContext(), "On Create", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Created");
        playBtn.setOnClickListener(listener);
        imgBtn.setOnClickListener(listener);
    }

    @Override
    protected void onStop() {
        Toast.makeText(getApplicationContext(), "On stop Toast", Toast.LENGTH_SHORT).show();
        Log.w(TAG, "ВНИМАНИЕ!");
        if (isFinishing()) {
            finish();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(getApplicationContext(), "On Destroy", Toast.LENGTH_SHORT).show();
        Log.v(TAG, "Destroy is the off of programm....");
        super.onDestroy();
        finish();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.wtf(TAG, "Lol, i dont know what happened");
        Toast.makeText(getApplicationContext(), "On Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.wtf(TAG, "wtf");
        Log.d(TAG, "Debug msg");
        Toast.makeText(getApplicationContext(), "On Resume", Toast.LENGTH_SHORT).show();
    }

    //Методы снизу
    //Может сделать свой листенер для каждой кнопки
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i;
            if (view.getId() == R.id.playButton) {
                txt.setText("PlayingRadio");
            } else if (view.getId() == R.id.settingsButton) {
                txt.setText("GoToSettings");
                i = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(i);
            }
        }
    };

}