package com.xibeiwuliu.web;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.util.Log;

import com.ab.global.AbAppException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xibeiwuliu.entity.DriverUserInfo;
import com.xibeiwuliu.entity.UserInfo;
import com.xibeiwuliu.global.Constant;
import com.xibeiwuliu.util.MethodUtil;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * 
 * @Name：UserInfoWeb.java
 * @Describe：用户个人信息网络接口
 * @Author: yfr5734@gmail.com
 * @Date：2014年5月27日 上午9:53:58
 * @Version v1.0
 */
public class UserInfoWeb {

	/**
	 * 
	 * @Describe： 用户注册
	 * @param realName
	 *            真实姓名
	 * @param mobile
	 *            账号(电话)
	 * @param password
	 *            密码
	 * @param userType
	 *            用户类型(0:司机；1:物流公司)
	 * @param imei
	 *            手机身份编码
	 * @param other
	 * @return
	 * @throws AbAppException
	 * @Throws:
	 * @Date：2014年5月27日 上午9:50:48
	 * @Version v1.0
	 */
	public static boolean userRegister(String realName, String mobile, String password, int userType, String imei, String other) throws AbAppException {
		boolean isRegister = false;
		String sRealName = realName;
		String sMobile = mobile;
		String sPassword = password;
		int sUserType = userType;
		String sOther = other;
		String sImei = imei;
		String path = Constant.HOSTURL + "estar/aclient/register.asp?action=Register&names=" + sRealName + "&telphone=" + sMobile + "&username=" + sMobile + "&password=" + sPassword + "&usertypes="
				+ sUserType + "&email=" + sOther + "&mibaoQ=" + sOther + "&mibaoA=" + sOther + "&imei=" + sImei;
		try {
			HttpGet request = new HttpGet(path);
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json;charset=UTF-8");
			HttpResponse response = new DefaultHttpClient().execute(request);
			int ret = response.getStatusLine().getStatusCode();
			HttpEntity entity_res = response.getEntity();
			if (entity_res != null) {
				InputStream instream = entity_res.getContent();
				String responseStr = MethodUtil.decodeUnicode(MethodUtil.convertStreamToString(instream));
				Log.d("UserInfoWeb", "userRegister get result is ----------------->>" + responseStr);

				// 解析结果
				JSONObject jsonObject = new JSONObject(responseStr);
				String resultState = jsonObject.getString("state");
				if ("RegSuccess".equals(resultState)) {
					isRegister = true;
				} else {
					String exMsg = jsonObject.getString("answer");
					AbAppException mAppException = new AbAppException(exMsg);
					throw mAppException;
				}
			}
		} catch (Exception e) {
			AbAppException mAppException = new AbAppException(e);
			throw mAppException;
		}
		return isRegister;
	}

	/**
	 * 
	 * @Describe：用户登录
	 * @param userName
	 *            账号(电话)
	 * @param password
	 *            密码
	 * @param userType
	 *            用户类型
	 * @param imei
	 *            手机身份编码
	 * @return
	 * @throws AbAppException
	 * @Throws:
	 * @Date：2014年5月27日 上午10:46:46
	 * @Version v1.0
	 */
	public static UserInfo userLogin(String userName, String password, int userType, String imei) throws AbAppException {
		String sMobile = userName;
		String sPassword = password;
		int sUserType = userType;
		String sImei = imei;
		UserInfo getUserInfo = new UserInfo();
		String path = Constant.HOSTURL + "estar/aclient/clientlogin.asp?action=login&loginSign=0&username="+sMobile+"&password="+sPassword+"&usertypes="+sUserType+"&imei="+sImei;
		
		try {
			HttpGet request = new HttpGet(path);
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json;charset=UTF-8");
			HttpResponse response = new DefaultHttpClient().execute(request);
			int ret = response.getStatusLine().getStatusCode();
			HttpEntity entity_res = response.getEntity();
			if (entity_res != null) {
				InputStream instream = entity_res.getContent();
				String responseStr = MethodUtil.decodeUnicode(MethodUtil.convertStreamToString(instream));
				Log.d("UserInfoWeb", "userLogin get result is ----------------->>" + responseStr);

				// 解析结果
				JSONObject jsonObject = new JSONObject(responseStr);
				String resultState = jsonObject.getString("state");
				if ("ok".equals(resultState)) {
					
//					if (userDataSize > 0) {
//						JSONArray array = driverInfoObject.getJSONArray("data");
//						for (int i = 0; i < array.length(); i++) {
//							JSONObject objectItem = array.getJSONObject(i);
//							GsonBuilder builder = new GsonBuilder();
//							Gson gson = builder.create();
//							driverUserInfo = gson.fromJson(objectItem.toString(), DriverUserInfo.class);
//							userInfo.setDriverUserInfo(driverUserInfo);
//						}
//					}
//					JSONObject objectItem = array.getJSONObject(i);
					JSONObject jsonObjecta = jsonObject.getJSONObject("userinfos");
					GsonBuilder builder = new GsonBuilder();
					Gson gson = builder.create();
					getUserInfo = gson.fromJson(jsonObjecta.toString(), UserInfo.class);
//					isLogin = true;
				} else {
					String exMsg = jsonObject.getString("answer");
					AbAppException mAppException = new AbAppException(exMsg);
					throw mAppException;
				}
			}
		} catch (Exception e) {
			AbAppException mAppException = new AbAppException(e);
			throw mAppException;
		}
		return getUserInfo;
	}

}
