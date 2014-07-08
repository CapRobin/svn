package com.estar.NMGMClient;

import android.content.Intent;
import android.os.Bundle;

import com.estar.comm.MySuperActivity;

public class welcome extends MySuperActivity{

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		Start();
	}
	//利用线程 启动画面
	private void Start() {
		// TODO Auto-generated method stub
		new Thread(){

			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//super.run();
				//启动画面延时3秒
				 try {
					Thread.sleep(2000);//启动画面显示3秒
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 //显示登录窗口
				 Intent intent = new Intent();
                 intent.setClass(welcome.this, login.class);//默认login.class		MainActivity
                 welcome.this.startActivity(intent);
                 finish();
			}
			
		}.start();
	}

}
