package com.example.universityproject.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.universityproject.R;
import com.example.universityproject.ui.adapters.ListViewAdapter;
import com.example.universityproject.ui.adapters.RadioItem;
import com.example.universityproject.databinding.FragmentListviewBinding;

import java.util.ArrayList;
import java.util.Objects;

public class ListViewFragment extends Fragment {
    ArrayList<RadioItem> stations = new ArrayList<>();
    ListView stationList;
    private FragmentListviewBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListviewBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ListView
        stationList = binding.ListViewStations;
        stationList.setAdapter(new ListViewAdapter(getContext(), R.layout.adapter_item, stations));
        setInitialData();
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RadioItem selectedItem = (RadioItem) parent.getItemAtPosition(position);
                Bundle b = new Bundle();
                b.putString("TxtToMainFragment", "Радио " + selectedItem.getStationName());
                Objects.requireNonNull(Navigation.findNavController(view).getPreviousBackStackEntry()).getSavedStateHandle().set("TxtToMainFragment", b);
                Navigation.findNavController(view).popBackStack();
            }
        };
        stationList.setOnItemClickListener(itemClickListener);

    }

    private void setInitialData() {
        for (int i = 1; i <= 200; i++) {
            stations.add(new RadioItem(i * 2 + "." + i * 3, R.drawable.radio_fill0_wght400_grad0_opsz48));
        }
    }


}
