package com.example.universityproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.universityproject.databinding.FragmentSettingsBinding;


public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == binding.SaveButton.getId()) {
                Bundle b = new Bundle();
               b.putString("TxtToMainFragment", binding.PlainTextName.getText().toString());

                Log.i("logs", binding.PlainTextName.getText().toString());
                Navigation.findNavController(v).getPreviousBackStackEntry().getSavedStateHandle().set("TxtToMainFragment", b);
                Navigation.findNavController(v).popBackStack();
            }
        }
    };

    public SettingsFragment() {
        super(R.layout.fragment_settings);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentSettingsBinding.inflate(getLayoutInflater());
        binding.SaveButton.setOnClickListener(listener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String bundle = getArguments().getString("TxtToSettings");
        binding.PlainTextName.setHint(bundle);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
