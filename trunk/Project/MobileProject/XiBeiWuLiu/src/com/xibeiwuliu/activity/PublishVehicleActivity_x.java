package com.xibeiwuliu.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.xibeiwuliu.global.MyApplication;

/**
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：PublishVehicleActivity.java
 * @Describe：发布车源
 * @Author: yfr5734@gmail.com
 * @Date：2014年4月28日 下午4:42:44
 * @Version v1.0 *
 * 
 */
public class PublishVehicleActivity_x extends BaseActivity {
	private MyApplication application = null;
	private TextView text;
	private String getMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.public_vehicle_info);
		application = (MyApplication) abApplication;
		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout(getMsg, false);
		initView();

	}

	/**
	 * 
	 * 描述：初始化View
	 * 
	 * @throws
	 * @date：2013-11-13 上午10:21:24
	 * @version v1.0
	 */
	private void initView() {
//		text = (TextView) findViewById(R.id.text);
//		text.setText(getMsg);

	}
}
