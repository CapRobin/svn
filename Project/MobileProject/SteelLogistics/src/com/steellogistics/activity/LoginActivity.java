package com.steellogistics.activity;

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
import com.steellogistics.R;
import com.steellogistics.entity.UserInfo;
import com.steellogistics.global.MyApplication;
import com.steellogistics.util.MethodUtil;
import com.steellogistics.web.UserInfoWeb;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：LoginActivity.java
 * @Describe：用户登录页面
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月24日 上午9:30:26
 * @Version v1.0
 */
public class LoginActivity extends BaseActivity {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = true;
	private MyApplication application = null;
	private Button loginBtn;
	private EditText userNameEdit, userPwdEdit;
	private String getMsg, userNameEditStr, userPwdEditStr = null;
	private Spinner userTypeSpin = null;
	private boolean isLogin = false;
	private int userType = 1;
	private UserInfo getUserInfo = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.login);
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
		setTitleInfo("登    录", isShowLeftBut, "返回", isShowRightBut, "注册");
		if (isShowLeftBut) {
			titleLeftBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}

		if (isShowRightBut) {
			titleRightBut.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
				}
			});
		}
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
				if ("普通会员".equals(getUserTypeStr)) {
					userType = 1;
				} else {
					userType = 0;
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
				// if (getUserInfo != null) {
				// Toast.makeText(LoginActivity.this, "登录成功", 5).show();
				// application.userInfo = getUserInfo;
				// application.isLogin = true;
				// startActivity(new Intent(LoginActivity.this,
				// MainActivity.class));
				// }
				Toast.makeText(LoginActivity.this, "登录成功", 5).show();
				finish();
				application.isLogin = true;
			}

			@Override
			public void get() {
				try {
					// getUserInfo = UserInfoWeb.userLogin(userNameEditStr,
					// userPwdEditStr, userType, Imei);
					// } catch (AbAppException e) {
					// e.printStackTrace();
					// showToastInThread(e.getMessage());
					// }
				} catch (Exception e) {
					e.printStackTrace();
					showToastInThread(e.getMessage());
				}
			};
		};
		// 开始执行
		mAbTaskThread.execute(item);
	}

}
