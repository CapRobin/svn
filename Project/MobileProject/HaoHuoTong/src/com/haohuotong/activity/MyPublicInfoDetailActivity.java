package com.haohuotong.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.haohuotong.R;
import com.haohuotong.entity.CargoInfo;
import com.haohuotong.global.MyApplication;
import com.haohuotong.other.InfoEntity;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：MyPublicInfoDetailActivity.java
 * @Describe：我的信息详情
 * @Author FaRong——yfr5734@gmail.com
 * @Date：2013-12-13 上午11:16:45
 * @Version v1.0
 */
public class MyPublicInfoDetailActivity extends AbActivity implements OnClickListener {
	private TextView info_contextStr, publicTimeStr, phoneStr1, phoneStr2, phoneStr3;
	private RelativeLayout phoneLayout = null;
	private Button phoneBut, updateInfo, shareInfo, actionListBut,rebeak;
	private InfoEntity entitySet = null;
	private MyApplication application = null;
	private List<String> phoneStr = new ArrayList<String>();
	private boolean isClink = false;
	private int phoneSize = 0;
	private int ADDFRIENDINTENT = 0;
	private int SHOWPHONE = 1;
	private CargoInfo cargoInfo = null;
	private int carInfoId = 0 ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.my_info_detail);

		// 获取Intent传递的数据
//		entitySet = getIntent().getParcelableExtra("DATA");
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("bundle");
		cargoInfo = (CargoInfo)bundle.get("cargoInfo");
		carInfoId = Integer.parseInt(cargoInfo.getInfo_ADDID());
		initTitleLayout();
		// getPhoneList(entitySet.getMsg_Tel());
		initView();

	}

	/**
	 * 描述：初始化标题栏
	 * 
	 * @throws
	 * @date：2013-4-25 上午10:21:18
	 * @version v1.0
	 */
	// private void initTitleRightLayout() {
	// AbTitleBar mAbTitleBar = this.getTitleBar();
	// mAbTitleBar.setTitleText("信息详情");
	// mAbTitleBar.setLogo(R.drawable.button_selector_back);
	// mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
	// mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
	// mAbTitleBar.setLogoLine(R.drawable.line);
	//
	// application = (MyApplication) abApplication;
	//
	// }

	/**
	 * 描述：初始化标题栏
	 * 
	 * @throws
	 * @date：2013-4-25 上午10:21:18
	 * @version v1.0
	 */
	private void initTitleLayout() {
		AbTitleBar mAbTitleBar = this.getTitleBar();

		// 添加左侧布局文件
		mAbTitleBar.setTitleText("我的信息详情");
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		// mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		// 添加右侧布局文件
		// View rightViewApp = mInflater.inflate(R.layout.app_btn, null);
		View rightViewMore = mInflater.inflate(R.layout.more_btn, null);
		// mAbTitleBar.addRightView(rightViewApp);
		mAbTitleBar.addRightView(rightViewMore);
		Button about = (Button) rightViewMore.findViewById(R.id.moreBtn);
		// Button appBtn = (Button)rightViewApp.findViewById(R.id.appBtn);

		about.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyPublicInfoDetailActivity.this, ConsultActivity.class);
				startActivity(intent);
			}
		});
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
		updateInfo = (Button) findViewById(R.id.updateInfo);
		shareInfo = (Button) findViewById(R.id.shareInfo);
		actionListBut = (Button) findViewById(R.id.actionListBut);
		rebeak = (Button) findViewById(R.id.rebeak);
		info_contextStr.setText(cargoInfo.getInfo_connect());
		publicTimeStr.setText(cargoInfo.getInfo_AddTime());
//		if (entitySet != null) {
//			info_contextStr.setText(entitySet.getMsg_Content());
//			publicTimeStr.setText(entitySet.getMsg_Date());
//		} else {
//			info_contextStr.setText("显示信息详情介绍");
//			publicTimeStr.setText("显示交易时间");
//		}
		updateInfo.setOnClickListener(this);
		actionListBut.setOnClickListener(this);
		rebeak.setOnClickListener(this);
	}

	/**
	 * 
	 * 描述：获取电话列表
	 * 
	 * @throws
	 * @date：2013-11-18 上午10:25:44
	 * @version v1.0
	 */
	private void getPhoneList(String getStr1) {
		String getStr2 = getStr1 + " ";
		String[] textPhone = getStr2.split(" ");
		// int m = getStr2.length();
		int n = 0;
		for (int j = 0; j < textPhone.length; j++) {
			String itmePhone = textPhone[j];
			phoneStr.add(formatPhone(itmePhone.trim()));

			// if (getStr2.charAt(j) == ' ') {
			// phoneText = getStr2.substring(n, (j + 1)).trim();
			// if (!phoneText.isEmpty()) {
			// phoneStr.add(formatPhone(itmePhone.trim()));
			// n = j;
			// }
			// }
		}
	}

	/**
	 * 
	 * 描述：格式化电话
	 * 
	 * @param phoneStr
	 * @return
	 * @throws
	 * @date：2013-11-18 上午11:56:19
	 * @version v1.0
	 */
	private String formatPhone(String phoneStr) {
		String phoneStrText = null;
		for (int i = 0; i < phoneStr.length(); i++) {
			if (!Character.isDigit(phoneStr.charAt(i))) {
				phoneStrText = phoneStr.substring(0, i) + phoneStr.substring(i + 1);
			}
		}
		if (phoneStrText == null) {
			phoneStrText = phoneStr;
		}
		return phoneStrText;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 0:
			if (application.isLogin) {
				Toast.makeText(MyPublicInfoDetailActivity.this, "该模块正在开发中，请稍后...", 5).show();
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
					Toast.makeText(MyPublicInfoDetailActivity.this, "该订单暂时没有联系电话！", 5).show();
				}
			}
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.updateInfo:
			Toast.makeText(MyPublicInfoDetailActivity.this, "修改信息", 5).show();
			break;
		case R.id.actionListBut:
			// Toast.makeText(MyPublicInfoDetailActivity.this, "查看竞价信息",
			// 5).show();
			Intent intent = new Intent(MyPublicInfoDetailActivity.this, UserAuctionListActivity.class);
			intent.putExtra("carInfoId",carInfoId);
			startActivity(intent);
			break;
		case R.id.rebeak:
			finish();
			break;
		}
	}
}
