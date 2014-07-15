package com.xibeiwuliu.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.xibeiwuliu.entity.UserInfo;
import com.xibeiwuliu.global.MyApplication;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：PersonalDriverInfoActivity.java
 * @Describe：司机个人信息页面
 * @Author: yfr5734@gmail.com
 * @Date：2014年5月12日 上午9:27:53
 * @Version v1.0 *
 * 
 */
public class PersonalDriverInfoActivity extends BaseActivity {
	private MyApplication application = null;
	private TextView text;
	private String getMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.user_driver_info);
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
		// text = (TextView) findViewById(R.id.text);
		// text.setText(getMsg);
		
		UserInfo userInfo = application.userInfo;
		String userInfoStr = userInfo.getNames();
		
	}
}
