package com.steellogistics.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TabHost;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.steellogistics.R;
import com.steellogistics.entity.UserInfo;
import com.steellogistics.global.MyApplication;
import com.steellogistics.util.MethodUtil;
import com.steellogistics.view.MoveBg;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：MainActivity.java
 * @Describe：程序首页
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月22日 上午10:09:15
 * @Version v1.0
 */
public class MainActivity_x extends TabActivity {
	private TabHost tabHost;
	private TabHost.TabSpec tabSpec;
	private RadioGroup radioGroup;
	private RelativeLayout bottom_layout;
	private ImageView img;
	private int startLeft;
	private MyApplication application = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		application = (MyApplication) getApplication();
		bottom_layout = (RelativeLayout) findViewById(R.id.layout_bottom);

		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("news").setIndicator("News").setContent(new Intent(this, SupplyInfoListActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("topic").setIndicator("Topic").setContent(new Intent(this, GongQiuActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("picture").setIndicator("Picture").setContent(new Intent(this, PublishActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("follow").setIndicator("Follow").setContent(new Intent(this, RelevantInfoActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("vote").setIndicator("Vote").setContent(new Intent(this, MoreActivity.class)));

		radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
		radioGroup.setOnCheckedChangeListener(checkedChangeListener);

		img = new ImageView(this);
		img.setImageResource(R.drawable.tab_front_bg);
		bottom_layout.addView(img);
		
		// 获取本地构造数据
		String getInfo = MethodUtil.getSharedPreferences(this, "AppData", "userinfo");

		if (!TextUtils.isEmpty(getInfo)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			application.userInfo = gson.fromJson(getInfo, UserInfo.class);
			}else {
				Toast.makeText(MainActivity_x.this, "获取个人信息失败", Toast.LENGTH_SHORT).show();
			}
	}

	private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.radio_news:
				tabHost.setCurrentTabByTag("news");
				// moveFrontBg(img, startLeft, 0, 0, 0);
				MoveBg.moveFrontBg(img, startLeft, 0, 0, 0);
				startLeft = 0;
				break;
			case R.id.radio_topic:
				tabHost.setCurrentTabByTag("topic");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth(), 0, 0);
				startLeft = img.getWidth();
				break;
			case R.id.radio_pic:
				tabHost.setCurrentTabByTag("picture");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth() * 2, 0, 0);
				startLeft = img.getWidth() * 2;
				break;
			case R.id.radio_follow:
				tabHost.setCurrentTabByTag("follow");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth() * 3, 0, 0);
				startLeft = img.getWidth() * 3;
				break;
			case R.id.radio_vote:
				tabHost.setCurrentTabByTag("vote");
				MoveBg.moveFrontBg(img, startLeft, img.getWidth() * 4, 0, 0);
				startLeft = img.getWidth() * 4;
				break;

			default:
				break;
			}
		}
	};
}