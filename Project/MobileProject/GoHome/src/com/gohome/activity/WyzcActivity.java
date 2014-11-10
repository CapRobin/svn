package com.gohome.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.gohome.R;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：WyzcActivity.java
 * @Describe：我要找车
 * @Author: yfr5734@gmail.com
 * @Date：2014年11月10日 下午3:58:20
 * @Version v1.0
 */
public class WyzcActivity extends BaseActivity {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.wyzc);
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
		setTitleInfo("返回", isShowLeftBut, "返回", isShowRightBut, "");
		if (isShowLeftBut) {
			titleLeftBut.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					finish();
				}
			});
		}

		if (isShowRightBut) {
			titleRightBut.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// startActivity(new Intent(WyzcActivity.this,
					// RegisterActivity.class));
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

	}

}
