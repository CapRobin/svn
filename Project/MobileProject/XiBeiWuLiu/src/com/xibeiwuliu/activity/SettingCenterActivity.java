package com.xibeiwuliu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.xibeiwuliu.global.MyApplication;

/**
 * 
  * Copyright (c) 2013 All rights reserved
  * @Name：SettingCenterActivity.java 
  * @Describe：设置中心
  * @Author:  yfr5734@gmail.com
  * @Date：2014年4月28日 下午4:48:29
  * @Version v1.0 *
  *
 */
public class SettingCenterActivity extends BaseActivity implements OnClickListener {
	private MyApplication application = null;
	private RelativeLayout layout_01, layout_02, layout_03, layout_04, layout_05, layout_06 ;
	private String getMsg;
	private boolean isShowRightBut = true;		//是否显示右边按钮
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
//		application.newMsgNum = 0;
//		String pushMsg = application.getPushMsg;
//		if (pushMsg != null) {
//			Toast.makeText(SettingCenterActivity.this, "Push Msg is-->> "+application.getPushMsg, 5).show();
//			application.getPushMsg = null;
//		}
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
					Toast.makeText(SettingCenterActivity.this, "个人中心设置", 5).show();
				}
			});
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_01:
			Intent intent = new Intent(SettingCenterActivity.this, PersonalDriverInfoActivity.class);
			String Msg = "个人信息";
			intent.putExtra("msg", Msg);
			startActivity(intent);
//			if (application.isLogin) {
//				if (application.userInfo.getUserType() == 1) {
//					Intent intent1 = new Intent(SettingCenterActivity.this, PersonalDriverInfoActivity.class);
//					startActivity(intent1);
//				}else {
//					Intent intent2 = new Intent(SettingCenterActivity.this, PersonalLogisticsInfoActivity.class);
//					startActivity(intent2);
//				}
//			} else {
//				Toast.makeText(SettingCenterActivity.this, "登录后才能查看个人信息！", 5).show();
//				Intent intent3 = new Intent(SettingCenterActivity.this, LoginActivity.class);
//				startActivity(intent3);
//			}
			Toast.makeText(SettingCenterActivity.this, "个人信息！", 5).show();
			break;
		case R.id.layout_02:
			Toast.makeText(SettingCenterActivity.this, "会员管理", 5).show();
//			if (application.isLogin) {
////				Intent intent1 = new Intent(SettingCenterActivity.this, FriendListActivity.class);
//				Intent intent1 = new Intent(SettingCenterActivity.this, FriendActivity.class);
//				intent1.putExtra(Constants.MSGKEY, msg);
//				startActivity(intent1);
//			} else {
//				Toast.makeText(SettingCenterActivity.this, "登录后才能查看个人信息！", 5).show();
//				Intent intent3 = new Intent(SettingCenterActivity.this, LoginActivity.class);
//				startActivity(intent3);
//			}
			break;
		case R.id.layout_03:
			Toast.makeText(SettingCenterActivity.this, "软件设置", 5).show();
//			Intent intent = new Intent(SettingCenterActivity.this, CommonalityActivity.class);
//			intent.putExtra("intentType", 0);
//			startActivity(intent);
			break;
		case R.id.layout_04:
			Toast.makeText(SettingCenterActivity.this, "检测更新", 5).show();
			break;
		case R.id.layout_05:
			Toast.makeText(SettingCenterActivity.this, "关于公司", 5).show();
			break;
		case R.id.layout_06:
			Toast.makeText(SettingCenterActivity.this, "更多应用", 5).show();
			break;
		}
	}
}
