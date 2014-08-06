package com.steellogistics.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

import com.steellogistics.R;

/**
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：网页显示界面
 * @Author: yfr5734@gmail.com
 * @Date：2014年4月28日 下午4:17:08
 * @Version v1.0
 */
public class MyWebView extends Activity {
	private WebView webView;
	private static String getURL = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 取消标题
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 进行全屏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.web_view);
		// 获取URL地址
		getURL = getIntent().getStringExtra("url");

		// 实例化WebView
		webView = (WebView) this.findViewById(R.id.wv_oauth);
		// 调用loadUrl()方法进行加载内容
		webView.loadUrl(getURL);
		// 设置WebView的属性，此时可以去执行JavaScript脚本
		webView.getSettings().setJavaScriptEnabled(true);
	}
}
