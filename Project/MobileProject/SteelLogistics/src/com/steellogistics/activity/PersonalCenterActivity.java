package com.steellogistics.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.steellogistics.R;
import com.steellogistics.entity.UserInfo;
import com.steellogistics.global.MyApplication;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：PersonalCenterActivity.java
 * @Describe：个人中心
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月22日 上午11:07:42
 * @Version v1.0
 */
public class PersonalCenterActivity extends BaseActivity {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = false;
	private MyApplication application = null;
	private TextView userName, userGrade, expireTime;
	private EditText realNameEdit, identityCardEdit, sexEdit, ageEdit, mobileEdit, telephoneEdit, emailEdit, industryEdit, addressEdit, otherInfoEdit;
	private Button updateSubmit = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.personal_center);
		application = (MyApplication) getApplication();
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
		setTitleInfo("个人中心", isShowLeftBut, "返回", isShowRightBut, null);
		if (isShowLeftBut) {
			titleLeftBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}
	}

	/**
	 * 
	 * @Describe：初始化View
	 * @Throws:
	 * @Date：2014年4月28日 上午11:07:32
	 * @Version v1.0
	 */
	private void initView() {
		UserInfo info = application.userInfo;

		userName = (TextView) findViewById(R.id.userName);
		userGrade = (TextView) findViewById(R.id.userGrade);
		expireTime = (TextView) findViewById(R.id.expireTime);
		realNameEdit = (EditText) findViewById(R.id.realNameEdit);
		identityCardEdit = (EditText) findViewById(R.id.identityCardEdit);
		sexEdit = (EditText) findViewById(R.id.sexEdit);
		ageEdit = (EditText) findViewById(R.id.ageEdit);
		mobileEdit = (EditText) findViewById(R.id.mobileEdit);
		telephoneEdit = (EditText) findViewById(R.id.telephoneEdit);
		emailEdit = (EditText) findViewById(R.id.emailEdit);
		industryEdit = (EditText) findViewById(R.id.industryEdit);
		addressEdit = (EditText) findViewById(R.id.addressEdit);
		otherInfoEdit = (EditText) findViewById(R.id.otherInfoEdit);
		updateSubmit = (Button) findViewById(R.id.updateSubmit);

		if (info != null) {
			userName.setText(info.getUserName());
			userGrade.setText(info.getGrade());
			expireTime.setText(info.getLasttime());

			realNameEdit.setText(info.getRealName());
			identityCardEdit.setText(info.getIdentityCard());
			sexEdit.setText(info.getSex());
			ageEdit.setText(info.getAge());
			mobileEdit.setText(info.getMobile());
			telephoneEdit.setText(info.getTelephone());
			emailEdit.setText(info.getEmail());
			industryEdit.setText(info.getIndustry());
			addressEdit.setText(info.getAddress());
			otherInfoEdit.setText(info.getCompanyInfo());
		}

		updateSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// finish();
				getPersonalInfo();
				Toast.makeText(PersonalCenterActivity.this, "修改个人信息成功！", 50).show();
			}
		});

	}

	/**
	 * 
	 * @Describe：获取修改后的用户信息
	 * @Throws:
	 * @Date：2014年8月1日 上午11:46:44
	 * @Version v1.0
	 */
	private void getPersonalInfo() {
		UserInfo userInfo = new UserInfo();

		userInfo.setUserName(userName.getText().toString());
		userInfo.setGrade(userGrade.getText().toString());
		userInfo.setLasttime(expireTime.getText().toString());

		userInfo.setRealName(realNameEdit.getText().toString());
		userInfo.setIdentityCard(identityCardEdit.getText().toString());
		userInfo.setSex(sexEdit.getText().toString());
		userInfo.setAge(ageEdit.getText().toString());
		userInfo.setMobile(mobileEdit.getText().toString());
		userInfo.setTelephone(telephoneEdit.getText().toString());
		userInfo.setEmail(emailEdit.getText().toString());
		userInfo.setIndustry(industryEdit.getText().toString());
		userInfo.setAddress(addressEdit.getText().toString());
		userInfo.setCompanyInfo(otherInfoEdit.getText().toString());
		Gson gson = new Gson();
		String getUserInfo = gson.toJson(userInfo);
		System.out.println("个人信息Json串为----------->>" + getUserInfo);

		// 保存个人信息到全局变量
		application.userInfo = userInfo;
		finish();
		Toast.makeText(PersonalCenterActivity.this, "修改个人信息成功", 50).show();
	}
}
