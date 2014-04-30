package com.xibeiwuliu.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.xibeiwuliu.adapter.ContactAdapter;
import com.xibeiwuliu.entity.ContactGenerator;
import com.xibeiwuliu.entity.ContactInfo;
import com.xibeiwuliu.util.MethodUtil;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：ContactListActivity.java
 * @Describe：联系人列表
 * @Author Administrator yfr5734@gmail.com
 * @Date：2013-12-2 下午8:37:24
 * @Version v1.0
 */
public class ContactListActivity extends AbActivity implements OnClickListener {
	private List<ContactInfo> lists = null;
	private ListView contactsList;
	private ImageView backImage;
	private ContactAdapter adapter;
	private List<Integer> listItemID = new ArrayList<Integer>();
	private EditText phoneEdit;
	private EditText smsContent;
	private Button sendBtn;
	private String smsText = null;
	private SmsManager smsManager = SmsManager.getDefault();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_list);
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
		contactsList = (ListView) findViewById(R.id.contacts_list_view);
		ContactGenerator generator = new ContactGenerator(this);
		lists = generator.generateList();
		adapter = new ContactAdapter(ContactListActivity.this, lists);
		contactsList.setAdapter(adapter);
		backImage = (ImageView) findViewById(R.id.backImage);
		phoneEdit = (EditText) findViewById(R.id.numberContent);
		smsContent = (EditText) findViewById(R.id.smsContent);
		sendBtn = (Button) findViewById(R.id.sendBtn);
		smsContent.setHeight(100);
		smsContent.setMinHeight(70);

		smsText = getResources().getString(R.string.smsText);
		smsContent.setText(smsText);
		backImage.setOnClickListener(this);
		sendBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backImage:
			finish();
			break;
		case R.id.addNumber:
			startActivity(new Intent(ContactListActivity.this, ContactListActivity.class));
			break;
		case R.id.sendBtn:

			// 获取输入号码
			String inputPhone = phoneEdit.getText().toString().trim();
			if (!TextUtils.isEmpty(inputPhone)) {
				if (!MethodUtil.isMobileNo(inputPhone)) {
					phoneEdit.setError(getResources().getText(R.string.error_phone));
					phoneEdit.setFocusable(true);
					phoneEdit.setFocusableInTouchMode(true);
					phoneEdit.requestFocus();
					return;
				}
			}

			// 获取选择联系人
			listItemID.clear();
			for (int i = 0; i < adapter.mChecked.size(); i++) {
				if (adapter.mChecked.get(i)) {
					listItemID.add(i);
				}
			}
			int userSize = listItemID.size();
			if ((userSize > 0 && listItemID != null) || !TextUtils.isEmpty(inputPhone)) {
				try {
					// 输入号码发送
					if (!TextUtils.isEmpty(inputPhone)) {
						sendSms(inputPhone);
					}

					// 选择号码发送
					if (userSize > 0 && listItemID != null) {
						for (int i = 0; i < userSize; i++) {
							String phoneNumber = lists.get(listItemID.get(i)).getPhone();
							sendSms(phoneNumber);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				Toast.makeText(ContactListActivity.this, "发送完成", Toast.LENGTH_SHORT).show();
				finish();

			} else {
				Toast.makeText(ContactListActivity.this, "请输入或选择联系人", Toast.LENGTH_LONG).show();
			}
			break;
		}
	}

	/**
	 * 
	 * 描述：发送短信
	 * 
	 * @param phoneNumber
	 * @throws
	 * @date：2013-12-4 上午9:25:49
	 * @version v1.0
	 */
	private void sendSms(String phoneNumber) {

		if (smsText.length() > 70) {
			List<String> contents = smsManager.divideMessage(smsText);
			for (String sms : contents) {
				smsManager.sendTextMessage(phoneNumber, null, sms, null, null);
			}
		} else {
			smsManager.sendTextMessage(phoneNumber, null, smsText, null, null);
		}
	}
}