package com.estar.NMGMClient;

import android.os.Bundle;

import com.estar.comm.MySuperActivity;

public class TestActivity extends MySuperActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.register_x);
//		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout("зЂВс", false);
//		initView();
	}

}
