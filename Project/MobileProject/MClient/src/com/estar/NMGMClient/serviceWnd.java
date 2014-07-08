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

		//ȫ�ֱ������
		app = (MySuperApplication) getApplication(); //������ǵ�Ӧ�ó���MySuperApplication
		
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
	      /*�Ӻ�ѧϰ
	      @Override
	      public void onPageFinished(WebView view, String url)
	      {
	        // TODO Auto-generated method stub
	        super.onPageFinished(view, url);
	      }
	      */     
	    });
		
		String url = app.GetServiceUrl();//����ȫ�ֱ���ȡ�÷�������·����getString(R.string.serviceUrl)
		// �õ�WebSettings��������֧��JavaScript����  
        // ������ʵ�ҳ������JavaScript����WebView��������֧��JavaScript ��������ʾ�հ�ҳ��  
		mWebView1.getSettings().setJavaScriptEnabled(true); 
		// ����URL  
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
