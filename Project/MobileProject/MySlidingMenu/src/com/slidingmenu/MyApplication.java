package com.slidingmenu;

import android.app.Application;

public class MyApplication extends Application {

	//��¼�û�
    public boolean userPasswordRemember = false;
    public MainActivity attachExample = null;
    private static MyApplication mInstance = null;
    
	public static MyApplication getInstance() {
		return mInstance;
	}

}
