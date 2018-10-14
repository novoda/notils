package com.novodo.notils.demo;

import android.app.Application;

import com.novoda.notils.logger.simple.Log;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // recommended to call here otherwise have to call this every onCreate() before you use the
        // Notils Log
        Log.setShowLogs(BuildConfig.DEBUG);
    }
}
