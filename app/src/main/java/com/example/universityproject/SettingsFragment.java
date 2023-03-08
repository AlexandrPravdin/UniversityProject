package com.example.universityproject;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.universityproject.databinding.FragmentSettingsBinding;


public class SettingsFragment extends Fragment {
    private EditText PlainText;
    FragmentSettingsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentSettingsBinding.inflate(getLayoutInflater());
       // PlainText = findViewById(R.id.PlainTextName);
        //binding.SaveButton.setOnClickListener(settingsListener);*/
        //Toast.makeText(this, "Settings On Create", Toast.LENGTH_SHORT).show();
    }


/*
    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        Toast.makeText(this, "Settings On Stop", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onStop() {
        //Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("NameFromPlainTextToTextVew","helloWorld");

        super.onStop();
    }

    void sendMessage(String message) {
        Intent data = new Intent();
        data.putExtra("Name", message);
        Log.i("Cool",message);
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, data);
        finish();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    */
}
