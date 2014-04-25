package com.xibeiwuliu.global;

import android.app.Application;
import android.content.Context;

//import com.haohuotong.other.LogisticsDB;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @name：MyApplication.java
 * @describe：全局应用
 * @author Administrator
 * @date：2013-11-11 下午1:29:05
 * @version v1.0
 */
public class MyApplication extends Application {
	private static Context context;
	private static MyApplication mInstance = null;

	@Override
	public void onCreate() {
		super.onCreate();

		context = getApplicationContext();
		mInstance = this;
	}

	public static Context getAppContext() {
		return MyApplication.context;
	}

	public static MyApplication getInstance() {
		return mInstance;
	}

}
