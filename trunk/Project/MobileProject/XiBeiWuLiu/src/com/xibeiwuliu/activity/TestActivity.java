package com.xibeiwuliu.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.xibeiwuliu.global.MyApplication;

public class TestActivity extends BaseActivity {
	private MyApplication application = null;
	private TextView text;
	private String getMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.test);
		application = (MyApplication) abApplication;
		getMsg = getIntent().getStringExtra("msg");
		initTitleLayout(getMsg, false);
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
		text = (TextView) findViewById(R.id.text);
		text.setText(getMsg);

	}
}
