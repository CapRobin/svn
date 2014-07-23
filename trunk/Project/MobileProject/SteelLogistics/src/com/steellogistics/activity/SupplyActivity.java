package com.steellogistics.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.steellogistics.R;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：SupplyActivity.java
 * @Describe：供货信息
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月22日 上午11:09:30
 * @Version v1.0
 */
public class SupplyActivity extends BaseActivity {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.supply);
		setTitleInfo("供货信息", isShowLeftBut, "返回", isShowRightBut, null);
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
