package com.haohuotong.web;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.kobjects.base64.Base64;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.text.TextUtils;
import android.util.Log;

import com.ab.global.AbAppException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.haohuotong.entity.DriverCarInfo;
import com.haohuotong.entity.DriverUserInfo;
import com.haohuotong.entity.LogisticsCompanyInfo;
import com.haohuotong.entity.LogisticsUserInfo;
import com.haohuotong.entity.Parameter;
import com.haohuotong.entity.UserInfo;
import com.haohuotong.global.Constant;
import com.haohuotong.util.MethodUtil;

/**
 * 
 * Copyright (c) 2012 All rights reserved
 * 
 * @Name：UserInfoWeb.java
 * @Describe：TODO
 * @Author yufarong_yfr5734@163.com
 * @Date：2013-12-19 下午4:25:21
 * @Version v1.0
 */
public class UserInfoWeb {

	/**
	 * 
	 * @Describe：用户注册
	 * @param parameterList
	 * @return
	 * @throws Exception
	 * @Throws
	 * @Date：2013-12-23 下午5:07:27
	 * @Version v1.0
	 */
	public static boolean userRegister(List<Parameter> parameterList) throws AbAppException {
		boolean isRegister = false;
		String resultString = null;
		String interfaceName = "InvokeForMobile";
		try {
			String xml = getParameterString("I_A001", parameterList);
			// 根据内容来源地址创建一个Http请求
			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			// 添加必须的参数
			params.add(new BasicNameValuePair("Xml", xml));
			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
			params.add(new BasicNameValuePair("Token", "bbb"));
			params.add(new BasicNameValuePair("ArrayTables", "driver|car"));
			// 设置参数的编码
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 发送请求并获取反馈
			HttpResponse httpResponse = new DefaultHttpClient().execute(request);

			// 解析返回的内容
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(httpResponse.getEntity());

				// 解析Xml文件返回Json结果集
				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
				String resultJson = MethodUtil.parse(is);
				Log.d("UserInfoWeb", "UserInfoWeb_UserRegister is ------------>>" + resultJson);

				// 解析结果
				JSONObject jsonObject = new JSONObject(resultJson);
				boolean resultTag = jsonObject.getBoolean("resultTag");
				if (resultTag) {
					isRegister = resultTag;
				} else {
					String exMsg = jsonObject.getString("msg");
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
	 * @param portName
	 * @param parameterList
	 * @return
	 * @throws AbAppException
	 * @Throws
	 * @Date：2013-12-23 下午5:07:56
	 * @Version v1.0
	 */
	public static UserInfo userLogin(String portName, List<Parameter> parameterList) throws AbAppException {
		int userType = Integer.valueOf(parameterList.get(2).getParameterValue());
		String resultString = null;
		UserInfo userInfo = new UserInfo();
		String interfaceName = "InvokeForMobile";
		DriverUserInfo driverUserInfo = null;
		DriverCarInfo driverCarInfo = null;
		LogisticsUserInfo logisticsUserInfo = null;
		LogisticsCompanyInfo logisticsCompanyInfo = null;
		try {
			String xml = getParameterString(portName, parameterList);
			// 根据内容来源地址创建一个Http请求
			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			// 添加必须的参数
			params.add(new BasicNameValuePair("Xml", xml));
			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
			params.add(new BasicNameValuePair("Token", "bbb"));
			if (userType == 1) {
				params.add(new BasicNameValuePair("ArrayTables", "driverInfo|driverCarInfo"));
			} else {
				params.add(new BasicNameValuePair("ArrayTables", "logisticsInfo|logisticsCompanyInfo"));
			}
			// 设置参数的编码
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 发送请求并获取反馈
			HttpResponse httpResponse = new DefaultHttpClient().execute(request);

			// 解析返回的内容
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(httpResponse.getEntity());

				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
				String resultJson = MethodUtil.parse(is);
				Log.d("UserInfoWeb", "UserInfoWeb_UserLogin is ------------>>" + resultJson);
				// 解析结果
				if (!TextUtils.isEmpty(resultJson)) {
					JSONObject jsonObject = new JSONObject(resultJson);
					Boolean resultTag = jsonObject.getBoolean("resultTag");
					if (resultTag) {
						// isRegister =resultTag;
						userInfo.setOk(resultTag);
						// 获取司机个人信息
						if (userType == 1) { // 司机返回数据
							userInfo.setUserType(userType);
							JSONObject driverInfoObject = jsonObject.getJSONObject("driverInfo");
							JSONObject driverCarInfoObject = jsonObject.getJSONObject("driverCarInfo");
							int userDataSize = driverInfoObject.getInt("rowcount");
							int carDataSize = driverCarInfoObject.getInt("rowcount");

							if (userDataSize > 0) {
								JSONArray array = driverInfoObject.getJSONArray("data");
								for (int i = 0; i < array.length(); i++) {
									JSONObject objectItem = array.getJSONObject(i);
									GsonBuilder builder = new GsonBuilder();
									Gson gson = builder.create();
									driverUserInfo = gson.fromJson(objectItem.toString(), DriverUserInfo.class);
									userInfo.setDriverUserInfo(driverUserInfo);
								}
							}

							// 获取司机个人信息
							if (carDataSize > 0) {
								JSONArray array = driverCarInfoObject.getJSONArray("data");
								for (int i = 0; i < array.length(); i++) {
									JSONObject objectItem = array.getJSONObject(i);
									GsonBuilder builder = new GsonBuilder();
									Gson gson = builder.create();
									driverCarInfo = gson.fromJson(objectItem.toString(), DriverCarInfo.class);
									userInfo.setDriverCarInfo(driverCarInfo);
								}
							}
						} else { // 物流公司返回数据
							userInfo.setUserType(userType);
							JSONObject logisticsInfoObject = jsonObject.getJSONObject("logisticsInfo");
							JSONObject logisticsCompanyInfoObject = jsonObject.getJSONObject("logisticsCompanyInfo");
							int logisticsUserDataSize = logisticsInfoObject.getInt("rowcount");
							int logisticsCompanyDataSize = logisticsCompanyInfoObject.getInt("rowcount");
							// 获取物流公司个人信息
							if (logisticsUserDataSize > 0) {
								JSONArray array = logisticsInfoObject.getJSONArray("data");
								for (int i = 0; i < array.length(); i++) {
									JSONObject objectItem = array.getJSONObject(i);
									GsonBuilder builder = new GsonBuilder();
									Gson gson = builder.create();
									logisticsUserInfo = gson.fromJson(objectItem.toString(), LogisticsUserInfo.class);
									userInfo.setLogisticsUserInfo(logisticsUserInfo);
								}
							}

							// 获取物流公司信息
							if (logisticsCompanyDataSize > 0) {
								JSONArray array = logisticsCompanyInfoObject.getJSONArray("data");
								for (int i = 0; i < array.length(); i++) {
									JSONObject objectItem = array.getJSONObject(i);
									GsonBuilder builder = new GsonBuilder();
									Gson gson = builder.create();
									logisticsCompanyInfo = gson.fromJson(objectItem.toString(), LogisticsCompanyInfo.class);
									userInfo.setLogisticsCompanyInfo(logisticsCompanyInfo);
								}
							}
						}
					} else {
						String exMsg = jsonObject.getString("msg");
						AbAppException mAppException = new AbAppException(exMsg);
						throw mAppException;
					}
				}
			}
		} catch (Exception e) {
			AbAppException mAppException = new AbAppException(e);
			throw mAppException;
		}
		return userInfo;
	}
	
	
	/**
	 * 
	 * @Describe：物流公司用户信息
	 * @param portName
	 * @param parameterList
	 * @return
	 * @throws AbAppException
	 * @Throws
	 * @Date：2013-12-23 下午5:07:56
	 * @Version v1.0
	 */
	public static LogisticsUserInfo getLogisticsUserInfo(String portName, List<Parameter> parameterList) throws AbAppException {
		String resultString = null;
		String interfaceName = "InvokeForMobile";
		LogisticsUserInfo logisticsUserInfo = null;
		try {
			String xml = getParameterString(portName, parameterList);
			// 根据内容来源地址创建一个Http请求
			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			// 添加必须的参数
			params.add(new BasicNameValuePair("Xml", xml));
			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
			params.add(new BasicNameValuePair("Token", "bbb"));
			params.add(new BasicNameValuePair("ArrayTables", "logisticsInfo|logisticsCompanyInfo"));
			
			// 设置参数的编码
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 发送请求并获取反馈
			HttpResponse httpResponse = new DefaultHttpClient().execute(request);

			// 解析返回的内容
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(httpResponse.getEntity());

				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
				String resultJson = MethodUtil.parse(is);
				Log.d("UserInfoWeb", "UserInfoWeb_UserLogin is ------------>>" + resultJson);
				// 解析结果
				if (!TextUtils.isEmpty(resultJson)) {
					JSONObject jsonObject = new JSONObject(resultJson);
					Boolean resultTag = jsonObject.getBoolean("resultTag");
					if (resultTag) {
						 // 物流公司返回数据
							JSONObject logisticsInfoObject = jsonObject.getJSONObject("logisticsInfo");
							int logisticsUserDataSize = logisticsInfoObject.getInt("rowcount");
							// 获取物流公司个人信息
							if (logisticsUserDataSize > 0) {
								JSONArray array = logisticsInfoObject.getJSONArray("data");
								for (int i = 0; i < array.length(); i++) {
									JSONObject objectItem = array.getJSONObject(i);
									GsonBuilder builder = new GsonBuilder();
									Gson gson = builder.create();
									logisticsUserInfo = gson.fromJson(objectItem.toString(), LogisticsUserInfo.class);
								}
							}
						}
					}
			}
		} catch (Exception e) {
			AbAppException mAppException = new AbAppException(e);
			throw mAppException;
		}
		return logisticsUserInfo;
	}
	
	
	/**
	 * 
	 * @Describe：获取物流公司信息
	 * @param portName
	 * @param parameterList
	 * @return
	 * @throws AbAppException
	 * @Throws
	 * @Date：2013-12-23 下午5:07:56
	 * @Version v1.0
	 */
	public static LogisticsCompanyInfo getLogisticsCompanyInfo(String portName, List<Parameter> parameterList) throws AbAppException {
		String resultString = null;
		String interfaceName = "InvokeForMobile";
		LogisticsCompanyInfo logisticsCompanyInfo = null;
		try {
			String xml = getParameterString(portName, parameterList);
			// 根据内容来源地址创建一个Http请求
			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			// 添加必须的参数
			params.add(new BasicNameValuePair("Xml", xml));
			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
			params.add(new BasicNameValuePair("Token", "bbb"));
			params.add(new BasicNameValuePair("ArrayTables", "logisticsInfo|logisticsCompanyInfo"));
			
			// 设置参数的编码
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 发送请求并获取反馈
			HttpResponse httpResponse = new DefaultHttpClient().execute(request);

			// 解析返回的内容
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(httpResponse.getEntity());

				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
				String resultJson = MethodUtil.parse(is);
				Log.d("UserInfoWeb", "UserInfoWeb_UserLogin is ------------>>" + resultJson);
				// 解析结果
				if (!TextUtils.isEmpty(resultJson)) {
					JSONObject jsonObject = new JSONObject(resultJson);
					Boolean resultTag = jsonObject.getBoolean("resultTag");
					if (resultTag) {
						 // 物流公司返回数据
							JSONObject logisticsCompanyInfoObject = jsonObject.getJSONObject("logisticsCompanyInfo");
							int logisticsCompanyDataSize = logisticsCompanyInfoObject.getInt("rowcount");
							
							// 获取物流公司信息
							if (logisticsCompanyDataSize > 0) {
								JSONArray array = logisticsCompanyInfoObject.getJSONArray("data");
								for (int i = 0; i < array.length(); i++) {
									JSONObject objectItem = array.getJSONObject(i);
									GsonBuilder builder = new GsonBuilder();
									Gson gson = builder.create();
									logisticsCompanyInfo = gson.fromJson(objectItem.toString(), LogisticsCompanyInfo.class);
								}
							}
						}
					}
			}
		} catch (Exception e) {
			AbAppException mAppException = new AbAppException(e);
			throw mAppException;
		}
		return logisticsCompanyInfo;
	}
	

