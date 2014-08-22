package com.steellogistics.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import com.steellogistics.R;
import com.steellogistics.view.CheckSwitchButton;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：SearchActivity.java
 * @Describe：搜索信息
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月22日 上午11:08:59
 * @Version v1.0
 */
public class SearchActivity extends BaseActivity {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = false;
	private CheckSwitchButton mCheckSwithcButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.search);
		titleBarInitView();
//		initView
		mCheckSwithcButton = (CheckSwitchButton)findViewById(R.id.mCheckSwithcButton);
		mCheckSwithcButton.setChecked(false);
		mCheckSwithcButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					Toast.makeText(SearchActivity.this, "打开", 5).show();
				}else{
					Toast.makeText(SearchActivity.this, "关闭", 5).show();
				}
			}
		});
	}

	/**
	 * 
	 * @Describe：初始化标题栏
	 * @Throws:
	 * @Date：2014年7月24日 上午9:41:44
	 * @Version v1.0
	 */
	private void titleBarInitView() {
		setTitleInfo("搜    索", isShowLeftBut, "返回", isShowRightBut, null);
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
