package com.gohome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.gohome.R;

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

				public void onClick(View v) {
					finish();
				}
			});
		}
		if (isShowRightBut) {
			titleRightBut.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					startActivity(new Intent(RegisterActivity.this, MainActivity.class));
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

//		userTypeSpin.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//				String getUserTypeStr = userTypeSpin.getSelectedItem().toString().toString();
//				userType = position;
//				Toast.makeText(RegisterActivity.this, userType + "-------" + getUserTypeStr, 5).show();
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//			}
//		});
//
//		registerBtn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				startRegister();
//			}
//		});

	}

}
