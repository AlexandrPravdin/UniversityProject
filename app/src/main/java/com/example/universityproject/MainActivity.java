package com.example.universityproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.universityproject.databinding.ActivityMainBinding;


//Музыку сделать при помощи Service

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Buttons";
    TextView txt, textViewName;
    ActivityMainBinding binding;

    //Жизненные циклы приложения
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setReorderingAllowed(true);
        fragmentTransaction.add(R.id.main_activity_fragment_container, MainFragment.class, null);
        fragmentTransaction.commit();

        //txt = findViewById(R.id.txtView1);
        /*binding.playButton.setOnClickListener(listener);
        binding.settingsButton.setOnClickListener(listener);*/
    }

    @Override
    protected void onStop() {
/*        //Toast.makeText(getApplicationContext(), "Main On stop Toast", Toast.LENGTH_SHORT).show();
        if (isFinishing()) {
            finish();
        }*/
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        //Toast.makeText(getApplicationContext(), "Main On Destroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Toast.makeText(getApplicationContext(), "Main On Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //textViewName = findViewById(R.id.PlainTextName);
        //Bundle arguments = getIntent().getExtras();
        //String name = arguments.get("NameFromPlainTextToTextVew").toString();
        //Toast.makeText(getApplicationContext(), "Main On Resume", Toast.LENGTH_SHORT).show();
    }

    //Методы снизу
    //Может сделать свой листенер для каждой кнопки
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i;
            if (view.getId() == R.id.playButton) {
                txt.setText("PlayingRadio");
                Log.i(TAG, "Play Button Pushed");
            } else if (view.getId() == R.id.settingsButton) {
                txt.setText("GoToSettings");
                Log.i(TAG, "Settings Button Pushed");
                i = new Intent(MainActivity.this, SettingsFragment.class);
                mStartForResult.launch(i);

            }
        }
    };

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == RESULT_OK) {
                                textViewName = findViewById(R.id.textView);
                                Intent intent = result.getData();
                                String keyOfName = "Name";
                                String a = intent.getStringExtra(keyOfName);
                                Toast.makeText(getApplicationContext(), a, Toast.LENGTH_SHORT);
                                textViewName.setText(a);
                            } else {
                                Toast.makeText(getApplicationContext(), "Залогинься дурак", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

}