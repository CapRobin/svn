package com.xibeiwuliu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.xibeiwuliu.global.MyApplication;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：RelevantInfoDetailActivity.java
 * @Describe：行业资讯详情
 * @Author: yfr5734@gmail.com
 * @Date：2014年5月12日 上午9:30:29
 * @Version v1.0 *
 * 
 */
public class RelevantInfoDetailActivity extends BaseActivity {
	private MyApplication application = null;
	private TextView infoTitleName, infoTime, infoContent;
	private ImageView infoImage = null;
	private String getMsg = "信息详情";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.relevant_info_detail);
		application = (MyApplication) abApplication;
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
		infoTitleName = (TextView) findViewById(R.id.infoTitleName);
		infoTime = (TextView) findViewById(R.id.infoTime);
		infoContent = (TextView) findViewById(R.id.infoContent);
		infoImage = (ImageView) findViewById(R.id.infoImage);

		// GetIntentInfo
		String getName = getIntent().getStringExtra("infoName");
		String getContent = getIntent().getStringExtra("infoContent");
		String getTime = getIntent().getStringExtra("infoTime");

		infoTitleName.setText(getName);
		infoContent.setText(getContent);
		infoTime.setText(getTime);
		infoImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RelevantInfoDetailActivity.this, MyWebView.class);
				intent.putExtra("url", "http://www.sinopec.com");
				startActivity(intent);
			}
		});

	}
}
