package com.steellogistics.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.steellogistics.R;
import com.steellogistics.entity.UserInfo;
import com.steellogistics.util.MethodUtil;

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
	private TextView userName, userGrade, expireTime;
	private EditText realNameEdit, identityCardEdit, sexEdit, ageEdit, mobileEdit, telephoneEdit, emailEdit, industryEdit, addressEdit, otherInfoEdit;
	private Button updateSubmit = null;
	private UserInfo userInfo = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.personal_center);
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

		// 获取本地构造数据
		String getInfo = MethodUtil.getSharedPreferences(this, "AppData", "userinfo");

		if (!TextUtils.isEmpty(getInfo)) {
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			userInfo = gson.fromJson(getInfo, UserInfo.class);

			userName.setText(userInfo.getUserName());
			userGrade.setText(userInfo.getGrade());
			expireTime.setText(userInfo.getLasttime());
			realNameEdit.setText(userInfo.getRealName());
			identityCardEdit.setText(userInfo.getIdentityCard());
			sexEdit.setText(userInfo.getSex());
			ageEdit.setText(userInfo.getAge());
			mobileEdit.setText(userInfo.getMobile());
			telephoneEdit.setText(userInfo.getTelephone());
			emailEdit.setText(userInfo.getEmail());
			industryEdit.setText(userInfo.getIndustry());
			addressEdit.setText(userInfo.getAddress());
			otherInfoEdit.setText(userInfo.getCompanyInfo());
		}

		updateSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getPersonalInfo();
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

		// 保存个人信息到本地
		// setData(getUserInfo);
		MethodUtil.setSharedPreferences(this, "AppData", "userinfo", getUserInfo);
		// application.userInfo = userInfo;
		finish();
		Toast.makeText(PersonalCenterActivity.this, "修改个人信息成功", 50).show();
	}

	/**
	 * 
	 * @Describe：保存数据
	 * @Throws:
	 * @Date：2014年8月14日 下午4:15:50
	 * @Version v1.0
	 */
	private void setData(String savaString) {
		SharedPreferences sharedPreferences = getSharedPreferences("AppData", Context.MODE_WORLD_READABLE);
		Editor editor = sharedPreferences.edit();
		editor.putString("userinfo", savaString);
		// editor.putInt("age", new Integer(ageEditText.getText().toString()));
		editor.commit();
		Toast.makeText(PersonalCenterActivity.this, "UserInfo Sava is Successed ！", Toast.LENGTH_LONG).show();
	}

	/**
	 * 
	 * @Describe：获取数据
	 * @Throws:
	 * @Date：2014年8月14日 下午4:15:50
	 * @Version v1.0
	 */
	private String getData() {

		SharedPreferences sharedPreferences = this.getSharedPreferences("AppData", Context.MODE_PRIVATE);
		String userinfo = sharedPreferences.getString("userinfo", "");
		// int age = sharedPreferences.getInt("age", 20);
		// nameEditText.setText(name);
		// ageEditText.setText(String.valueOf(age));
		return userinfo;

	}
}
