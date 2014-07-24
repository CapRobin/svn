package com.slidingmenu;

import android.app.Application;

public class MyApplication extends Application {

	//µÇÂ¼ÓÃ»§
    public boolean userPasswordRemember = false;
    public MainActivity attachExample = null;
    private static MyApplication mInstance = null;
    
	public static MyApplication getInstance() {
		return mInstance;
	}

}
