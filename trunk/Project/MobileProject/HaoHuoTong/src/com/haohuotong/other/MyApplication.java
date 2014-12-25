package com.haohuotong.other;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static Context context;
    private static LogisticsDB logisticsDB;

//    public void onCreate(){
//        super.onCreate();
//        context = getApplicationContext();
//        logisticsDB = new LogisticsDB(context);
//        logisticsDB.open().close(); // to initialize
//    }
//
    public static void SetMyContext() {
      logisticsDB = new LogisticsDB(context);
      logisticsDB.open().close(); // to initialize
    }
    
    public static Context getAppContext() {
        return MyApplication.context;
    }
    
    public static LogisticsDB getLogisticsDB() {
        return MyApplication.logisticsDB;
    }
    
    
}