package com.example.universityproject;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MusicService extends Service {
    private WindowManager windowManager;
    private View overlayView;
    private TextView textView;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        overlayView = LayoutInflater.from(this).inflate(R.layout.service_overlay, null);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        textView = overlayView.findViewById(R.id.textView);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        params.y = 100;

        //Ask for permissions
        if (!Settings.canDrawOverlays(this)) {
            Intent intent2 = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent2);
        } else {
            //Show banner
            windowManager.addView(overlayView, params);
        }

        textView.setOnClickListener(view -> {
            Intent toMainIntent = new Intent(getApplicationContext(), MainActivity.class);
            toMainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(toMainIntent);
            stopSelf();
        });
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Deleting service
        if (overlayView != null) {
            windowManager.removeView(overlayView);
        }
    }
}