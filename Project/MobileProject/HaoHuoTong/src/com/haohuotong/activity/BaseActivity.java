package com.haohuotong.activity;


import com.haohuotong.global.MyApplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * @name��BaseActivity.java 
 * @describe��Activity ����
 * @author Administrator
 * @date��2013-11-11 ����1:33:00
 * @version v1.0
 */
public class BaseActivity extends Activity {
	public MyApplication application = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		application = (MyApplication) getApplication();
	}
}
