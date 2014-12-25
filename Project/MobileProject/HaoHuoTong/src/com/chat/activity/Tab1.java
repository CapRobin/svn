package com.chat.activity;

import com.haohuotong.R;
import com.way.chat.common.util.Constants;
import com.way.util.SharePreferenceUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Tab1 extends Activity {
	private SharePreferenceUtil util;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab1);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
	}
	@Override
	public void onBackPressed() {//捕获返回按键事件
		// TODO Auto-generated method stub
		//发�?广播，�?知服务，已进入后台运�?
		Intent i = new Intent();
		i.setAction(Constants.BACKKEY_ACTION);
		sendBroadcast(i);
		util.setIsStart(true);//设置后台运行标志，正在运�?
		finish();//再结束自�?
	}

}
