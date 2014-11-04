package com.gohome.activity;

import java.text.SimpleDateFormat;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager.LayoutParams;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.gohome.R;
import com.gohome.global.MyApplication;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name��BaseActivity.java
 * @Describe��Activity����
 * @Author: yfr5734@gmail.com
 * @Date��2014��5��12�� ����9:23:10
 * @Version v1.0 *
 * 
 */
public class BaseActivity extends AbActivity {
	public Button titleLeftBut, titleRightBut;
	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd   hh:mm:ss");
	public String formatStr = "yyyy-MM-dd HH:mm:ss";
	public TextView titleName = null;
	private LayoutInflater inflater = null;
	public RelativeLayout contentLayout, titleLayout = null;
	public LinearLayout.LayoutParams layoutParamsFF = null;
	public MyApplication application = null;
	private AlertDialog dialog;
	// public UserInfo baseUserInfo = null;
	public Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(BaseActivity.this, "��ʾ����", 5).show();
				break;
			case 1:

				break;
			case 2:

				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base);
		application = (MyApplication) getApplication();
		titleLeftBut = (Button) findViewById(R.id.titleLeftBut);
		titleRightBut = (Button) findViewById(R.id.titleRightBut);
		titleName = (TextView) findViewById(R.id.titleName);
		titleLayout = (RelativeLayout) findViewById(R.id.titleLayout);
		contentLayout = (RelativeLayout) findViewById(R.id.contentLayout);
		inflater = LayoutInflater.from(this);
		layoutParamsFF = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		// baseUserInfo = application.userInfo;
	}

	/**
	 * 
	 * ��������ʾ��ʾ�Ի���
	 * 
	 * @throws
	 * @date��2013-11-19 ����10:36:52
	 * @version v1.0
	 */
	public void showMyDialog() {
		String titleInfo = null;
		Builder builder = new AlertDialog.Builder(this);
		dialog = builder.create();
		View retieve = LayoutInflater.from(this).inflate(R.layout.dialog_show, null);
		dialog.setView(retieve, 0, 0, 0, 0);
		Button acceptBtn = (Button) retieve.findViewById(R.id.acceptBtn);
		Button unAcceptBtn = (Button) retieve.findViewById(R.id.unAcceptBtn);
		TextView dialogTitleText1 = (TextView) retieve.findViewById(R.id.dialogTitleText1);
		TextView setMessage = (TextView) retieve.findViewById(R.id.setMessage);
		dialogTitleText1.setText("��ܰ��ʾ");
		setMessage.setText("		��ȷ��Ҫ�˳�������");
		acceptBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});

		unAcceptBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	/**
	 * 
	 * @Describe�����ü���������ʾ����View
	 * @param id
	 * @Throws:
	 * @Date��2014��7��23�� ����5:05:58
	 * @Version v1.0
	 */
	public void setBaseContentView(int id) {
		contentLayout.removeAllViews();
		contentLayout.addView(inflater.inflate(id, null), layoutParamsFF);
	}

	/**
	 * 
	 * @Describe�����ñ��������������
	 * @param pageName
	 * @param isShowLeftBut
	 * @param leftButText
	 * @param isShowRightBut
	 * @param rightButText
	 * @Throws:
	 * @Date��2014��7��23�� ����5:05:27
	 * @Version v1.0
	 */
	public void setTitleInfo(String pageName, boolean isShowLeftBut, String leftButText, boolean isShowRightBut, String rightButText) {
		titleName.setText(pageName);
		if (isShowLeftBut) {
			titleLeftBut.setVisibility(View.VISIBLE);
			if (!TextUtils.isEmpty(leftButText)) {
				titleLeftBut.setText(leftButText);
			} else {
				titleLeftBut.setVisibility(View.GONE);
			}
		}

		if (isShowRightBut) {
			titleRightBut.setVisibility(View.VISIBLE);
			if (!TextUtils.isEmpty(rightButText)) {
				titleRightBut.setText(rightButText);
			}
		} else {
			titleRightBut.setVisibility(View.GONE);
		}
	}
}