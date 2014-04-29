package com.xibeiwuliu.web;

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

import android.util.Log;

import com.ab.global.AbAppException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xibeiwuliu.entity.CargoInfo;
import com.xibeiwuliu.entity.Parameter;
import com.xibeiwuliu.entity.VehicleInfo;
import com.xibeiwuliu.global.Constant;
import com.xibeiwuliu.util.MethodUtil;

/**
 * ������Ϣ�ӿڵ���
 * Copyright (c) 2012 All rights reserved
 * ���ƣ�PublicInfoWeb.java 
 * ������TODO
 * @author Yu Farong - yfr5734@gmail.com
 * @date��2013��12��27�� ����3:05:06
 * @version v1.0
 */
public class PublicInfoWeb {

    private final String mUrlBase = "http://nmgclient2.net188.net/";
//    private HttpRequestEx mHttp;
//    
//    public PublicInfoWeb() {
//    	mHttp = new HttpRequestEx();
//	}
//	public EntitySet getPagedInfoListSync2(String city, String keywords, InfoEntity.Type type, int page) {
//		String url = mUrlBase + "estar/mclient2/GetMInfo.asp";
////		http://nmgclient2.net188.net//estar/mclient2/GetmInfo.asp?action=GetDatas&Code=����&KeyWords=&inType=8&pag=1
//		Map<Object, Object> params = new HashMap<Object, Object>();
//		params.put("action", "GetDatas");
//		params.put("Code", city);
//		params.put("Keywords", keywords);
//		params.put("inType", InfoEntity.Type.toInteger(type));
//		params.put("pag", page);
//		HttpResultEx res = mHttp.getHttpData(url, params);
//		// http://nmgclient2.net188.net/estar/mclient/GetMInfo.asp
//		// {action=GetDatas, inType=8, pag=1, Code=����, Keywords=}
//		int pageNum = 0;
//		int totalNum = 0;
//		EntitySet es = new EntitySet();
//		if (res.getStatusCode() == 200) {
//			int i = 1;
//			String[] strings = res.getData().split("@@");
//			try {
//				String[] nums = strings[i++].split("\\|");
//				pageNum = Integer.parseInt(nums[0]);
//				es.setTotalPage(pageNum);
//				totalNum = Integer.parseInt(nums[1]);
//				es.setTotalNum(totalNum);
//			} catch (NumberFormatException ex) {
//				ex.printStackTrace();
//				i = 1; // so, it has no page or total.
//			}
//
//			if (page <= pageNum) {
//				for (; i < strings.length; i++) {
//					es.add(new InfoEntity(strings[i]));
//				}
//			}
//		}
//
//		return es;
//	}
	
