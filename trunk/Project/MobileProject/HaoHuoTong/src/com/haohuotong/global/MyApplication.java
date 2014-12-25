package com.haohuotong.global;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.haohuotong.entity.UserInfo;
import com.haohuotong.other.EntitySet;
import com.haohuotong.other.InfoEntity;
//import com.haohuotong.other.LogisticsDB;
import com.haohuotong.other.UserEntity;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @name：MyApplication.java
 * @describe：全局应用
 * @author Administrator
 * @date：2013-11-11 下午1:29:05
 * @version v1.0
 */
public class MyApplication extends Application {
	public InfoEntity entity = null;
	public int m = 0;
	public EntitySet entitySet = null;
//	public boolean loginState = false; // false: 未登录 true:已登录
	private static Context context;
//	private static LogisticsDB logisticsDB;

	public static final String strKey = "BmMSMcakujWRDzG8aFGPQgeB";
	private static MyApplication mInstance = null;
	public boolean m_bKeyRight = true;
	public BMapManager mBMapManager = null;
	public UserEntity userEntity = null;
	public boolean isLogin = false; // false: 未登录 true:已登录
	public String getPushMsg = null; // 得到推送消息
	public String firstGetMsg = null; // 得到推送消息
	public String socketRegisterCode = null; // 得到推送消息
	public int newMsgNum = 0;// 新消息条�?
	public UserInfo userInfo = null; 

	@Override
	public void onCreate() {
		super.onCreate();

		context = getApplicationContext();
//		logisticsDB = new LogisticsDB(context);
//		logisticsDB.open().close(); // to initialize

		mInstance = this;
		initEngineManager(this);
	}

	public static Context getAppContext() {
		return MyApplication.context;
	}

//	public static LogisticsDB getLogisticsDB() {
//		return MyApplication.logisticsDB;
//	}

	public void initEngineManager(Context context) {
		if (mBMapManager == null) {
			mBMapManager = new BMapManager(context);
		}

		if (!mBMapManager.init(strKey, new MyGeneralListener())) {
			Toast.makeText(MyApplication.getInstance().getApplicationContext(), "BMapManager  初始化错误!", Toast.LENGTH_LONG).show();
		}
	}

	public static MyApplication getInstance() {
		return mInstance;
	}

	// 常用事件监听，用来处理通常的网络错误，授权验证错误等
	public static class MyGeneralListener implements MKGeneralListener {

		@Override
		public void onGetNetworkState(int iError) {
			if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
				Toast.makeText(MyApplication.getInstance().getApplicationContext(), "您的网络出错啦！", Toast.LENGTH_LONG).show();
			} else if (iError == MKEvent.ERROR_NETWORK_DATA) {
				Toast.makeText(MyApplication.getInstance().getApplicationContext(), "输入正确的检索条件！", Toast.LENGTH_LONG).show();
			}
			// ...
		}

		@Override
		public void onGetPermissionState(int iError) {
			if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
				// 授权Key错误：
				Toast.makeText(MyApplication.getInstance().getApplicationContext(), "请在 DemoApplication.java文件输入正确的授权Key！", Toast.LENGTH_LONG).show();
				MyApplication.getInstance().m_bKeyRight = false;
			}
		}
	}
}
