package com.trov.fred;

import android.app.Application;

public class FredApplication extends Application {

    private static FredApplication mInstance;

    public static FredApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
