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
 * @Name：MoreActivity.java
 * @Describe：更多页面
 * @Author: yfr5734@gmail.com
 * @Date：2014年5月12日 上午9:26:18
 * @Version v1.0 *
 * 
 */
public class MoreActivity extends BaseActivity implements OnClickListener {
	private MyApplication application = null;
	private RelativeLayout moreLayout_01, moreLayout_02, moreLayout_03, moreLayout_04, moreLayout_05;
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
		moreLayout_01 = (RelativeLayout) findViewById(R.id.moreLayout_01);
		moreLayout_02 = (RelativeLayout) findViewById(R.id.moreLayout_02);
		moreLayout_03 = (RelativeLayout) findViewById(R.id.moreLayout_03);
		moreLayout_04 = (RelativeLayout) findViewById(R.id.moreLayout_04);
		moreLayout_05 = (RelativeLayout) findViewById(R.id.moreLayout_05);

		moreLayout_01.setOnClickListener(this);
		moreLayout_02.setOnClickListener(this);
		moreLayout_03.setOnClickListener(this);
		moreLayout_04.setOnClickListener(this);
		moreLayout_05.setOnClickListener(this);

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
		case R.id.moreLayout_01:
			Toast.makeText(MoreActivity.this, "附近检索", 5).show();
			break;
		case R.id.moreLayout_02:
			Toast.makeText(MoreActivity.this, "快捷导航", 5).show();
			break;
		case R.id.moreLayout_03:
			Toast.makeText(MoreActivity.this, "在线投保", 5).show();
			break;
		case R.id.moreLayout_04:
			Toast.makeText(MoreActivity.this, "一键配货", 5).show();
			break;
		case R.id.moreLayout_05:
			Toast.makeText(MoreActivity.this, "快递查询", 5).show();
			break;
		}
	}
}
