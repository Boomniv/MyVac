package com.example.myvac;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;

public class SampleService extends Service {
    Vibrator vibrator;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        vibrator.vibrate(2000);
        return super.onStartCommand(intent, flags, startId);

    }
}