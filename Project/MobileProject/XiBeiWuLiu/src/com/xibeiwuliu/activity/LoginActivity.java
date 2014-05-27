package com.xibeiwuliu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ab.global.AbAppException;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbThread;
import com.xibeiwuliu.global.MyApplication;
import com.xibeiwuliu.util.MethodUtil;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：LoginActivity.java
 * @Describe：用户登录页面
 * @Author: yfr5734@gmail.com
 * @Date：2014年5月27日 上午10:46:00
 * @Version v1.0
 */
public class LoginActivity extends BaseActivity {
	private MyApplication application = null;
	private Button loginBtn;
	private EditText userNameEdit, userPwdEdit;
	private String getMsg, userNameEditStr, userPwdEditStr = null;
	private Spinner userTypeSpin = null;
	private boolean isShowRightBut = true; // 是否显示右边按钮
	private boolean isLogin = false;
	private int userType = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		application = (MyApplication) abApplication;
		setAbContentView(R.layout.login);
		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout(getMsg, isShowRightBut);
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
		loginBtn = (Button) findViewById(R.id.loginBtn);
		userNameEdit = (EditText) findViewById(R.id.userNameEdit);
		userPwdEdit = (EditText) findViewById(R.id.userPwdEdit);
		userTypeSpin = (Spinner) findViewById(R.id.userTypeSpin);
		userTypeSpin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

				String getUserTypeStr = userTypeSpin.getSelectedItem().toString().toString();
				if ("司机".equals(getUserTypeStr)) {
					userType = 0;
				} else {
					userType = 1;
				}
				Toast.makeText(LoginActivity.this, getUserTypeStr, 5).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startLogin();

			}
		});

		// 右侧按钮的点击事件
		if (isShowRightBut) {
			this.rightTitleBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String Msg = "注    册";
					Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
					intent.putExtra("msg", Msg);
					startActivity(intent);
				}
			});
		}
	}

	/**
	 * 
	 * @Describe：用户登录
	 * @Throws:
	 * @Date：2014年5月27日 上午10:44:06
	 * @Version v1.0
	 */
	private void startLogin() {
		final String Imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE)).getDeviceId();
		userNameEditStr = userNameEdit.getText().toString().trim();
		userPwdEditStr = userPwdEdit.getText().toString().trim();

		// 输入电话验证
		if (TextUtils.isEmpty(userNameEditStr)) {
			userNameEdit.setError(getResources().getString(R.string.phoneNumError));
			userNameEdit.setFocusable(true);
			userNameEdit.setFocusableInTouchMode(true);
			userNameEdit.requestFocus();
			return;
		}
		if (!MethodUtil.isMobileNo(userNameEditStr)) {
			userNameEdit.setError(getResources().getText(R.string.error_phone));
			userNameEdit.setFocusable(true);
			userNameEdit.setFocusableInTouchMode(true);
			userNameEdit.requestFocus();
			return;
		}

		// 输入密码验证
		if (TextUtils.isEmpty(userPwdEditStr)) {
			userPwdEdit.setError(getResources().getString(R.string.setPwdError));
			userPwdEdit.setFocusable(true);
			userPwdEdit.setFocusableInTouchMode(true);
			userPwdEdit.requestFocus();
			return;
		}

		// 显示进度框
		showProgressDialog();
		AbThread mAbTaskThread = new AbThread();
		// 定义异步执行的对象
		final AbTaskItem item = new AbTaskItem();
		item.listener = new AbTaskListener() {

			@Override
			public void update() {
				removeProgressDialog();
				if (isLogin) {
					Toast.makeText(LoginActivity.this, "注册成功", 5).show();
					application.isLogin = isLogin;
				} else {
					Toast.makeText(LoginActivity.this, "注册失败", 5).show();
				}
			}

			@Override
			public void get() {
				try {
					isLogin = com.xibeiwuliu.web.UserInfoWeb.userLogin(userNameEditStr, userPwdEditStr, userType, Imei);
				} catch (AbAppException e) {
					e.printStackTrace();
					showToastInThread(e.getMessage());
				}
			};
		};
		// 开始执行
		mAbTaskThread.execute(item);
	}
}
