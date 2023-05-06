package com.example.universityproject.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.universityproject.R;
import com.example.universityproject.databinding.FragmentSettingsBinding;


public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(getLayoutInflater());
        //Get arguments from MainFragment
        String bundle = getArguments().getString("TxtToSettings");
        binding.PlainTextName.setHint(bundle);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Buttons
        binding.SaveButton.setOnClickListener(v -> {
            Bundle b = new Bundle();
            b.putString("TxtToMainFragment", binding.PlainTextName.getText().toString());
            Navigation.findNavController(v).navigate(R.id.action_settingsFragment_to_mainFragment,b);

        });


    }

}
