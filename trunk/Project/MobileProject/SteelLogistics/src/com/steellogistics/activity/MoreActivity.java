package com.steellogistics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.steellogistics.R;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：MoreActivity.java
 * @Describe：更多功能
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月22日 上午11:14:05
 * @Version v1.0
 */
public class MoreActivity extends BaseActivity implements OnClickListener{
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = false;
	private RelativeLayout centerLayout_01, centerLayout_02, centerLayout_03, centerLayout_04, centerLayout_05, centerLayout_06, centerLayout_07, centerLayout_08 = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.setting_center);
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
		setTitleInfo("更    多", isShowLeftBut, "返回", isShowRightBut, null);
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
		
		}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.centerLayout_01:
//			startActivity(new Intent(MoreActivity.this, PersonalCenterActivity.class));
			if (application.isLogin) {
				startActivity(new Intent(MoreActivity.this, PersonalCenterActivity.class));
			} else {
				startActivity(new Intent(MoreActivity.this, LoginActivity.class));
			}
			break;
		case R.id.centerLayout_02:
			Toast.makeText(MoreActivity.this, "我的发布", 5).show();
			break;
		case R.id.centerLayout_03:
			Toast.makeText(MoreActivity.this, "好用管理", 5).show();
			break;
		case R.id.centerLayout_04:
			Toast.makeText(MoreActivity.this, "软件分享", 5).show();
			break;
		case R.id.centerLayout_05:
			Toast.makeText(MoreActivity.this, "检测更新", 5).show();
			break;
		case R.id.centerLayout_06:
			Toast.makeText(MoreActivity.this, "用户反馈", 5).show();
			break;
		case R.id.centerLayout_07:
			Toast.makeText(MoreActivity.this, "应用推荐", 5).show();
			break;
		case R.id.centerLayout_08:
			Toast.makeText(MoreActivity.this, "关于我们", 5).show();
			break;
		}
	}
	
}
