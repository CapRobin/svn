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
import com.steellogistics.util.MethodUtil;
import com.steellogistics.web.UserInfoWeb;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：RegisterActivity.java
 * @Describe：用户注册页面
 * @Author: yfr5734@gmail.com
 * @Date：2014年7月24日 上午9:33:54
 * @Version v1.0
 */
public class RegisterActivity extends BaseActivity {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = false;
	private String getMsg;
	private boolean isRegister = false;

	private Button registerBtn;
	private EditText realNameEdit, userNameEdit, pwdEdit, affirmPwdEdit;
	private String realNameEditStr, userNameEditStr, pwdEditStr, affirmPwdEditStr;
	private Spinner userTypeSpin = null;
	private int userType = 0; // 0.普通会员; 1.公司商户

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.register);
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
		setTitleInfo("注    册", isShowLeftBut, "返回", isShowRightBut, "个人中心");
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
					startActivity(new Intent(RegisterActivity.this, PersonalCenterActivity.class));
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
		realNameEdit = (EditText) findViewById(R.id.realNameEdit);
		userNameEdit = (EditText) findViewById(R.id.userNameEdit);
		pwdEdit = (EditText) findViewById(R.id.pwdEdit);
		affirmPwdEdit = (EditText) findViewById(R.id.affirmPwdEdit);
		userTypeSpin = (Spinner) findViewById(R.id.userTypeSpin);
		registerBtn = (Button) findViewById(R.id.registerBtn);

		userTypeSpin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

				String getUserTypeStr = userTypeSpin.getSelectedItem().toString().toString();
				userType = position;
				Toast.makeText(RegisterActivity.this, userType + "-------" + getUserTypeStr, 5).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		registerBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startRegister();
			}
		});

	}

	/**
	 * 
	 * @Describe：开始注册
	 * @Throws:
	 * @Date：2014年5月27日 上午9:48:50
	 * @Version v1.0
	 */
	private void startRegister() {
		final String Imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE)).getDeviceId();
		realNameEditStr = realNameEdit.getText().toString().trim();
		userNameEditStr = userNameEdit.getText().toString().trim();
		pwdEditStr = pwdEdit.getText().toString().trim();
		affirmPwdEditStr = affirmPwdEdit.getText().toString().trim();

		// 输入姓名验证
		if (TextUtils.isEmpty(realNameEditStr)) {
			realNameEdit.setError(getResources().getString(R.string.realName));
			realNameEdit.setFocusable(true);
			realNameEdit.setFocusableInTouchMode(true);
			realNameEdit.requestFocus();
			return;
		}
		if (MethodUtil.chineseLength(realNameEditStr) < 3 || MethodUtil.chineseLength(realNameEditStr) > 16) {
			realNameEdit.setError(getResources().getText(R.string.really_name_on));
			realNameEdit.setFocusable(true);
			realNameEdit.setFocusableInTouchMode(true);
			realNameEdit.requestFocus();
			return;
		}

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
		if (TextUtils.isEmpty(pwdEditStr)) {
			pwdEdit.setError(getResources().getString(R.string.setPwdError));
			pwdEdit.setFocusable(true);
			pwdEdit.setFocusableInTouchMode(true);
			pwdEdit.requestFocus();
			return;
		}

		// 确认密码验证
		if (!pwdEditStr.equals(affirmPwdEditStr)) {
			affirmPwdEdit.setError(getResources().getString(R.string.affirmPwdError));
			affirmPwdEdit.setFocusable(true);
			affirmPwdEdit.setFocusableInTouchMode(true);
			affirmPwdEdit.requestFocus();
			return;
		} else {
			pwdEditStr = affirmPwdEditStr;
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
				if (isRegister) {
					finish();
					Toast.makeText(RegisterActivity.this, "注册成功并通过审核，您将有100天使用期限", 5).show();
				}
			}

			@Override
			public void get() {
				try {
					// 等待调用接口
					isRegister = UserInfoWeb.userRegister(realNameEditStr, userNameEditStr, pwdEditStr, userType, Imei, "1542519456");
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
