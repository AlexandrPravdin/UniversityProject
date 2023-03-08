package com.example.universityproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.universityproject.databinding.FragmentMainBinding;


//Музыку сделать при помощи Service

public class MainFragment extends Fragment {
    private final static String LAST_TEXT_ON_THE_SCREEN = "Popa";
    private String lastTextThatWasOnTheScreen;
    private FragmentMainBinding binding;
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Intent i;
            Log.i("ff", "Кнопка нажата");
            if (v.getId() == R.id.playButton) {
                binding.txtView1.setText("PlayingRadio");
            } else if (v.getId() == R.id.settingsButton) {
                binding.txtView1.setText("GoToSettings");
                //Сохранность в SaveState
                Bundle b = new Bundle();
                b.putString("1", "GoToSettings");
                onSaveInstanceState(b);

                //Переход с фрагмента на фрагмент
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setReorderingAllowed(true);
                fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.base_out, R.anim.base_in, R.anim.slide_out);
                fragmentTransaction.replace(R.id.main_activity_fragment_container, SettingsFragment.class, null);
                fragmentTransaction.addToBackStack("FromSettingsFragment");
                fragmentTransaction.commit();
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            lastTextThatWasOnTheScreen = savedInstanceState.getString(LAST_TEXT_ON_THE_SCREEN);
        } else {
            lastTextThatWasOnTheScreen = "newView";
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        binding.settingsButton.setOnClickListener(listener);
        binding.playButton.setOnClickListener(listener);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.txtView1.setText(lastTextThatWasOnTheScreen);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LAST_TEXT_ON_THE_SCREEN, outState.getString("1"));
    }

}
