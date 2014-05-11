package com.xibeiwuliu.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xibeiwuliu.global.MyApplication;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：SettingCenterActivity.java
 * @Describe：设置中心
 * @Author: yfr5734@gmail.com
 * @Date：2014年4月28日 下午4:48:29
 * @Version v1.0 *
 * 
 */
public class MoreActivity extends BaseActivity implements OnClickListener {
	private MyApplication application = null;
	private RelativeLayout layout_01, layout_02, layout_03, layout_04, layout_05, layout_06;
	private String getMsg;
	private boolean isShowRightBut = true; // 是否显示右边按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.more_function);
		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout(getMsg, isShowRightBut);
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
		layout_01 = (RelativeLayout) findViewById(R.id.layout_01);
		layout_02 = (RelativeLayout) findViewById(R.id.layout_02);
		layout_03 = (RelativeLayout) findViewById(R.id.layout_03);
		layout_04 = (RelativeLayout) findViewById(R.id.layout_04);
		layout_05 = (RelativeLayout) findViewById(R.id.layout_05);
		layout_06 = (RelativeLayout) findViewById(R.id.layout_06);

		layout_01.setOnClickListener(this);
		layout_02.setOnClickListener(this);
		layout_03.setOnClickListener(this);
		layout_04.setOnClickListener(this);
		layout_05.setOnClickListener(this);
		layout_06.setOnClickListener(this);

		if (isShowRightBut) {
			this.rightTitleBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(MoreActivity.this, "个人中心设置", 5).show();
				}
			});
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_01:
			Toast.makeText(MoreActivity.this, "附近检索", 5).show();
			break;
		case R.id.layout_02:
			Toast.makeText(MoreActivity.this, "快捷导航", 5).show();
			break;
		case R.id.layout_03:
			Toast.makeText(MoreActivity.this, "在线投保", 5).show();
			break;
		case R.id.layout_04:
			Toast.makeText(MoreActivity.this, "一键配货", 5).show();
			break;
		case R.id.layout_05:
			Toast.makeText(MoreActivity.this, "快递查询", 5).show();
			break;
		case R.id.layout_06:
			Toast.makeText(MoreActivity.this, "应用推荐", 5).show();
			// Toast.makeText(MoreActivity.this, "版本更新", 5).show();
			// Toast.makeText(MoreActivity.this, "用户反馈", 5).show();
			// Toast.makeText(MoreActivity.this, "用户反馈", 5).show();
			// Toast.makeText(MoreActivity.this, "一键分享", 5).show();
			break;
		}
	}
}
