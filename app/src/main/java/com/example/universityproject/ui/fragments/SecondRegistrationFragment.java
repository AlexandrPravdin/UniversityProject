package com.example.universityproject.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.universityproject.R;
import com.example.universityproject.databinding.FragmentRegistrationBinding;
import com.example.universityproject.databinding.FragmentSecondRegistrationBinding;

public class SecondRegistrationFragment extends Fragment {
    private FragmentSecondRegistrationBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondRegistrationBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        //Button
        binding.registrSecondNextButton.setOnClickListener(v -> {
            if(binding.editTextText.getText().equals("")){
                Toast.makeText(requireContext(), "Enter the name", Toast.LENGTH_SHORT).show();
            }
            else {
                Bundle b = new Bundle();
                b.putString("NameToMainGraph",binding.editTextText.getText().toString());
                Navigation.findNavController(v).navigate(R.id.action_secondRegistrationFragment2_to_nav_graph,b);
            }
        });

    }
}