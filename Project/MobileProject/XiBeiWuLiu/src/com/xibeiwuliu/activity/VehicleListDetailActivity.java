package com.xibeiwuliu.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xibeiwuliu.entity.Parameter;
import com.xibeiwuliu.entity.VehicleInfo;
import com.xibeiwuliu.global.MyApplication;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：VehicleListDetailActivity.java
 * @Describe：信息详情
 * @Author: yfr5734@gmail.com
 * @Date：2014年4月30日 下午3:40:08
 * @Version v1.0 *
 * 
 */
public class VehicleListDetailActivity extends BaseActivity implements OnClickListener {
	private TextView info_contextStr, publicTimeStr, phoneStr1, phoneStr2, phoneStr3;
	private RelativeLayout phoneLayout = null;
	private Button phoneBut, addFriendBut, shareInfo, myFriendBut;
	private MyApplication application = null;
	private List<String> phoneStr = new ArrayList<String>();
	private boolean isClink = false;
	private int phoneSize = 0;
	private VehicleInfo vehicleInfo = null;
	private int vehicleInfoId = 0;
	private ArrayList<Parameter> configurationParameter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.info_detail);

		// 获取Intent传递的数据
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("bundle");
		vehicleInfo = (VehicleInfo) bundle.get("vehicleInfo");
		vehicleInfoId = Integer.parseInt(vehicleInfo.getAdd_id());
		Log.i("tag", "------------>vehicleInfoId=" + vehicleInfoId);
		initTitleLayout("信息详情", true);
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

		application = (MyApplication) abApplication;
		phoneLayout = (RelativeLayout) findViewById(R.id.layout_3);
		info_contextStr = (TextView) findViewById(R.id.info_contextStr);
		publicTimeStr = (TextView) findViewById(R.id.publicTimeStr);
		phoneStr1 = (TextView) findViewById(R.id.phoneStr1);
		phoneStr2 = (TextView) findViewById(R.id.phoneStr2);
		phoneStr3 = (TextView) findViewById(R.id.phoneStr3);
		phoneBut = (Button) findViewById(R.id.phoneBut);
		addFriendBut = (Button) findViewById(R.id.addFriendBut);
		shareInfo = (Button) findViewById(R.id.shareInfo);
		myFriendBut = (Button) findViewById(R.id.myFriendBut);
		info_contextStr.setText(vehicleInfo.getInfo_connect());
		publicTimeStr.setText(vehicleInfo.getCar_InfoAddTime());

		phoneSize = phoneStr.size();
		if (phoneStr != null && phoneSize > 0) {
			switch (phoneStr.size()) {
			case 1:
				phoneStr1.setText(phoneStr.get(0));
				phoneStr2.setVisibility(View.GONE);
				phoneStr3.setVisibility(View.GONE);
				break;
			case 2:
				phoneStr1.setText(phoneStr.get(0));
				phoneStr2.setText(phoneStr.get(1));
				phoneStr3.setVisibility(View.GONE);
				break;
			case 3:
				phoneStr1.setText(phoneStr.get(0));
				phoneStr2.setText(phoneStr.get(1));
				phoneStr3.setText(phoneStr.get(2));
				break;
			}
		}

		phoneBut.setOnClickListener(this);
		addFriendBut.setOnClickListener(this);
		shareInfo.setOnClickListener(this);
		myFriendBut.setOnClickListener(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 0:
			if (application.isLogin) {
				Toast.makeText(VehicleListDetailActivity.this, "该模块正在开发中，请稍后...", 5).show();
			}
			break;
		case 1:
			// Toast.makeText(CommonalityInfoDetailActivity.this,
			// "该模块正在开发中，请稍后...", 5).show();

			if (application.isLogin) {
				if (phoneSize > 0) {
					if (isClink) {
						phoneLayout.setVisibility(View.GONE);
						isClink = false;
					} else {
						phoneLayout.setVisibility(View.VISIBLE);
						isClink = true;
					}
				} else {
					Toast.makeText(VehicleListDetailActivity.this, "该订单暂时没有联系电话！", 5).show();
				}
			}
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.phoneBut:
			// getData();
			if (application.isLogin) {
				if (phoneSize > 0) {
					if (isClink) {
						phoneLayout.setVisibility(View.GONE);
						isClink = false;
					} else {
						phoneLayout.setVisibility(View.VISIBLE);
						isClink = true;
					}
				} else {
					Toast.makeText(VehicleListDetailActivity.this, "该订单暂时没有联系电话！", 5).show();
				}
			} else {
				Toast.makeText(VehicleListDetailActivity.this, "登录后才能查看联系方式！", 5).show();
				// startActivityForResult(new
				// Intent(VehicleListDetailActivity.this, LoginActivity.class),
				// SHOWPHONE);
			}
			break;
		case R.id.addFriendBut:
			// boolean isLogin = application.isLogin;
			if (application.isLogin) {
				Toast.makeText(VehicleListDetailActivity.this, "该模块正在开发中，请稍后...", 5).show();
			} else {
				Toast.makeText(VehicleListDetailActivity.this, "登录后才能添加好友！", 5).show();
				// startActivityForResult(new
				// Intent(VehicleListDetailActivity.this, LoginActivity.class),
				// ADDFRIENDINTENT);
			}
			break;
		case R.id.shareInfo:
			startActivity(new Intent(VehicleListDetailActivity.this, ContactListActivity.class));
			break;
		case R.id.myFriendBut:

			// Toast.makeText(CommonalityInfoDetailActivity.this, "我的好友列表",
			// 5).show();
			if (application.isLogin) {
				// showMyDialog();
			} else {
				Toast.makeText(VehicleListDetailActivity.this, "请先登录！", 5).show();
			}
			break;
		}
	}
}
