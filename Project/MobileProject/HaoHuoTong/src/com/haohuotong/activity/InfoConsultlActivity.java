package com.haohuotong.activity;

import android.os.Bundle;
import android.view.Window;

import com.haohuotong.R;


/**
 * Copyright (c) 2012 All rights reserved
 * 
 * @name��ConsultlActivity.java
 * @describe��ҵ����ѯ
 * @author Administrator
 * @date��2013-11-11 ����5:36:41
 * @version v1.0
 */
public class InfoConsultlActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.consult);
		
//		InfoEntity entity = application.entity;

	}

}
