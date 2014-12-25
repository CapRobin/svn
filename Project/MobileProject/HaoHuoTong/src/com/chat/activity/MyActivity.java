package com.chat.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.way.chat.common.tran.bean.TranObject;
import com.way.chat.common.util.Constants;

/**
 * 自定义一个抽象的MyActivity类，每个Activity都继承他，实现消息的接收（优化�?能，减少代码重复�?
 * 
 * @author way
 * 
 */
public abstract class MyActivity extends Activity {
	/**
	 * 广播接收者，接收GetMsgService发�?过来的消�?
	 */
	private BroadcastReceiver MsgReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			TranObject msg = (TranObject) intent
					.getSerializableExtra(Constants.MSGKEY);
			if (msg != null) {//如果不是空，说明是消息广�?
				 System.out.println("GetReceiveMsg is ----------------------->>:" + msg);
				getMessage(msg);// 把收到的消息传�?给子�?
			} else {//如果是空消息，说明是关闭应用的广�?
				close();
			}
		}
	};

	/**
	 * 抽象方法，用于子类处理消息，
	 * 
	 * @param msg
	 * 传�?给子类的消息对象
	 */
	public abstract void getMessage(TranObject msg);

	/**
	 * 子类直接调用这个方法关闭应用
	 */
	public void close() {
		Intent i = new Intent();
		i.setAction(Constants.ACTION);
		sendBroadcast(i);
		finish();
	}

	@Override
	public void onStart() {// 在start方法中注册广播接收�?
		super.onStart();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Constants.ACTION);
		registerReceiver(MsgReceiver, intentFilter);// 注册接受消息广播

	}

	@Override
	protected void onStop() {// 在stop方法中注�?��播接收�?
		super.onStop();
		unregisterReceiver(MsgReceiver);// 注销接受消息广播
	}
}
