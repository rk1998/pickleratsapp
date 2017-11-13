package edu.gatech.pickleratsapp.cs2340.udirtyrat.database;

import android.app.Application;
import android.content.Context;

/**
 *  Represents AppContext
 */

 public class UDirtyRatApplication extends Application {
    private static Context context;
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getAppContext() {
        return context;
    }
}
