package com.example.mars.day_service;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by mars on 3/6/15.
 */
public class MyService extends Service {
    TextView textView;
    Activity activity;
    Intent intent;

    final static String MY_ACTION = "MY_ACTION";
    final String LOG_TAG = "myLogs";
    Flow flow;

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
        intent = new Intent("com.example.mars.day_service");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(LOG_TAG, "onStartCommand");
        intent.putExtra("log_i","Ollolo");
        someTask();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return null;
    }

    void someTask() {
        flow = new Flow(this);
    }
}
