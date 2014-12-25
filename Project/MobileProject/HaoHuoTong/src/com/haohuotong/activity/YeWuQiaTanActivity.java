package com.haohuotong.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.haohuotong.R;
import com.haohuotong.global.MyApplication;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * @Name��YeWuQiaTanActivity.java 
 * @Describe��ҵ��Ǣ̸
 * @Author Administrator  yfr5734@gmail.com
 * @Date��2013-11-13 ����11:59:17
 * @Version v1.0
 */
public class YeWuQiaTanActivity extends AbActivity {
	private TextView extString;
	private MyApplication application = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setAbContentView(R.layout.ywqt);

		initTitleRightLayout();
		initView();

	}

	/**
	 * ��������ʼ��������
	 * 
	 * @throws
	 * @date��2013-4-25 ����10:21:18
	 * @version v1.0
	 */
	private void initTitleRightLayout() {
		AbTitleBar mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText("ҵ��Ǣ̸");
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
//		mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
        mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		application = (MyApplication) abApplication;

	}

	/**
	 * 
	 * ��������ʼ��View
	 * 
	 * @throws
	 * @date��2013-11-13 ����10:21:24
	 * @version v1.0
	 */
	private void initView() {
		extString = (TextView) findViewById(R.id.textString);
		extString.setText("ҵ��Ǣ̸");

	}
}
