package com.example.universityproject.ui.fragments;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.universityproject.R;
import com.example.universityproject.databinding.FragmentMainBinding;
import com.example.universityproject.logic.models.MainViewModel;
import com.example.universityproject.logic.models.RadioItem;
import com.example.universityproject.services.MusicService;
import com.example.universityproject.ui.adapters.ListRecycleAdapter;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private static final int PERMISSION_REQUEST_CODE = 1;
    private NotificationManager manager;
    private NotificationChannel importantChannel;
    private ArrayList<RadioItem> stations;
    private FragmentMainBinding binding;
    private Intent intent;
    private Context context;
    private MainViewModel dataModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ViewModel
        dataModel = new ViewModelProvider(this).get(MainViewModel.class);

        //Name string observer
        final Observer<String> nameObserver = newName -> {
            Log.i("AAA", "In observer ");
            binding.textView.setText(newName);
        };
        dataModel.getName().observe(this, nameObserver);

        //Station string observer
        final Observer<String> stationObserver = newStation -> {
            Log.i("AAA", "In observer");
            binding.textView2.setText(newStation);
        };
        dataModel.getStation().observe(this, stationObserver);

        //OnCreate
        context = requireContext();
        intent = new Intent(getActivity(), MusicService.class); // Build the intent for the service
        stations = new ArrayList<>();

        //Notifications
        importantChannel = new NotificationChannel(getString(R.string.WARNING_CHANNEL), getString(R.string.WARNING_CHANNEL), NotificationManager.IMPORTANCE_DEFAULT);
        manager = requireContext().getSystemService(NotificationManager.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Getting information from settings fragment
        getParentFragmentManager().setFragmentResultListener("ResultToMainFragment", this, (requestKey, result) -> {
                    dataModel.getName().setValue(result.getString("TxtToMainFragment"));
                }
        );

        String txtFromRegistration = getArguments().getString("NameToMainGraph");
        dataModel.getName().setValue(txtFromRegistration);

        //Getting last information from model View
        binding.textView.setText(dataModel.getName().getValue());
        binding.textView2.setText(dataModel.getStation().getValue());

        //Recycle
        binding.recycleView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recycleView.setAdapter(new ListRecycleAdapter(requireContext(), stations, ((item, position) -> {
            dataModel.getStation().setValue(item.getStationName());
        })));
        setInitialData();

        //Settings Button
        binding.settingsButton.setOnClickListener(v -> {
            Bundle b = new Bundle();
            b.putString("TxtToSettings", dataModel.getName().getValue());
            Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_settingsFragment, b);
        });

        //Play Button
        binding.playButton.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                sendNotification("Update info", "Are you sure about your app's version?", importantChannel.getId(), R.drawable.radio_fill0_wght400_grad0_opsz48);
            }
            dataModel.getName().setValue("1111");
        });

        //List Button
        binding.listButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_listViewFragment);
        });
        manager.createNotificationChannel(importantChannel);
    }


    //Setting array of stations
    private void setInitialData() {
        for (int i = 1; i <= 200; i++) {
            stations.add(new RadioItem(i * 2 + "." + i * 3, R.drawable.radio_fill0_wght400_grad0_opsz48));
        }
    }

    //Sendig notifications from main fragment
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    void sendNotification(String title, String text, String channelId, int drawable) {
        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requireActivity().requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, PERMISSION_REQUEST_CODE);
            return;
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), channelId);
        builder.setSmallIcon(drawable);
        builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setAutoCancel(true);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(requireContext());
        managerCompat.notify(1, builder.build());
    }


}
