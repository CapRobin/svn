package com.xibeiwuliu.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.xibeiwuliu.view.MyView;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：PublishVehicleActivity.java
 * @Describe：发布车源
 * @Author: yfr5734@gmail.com
 * @Date：2014年5月12日 上午9:29:41
 * @Version v1.0 *
 * 
 */
public class PublishVehicleActivity extends BaseActivity implements OnClickListener {
	private boolean isShowRightBut = false;
	private Button publicInfoBut;
	private String getMsg;
	private MyView myView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.public_vehicle_info);
		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout(getMsg, isShowRightBut);
		initView();
	}

	/**
	 * 
	 * @Describe：初始化View
	 * @Throws:
	 * @Date：2014年5月6日 下午3:45:47
	 * @Version v1.0
	 * 
	 */
	private void initView() {
		myView = (MyView) findViewById(R.id.myView);
		publicInfoBut = (Button) findViewById(R.id.publicInfoBut);
		// //设置出发地合目的地为空
		MyView.setStartAddress();
		MyView.setEndAddress();

		publicInfoBut.setOnClickListener(this);
	}

	/**
	 * 
	 * @Describe：初始化View
	 * @Throws:
	 * @Date：2014年5月6日 下午3:45:47
	 * @Version v1.0
	 * 
	 */

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.publicInfoBut:
			String startAddress = MyView.getStartAddress();
			String endAddress = MyView.getEndAddress();
			if (TextUtils.isEmpty(startAddress) && TextUtils.isEmpty(endAddress)) {
				Toast.makeText(PublishVehicleActivity.this, "请先选择出发地和目的地！", 5).show();
			} else if (TextUtils.isEmpty(startAddress)) {
				Toast.makeText(PublishVehicleActivity.this, "请先选择出发地！", 5).show();
			} else if (TextUtils.isEmpty(endAddress)) {
				Toast.makeText(PublishVehicleActivity.this, "请先选择目的地！", 5).show();
			} else {
				Toast.makeText(PublishVehicleActivity.this, "出发地为：" + myView.getStartAddress() + "\n目的地为：" + myView.getEndAddress(), 5).show();
			}
			break;
		}
	}
}