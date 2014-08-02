package com.steellogistics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.steellogistics.R;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：BuyInfoDetailActivity.java
 * @Describe：求购信息详情
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月25日 上午11:21:39
 * @Version v1.0
 */
public class BuyInfoDetailActivity extends BaseActivity {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = false;
	private TextView infoTitleName, infoTime, infoContent;
	private ImageView infoImage = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.buy_info_detail);
		titleBarInitView();
		initView();

	}

	/**
	 * 
	 * @Describe：初始化标题栏
	 * @Throws:
	 * @Date：2014年7月24日 上午9:41:44
	 * @Version v1.0
	 */
	private void titleBarInitView() {
		setTitleInfo("信息详情", isShowLeftBut, "返回", isShowRightBut, null);
		if (isShowLeftBut) {
			titleLeftBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}
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
				Intent intent = new Intent(BuyInfoDetailActivity.this, MyWebView.class);
				intent.putExtra("url", "http://www.sinopec.com");
				startActivity(intent);
			}
		});

	}
}
