package com.xibeiwuliu.activity;

import android.content.Intent;
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
 * @Name：PersonalCenterActivity.java
 * @Describe：个人中心
 * @Author: yfr5734@gmail.com
 * @Date：2014年5月12日 上午9:31:54
 * @Version v1.0 *
 * 
 */
public class PersonalCenterActivity extends BaseActivity implements OnClickListener {
	private MyApplication application = null;
	// private RelativeLayout layout_01, layout_02, layout_03, layout_04,
	// layout_05, layout_06;
	private RelativeLayout centerLayout_01, centerLayout_02, centerLayout_03, centerLayout_04, centerLayout_05, centerLayout_06, centerLayout_07,
			centerLayout_08;
	private String getMsg;
	private boolean isShowRightBut = true; // 是否显示右边按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.setting_center);
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
		// application.newMsgNum = 0;
		// String pushMsg = application.getPushMsg;
		// if (pushMsg != null) {
		// Toast.makeText(SettingCenterActivity.this,
		// "Push Msg is-->> "+application.getPushMsg, 5).show();
		// application.getPushMsg = null;
		// }
		// layout_01 = (RelativeLayout) findViewById(R.id.layout_01);
		// layout_02 = (RelativeLayout) findViewById(R.id.layout_02);
		// layout_03 = (RelativeLayout) findViewById(R.id.layout_03);
		// layout_04 = (RelativeLayout) findViewById(R.id.layout_04);
		// layout_05 = (RelativeLayout) findViewById(R.id.layout_05);
		// layout_06 = (RelativeLayout) findViewById(R.id.layout_06);

		centerLayout_01 = (RelativeLayout) findViewById(R.id.centerLayout_01);
		centerLayout_02 = (RelativeLayout) findViewById(R.id.centerLayout_02);
		centerLayout_03 = (RelativeLayout) findViewById(R.id.centerLayout_03);
		centerLayout_04 = (RelativeLayout) findViewById(R.id.centerLayout_04);
		centerLayout_05 = (RelativeLayout) findViewById(R.id.centerLayout_05);
		centerLayout_06 = (RelativeLayout) findViewById(R.id.centerLayout_06);
		centerLayout_07 = (RelativeLayout) findViewById(R.id.centerLayout_07);
		centerLayout_08 = (RelativeLayout) findViewById(R.id.centerLayout_08);

		centerLayout_01.setOnClickListener(this);
		centerLayout_02.setOnClickListener(this);
		centerLayout_03.setOnClickListener(this);
		centerLayout_04.setOnClickListener(this);
		centerLayout_05.setOnClickListener(this);
		centerLayout_06.setOnClickListener(this);
		centerLayout_07.setOnClickListener(this);
		centerLayout_08.setOnClickListener(this);

		if (isShowRightBut) {
			this.rightTitleBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(PersonalCenterActivity.this, "个人中心设置", 5).show();
				}
			});
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.centerLayout_01:
			Intent intent = new Intent(PersonalCenterActivity.this, PersonalDriverInfoActivity.class);
			String Msg = "个人信息";
			intent.putExtra("msg", Msg);
			startActivity(intent);
			break;
		case R.id.centerLayout_02:
			Toast.makeText(PersonalCenterActivity.this, "我的发布", 5).show();
			break;
		case R.id.centerLayout_03:
			Toast.makeText(PersonalCenterActivity.this, "好友管理", 5).show();
			break;
		case R.id.centerLayout_04:
			Intent intent2 = new Intent();
			String Msge = "软件分享";
			intent2.putExtra("msg", Msge);
			intent2.putExtra("content", "http://dl.5671.cc");
			intent2.setClass(PersonalCenterActivity.this, TwoDimensionCodeActivity.class);
			startActivity(intent2);
			break;
		case R.id.centerLayout_05:
			Toast.makeText(PersonalCenterActivity.this, "检测更新", 5).show();
			break;
		case R.id.centerLayout_06:
			Toast.makeText(PersonalCenterActivity.this, "用户反馈", 5).show();
			break;
		case R.id.centerLayout_07:
			Toast.makeText(PersonalCenterActivity.this, "应用推荐", 5).show();
			break;
		case R.id.centerLayout_08:
			Toast.makeText(PersonalCenterActivity.this, "关于公司", 5).show();
			break;
		}
	}
}
