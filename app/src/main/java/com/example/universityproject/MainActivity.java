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

    private static final String TAG = "Buttons";
    Button playBtn;
    TextView txt;
    ImageButton imgBtn;


    //Жизненные циклы приложения
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txtView1);
        imgBtn = findViewById(R.id.settingsButton);
        playBtn = findViewById(R.id.playButton);

        Toast.makeText(getApplicationContext(), " Main On Create", Toast.LENGTH_SHORT).show();
        playBtn.setOnClickListener(listener);
        imgBtn.setOnClickListener(listener);
    }

    @Override
    protected void onStop() {
        Toast.makeText(getApplicationContext(), "Main On stop Toast", Toast.LENGTH_SHORT).show();
        if (isFinishing()) {
            finish();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(getApplicationContext(), "Main On Destroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        finish();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "Main On Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "Main On Resume", Toast.LENGTH_SHORT).show();
    }

    //Методы снизу
    //Может сделать свой листенер для каждой кнопки
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i;
            if (view.getId() == R.id.playButton) {
                txt.setText("PlayingRadio");
                Log.i(TAG,"Play Button Pushed");
            } else if (view.getId() == R.id.settingsButton) {
                txt.setText("GoToSettings");
                Log.i(TAG,"Settings Button Pushed");
                i = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(i);
            }
        }
    };

}