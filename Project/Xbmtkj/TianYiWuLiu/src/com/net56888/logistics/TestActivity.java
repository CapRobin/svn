package com.net56888.logistics;

import android.app.Activity;
import android.os.Bundle;

public class TestActivity extends Activity {

	/**
	 * 
	 * @Describe：测试页面
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * @Author: Administrator
	 * @Date：2014年12月26日 下午4:54:21
	 * @Version v1.0
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		intView();
	}

	// 初始化View
	private void intView() {

	}
}