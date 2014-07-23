package com.steellogistics.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

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
public class MoreActivity extends BaseActivity {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.more);
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
}
