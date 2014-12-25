package com.haohuotong.activity;

import java.util.List;

import com.haohuotong.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：ShareInfoActivity.java
 * @Describe：信息分享界面
 * @Author Administrator yfr5734@gmail.com
 * @Date：2013-12-3 下午2:33:26
 * @Version v1.0
 */
public class ShareInfoActivity extends Activity {
	private EditText phone_number_editText;
	private EditText sms_content_editText;
	private Button send_sms_button;
	private ImageView addNumber;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_sms);

		phone_number_editText = (EditText) findViewById(R.id.numberContent);
		sms_content_editText = (EditText) findViewById(R.id.smsContent);
		send_sms_button = (Button) findViewById(R.id.sendBtn);
		addNumber = (ImageView) findViewById(R.id.addNumber);

		addNumber.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(ShareInfoActivity.this,ContactListActivity.class));
			}
		});
		
		send_sms_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String phone_number = phone_number_editText.getText().toString().trim();
				String sms_content = sms_content_editText.getText().toString().trim();
				if (phone_number.equals("")) {
					Toast.makeText(ShareInfoActivity.this, "请输入手机号", Toast.LENGTH_LONG).show();
				} else {
					SmsManager smsManager = SmsManager.getDefault();
					if (sms_content.length() > 70) {
						List<String> contents = smsManager.divideMessage(sms_content);
						for (String sms : contents) {
							smsManager.sendTextMessage(phone_number, null, sms, null, null);
						}
					} else {
						smsManager.sendTextMessage(phone_number, null, sms_content, null, null);
					}
					Toast.makeText(ShareInfoActivity.this, "发送完成", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

}