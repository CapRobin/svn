package com.xibeiwuliu.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.ab.global.AbAppException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xibeiwuliu.entity.CargoInfo;
import com.xibeiwuliu.entity.Parameter;
import com.xibeiwuliu.global.Constant;
import com.xibeiwuliu.util.MethodUtil;

/**
 * 
 * Copyright (c) 2013 All rights reserved
 * @Name：UserInfoWeb.java 
 * @Describe：用户个人信息
 * @Author:  yfr5734@gmail.com
 * @Date：2014年5月26日 上午10:02:42
 * @Version v1.0
 */
public class UserInfoWeb {
//	public static List<CargoInfo> cargoInfoList(String portName, List<Parameter> parameterList) throws AbAppException {
//	public static String userRegister() throws AbAppException {
//		String responseStr = null;
//		try {
//			String path = "http://xbmt.net188.net/estar/aclient/register.asp?action=Register&names=%E6%9D%8E%E5%85%88%E7%94%9F&telphone=010-88888888&username=11111&password=111111&usertypes=1&email=&mibaoQ=lksjfasd&mibaoA=dsaflkds&imei=02390290393232032";
//			HttpGet request = new HttpGet(path);
//			request.setHeader("Accept", "application/json");
//			request.setHeader("Content-type", "application/json;charset=UTF-8");
//			HttpResponse response = new DefaultHttpClient().execute(request);
//			int ret = response.getStatusLine().getStatusCode();
//			HttpEntity entity_res = response.getEntity();
//			if (entity_res != null) {
//				InputStream instream = entity_res.getContent();
////				responseStr = convertStreamToString(instream);
//				responseStr = MethodUtil.decodeUnicode(convertStreamToString(instream));
////				if (D) Log.d(TAG, "登录:" + responseStr);
////				if (ret == 200) {
////					GsonBuilder gsonb = new GsonBuilder();
////					Gson gson = gsonb.create();
////					user = gson.fromJson(responseStr, ResultUser.class);
////				}else{
////					GsonBuilder gsonb = new GsonBuilder();
////					Gson gson = gsonb.create();
////					Fault f = gson.fromJson(responseStr, Fault.class);
////					throw new AppException(f.message);
////				}
//				
//			}
//			
//		} catch (Exception e) {
//			 throw new Exception(e);
//		}
//	   return responseStr;
//}
	
// 	public static String convertStreamToString(InputStream is) {
//		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//		StringBuilder sb = new StringBuilder();
//		String line = null;
//		try {
//			while ((line = reader.readLine()) != null) {
//				sb.append(line + "\n");
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				is.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return sb.toString();
//	}
 	
/**
 * 
 * @Describe：用户注册
 * @return
 * @throws AbAppException
 * @Throws:  
 * @Date：2014年5月26日 下午4:24:51
 * @Version v1.0
 */
	
	/**
	 */
 	public static String userRegister(String realName,	String mobile,	 String password,  int userType, String imei, String other) throws AbAppException {
		String responseStr = null;
		String sRealName = realName;
		String sMobile = mobile;
		String sPassword = password;
		int sUserType = userType;
		String sOther = other;
		String sImei = imei;
		String path = Constant.HOSTURL+"estar/aclient/register.asp?action=Register&names="+sRealName+"&telphone="+sMobile+"&password="+sPassword+"&usertypes="+sUserType+"&email="+sOther+"&mibaoQ="+sOther+"&mibaoA="+sOther+"&imei="+sImei;
		try {
			HttpGet request = new HttpGet(path);
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json;charset=UTF-8");
			HttpResponse response = new DefaultHttpClient().execute(request);
			int ret = response.getStatusLine().getStatusCode();
			HttpEntity entity_res = response.getEntity();
			if (entity_res != null) {
				InputStream instream = entity_res.getContent();
//				responseStr = convertStreamToString(instream);
				responseStr = MethodUtil.decodeUnicode(MethodUtil.convertStreamToString(instream));
//				Log.d("UserInfoWeb", "UserInfoWeb_UserRegister is ------------>>" + resultJson);

				// 解析结果
//				JSONObject jsonObject = new JSONObject(resultJson);
//				boolean resultTag = jsonObject.getBoolean("resultTag");
//				if (resultTag) {
//
//					JSONObject cargoList = jsonObject.getJSONObject("cargoList");
//					JSONArray jsonArray = cargoList.getJSONArray("data");
//					for (int i = 0; i < jsonArray.length(); i++) {
//						JSONObject objectItem = jsonArray.getJSONObject(i);
//						GsonBuilder gBuilder = new GsonBuilder();
//						Gson gson = gBuilder.create();
//						cargoInfo = gson.fromJson(String.valueOf(objectItem), CargoInfo.class);
//						cargoInfoList.add(cargoInfo);
//					}
//					
//					
//					isUpdate = resultTag;
//				} else {
//					String exMsg = jsonObject.getString("msg");
//					AbAppException mAppException = new AbAppException(exMsg);
//					throw mAppException;
//				}
			}
		} catch (Exception e) {
			AbAppException mAppException = new AbAppException(e);
			throw mAppException;
		}
		return responseStr;
	}
 	
}
