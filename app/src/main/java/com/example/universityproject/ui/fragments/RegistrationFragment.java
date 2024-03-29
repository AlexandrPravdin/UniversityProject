package com.example.universityproject.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.universityproject.R;
import com.example.universityproject.databinding.FragmentMainBinding;
import com.example.universityproject.databinding.FragmentRegistrationBinding;

public class RegistrationFragment extends Fragment {
    private FragmentRegistrationBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        //Button
        binding.letsStartButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_registrationFragment_to_secondRegistrationFragment2);
        });

    }
}