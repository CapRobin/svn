package com.steellogistics.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.steellogistics.R;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：RelevantInfoActivity.java
 * @Describe：行业资讯
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月22日 上午11:08:33
 * @Version v1.0
 */
public class RelevantInfoActivity extends BaseActivity {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.relevant_info);
		setTitleInfo("行业资讯", isShowLeftBut, "返回", isShowRightBut, null);
		if (isShowLeftBut) {
			titleLeftBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}
	}
}
