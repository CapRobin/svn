package com.xibeiwuliu.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.xibeiwuliu.global.MyApplication;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：SpecialLineActivity.java
 * @Describe：专线查询
 * @Author: yfr5734@gmail.com
 * @Date：2014年4月28日 下午4:45:22
 * @Version v1.0 *
 * 
 */
public class SpecialLineActivity extends AbActivity {
	private MyApplication application = null;
	private TextView text;
	private String getMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.test);
		getMsg = getIntent().getStringExtra("msg");
		initTitleRightLayout();
		initView();

	}

	/**
	 * 描述：初始化标题栏
	 * 
	 * @throws
	 * @date：2013-4-25 上午10:21:18
	 * @version v1.0
	 */
	private void initTitleRightLayout() {
		AbTitleBar mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText("专线查询");
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		// mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		application = (MyApplication) abApplication;

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
		text = (TextView) findViewById(R.id.text);
		text.setText(getMsg);

	}
}
