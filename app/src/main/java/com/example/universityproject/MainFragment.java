package com.example.universityproject;

import androidx.annotation.AnimRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.universityproject.databinding.FragmentMainBinding;


//Музыку сделать при помощи Service

public class MainFragment extends Fragment {
    private static final String TAG = "Buttons";
    TextView txt, textViewName;
    FragmentMainBinding binding;

    public MainFragment() {
        super(R.layout.fragment_main);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        binding.settingsButton.setOnClickListener(listener);
        binding.playButton.setOnClickListener(listener);
        return binding.getRoot();
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Intent i;
            Log.i("ff", "Кнопка нажата");
            if (v.getId() == R.id.playButton) {
                binding.txtView1.setText("PlayingRadio");

            } else if (v.getId() == R.id.settingsButton) {
                binding.txtView1.setText("GoToSettings");
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setReorderingAllowed(true);
                fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.base_out, R.anim.base_in, R.anim.slide_out);
                fragmentTransaction.replace(R.id.main_activity_fragment_container, SettingsFragment.class, null);
                fragmentTransaction.addToBackStack("FromSettingsFragment");
                fragmentTransaction.commit();
/*                SettingsFragment nextFrag= new SettingsFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_activity_fragment_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();*/
            }
        }
    };
}


//Жизненные циклы приложения
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Toast.makeText(getApplicationContext(), " Main On Create", Toast.LENGTH_SHORT).show();
        txt = findViewById(R.id.txtView1);
        binding.playButton.setOnClickListener(listener);
        binding.settingsButton.setOnClickListener(listener);
    }

    @Override
    protected void onStop() {
        //Toast.makeText(getApplicationContext(), "Main On stop Toast", Toast.LENGTH_SHORT).show();
        if (isFinishing()) {
            finish();
        }
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
*/
//Методы снизу
//Может сделать свой листенер для каждой кнопки

/*
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
*/
