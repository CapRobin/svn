package com.net56888.logistics;

import android.app.Application;
import android.content.Context;

import com.net56888.logistics.data.LogisticsDB;

public class App extends Application {
    private static Context context;
    private static LogisticsDB logisticsDB;

    public void onCreate(){
        super.onCreate();
        context = getApplicationContext();
        logisticsDB = new LogisticsDB(context);
        logisticsDB.open().close(); // to initialize
    }

    public static Context getAppContext() {
        return App.context;
    }
    
    public static LogisticsDB getLogisticsDB() {
        return App.logisticsDB;
    }
    
    
}