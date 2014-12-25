package com.haohuotong.activity;

import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.haohuotong.R;
import com.haohuotong.adapter.ContactAdapter;
import com.haohuotong.global.MyApplication;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：ConsultActivity.java
 * @Describe：信息咨询
 * @Author Administrator yfr5734@gmail.com
 * @Date：2013-12-4 上午10:33:24
 * @Version v1.0
 */
public class ConsultActivity extends AbActivity {
	private MyApplication application = null;
	private ListView contactsList;
	private ContactAdapter adapter;
	private EditText consultContent;
	private Button consultBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.info_consult);
		
		initTitleRightLayout();
		initView();
	}
	
	/**
	 * 描述：初始化标题栏
	 * 
	 * @throws
	 * @date：2013-4-25 上午10:21:18
	 * @version v1.0
	 */
	private void initTitleRightLayout() {
		AbTitleBar mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText("信息咨询");
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
//		mAbTitleBar.setTitleLayoutBackground(R.drawable.top_bg);
        mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		application = (MyApplication) abApplication;

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
		contactsList = (ListView) findViewById(R.id.consultList);
		consultContent = (EditText) findViewById(R.id.consultContent);
		consultBtn = (Button) findViewById(R.id.consultBtn);
		
		consultContent.setHeight(100);
		consultContent.setMinHeight(70);

	}
}
