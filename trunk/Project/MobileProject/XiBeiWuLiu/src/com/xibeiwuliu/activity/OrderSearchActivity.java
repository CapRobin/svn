package com.xibeiwuliu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xibeiwuliu.global.MyApplication;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：OrderSearchActivity.java
 * @Describe：预约查询
 * @Author: yfr5734@gmail.com
 * @Date：2014年5月12日 下午2:55:04
 * @Version v1.0 *
 * 
 */
public class OrderSearchActivity extends BaseActivity implements OnClickListener {
	private MyApplication application = null;
	private String getMsg;
	private RelativeLayout contentLayout, leftBar, rightBar;
	private LayoutInflater inflater = null;
	private boolean witchSelect = true; // true:查询货源；false:查询车源

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.order_search);
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
		inflater = LayoutInflater.from(this);
		leftBar = (RelativeLayout) findViewById(R.id.leftBar);
		rightBar = (RelativeLayout) findViewById(R.id.rightBar);
		contentLayout = (RelativeLayout) findViewById(R.id.contentLayout);
		getContentView(witchSelect);
		leftBar.setOnClickListener(this);
		rightBar.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.leftBar: // 查询货源
			if (!witchSelect) {
				Toast.makeText(OrderSearchActivity.this, "查询货源", 5).show();
				leftBar.setBackgroundColor(getResources().getColor(R.color.gray));
				rightBar.setBackgroundColor(getResources().getColor(R.color.white));
				witchSelect = true;
				getContentView(witchSelect);
			}
			break;
		case R.id.rightBar: // 查询车源
			if (witchSelect) {
				Toast.makeText(OrderSearchActivity.this, "查询车源", 5).show();
				leftBar.setBackgroundColor(getResources().getColor(R.color.white));
				rightBar.setBackgroundColor(getResources().getColor(R.color.gray));
				witchSelect = false;
				getContentView(witchSelect);
			}
			break;
		case R.id.cargoTime:
			Toast.makeText(OrderSearchActivity.this, "请输入预约查询货源时间", 5).show();
			break;
		case R.id.cargoStartSearch:
			intent = new Intent(OrderSearchActivity.this, CargoListActivity.class);
			intent.putExtra("msg", "预约货源");
			startActivity(intent);
			break;
		case R.id.vehicleTime:
			Toast.makeText(OrderSearchActivity.this, "请输入预约查询车源时间", 5).show();
			break;
		case R.id.vehicleStartSearch:
			intent = new Intent(OrderSearchActivity.this, CargoListActivity.class);
			intent.putExtra("msg", "预约车源");
			startActivity(intent);
			break;

		}
	}

	/**
	 * 
	 * @Describe：得到相关显示View
	 * @Throws:
	 * @Date：2014年5月12日 下午3:10:31
	 * @Version v1.0
	 * 
	 */
	private void getContentView(boolean isLeft) {
		View view = null;
		contentLayout.removeAllViews();
		if (isLeft) {
			view = inflater.inflate(R.layout.order_cargo, null);
			EditText cargoTime = (EditText) view.findViewById(R.id.cargoTime);
			Button cargoStartSearch = (Button) view.findViewById(R.id.cargoStartSearch);

			cargoTime.setOnClickListener(this);
			cargoStartSearch.setOnClickListener(this);
		} else {
			view = inflater.inflate(R.layout.order_vehicle, null);
			EditText vehicleTime = (EditText) view.findViewById(R.id.vehicleTime);
			Button vehicleStartSearch = (Button) view.findViewById(R.id.vehicleStartSearch);

			vehicleTime.setOnClickListener(this);
			vehicleStartSearch.setOnClickListener(this);
		}
		contentLayout.addView(view);
	}
}
