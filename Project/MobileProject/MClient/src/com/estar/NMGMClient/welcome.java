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
	//�����߳� ��������
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
				//����������ʱ3��
				 try {
					Thread.sleep(2000);//����������ʾ3��
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 //��ʾ��¼����
				 Intent intent = new Intent();
                 intent.setClass(welcome.this, login.class);//Ĭ��login.class		MainActivity
                 welcome.this.startActivity(intent);
                 finish();
			}
			
		}.start();
	}

}