	/**
	 * @Describe����Դ��Ϣ�б�
	 * @param portName
	 * @param parameterList
	 * @return
	 * @throws AbAppException
	 * @Throws 
	 * @Date��2013-12-27 ����10:10:09
	 * @Version v1.0
	 */
	public static List<CargoInfo> cargoInfoList(String portName, List<Parameter> parameterList) throws AbAppException {
		boolean isUpdate = false;
		String resultString = null;
		String interfaceName = "InvokeForMobile";
		List<CargoInfo> cargoInfoList = new ArrayList<CargoInfo>();
		CargoInfo cargoInfo = null;
		try {
			String xml = MethodUtil.getParameterString(portName, parameterList);
			// ����������Դ��ַ����һ��Http����
			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			// ��ӱ���Ĳ���
			params.add(new BasicNameValuePair("Xml", xml));
			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
			params.add(new BasicNameValuePair("Token", "bbb"));
			params.add(new BasicNameValuePair("ArrayTables", "cargoList"));
			// ���ò����ı���
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// �������󲢻�ȡ����
			HttpResponse httpResponse = new DefaultHttpClient().execute(request);

			// �������ص�����
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(httpResponse.getEntity());

				// ����Xml�ļ�����Json�����
				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
				String resultJson = MethodUtil.parse(is);
				Log.d("UserInfoWeb", "UserInfoWeb_UserRegister is ------------>>" + resultJson);

				// �������
				JSONObject jsonObject = new JSONObject(resultJson);
				boolean resultTag = jsonObject.getBoolean("resultTag");
				if (resultTag) {

					JSONObject cargoList = jsonObject.getJSONObject("cargoList");
					JSONArray jsonArray = cargoList.getJSONArray("data");
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject objectItem = jsonArray.getJSONObject(i);
						GsonBuilder gBuilder = new GsonBuilder();
						Gson gson = gBuilder.create();
						cargoInfo = gson.fromJson(String.valueOf(objectItem), CargoInfo.class);
						cargoInfoList.add(cargoInfo);
					}
					
					
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
		return cargoInfoList;
	}
	
	/**
	 * @Describe����Դ��Ϣ�б�
	 * @param portName
	 * @param parameterList
	 * @return
	 * @throws AbAppException
	 * @Throws 
	 * @Date��2013-12-27 ����10:10:09
	 * @Version v1.0
	 */
	public static List<VehicleInfo> vehicleInfoList(String portName, List<Parameter> parameterList) throws AbAppException {
		boolean isUpdate = false;
		String resultString = null;
		String interfaceName = "InvokeForMobile";
		List<VehicleInfo> vehicleInfoList = new ArrayList<VehicleInfo>();
		VehicleInfo vehicleInfo = null;
		try {
			String xml = MethodUtil.getParameterString(portName, parameterList);
			// ����������Դ��ַ����һ��Http����
			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			// ��ӱ���Ĳ���
			params.add(new BasicNameValuePair("Xml", xml));
			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
			params.add(new BasicNameValuePair("Token", "bbb"));
			params.add(new BasicNameValuePair("ArrayTables", "vehicleList"));
			// ���ò����ı���
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// �������󲢻�ȡ����
			HttpResponse httpResponse = new DefaultHttpClient().execute(request);

			// �������ص�����
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(httpResponse.getEntity());

				// ����Xml�ļ�����Json�����
				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
				String resultJson = MethodUtil.parse(is);
				Log.d("UserInfoWeb", "UserInfoWeb_UserRegister is ------------>>" + resultJson);

				// �������
				JSONObject jsonObject = new JSONObject(resultJson);
				boolean resultTag = jsonObject.getBoolean("resultTag");
				if (resultTag) {

					JSONObject cargoList = jsonObject.getJSONObject("vehicleList");
					JSONArray jsonArray = cargoList.getJSONArray("data");
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject objectItem = jsonArray.getJSONObject(i);
						GsonBuilder gBuilder = new GsonBuilder();
						Gson gson = gBuilder.create();
						vehicleInfo = gson.fromJson(String.valueOf(objectItem), VehicleInfo.class);
						vehicleInfoList.add(vehicleInfo);
					}
					
					
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
		return vehicleInfoList;
	}
	
    
//	public static boolean publicCargoInfo(String portName, List<Parameter> parameterList) throws AbAppException {
//		boolean isUpdate = false;
//		String resultString = null;
//		String interfaceName = "InvokeForMobile";
//		try {
//			String xml = MethodUtil.getParameterString(portName, parameterList);
//			// ����������Դ��ַ����һ��Http����
//			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
//			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
//			// ��ӱ���Ĳ���
//			params.add(new BasicNameValuePair("Xml", xml));
//			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
//			params.add(new BasicNameValuePair("Token", "bbb"));
//			params.add(new BasicNameValuePair("ArrayTables", "driver|car"));
//			// ���ò����ı���
//			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//			// �������󲢻�ȡ����
//			HttpResponse httpResponse = new DefaultHttpClient().execute(request);
//
//			// �������ص�����
//			if (httpResponse.getStatusLine().getStatusCode() == 200) {
//				resultString = EntityUtils.toString(httpResponse.getEntity());
//
//				// ����Xml�ļ�����Json�����
//				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
//				String resultJson = MethodUtil.parse(is);
//				Log.d("UserInfoWeb", "UserInfoWeb_UserRegister is ------------>>" + resultJson);
//
//				// �������
//				JSONObject jsonObject = new JSONObject(resultJson);
//				boolean resultTag = jsonObject.getBoolean("resultTag");
//				if (resultTag) {
//					isUpdate = resultTag;
//				} else {
//					String exMsg = jsonObject.getString("msg");
//					AbAppException mAppException = new AbAppException(exMsg);
//					throw mAppException;
//				}
//			}
//		} catch (Exception e) {
//			AbAppException mAppException = new AbAppException(e);
//			throw mAppException;
//		}
//		return isUpdate;
//	}
	
	
	/**
	 * @Describe����ȡ��������Ϣ�б�
	 * @param portName
	 * @param parameterList
	 * @return
	 * @throws AbAppException
	 * @Throws 
	 * @Date��2013-12-31 ����10:10:09
	 * @Version v1.0
	 */
//	public static List<AuctionInfo> driverInfoList(String portName, List<Parameter> parameterList) throws AbAppException {
//		boolean isUpdate = false;
//		String resultString = null;
//		String interfaceName = "InvokeForMobile";
//		List<AuctionInfo> auctionInfoList = new ArrayList<AuctionInfo>();
//		AuctionInfo auctionInfo = null;
//		try {
//			String xml = MethodUtil.getParameterString(portName, parameterList);
//			// ����������Դ��ַ����һ��Http����
//			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
//			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
//			// ��ӱ���Ĳ���
//			params.add(new BasicNameValuePair("Xml", xml));
//			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
//			params.add(new BasicNameValuePair("Token", "bbb"));
//			params.add(new BasicNameValuePair("ArrayTables", "cargoList"));
//			// ���ò����ı���
//			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//			// �������󲢻�ȡ����
//			HttpResponse httpResponse = new DefaultHttpClient().execute(request);
//
//			// �������ص�����
//			if (httpResponse.getStatusLine().getStatusCode() == 200) {
//				resultString = EntityUtils.toString(httpResponse.getEntity());
//
//				// ����Xml�ļ�����Json�����
//				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
//				String resultJson = MethodUtil.parse(is);
//				Log.i("PublicWeb", "AuctionInfoWeb is ------------>>" + resultJson);
//
//				// �������
//				JSONObject jsonObject = new JSONObject(resultJson);
//				boolean resultTag = jsonObject.getBoolean("resultTag");
//				if (resultTag) {
//
//					JSONObject cargoList = jsonObject.getJSONObject("cargoList");//?
//					JSONArray jsonArray = cargoList.getJSONArray("data");//?
//					for (int i = 0; i < jsonArray.length(); i++) {
//						JSONObject objectItem = jsonArray.getJSONObject(i);
//						GsonBuilder gBuilder = new GsonBuilder();
//						Gson gson = gBuilder.create();
//						auctionInfo = gson.fromJson(String.valueOf(objectItem), AuctionInfo.class);
//						auctionInfoList.add(auctionInfo);
//					}
//					isUpdate = resultTag;
//				} else {
//					String exMsg = jsonObject.getString("msg");
//					AbAppException mAppException = new AbAppException(exMsg);
//					throw mAppException;
//				}
//			}
//		} catch (Exception e) {
//			AbAppException mAppException = new AbAppException(e);
//			throw mAppException;
//		}
//		return auctionInfoList;
//	}
	
	
	/**
	 * @Describe�����;�����Ϣ
	 * @param portName
	 * @param parameterList
	 * @return
	 * @throws AbAppException
	 * @Throws 
	 * @Date��2013-12-31 ����10:10:09
	 * @Version v1.0
	 */
//	public static boolean sendAuction(String portName, List<Parameter> parameterList) throws AbAppException {
//		boolean isUpdate = false;
//		String resultString = null;
//		String interfaceName = "InvokeForMobile";
////		List<AuctionInfo> auctionInfoList = new ArrayList<AuctionInfo>();
////		AuctionInfo auctionInfo = null;
//		try {
//			String xml = MethodUtil.getParameterString(portName, parameterList);
//			// ����������Դ��ַ����һ��Http����
//			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
//			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
//			// ��ӱ���Ĳ���
//			params.add(new BasicNameValuePair("Xml", xml));
//			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
//			params.add(new BasicNameValuePair("Token", "bbb"));
//			params.add(new BasicNameValuePair("ArrayTables", "cargoList"));
//			// ���ò����ı���
//			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//			// �������󲢻�ȡ����
//			HttpResponse httpResponse = new DefaultHttpClient().execute(request);
//
//			// �������ص�����
//			if (httpResponse.getStatusLine().getStatusCode() == 200) {
//				resultString = EntityUtils.toString(httpResponse.getEntity());
//
//				// ����Xml�ļ�����Json�����
//				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
//				String resultJson = MethodUtil.parse(is);
//				Log.i("PublicWeb", "AuctionInfoWeb is ------------>>" + resultJson);
//
//				// �������
//				JSONObject jsonObject = new JSONObject(resultJson);
//				boolean resultTag = jsonObject.getBoolean("resultTag");
//				if (resultTag) {
////
////					JSONObject cargoList = jsonObject.getJSONObject("cargoList");//?
////					JSONArray jsonArray = cargoList.getJSONArray("data");//?
////					for (int i = 0; i < jsonArray.length(); i++) {
////						JSONObject objectItem = jsonArray.getJSONObject(i);
////						GsonBuilder gBuilder = new GsonBuilder();
////						Gson gson = gBuilder.create();
////						auctionInfo = gson.fromJson(String.valueOf(objectItem), AuctionInfo.class);
////						auctionInfoList.add(auctionInfo);
////					}
//					isUpdate = resultTag;
//				} else {
//					String exMsg = jsonObject.getString("msg");
//					AbAppException mAppException = new AbAppException(exMsg);
//					throw mAppException;
//				}
//			}
//		} catch (Exception e) {
//			AbAppException mAppException = new AbAppException(e);
//			throw mAppException;
//		}
//		return isUpdate;
//	}
	
	

	/**
	 * 
	 * @Describe����ȡ��Դ��Ϣ��������
	 * @param portName
	 * @param parameterList
	 * @return
	 * @throws AbAppException
	 * @Throws 
	 * @Date��2014-1-7 ����3:52:01
	 * @Version v1.0
	 */
//	public static CargoInfo cargoInfoDetail(String portName, List<Parameter> parameterList) throws AbAppException {
//		boolean isUpdate = false;
//		String resultString = null;
//		String interfaceName = "InvokeForMobile";
////		List<CargoInfo> cargoInfoList = new ArrayList<CargoInfo>();
//		CargoInfo cargoInfo = null;
//		try {
//			String xml = MethodUtil.getParameterString(portName, parameterList);
//			// ����������Դ��ַ����һ��Http����
//			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
//			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
//			// ��ӱ���Ĳ���
//			params.add(new BasicNameValuePair("Xml", xml));
//			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
//			params.add(new BasicNameValuePair("Token", "bbb"));
//			params.add(new BasicNameValuePair("ArrayTables", "cargoDetail"));
//			// ���ò����ı���
//			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//			// �������󲢻�ȡ����
//			HttpResponse httpResponse = new DefaultHttpClient().execute(request);
//
//			// �������ص�����
//			if (httpResponse.getStatusLine().getStatusCode() == 200) {
//				resultString = EntityUtils.toString(httpResponse.getEntity());
//
//				// ����Xml�ļ�����Json�����
//				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
//				String resultJson = MethodUtil.parse(is);
//				Log.d("UserInfoWeb", "UserInfoWeb_UserRegister is ------------>>" + resultJson);
//
//				// �������
//				JSONObject jsonObject = new JSONObject(resultJson);
//				boolean resultTag = jsonObject.getBoolean("resultTag");
//				if (resultTag) {
//
//					JSONObject cargoList = jsonObject.getJSONObject("cargoDetail");
//					JSONArray jsonArray = cargoList.getJSONArray("data");
//					for (int i = 0; i < jsonArray.length(); i++) {
//						JSONObject objectItem = jsonArray.getJSONObject(i);
//						GsonBuilder gBuilder = new GsonBuilder();
//						Gson gson = gBuilder.create();
//						cargoInfo = gson.fromJson(String.valueOf(objectItem), CargoInfo.class);
//					}
//
//					isUpdate = resultTag;
//				} else {
//					String exMsg = jsonObject.getString("msg");
//					AbAppException mAppException = new AbAppException(exMsg);
//					throw mAppException;
//				}
//			}
//		} catch (Exception e) {
//			AbAppException mAppException = new AbAppException(e);
//			throw mAppException;
//		}
//		return cargoInfo;
//	}
	

	/**
	 * 
	 * @Describe����ȡ��Դ��Ϣ��������
	 * @param portName
	 * @param parameterList
	 * @return
	 * @throws AbAppException
	 * @Throws 
	 * @Date��2014-1-7 ����4:58:16
	 * @Version v1.0
	 */
//	public static String vehicleInfoDetail(String portName, List<Parameter> parameterList) throws AbAppException {
//		boolean isUpdate = false;
//		String resultString = null;
//		String interfaceName = "InvokeForMobile";
//		List<AuctionInfo> auctionInfoList = new ArrayList<AuctionInfo>();
//		AuctionInfo auctionInfo = null;
//		try {
//			String xml = MethodUtil.getParameterString(portName, parameterList);
//			// ����������Դ��ַ����һ��Http����
//			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
//			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
//			// ��ӱ���Ĳ���
//			params.add(new BasicNameValuePair("Xml", xml));
//			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
//			params.add(new BasicNameValuePair("Token", "bbb"));
//			params.add(new BasicNameValuePair("ArrayTables", "cargoList"));
//			// ���ò����ı���
//			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//			// �������󲢻�ȡ����
//			HttpResponse httpResponse = new DefaultHttpClient().execute(request);
//
//			// �������ص�����
//			if (httpResponse.getStatusLine().getStatusCode() == 200) {
//				resultString = EntityUtils.toString(httpResponse.getEntity());
//
//				// ����Xml�ļ�����Json�����
//				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
//				String resultJson = MethodUtil.parse(is);
//				Log.i("PublicWeb", "AuctionInfoWeb is ------------>>" + resultJson);
//
//				// �������
//				JSONObject jsonObject = new JSONObject(resultJson);
//				boolean resultTag = jsonObject.getBoolean("resultTag");
//				if (resultTag) {
//
//					JSONObject cargoList = jsonObject.getJSONObject("cargoList");//?
//					JSONArray jsonArray = cargoList.getJSONArray("data");//?
//					for (int i = 0; i < jsonArray.length(); i++) {
//						JSONObject objectItem = jsonArray.getJSONObject(i);
//						GsonBuilder gBuilder = new GsonBuilder();
//						Gson gson = gBuilder.create();
//						auctionInfo = gson.fromJson(String.valueOf(objectItem), AuctionInfo.class);
//						auctionInfoList.add(auctionInfo);
//					}
//					isUpdate = resultTag;
//				} else {
//					String exMsg = jsonObject.getString("msg");
//					AbAppException mAppException = new AbAppException(exMsg);
//					throw mAppException;
//				}
//			}
//		} catch (Exception e) {
//			AbAppException mAppException = new AbAppException(e);
//			throw mAppException;
//		}
//		return resultString;
//	}
	
	
	
	/**
	 * 
	 * @Describe�������û�User_id��ȡ˾����������
	 * @param portName
	 * @param parameterList
	 * @return
	 * @throws AbAppException
	 * @Throws 
	 * @Date��2014-1-7 ����3:52:01
	 * @Version v1.0
	 */
//	public static DriverUserInfo DriverUserInfoDetail(String portName, List<Parameter> parameterList) throws AbAppException {
//		boolean isUpdate = false;
//		String resultString = null;
//		String interfaceName = "InvokeForMobile";
////		List<CargoInfo> cargoInfoList = new ArrayList<CargoInfo>();
//		DriverUserInfo driverUserInfo = null;
//		try {
//			String xml = MethodUtil.getParameterString(portName, parameterList);
//			// ����������Դ��ַ����һ��Http����
//			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
//			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
//			// ��ӱ���Ĳ���
//			params.add(new BasicNameValuePair("Xml", xml));
//			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
//			params.add(new BasicNameValuePair("Token", "bbb"));
//			params.add(new BasicNameValuePair("ArrayTables", "cargoDetail"));
//			// ���ò����ı���
//			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//			// �������󲢻�ȡ����
//			HttpResponse httpResponse = new DefaultHttpClient().execute(request);
//
//			// �������ص�����
//			if (httpResponse.getStatusLine().getStatusCode() == 200) {
//				resultString = EntityUtils.toString(httpResponse.getEntity());
//
//				// ����Xml�ļ�����Json�����
//				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
//				String resultJson = MethodUtil.parse(is);
//				Log.d("DriverUserInfo", "DriverUserInfo is ------------>>" + resultJson);
//
//				// �������
//				JSONObject jsonObject = new JSONObject(resultJson);
//				boolean resultTag = jsonObject.getBoolean("resultTag");
//				if (resultTag) {
//
//					JSONObject cargoList = jsonObject.getJSONObject("cargoDetail");
//					JSONArray jsonArray = cargoList.getJSONArray("data");
//					for (int i = 0; i < jsonArray.length(); i++) {
//						JSONObject objectItem = jsonArray.getJSONObject(i);
//						GsonBuilder gBuilder = new GsonBuilder();
//						Gson gson = gBuilder.create();
//						driverUserInfo = gson.fromJson(String.valueOf(objectItem), DriverUserInfo.class);
//					}
//
//					isUpdate = resultTag;
//				} else {
//					String exMsg = jsonObject.getString("msg");
//					AbAppException mAppException = new AbAppException(exMsg);
//					throw mAppException;
//				}
//			}
//		} catch (Exception e) {
//			AbAppException mAppException = new AbAppException(e);
//			throw mAppException;
//		}
//		return driverUserInfo;
//	}
	
	/**
	 * 
	 * @Describe�����ݳ���Car_Id��ȡ������������
	 * @param portName
	 * @param parameterList
	 * @return
	 * @throws AbAppException
	 * @Throws 
	 * @Date��2014-1-7 ����3:52:01
	 * @Version v1.0
	 */
//	public static DriverCarInfo DriverCarInfoDetail(String portName, List<Parameter> parameterList) throws AbAppException {
//		boolean isUpdate = false;
//		String resultString = null;
//		String interfaceName = "InvokeForMobile";
//		DriverCarInfo driverCarInfo = null;
//		try {
//			String xml = MethodUtil.getParameterString(portName, parameterList);
//			// ����������Դ��ַ����һ��Http����
//			HttpPost request = new HttpPost(Constant.HOSTURL + interfaceName);
//			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
//			// ��ӱ���Ĳ���
//			params.add(new BasicNameValuePair("Xml", xml));
//			params.add(new BasicNameValuePair("DeviceMark", "aaa"));
//			params.add(new BasicNameValuePair("Token", "bbb"));
//			params.add(new BasicNameValuePair("ArrayTables", "cargoDetail"));
//			// ���ò����ı���
//			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//			// �������󲢻�ȡ����
//			HttpResponse httpResponse = new DefaultHttpClient().execute(request);
//
//			// �������ص�����
//			if (httpResponse.getStatusLine().getStatusCode() == 200) {
//				resultString = EntityUtils.toString(httpResponse.getEntity());
//
//				// ����Xml�ļ�����Json�����
//				InputStream is = new ByteArrayInputStream(resultString.getBytes("UTF-8"));
//				String resultJson = MethodUtil.parse(is);
//				Log.d("DriverCarInfo", "DriverCarInfo is ------------>>" + resultJson);
//
//				// �������
//				JSONObject jsonObject = new JSONObject(resultJson);
//				boolean resultTag = jsonObject.getBoolean("resultTag");
//				if (resultTag) {
//
//					JSONObject cargoList = jsonObject.getJSONObject("cargoDetail");
//					JSONArray jsonArray = cargoList.getJSONArray("data");
//					for (int i = 0; i < jsonArray.length(); i++) {
//						JSONObject objectItem = jsonArray.getJSONObject(i);
//						GsonBuilder gBuilder = new GsonBuilder();
//						Gson gson = gBuilder.create();
//						driverCarInfo = gson.fromJson(String.valueOf(objectItem), DriverCarInfo.class);
//					}
//
//					isUpdate = resultTag;
//				} else {
//					String exMsg = jsonObject.getString("msg");
//					AbAppException mAppException = new AbAppException(exMsg);
//					throw mAppException;
//				}
//			}
//		} catch (Exception e) {
//			AbAppException mAppException = new AbAppException(e);
//			throw mAppException;
//		}
//		return driverCarInfo;
//	}
}