	/**
	 * 
	 * @Describe：更新司机个人信息
	 * @param portName
	 * @param parameterList
	 * @return
	 * @throws Exception
	 * @Throws
	 * @Date：2013-12-23 下午5:06:28
	 * @Version v1.0
	 */
	public static boolean updateDriverUserInfo(String portName, List<Parameter> parameterList) throws AbAppException {
		boolean isUpdate = false;
		String resultString = null;
		String interfaceName = "InvokeForMobile";
		try {
			String xml = getParameterString(portName, parameterList);
			// 根据内容来源地址创建一个Http请求
			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			// 添加必须的参数
			params.add(new BasicNameValuePair("Xml", xml));
			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
			params.add(new BasicNameValuePair("Token", "bbb"));
			params.add(new BasicNameValuePair("ArrayTables", "driver|car"));
			// 设置参数的编码
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 发送请求并获取反馈
			HttpResponse httpResponse = new DefaultHttpClient().execute(request);

			// 解析返回的内容
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(httpResponse.getEntity());

				// 解析Xml文件返回Json结果集
				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
				String resultJson = MethodUtil.parse(is);
				Log.d("UserInfoWeb", "UserInfoWeb_UpdateDriverUserInfo is ------------>>" + resultJson);

				// 解析结果
				JSONObject jsonObject = new JSONObject(resultJson);
				boolean resultTag = jsonObject.getBoolean("resultTag");
				if (resultTag) {
					isUpdate = resultTag;
				} else {
					String exMsg = jsonObject.getString("msg");
					AbAppException mAppException = new AbAppException(exMsg);
					throw mAppException;
				}
			}
		} catch (Exception e) {
			AbAppException mAppException = new AbAppException(e);
			throw mAppException;
		}
		return isUpdate;
	}

	public static boolean updateDriverCarInfo(String portName, List<Parameter> parameterList) throws AbAppException {
		boolean isUpdate = false;
		String resultString = null;
		String interfaceName = "InvokeForMobile";
		try {
			String xml = getParameterString(portName, parameterList);
			// 根据内容来源地址创建一个Http请求
			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			// 添加必须的参数
			params.add(new BasicNameValuePair("Xml", xml));
			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
			params.add(new BasicNameValuePair("Token", "bbb"));
			params.add(new BasicNameValuePair("ArrayTables", "driver|car"));
			// 设置参数的编码
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 发送请求并获取反馈
			HttpResponse httpResponse = new DefaultHttpClient().execute(request);

			// 解析返回的内容
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(httpResponse.getEntity());

				// 解析Xml文件返回Json结果集
				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
				String resultJson = MethodUtil.parse(is);
				Log.d("UserInfoWeb", "UserInfoWeb_UserRegister is ------------>>" + resultJson);

				// 解析结果
				JSONObject jsonObject = new JSONObject(resultJson);
				boolean resultTag = jsonObject.getBoolean("resultTag");
				if (resultTag) {
					isUpdate = resultTag;
				} else {
					String exMsg = jsonObject.getString("msg");
					AbAppException mAppException = new AbAppException(exMsg);
					throw mAppException;
				}
			}
		} catch (Exception e) {
			AbAppException mAppException = new AbAppException(e);
			throw mAppException;
		}
		return isUpdate;
	}

	/**
	 * 
	 * @Describe：更新物流公司账号信息
	 * @param portName
	 * @param parameterList
	 * @return
	 * @throws Exception
	 * @Throws
	 * @Date：2013-12-24 下午4:50:16
	 * @Version v1.0
	 */
	public static boolean updateLogisticsCcountInfo(String portName, List<Parameter> parameterList) throws AbAppException {
		boolean isUpdate = false;
		String resultString = null;
		String interfaceName = "InvokeForMobile";
		try {
			String xml = getParameterString(portName, parameterList);
			// 根据内容来源地址创建一个Http请求
			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			// 添加必须的参数
			params.add(new BasicNameValuePair("Xml", xml));
			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
			params.add(new BasicNameValuePair("Token", "bbb"));
			params.add(new BasicNameValuePair("ArrayTables", "driver|car"));
			// 设置参数的编码
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 发送请求并获取反馈
			HttpResponse httpResponse = new DefaultHttpClient().execute(request);

			// 解析返回的内容
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(httpResponse.getEntity());

				// 解析Xml文件返回Json结果集
				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
				String resultJson = MethodUtil.parse(is);
				Log.d("UserInfoWeb", "UserInfoWeb_UserRegister is ------------>>" + resultJson);

				// 解析结果
				JSONObject jsonObject = new JSONObject(resultJson);
				boolean resultTag = jsonObject.getBoolean("resultTag");
				if (resultTag) {
					isUpdate = resultTag;
				} else {
					String exMsg = jsonObject.getString("msg");
					AbAppException mAppException = new AbAppException(exMsg);
					throw mAppException;
				}
			}
		} catch (Exception e) {
			AbAppException mAppException = new AbAppException(e);
			throw mAppException;
		}
		return isUpdate;
	}

	/**
	 * 
	 * @Describe：更新物流公司信息
	 * @param portName
	 * @param parameterList
	 * @return
	 * @throws Exception
	 * @Throws
	 * @Date：2013-12-24 下午4:50:45
	 * @Version v1.0
	 */
	public static boolean updateLogisticsCompanyInfo(String portName, List<Parameter> parameterList) throws AbAppException {
		boolean isUpdate = false;
		String resultString = null;
		String interfaceName = "InvokeForMobile";
		try {
			String xml = getParameterString(portName, parameterList);
			// 根据内容来源地址创建一个Http请求
			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			// 添加必须的参数
			params.add(new BasicNameValuePair("Xml", xml));
			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
			params.add(new BasicNameValuePair("Token", "bbb"));
			params.add(new BasicNameValuePair("ArrayTables", "driver|car"));
			// 设置参数的编码
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 发送请求并获取反馈
			HttpResponse httpResponse = new DefaultHttpClient().execute(request);

			// 解析返回的内容
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(httpResponse.getEntity());

				// 解析Xml文件返回Json结果集
				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
				String resultJson = MethodUtil.parse(is);
				Log.d("UserInfoWeb", "UserInfoWeb_UserRegister is ------------>>" + resultJson);

				// 解析结果
				JSONObject jsonObject = new JSONObject(resultJson);
				boolean resultTag = jsonObject.getBoolean("resultTag");
				if (resultTag) {
					isUpdate = resultTag;
				} else {
					String exMsg = jsonObject.getString("msg");
					AbAppException mAppException = new AbAppException(exMsg);
					throw mAppException;
				}
			}
		} catch (Exception e) {
			AbAppException mAppException = new AbAppException(e);
			throw mAppException;
		}
		return isUpdate;
	}

	
	public static String getImageInfo(byte[] head, String fileName) throws AbAppException {
		
//		DoctorInfo mDoctorInfo = null;
		String result = null;
		String methodName = "upfileimg";
		try {
			// 创建soap对象
			SoapObject soapObject = new SoapObject("http://tempuri.org/", methodName);
			// 添加参数
			String service = Base64.encode(head);
			soapObject.addProperty("arg2", service);
			soapObject.addProperty("arg3", fileName);

			// 设置连接参数
			HttpTransportSE hse = new HttpTransportSE("http://210.209.71.65/WebService/BPMS_Interface.asmx?wsdl", 5000);
			hse.debug = true;
			SoapSerializationEnvelope sse = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			sse.bodyOut = soapObject;
			sse.dotNet = false;
			hse.call(null, sse);
			result = sse.getResponse().toString();
			Log.d("Connector", "Connector_08 is ------------>>"+result);

			// 解析结果
//			JSONObject jsonObject = new JSONObject(result);
//			Boolean resultTag = jsonObject.getBoolean("resultTag");
//			if (resultTag) {
//				Gson gson = new GsonBuilder().create();
//				// 使用Gson 实现对Json字符的解析
//				String updateDoctorBaseInfo = jsonObject.getString("updateDoctorBaseInfo").trim();
//				mDoctorInfo = gson.fromJson(updateDoctorBaseInfo, DoctorInfo.class);
//			} else {
//				String exMsg = jsonObject.getString("msg");
//				AbAppException mAppException = new AbAppException(exMsg);
//				throw mAppException;
//			}
		} catch (Exception e) {
			AbAppException mAppException = new AbAppException(e);
			throw mAppException;
		}
		return result;
	}
	
	
	/**
	 * 
	 * @Describe：参数转换
	 * @param portName
	 * @param parameterList
	 * @return
	 * @Throws
	 * @Date：2013-12-23 下午5:08:55
	 * @Version v1.0
	 */
	public static String getParameterString(String portName, List<Parameter> parameterList) {
		int size = parameterList.size();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			String string2 = "<" + parameterList.get(i).getParameterKey() + ">" + parameterList.get(i).getParameterValue() + "</"
					+ parameterList.get(i).getParameterKey() + ">";
			sb.append(string2);
		}
		return "<Request><data code='" + portName + "'><no>" + sb + "</no></data></Request>";
	}

}
