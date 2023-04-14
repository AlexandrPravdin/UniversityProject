package com.example.universityproject;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.universityproject.adapter.RadioItem;
import com.example.universityproject.databinding.FragmentMainBinding;

import java.util.ArrayList;


//Музыку сделать при помощи Service

public class MainFragment extends Fragment {
    private static final int PERMISSION_REQUEST_CODE = 1;
    private NotificationManager manager;
    private NotificationChannel importantChannel;
    private ArrayList<RadioItem> stations;
    private FragmentMainBinding binding;
    private Intent intent;
    private Context context;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        intent = new Intent(getActivity(), MusicService.class); // Build the intent for the service
        stations = new ArrayList<>();
        importantChannel = new NotificationChannel(getString(R.string.WARNING_CHANNEL), getString(R.string.WARNING_CHANNEL), NotificationManager.IMPORTANCE_DEFAULT);
        manager = requireContext().getSystemService(NotificationManager.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);

        getChildFragmentManager().setFragmentResultListener("ResultToMainFragment", this, (requestKey, result) -> {
            String bundle = result.getString("2");
            binding.textView.setHint(bundle);
        });

        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Recycle
        binding.recycleView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recycleView.setAdapter(new ListRecycleAdapter(requireContext(), stations, (item, position) -> binding.textView.setText(item.getStationName())));
        setInitialData();
        //Buttons
        binding.settingsButton.setOnClickListener(v -> {
            binding.txtView1.setText("GoToSettings");

            Bundle b = new Bundle();
            if (!binding.textView.getText().toString().equals("")) {
                b.putString("TxtToSettings", binding.textView.getText().toString());
            } else {
                b.putString("TxtToSettings", "Pipiska");
            }
            Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_settingsFragment, b);
        });

        binding.playButton.setOnClickListener(v -> {
            binding.txtView1.setText(R.string.PlayRadioText);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                sendNotification("Update info", "Are you sure about your app's version?", importantChannel.getId(), R.drawable.radio_fill0_wght400_grad0_opsz48);
            }
            context.startForegroundService(intent);
        });

        binding.listButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_listViewFragment);
        });

        manager.createNotificationChannel(importantChannel);
    }

    //Заполнение массива
    private void setInitialData() {
        for (int i = 1; i <= 200; i++) {
            stations.add(new RadioItem(i * 2 + "." + i * 3, R.drawable.radio_fill0_wght400_grad0_opsz48));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    void sendNotification(String title, String text, String channelId, int drawable) {
        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requireActivity().requestPermissions(
                    new String[]{
                            android.Manifest.permission.POST_NOTIFICATIONS
                    },
                    PERMISSION_REQUEST_CODE);

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
