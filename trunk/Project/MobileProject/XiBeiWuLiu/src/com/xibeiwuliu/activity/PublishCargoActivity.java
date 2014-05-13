package com.xibeiwuliu.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.xibeiwuliu.view.MyView;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：PublishCargoActivity.java
 * @Describe：发布货源
 * @Author: yfr5734@gmail.com
 * @Date：2014年5月12日 上午9:29:08
 * @Version v1.0 *
 * 
 */
public class PublishCargoActivity extends BaseActivity implements OnClickListener {
	private String getMsg;
	private boolean isShowRightBut = false;
	private Button publicInfoBut;
	private MyView myView = null;
	private EditText zcsjEdit, ddsjEdit = null;
	private ImageView mapImageAddr;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.public_cargo_info);
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
		zcsjEdit = (EditText) findViewById(R.id.zcsjEdit);
		ddsjEdit = (EditText) findViewById(R.id.ddsjEdit);
		mapImageAddr = (ImageView) findViewById(R.id.mapImageAddr);
		// isYuYueBut = (Button) findViewById(R.id.isYuYueBut);
		// //设置出发地合目的地为空
		MyView.setStartAddress();
		MyView.setEndAddress();

		publicInfoBut.setOnClickListener(this);
		zcsjEdit.setOnClickListener(this);
		ddsjEdit.setOnClickListener(this);
		mapImageAddr.setOnClickListener(this);
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
				Toast.makeText(PublishCargoActivity.this, "请先选择出发地和目的地！", 5).show();
			} else if (TextUtils.isEmpty(startAddress)) {
				Toast.makeText(PublishCargoActivity.this, "请先选择出发地！", 5).show();
			} else if (TextUtils.isEmpty(endAddress)) {
				Toast.makeText(PublishCargoActivity.this, "请先选择目的地！", 5).show();
			} else {
				Toast.makeText(PublishCargoActivity.this, "出发地为：" + myView.getStartAddress() + "\n目的地为：" + myView.getEndAddress(), 5).show();
			}
			break;
		case R.id.zcsjEdit:
			Toast.makeText(PublishCargoActivity.this, "请选择装车时间", 5).show();
			break;
		case R.id.ddsjEdit:
			Toast.makeText(PublishCargoActivity.this, "请选择到达时间", 5).show();
			break;
		case R.id.mapImageAddr:
			Toast.makeText(PublishCargoActivity.this, "进入地图选取地址", 5).show();
			break;
		}
	}

}