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
 * @Name��VehicleListDetailActivity.java
 * @Describe����Ϣ����
 * @Author: yfr5734@gmail.com
 * @Date��2014��4��30�� ����3:40:08
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

		// ��ȡIntent���ݵ�����
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("bundle");
		vehicleInfo = (VehicleInfo) bundle.get("vehicleInfo");
		vehicleInfoId = Integer.parseInt(vehicleInfo.getAdd_id());
		Log.i("tag", "------------>vehicleInfoId=" + vehicleInfoId);
		initTitleLayout("��Ϣ����", true);
		initView();

	}

	/**
	 * 
	 * ��������ʼ��View
	 * 
	 * @throws
	 * @date��2013-11-13 ����10:21:24
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
				Toast.makeText(VehicleListDetailActivity.this, "��ģ�����ڿ����У����Ժ�...", 5).show();
			}
			break;
		case 1:
			// Toast.makeText(CommonalityInfoDetailActivity.this,
			// "��ģ�����ڿ����У����Ժ�...", 5).show();

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
					Toast.makeText(VehicleListDetailActivity.this, "�ö�����ʱû����ϵ�绰��", 5).show();
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
					Toast.makeText(VehicleListDetailActivity.this, "�ö�����ʱû����ϵ�绰��", 5).show();
				}
			} else {
				Toast.makeText(VehicleListDetailActivity.this, "��¼����ܲ鿴��ϵ��ʽ��", 5).show();
				// startActivityForResult(new
				// Intent(VehicleListDetailActivity.this, LoginActivity.class),
				// SHOWPHONE);
			}
			break;
		case R.id.addFriendBut:
			// boolean isLogin = application.isLogin;
			if (application.isLogin) {
				Toast.makeText(VehicleListDetailActivity.this, "��ģ�����ڿ����У����Ժ�...", 5).show();
			} else {
				Toast.makeText(VehicleListDetailActivity.this, "��¼�������Ӻ��ѣ�", 5).show();
				// startActivityForResult(new
				// Intent(VehicleListDetailActivity.this, LoginActivity.class),
				// ADDFRIENDINTENT);
			}
			break;
		case R.id.shareInfo:
			startActivity(new Intent(VehicleListDetailActivity.this, ContactListActivity.class));
			break;
		case R.id.myFriendBut:

			// Toast.makeText(CommonalityInfoDetailActivity.this, "�ҵĺ����б�",
			// 5).show();
			if (application.isLogin) {
				// showMyDialog();
			} else {
				Toast.makeText(VehicleListDetailActivity.this, "���ȵ�¼��", 5).show();
			}
			break;
		}
	}
}
