package com.cai.reader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity {
	private Button btnLogin;
	private Button btnChooseRss;
	private Button btnPlayGame;
	private void initView(){
		btnLogin = (Button)findViewById(R.id.btn_login_in_last);
		btnChooseRss = (Button)findViewById(R.id.btn_login_chose_rss);
		btnPlayGame = (Button)findViewById(R.id.btn_login_game_scrawl);
	
}
 private void initData(){
	
 }
 private void initListener(){
	 btnLogin.setOnClickListener(listener);
	 btnChooseRss.setOnClickListener(listener);
	 btnPlayGame.setOnClickListener(listener);
 }
 	private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent  intent=null;
			switch (v.getId()) {
			case R.id.btn_login_in_last:
				intent=new Intent(LoginActivity.this,MainRssReaderActivity.class);
				intent.putExtra(MainRssReaderActivity.INTENT_EXTRA_RSS_NAME,
						getPreferences(BIND_AUTO_CREATE).getString("pref_save_rss_name",
						"http://news.163.com/special/00011K6L/rss_newstop.xml"));
				intent.putExtra(MainRssReaderActivity.INTENT_EXTRA_RSS_TITLE,getPreferences(BIND_AUTO_CREATE).
						getString("pref_save_rss_title","ÍøÒ×ÐÂÎÅ"));
				break;
			case R.id.btn_login_chose_rss:
				intent=new Intent(LoginActivity.this,ChooseRssActivity.class);
				
				break;
			case R.id.btn_login_game_scrawl:
				intent=new Intent(LoginActivity.this,ScrawlGameActivity.class);
				
				break;

			}
			startActivity(intent);
		     finish();
			
		}
	};
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initView();
		initData();
		initListener();
	}
	
	
}
