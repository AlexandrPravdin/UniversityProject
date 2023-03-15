package com.example.universityproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.universityproject.databinding.FragmentMainBinding;


//Музыку сделать при помощи Service

public class MainFragment extends Fragment {
    private final static String LAST_TEXT_ON_THE_SCREEN = "Popa";
    private final static String LOG_ID = "logs";
    private String lastTextThatWasOnTheScreen;
    private FragmentMainBinding binding;
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.playButton) {
                binding.txtView1.setText(R.string.PlayRadioText);
            } else if (v.getId() == R.id.settingsButton) {
                binding.txtView1.setText("GoToSettings");

                //Сохранность в SaveState
                Bundle b = new Bundle();
                b.putString("TxtToSettings", "Pipiska");
                //onSaveInstanceState(b);
                getParentFragmentManager().setFragmentResult("ResultToSettingsFragment",b);
                //Переход с фрагмента на фрагмент
                FragmentTransaction fragmentTransaction = getParentFragmentManager().
                        beginTransaction().setReorderingAllowed(true).
                        setCustomAnimations(R.anim.slide_in, R.anim.base_out,
                                R.anim.base_in, R.anim.slide_out)
                        .replace(R.id.main_activity_fragment_container,
                                SettingsFragment.class, null)
                        .addToBackStack("FromSettingsFragment");
                fragmentTransaction.commit();
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_ID, "Created");
        Toast.makeText(getContext(), "MainOnCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_ID, "Resumed");
        Toast.makeText(getContext(), "MainOnResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.d(LOG_ID, "onViewStateRestored");
        super.onViewStateRestored(savedInstanceState);
        Toast.makeText(getContext(), "MainOnVewStateRestored", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        binding.settingsButton.setOnClickListener(listener);
        binding.playButton.setOnClickListener(listener);
        getChildFragmentManager().setFragmentResultListener("ResultToMainFragment", this, (requestKey, result) -> {
            String bundle = result.getString("2");
            Log.i("logs",bundle);
            binding.textView.setHint(bundle);
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
/*        if (savedInstanceState != null) {
            Log.i(LOG_ID, savedInstanceState.getString(LAST_TEXT_ON_THE_SCREEN));
            lastTextThatWasOnTheScreen = savedInstanceState.getString(LAST_TEXT_ON_THE_SCREEN);
        } else {
            lastTextThatWasOnTheScreen = "newView";
        }
        binding.txtView1.setText(lastTextThatWasOnTheScreen);*/
        getParentFragmentManager().setFragmentResultListener("ResultToMainFragment", this, (requestKey, result) -> {
            String nameTxt = result.getString("TxtToMainFragment");
            Log.i("logs",nameTxt);
            binding.textView.setText(nameTxt);
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LAST_TEXT_ON_THE_SCREEN, outState.getString("1"));
        Log.d(LOG_ID, "onSaveInstanceState");
        Log.i(LOG_ID, outState.getString("1"));
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_ID, "Paused");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_ID, "Stopped");
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_ID, "Destroyed");
        super.onDestroy();
    }
}
