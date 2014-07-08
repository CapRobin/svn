package com.estar.NMGMClient;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.estar.comm.MySuperActivity;
import com.estar.comm.MySuperApplication;

public class serviceWnd extends MySuperActivity{

	private MySuperApplication app;
	
	private WebView mWebView1;  
	Button backBtn;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_wnd);

		//全局变量相关
		app = (MySuperApplication) getApplication(); //获得我们的应用程序MySuperApplication
		
		backBtn=(Button)findViewById(R.id.typeBtn);
		backBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				serviceWnd.this.finish();
			}
		});
		
		mWebView1 = (WebView) findViewById(R.id.webView01);
		mWebView1.setWebViewClient(new WebViewClient() 
	    {
	      /*延含学习
	      @Override
	      public void onPageFinished(WebView view, String url)
	      {
	        // TODO Auto-generated method stub
	        super.onPageFinished(view, url);
	      }
	      */     
	    });
		
		String url = app.GetServiceUrl();//利用全局变量取得服务条款路径或getString(R.string.serviceUrl)
		// 得到WebSettings对象，设置支持JavaScript参数  
        // 如果访问的页面中有JavaScript，则WebView必须设置支持JavaScript ，否则显示空白页面  
		mWebView1.getSettings().setJavaScriptEnabled(true); 
		// 加载URL  
		try {
			mWebView1.loadUrl(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		//Toast.makeText(serviceWnd.this,url,Toast.LENGTH_LONG).show(); 
	}

	
}
