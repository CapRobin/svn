package com.gohome.activity;

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

import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbThread;
import com.gohome.R;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name��LoginActivity.java
 * @Describe���û���¼ҳ��
 * @Author: yfr5734@gmail.com
 * @Date��2014��7��24�� ����9:30:26
 * @Version v1.0
 */
public class LoginActivity extends BaseActivity {
	private boolean isShowLeftBut = true;
	private boolean isShowRightBut = true;
	private Button loginBtn;
	private EditText userNameEdit, userPwdEdit;
	private String getMsg, userNameEditStr, userPwdEditStr = null;
	private Spinner userTypeSpin = null;
	private boolean isLogin = false;
	private int userType = 1;
//	private UserInfo getUserInfo = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.login);
		titleBarInitView();
		initView();
	}

	/**
	 * 
	 * @Describe����ʼ��������
	 * @Throws:
	 * @Date��2014��7��24�� ����9:41:44
	 * @Version v1.0
	 */
	private void titleBarInitView() {
		setTitleInfo("��    ¼", isShowLeftBut, "����", isShowRightBut, "ע��");
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
					startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
				}
			});
		}
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
//		loginBtn = (Button) findViewById(R.id.loginBtn);
//		userNameEdit = (EditText) findViewById(R.id.userNameEdit);
//		userPwdEdit = (EditText) findViewById(R.id.userPwdEdit);
//		userTypeSpin = (Spinner) findViewById(R.id.userTypeSpin);
//		userTypeSpin.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//				String getUserTypeStr = userTypeSpin.getSelectedItem().toString().toString();
//				if ("��ͨ��Ա".equals(getUserTypeStr)) {
//					userType = 1;
//				} else {
//					userType = 0;
//				}
//				Toast.makeText(LoginActivity.this, getUserTypeStr, 5).show();
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//			}
//		});
//
//		loginBtn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				startLogin();
//
//			}
//		});
	}

}
