package com.xibeiwuliu.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.xibeiwuliu.global.MyApplication;
import com.xibeiwuliu.view.MyView;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 名称：SearchVehicleActivity.java 
 * 描述：搜索车源
 * @author Administrator - yfr5734@gmail.com
 * @date：2014年5月12日 上午5:02:13
 * @version v1.0
 */
public class SearchVehicleActivity extends BaseActivity implements OnClickListener{
	private MyApplication application = null;
	private String getMsg;
	private MyView myView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.search_vehicle);
		application = (MyApplication) abApplication;
		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout(getMsg, false);
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

		myView = (MyView) findViewById(R.id.myView);
		MyView.setStartAddress();
		MyView.setEndAddress();

		Button searchBut = (Button) findViewById(R.id.searchBut);
		Button startSearch = (Button) findViewById(R.id.startSearch);
		
		startSearch.setOnClickListener(this);	
		searchBut.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.searchBut:
			String startAddress = MyView.getStartAddress();
			String endAddress = MyView.getEndAddress();
			if (TextUtils.isEmpty(startAddress) && TextUtils.isEmpty(endAddress)) {
				Toast.makeText(SearchVehicleActivity.this, "请先选择出发地和目的地！", 5).show();
			} else if (TextUtils.isEmpty(startAddress)) {
				Toast.makeText(SearchVehicleActivity.this, "请先选择出发地！", 5).show();
			} else if (TextUtils.isEmpty(endAddress)) {
				Toast.makeText(SearchVehicleActivity.this, "请先选择目的地！", 5).show();
			} else {
				Toast.makeText(SearchVehicleActivity.this, "出发地为：" + myView.getStartAddress() + "\n目的地为：" + myView.getEndAddress(), 5).show();
			}
			break;
		case R.id.startSearch:
			finish();
			break;
		}
	}
}
