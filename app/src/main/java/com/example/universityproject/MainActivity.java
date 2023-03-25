package com.example.universityproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.universityproject.databinding.ActivityMainBinding;


//Музыку сделать при помощи Service

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    //Жизненные циклы приложения
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}