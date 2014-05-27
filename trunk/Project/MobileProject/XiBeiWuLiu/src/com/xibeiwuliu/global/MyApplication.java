package com.xibeiwuliu.global;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.ab.global.AbConstant;

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
	@Override
	public void onCreate() {
		super.onCreate();
		// initLoginParams();
		initData();  

	}

//    public static void SetMyContext() {
//        logisticsDB = new LogisticsDB(context);
//        logisticsDB.open().close(); // to initialize
//      }  
	
	/**
	 * 上次登录参数
	 */
	// private void initLoginParams() {
	// SharedPreferences sp = getSharedPreferences(AbConstant.SHAREPATH,
	// Context.MODE_PRIVATE);
	// String userId = sp.getString(Constant.USERID, null);
	// String userName = sp.getString(Constant.USERNAMECOOKIE, null);
	// String userPwd = sp.getString(Constant.USERPASSWORDCOOKIE, null);
	// firstStart = sp.getBoolean(Constant.FIRSTSTART, true);
	// Boolean userPwdRemember =
	// sp.getBoolean(Constant.USERPASSWORDREMEMBERCOOKIE, false);
	// userPasswordRemember = userPwdRemember;
	// if(userId!=null){
	// UserInfo mUserDb = UserDao.getInstance(this).getUserById(userId);
	// if(mUserDb!=null){
	// if(AbStrUtil.isEmpty(mUserDb.getPassWord())){
	// mUserDb.setPassWord(AbMd5.MD5(userPwd));
	// }
	// mUser = mUserDb;
	// mTempUser = mUser;
	// String mFamilyId = null;
	// String mFamilyName = null;
	// String createByUserId = null;
	// List<Family> mFamilyList = mUser.getFamily();
	// if(mFamilyList!=null && mFamilyList.size()>0){
	// Family mFamily = mFamilyList.get(0);
	// if(mFamily!=null){
	// mFamilyId = String.valueOf(mFamily.getId());
	// mFamilyName = mFamily.getName();
	// createByUserId = mFamily.getCreateByUserId();
	// }
	// }
	// this.mFamilyId = mFamilyId;
	// this.mFamilyName = mFamilyName;
	// this.createByUserId = createByUserId;
	// }
	// }
	// }

	/**
	 * 清空上次登录参数
	 */
	public void clearLoginParams() {
		SharedPreferences sp = getSharedPreferences(AbConstant.SHAREPATH, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.clear();
		editor.commit();
	}

	private void initData() {

	}

	@Override
	// 建议在您app的退出之前调用mapadpi的destroy()函数，避免重复初始化带来的时间消耗
	public void onTerminate() {
		super.onTerminate();
	}

	// public void clearApp(){
	// mTempFamilyId = "";
	// mUser = null;
	// mFamilyId = "";
	// mFamilyName = "";
	// mTempUser = null;
	// mDoctors.clear();
	// mUserList.clear();
	// }

}
