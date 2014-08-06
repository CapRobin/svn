package com.steellogistics.global;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

import com.steellogistics.entity.BuyInfoDetail;
import com.steellogistics.entity.SupplyInfoDetail;
import com.steellogistics.entity.UserInfo;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：MyApplication.java
 * @Describe：全局变量
 * @Author: yfr5734@gmail.com
 * @Date：2014年4月28日 下午3:25:27
 * @Version v1.0
 */
public class MyApplication extends Application {

	public String mFamilyId = null;
	public boolean firstStart = true;
	public boolean isBoot = false;
	public boolean isLogin = false;
	public UserInfo userInfo = null;
	// 构造供货和求购发布数据
	public List<SupplyInfoDetail> supplyInfoList = new ArrayList<SupplyInfoDetail>();
	public List<BuyInfoDetail> buyInfoList = new ArrayList<BuyInfoDetail>();

	@Override
	public void onCreate() {
		super.onCreate();
		initData();

	}

	private void initData() {

	}

	@Override
	// 建议在您app的退出之前调用mapadpi的destroy()函数，避免重复初始化带来的时间消耗
	public void onTerminate() {
		super.onTerminate();
	}

}
