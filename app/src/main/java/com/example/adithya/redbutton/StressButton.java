package com.example.adithya.redbutton;

import android.app.Application;
import android.content.Context;

/**
 * Created by Adithya on 11/13/2016.
 */
public class StressButton extends Application{
    private static Context context;

    public void onCreate() {
        super.onCreate();
        StressButton.context = getApplicationContext();
    }

    public static Context getContext() {
        return StressButton.context;
    }
}
