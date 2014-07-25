package com.steellogistics.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.steellogistics.R;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：BaseActivity.java
 * @Describe：Activity基类
 * @Author: yfr5734@gmail.com
 * @Date：2014年5月12日 上午9:23:10
 * @Version v1.0 *
 * 
 */
public class BaseActivity extends AbActivity {
	public Button titleLeftBut, titleRightBut;
	public TextView titleName = null;
	private LayoutInflater inflater = null;
	public RelativeLayout contentLayout, titleLayout = null;
	public LinearLayout.LayoutParams layoutParamsFF = null;

	// public Handler mBaseHandler = new Handler() {
	//
	// @Override
	// public void handleMessage(Message msg) {
	// switch (msg.what) {
	// case 0:
	// Toast.makeText(BaseActivity.this, msg.getData().getString("Msg"),
	// 5).show();
	// break;
	// case 1:
	// showDialog(0);
	// break;
	// case 2:
	// removeDialog(0);
	// break;
	// case 3:
	//
	// break;
	// }
	// }
	// };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base);
		titleLeftBut = (Button) findViewById(R.id.titleLeftBut);
		titleRightBut = (Button) findViewById(R.id.titleRightBut);
		titleName = (TextView) findViewById(R.id.titleName);
		titleLayout = (RelativeLayout) findViewById(R.id.titleLayout);
		contentLayout = (RelativeLayout) findViewById(R.id.contentLayout);
		inflater = LayoutInflater.from(this);
		layoutParamsFF = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	}

	/**
	 * 
	 * @Describe：设置加载内容显示区的View
	 * @param id
	 * @Throws:
	 * @Date：2014年7月23日 下午5:05:58
	 * @Version v1.0
	 */
	public void setBaseContentView(int id) {
		contentLayout.removeAllViews();
		contentLayout.addView(inflater.inflate(id, null), layoutParamsFF);
	}

	/**
	 * 
	 * @Describe：设置标题栏的相关属性
	 * @param pageName
	 * @param isShowLeftBut
	 * @param leftButText
	 * @param isShowRightBut
	 * @param rightButText
	 * @Throws:
	 * @Date：2014年7月23日 下午5:05:27
	 * @Version v1.0
	 */
	public void setTitleInfo(String pageName, boolean isShowLeftBut, String leftButText, boolean isShowRightBut, String rightButText) {
		titleName.setText(pageName);
		if (isShowLeftBut) {
			titleLeftBut.setVisibility(View.VISIBLE);
			if (!TextUtils.isEmpty(leftButText)) {
				titleLeftBut.setText(leftButText);
			} else {
				titleLeftBut.setVisibility(View.GONE);
			}
		}

		if (isShowRightBut) {
			titleRightBut.setVisibility(View.VISIBLE);
			if (!TextUtils.isEmpty(rightButText)) {
				titleRightBut.setText(rightButText);
			}
		} else {
			titleRightBut.setVisibility(View.GONE);
		}
	}
}
