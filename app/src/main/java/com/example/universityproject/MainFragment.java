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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universityproject.adapter.RadioItem;
import com.example.universityproject.databinding.FragmentMainBinding;

import java.util.ArrayList;


//Музыку сделать при помощи Service

public class MainFragment extends Fragment {
    private final static String LAST_TEXT_ON_THE_SCREEN = "Popa";
    private final static String LOG_ID = "logs";
    private final ArrayList<RadioItem> stations = new ArrayList<RadioItem>();
    SettingsFragment settingsFragment = new SettingsFragment();
    ListViewFragment listViewFragment = new ListViewFragment();
    RecyclerView recyclerView;
    ListRecycleAdapter adapter;
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
                if (binding.textView.getText().toString() != "") {
                    b.putString("TxtToSettings", binding.textView.getText().toString());
                } else {
                    b.putString("TxtToSettings", "Pipiska");
                }
                Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_settingsFragment, b);
            } else if (v.getId() == R.id.listButton) {
                Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_listViewFragment);
            }
        }
    };

    public MainFragment() {
        super(R.layout.fragment_main);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Toast.makeText(getContext(), "MainOnVewStateRestored", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        binding.settingsButton.setOnClickListener(listener);
        binding.playButton.setOnClickListener(listener);
        binding.listButton.setOnClickListener(listener);
        getChildFragmentManager().setFragmentResultListener("ResultToMainFragment", this, (requestKey, result) -> {
            String bundle = result.getString("2");
            Log.i("logs", bundle);
            binding.textView.setHint(bundle);
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (Navigation.findNavController(view).getCurrentBackStackEntry().getSavedStateHandle().get("TxtToMainFragment") != null) {
            Bundle b = Navigation.findNavController(view).getCurrentBackStackEntry().getSavedStateHandle().get("TxtToMainFragment");

            binding.textView.setText(b.getString("TxtToMainFragment"));
        }

        recyclerView = binding.recycleView;
        ListRecycleAdapter.OnListClickListener listClickListener = new ListRecycleAdapter.OnListClickListener() {
            @Override
            public void onItemClick(RadioItem item, int position) {
                binding.textView.setText(item.getStationName());
            }
        };
        setInitialData();
        Log.i("logs", "112");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListRecycleAdapter(getContext(), stations, listClickListener);
        recyclerView.setAdapter(adapter);
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

    private void setInitialData() {
        for (int i = 1; i <= 200; i++) {
            stations.add(new RadioItem(i * 2 + "." + i * 3, R.drawable.radio_fill0_wght400_grad0_opsz48));
        }
        Log.i("logs", "setInitalDataCreated");
    }
}
