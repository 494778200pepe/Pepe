package com.pepe.tool;

import android.app.Application;
import android.content.Context;



/**
 * Created by NessCurie on 2016/12/7.
 */

public class App extends Application {

    public static Context context;
    private static App mInstance;

    public static App getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context = this.getApplicationContext();
    }

}
