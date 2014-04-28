package com.xibeiwuliu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

/**
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name����ҳ��ʾ����
 * @Author: yfr5734@gmail.com
 * @Date��2014��4��28�� ����4:17:08
 * @Version v1.0
 */
public class MyWebView extends Activity {
	private WebView webView;
	private static String getURL = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ȡ������
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ����ȫ��
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.web_view);
		// ��ȡURL��ַ
		getURL = getIntent().getStringExtra("url");

		// ʵ����WebView
		webView = (WebView) this.findViewById(R.id.wv_oauth);
		// ����loadUrl()�������м�������
		webView.loadUrl(getURL);
		// ����WebView�����ԣ���ʱ����ȥִ��JavaScript�ű�
		webView.getSettings().setJavaScriptEnabled(true);
	}
}
